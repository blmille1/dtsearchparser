Use this as a guide if you get lost below: http://coding-art.blogspot.com/2016/05/compiling-and-running-apache-solr-600.html

Install:
	JDK (64-bit)
	Eclipse (64-bit)
	Install Eclipse Plugin for TFS: https://msdn.microsoft.com/en-us/library/hh301122(v=vs.120).aspx

Setup:
	Get Latest on $/Projects/SolrSystem
	Set up Environment Variables (see below)
		(NOTE: My TFS Workspace is located at D:\Millersoft\TFS, so adjust accordingly)
		CLASSPATH: .;D:\Millersoft\TFS\Projects\SolrSystem\Trunk\Source\Lib\Antlr4\antlr-4.5.3-complete.jar;D:\Millersoft\TFS\Projects\SolrSystem\Trunk\Source\Lib\Antlr4
		PATH: D:\Millersoft\TFS\Projects\SolrSystem\Trunk\Source\Lib\Antlr4;D:\Millersoft\TFS\Projects\SolrSystem\Trunk\Source\Lib\Apache\ant-1.9.7\bin;C:\Program Files\Java\jdk1.8.0_91\bin
	Eclipse:
		Launch eclipse
		You don't care about your working directory because you're not going to be using it
		File > Import...
		Team (you have this because you installed the Eclipse Plugin for TFS) > Projects from Team Foundation Server
		Next (you may need to specify http://tfs1:8080/tfs)
		Projects Selection:
			tfs1\DefaultCollection\Projects\SolrSystem\Trunk\Source
			Expand Plugins directory
			In total, you will want to select all three of these projects: MillersoftSolrPlugins, MillersoftSolrPluginsTest, Solr (the sibling folder to Plugins)
			Finish
		From here, eclipse will start building everything.
		In Project Explorer, right-click MillersoftSolrPluginsTests > Run As > JUnit Test
		If all is well, it will run all of the tests, which should all pass

Test Solr Run:
	cd Solr (this is the solr source directory.  See Solr Version.txt in this directory to know which version it is)
	cd solr
	ant server
	bin\solr.cmd start -e techproducts -noprompt
	open http://localhost:8983/solr in a web browser
	kick the tires
	bin\solr.cmd stop -all

Install the dtSearch, etc. Solr Plugins:
	Use for reference: http://coding-art.blogspot.com/2016/05/writing-custom-solr-query-parser-for.html
	All you should need to do is export the JAR for the MillersoftSolrPlugins, copy the JAR to the right folders,
	and edit the solrconfig.xml, then start solr.
	Put JAR file here (if you're using the techproducts example): Solr\solr\example\techproducts\solr\lib
	solrconfig.xml: Edit the one located here: Solr\solr\example\techproducts\solr\techproducts\conf\solrconfig.xml
	For the solrconfig.xml:
		To register the dtSearch parser:
			<queryParser name="dtsearch" class="com.millersoft.solr.parsers.dtsearch.DtSearchQParserPlugin"/>
		To make the default search parser for /select
			Find <requestHandler name="/select"...
			Locate the <lst name="defaults"...
			Add the following:
				<str name="defType">dtsearch</str>
	If you want the default query parser to be dtSearch:
		in the same solrconfig.xml

	NOTE: if you want to install for SolrCore, follow the instructions at the link above. :D

FYI:
	Antlr4
		Antlr4 compiles .g4 files into some target language, Java by default, which is what we are compiling into now for use in Solr.
		We have a DtSearch.g4 file located in the MillersoftSolrPlugins project.  If you don't need to compile the .g4 file, don't worry so much about this
	Apache Ant
		This is used to compile the solr project using the command line, which you will likely need to do.

WHEN YOU WANT TO UPDATE THE VERSION OF SOLR WE'RE USING
	Download the source for the new version
	cd Solr
	cd solr
	ant server
	Make sure everything builds fine
	Once you are confident it's good, delete the source folder and extract it again (we don't want to check in a bunch of compiled stuff)
	Do not proceed until you've ran all of our JUnit tests in MillersoftSolr*Tests projects and they all pass
	Delete the source to the old solr
	Extract the source tgz again
	cd Solr
	ant eclipse
	check that in


    