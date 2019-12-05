@echo off

echo Indexing batch %1
java -Durl=http://search1:9000/solr/MyTestWebsite/update -Dauto=yes -Drecursive=yes -jar ..\Solr\solr\example\exampledocs\post.jar F:\SolrImport\xml\sql2\%1\*.xml