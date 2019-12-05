cls
ECHO ""
#Adding SQL cmdlets if needed
    if ( (Get-PSSnapin -Name SqlServerCmdletSnapin100 -ErrorAction SilentlyContinue) -eq $null )
    {
        Add-PSSnapin SqlServerCmdletSnapin100 
    }
    if ( (Get-PSSnapin -Name SqlServerProviderSnapin100 -ErrorAction SilentlyContinue) -eq $null )
    {
        Add-PSSnapin SqlServerProviderSnapin100
    }

#Find current directory path
$scriptDir = $PSScriptRoot 

function Get-ExportArticles {
  <#
  .SYNOPSIS
  Grab articles to be exported
  .DESCRIPTION
  Grab articles to be exported
  .EXAMPLE
  Give an example of how to use it
  .EXAMPLE
  Give another example of how to use it
  .PARAMETER computername
  The computer name to query. Just one.
  .PARAMETER logname
  The name of a file to write failed computer names to. Defaults to errors.txt.
  #>
  [CmdletBinding()]
  param
  (
    [Parameter(Mandatory=$True,
    ValueFromPipeline=$True,
    ValueFromPipelineByPropertyName=$True,
      HelpMessage='What SQL server name would you like to target?')]
    [Alias('host')]
    [string]$server,
	
    [long]$last_id = 0,
    [int]$max_count = 100
  )

  begin {

  }

  process {
    $sw = [system.diagnostics.stopwatch]::startNew();
    

    $database = "legal_advantage_dev"
    #Export to CSV
    #$query = "Select $columns FROM $table"
    Write-Host -NoNewline "Executing query: "
    $query = "select top $max_count
	    cd.ContentId as id,
	    c.ProviderId as providerid,
	    c.SourceId as sourceid,
	    c.GroupId as groupid,
	    cd.Headline as headline,
	    cd.Description as contenttext
    from tblContentData cd (nolock) inner join tblContent c with(nolock) on cd.ContentId = c.ContentId
    where cd.ContentId > $last_id
    and c.SourceId is not null and c.ProviderId is not null and c.GroupId is not null
    "
    $results = Invoke-Sqlcmd -Query $query -ServerInstance $server -Database $database;


    $sw.Stop();
    $elapsed = $sw.Elapsed.ToString();
    $total_exported = $results.Count;
    $rate =  $total_exported / ($sw.ElapsedMilliseconds / 1000.0);
    Write-Host "$total_exported ($max_count requested) in $elapsed @ $rate records per second"

    return $results;
  }
}

function Export-ArticleBatch {
  <#
  .SYNOPSIS
  Export a batch of articles
  .DESCRIPTION
  Export a batch of articles
  .EXAMPLE
  Give an example of how to use it
  .EXAMPLE
  Give another example of how to use it
  .PARAMETER computername
  The computer name to query. Just one.
  #>
  [CmdletBinding()]
  param
  (
    [Parameter(Mandatory=$True,
    ValueFromPipeline=$True,
    ValueFromPipelineByPropertyName=$True,
      HelpMessage='What SQL server name would you like to target?')]
    [Alias('host')]
    [string]$server,
    [int]$export_max = 1000,
    [int]$batch_limit = 1000,
    [int]$max_file_limit = 100,
    [long]$last_id = 0,
    [string]$output_path = '.\'
  )

  begin {

  }

  process {
    $total_iterations = $export_max / $batch_limit;
    $total_exported = 0;

    for($i = 0; $i -lt $total_iterations; $i++){
        $results = Get-ExportArticles -server $server -max_count $batch_limit -last_id $last_id;
        if($results.Count -eq 0){
            return $total_exported;
        }

        $total_exported += $results.Count;

        ChunkSave-ArticleBatch -data $results -filename_base "${server}_articles_batch_${i}" -batch_number $i -chunk_size $max_file_limit -output_path $output_path
        $last_id = $results[$results.Count - 1].id;

        $percent_complete = $total_exported / (.01 * $export_max)

        Write-Host "Approx. $percent_complete% complete ($total_exported out of $export_max requested)"
    }
    return $total_exported;
  }
}

function Save-ArticleBatch {
  <#
  .SYNOPSIS
  Export a batch of articles
  .DESCRIPTION
  Export a batch of articles
  .EXAMPLE
  Give an example of how to use it
  .EXAMPLE
  Give another example of how to use it
  .PARAMETER computername
  The computer name to query. Just one.
  #>
  [CmdletBinding()]
  param
  (
    [Parameter(Mandatory=$True,
    ValueFromPipeline=$True,
    ValueFromPipelineByPropertyName=$True,
      HelpMessage='Data to export to file')]
    [System.Data.DataRow[]]$data,
    [string]$filename_base,
    [string]$output_path='',
    [int]$batch_number = 0,
    [int]$chunk = 0 # file index for this batch
  )

  begin {

  }

  process {
    $count = $data.Count;
    if($count -eq 0) {
        return;
    }
    $id_first = $data[0].id;
    $id_last = $data[$data.Count -1].id;

    $columns = @('id','providerid','sourceid', 'groupid','headline','contenttext');

    $filename = "${filename_base}_${id_first}_${id_last}_${count}.xml";
    if($output_path.Length -gt 0) {
        if(-not ($output_path.EndsWith("\") -or $output_path.EndsWith("/"))) {
            $output_path += "\";
        }
        $filename = $output_path + $filename;
    }
    
    $i = 0;
    $file_contents_start = "<add>";
    $file_contents_end = "</add>";
    $file_contents_doc_start = "  <doc>";
    $file_contents_doc_end = "  </doc>";
    $sb = New-Object System.Text.StringBuilder;
    # start the file
    $file_contents_start | Set-Content $filename -Encoding Unicode

    foreach($row in $data) {
        $i++;
        $ignore = $sb.AppendLine($file_contents_doc_start);
        foreach($col in $columns){
            $ignore = $sb.Append('    <field name="' + $col +'">');
            $data_str = $row[$col].ToString().Trim().Replace("`r`n",'');
            $data_str_escaped = [Security.SecurityElement]::Escape($data_str);
            $ignore = $sb.Append($data_str_escaped);
            $ignore = $sb.AppendLine("</field>");
        }
        $ignore = $sb.AppendLine($file_contents_doc_end);
        $ignore = $sb.AppendLine();
    }

    $ignore = $sb.AppendLine($file_contents_end);

    $sb.ToString() | Add-Content $filename -Encoding Unicode

    $full_filepath = Convert-Path "$filename"
    Write-Host "File: $full_filepath`t$i items saved."
  }
}

function ChunkSave-ArticleBatch {
  <#
  .SYNOPSIS
  Export a batch of articles
  .DESCRIPTION
  Export a batch of articles
  .EXAMPLE
  Give an example of how to use it
  .EXAMPLE
  Give another example of how to use it
  .PARAMETER computername
  The computer name to query. Just one.
  #>
  [CmdletBinding()]
  param
  (
    [Parameter(Mandatory=$True,
    ValueFromPipeline=$True,
    ValueFromPipelineByPropertyName=$True,
      HelpMessage='Data to export to file')]
    [System.Data.DataRow[]]$data,
    [string]$filename_base,
    [string]$output_path,
    [int]$batch_number = 0,
    [int]$chunk_size = 100 # Number of articles to store per file
  )

  begin {

  }

  process {
    $count = $data.Count;
    if($count -eq 0) {
        return;
    }

    $num_chunks = $count / $chunk_size;

    $sw = [system.diagnostics.stopwatch]::startNew();

    for($i=0;$i -lt $num_chunks; $i++) {
        $chunk_min_index = $i * $chunk_size;
        $chunk_max_index = $chunk_min_index + $chunk_size - 1;
        Save-ArticleBatch -data $data[$chunk_min_index..$chunk_max_index] -filename_base $filename_base -batch_number $batch_number -chunk $i -output_path $output_path
    }

    $sw.Stop();
    $elapsed = $sw.Elapsed.ToString();
    $rate =  $count / ($sw.ElapsedMilliseconds / 1000.0);
    #Write-Host "Chunked $count in $elapsed @ $rate articles per second`r`n"
    Write-Host
  }
}

function Export-Articles {
  <#
  .SYNOPSIS
  Export a articles to something Solr can understand
  .DESCRIPTION
  Export a batch of articles
  .EXAMPLE
  Give an example of how to use it
  .EXAMPLE
  Give another example of how to use it
  .PARAMETER computername
  The computer name to query. Just one.
  #>
  [CmdletBinding()]
  param
  (
    [Parameter(HelpMessage='SQL Server host name')]
    [string]$server = 'SQL2',
    [int]$max_articles = 100000,
    [int]$batch_size = 100000,
    [long]$start_id = 0,
    [int]$max_file_limit = 100,
    [string]$output_path = '.\'
  )

  begin {

  }

  process {
    Write-Host "SQL Server: $server`tRequest $max_articles articles`tBatch Size: $batch_size`tStart ContentId: $start_id`tMax # of articles per file: $max_file_limit"
    
    $sw = [system.diagnostics.stopwatch]::startNew();

    $total_exported = Export-ArticleBatch -server $server -export_max $max_articles -batch_limit $batch_size -last_id $start_id -max_file_limit $max_file_limit -output_path $output_path
    $sw.Stop();
    $elapsed = $sw.Elapsed.ToString();
    $rate =  $total_exported / ($sw.ElapsedMilliseconds / 1000.0);
    Write-Host "Finished exporting $total_exported ($max_articles requested) in batches of $batch_limit in $elapsed @ $rate articles per second."
    return $total_exported;
  }
}


Write-Host "`$total = Export-Articles -server 'SQL2' -max_articles 1000000 -batch_size 100000 -start_id 0 -max_file_limit 500"
