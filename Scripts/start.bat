@echo off

rem START ZOOKEEPER
rem call start_zk.bat


pushd ..\Solr\solr

rem IF EXIST "example\techproducts\solr\techproducts\conf\solrconfig.xml" (
rem 	echo Starting an existing Tech Products example
rem 	call bin\solr.cmd start -s "example\techproducts\solr" -m 6g
rem ) ELSE (
rem 	echo Initializing a new Tech Products example
rem 	call bin\solr.cmd start -e techproducts -noprompt -m 6g
rem )

echo Make sure that you name the collection MyTestWebsite
rem call ..\Solr\solr\bin\solr.cmd start -e cloud -z localhost:2181,localhost:2182,localhost:2183 -m 3g
rem call ..\Solr\solr\bin\solr.cmd start -e cloud -z localhost:2181 -m 3g

call bin\solr.cmd start -h bm1 -c -p 8983 -z "localhost:2181" -m 3g -s F:\SolrCores

popd
TIMEOUT 3
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" "http://localhost:8983/solr/#/MyTestWebsite/query"