@echo off

rem echo Running Antlr4 tool...
rem call antlr4 DtSearch.g4 -visitor
rem if errorlevel 1 goto failed

echo Did you run?: antlr4 DtSearch.g4 -visitor
echo Did you have Eclipse build?
 
rem echo Compiling Java...
rem javac DtSearchBaseListener.java DtSearchBaseVisitor.java DtSearchLexer.java DtSearchListener.java DtSearchParser.java DtSearchVisitor.java
rem if errorlevel 1 goto failed

pushd ..\..\..\..\..\..\bin

echo Running test input4.txt and generating its parse tree
call grun com.millersoft.solr.parsers.dtsearch.DtSearch query -gui ..\src\com\millersoft\solr\parsers\dtsearch\input4.txt -tokens -trace -diagnostics
popd

:failed

:success