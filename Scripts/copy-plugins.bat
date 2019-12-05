@echo off

IF EXIST "S:\BM\lucene\lib" (
	echo copying to nodes 1-4
	call xcopy /E /I S:\BM\lucene\lib ..\Solr\solr\example\cloud\node1\solr\lib\
	call xcopy /E /I S:\BM\lucene\lib ..\Solr\solr\example\cloud\node2\solr\lib\
	call xcopy /E /I S:\BM\lucene\lib ..\Solr\solr\example\cloud\node3\solr\lib\
	call xcopy /E /I S:\BM\lucene\lib ..\Solr\solr\example\cloud\node4\solr\lib\
) ELSE (
	echo Unable to find source LIB directory: S:\BM\lucene\lib
)
