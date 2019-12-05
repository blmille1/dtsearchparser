@echo off
echo Uploading config directory to local solr cloud for the MyTestWebsite collection
call ..\Solr\solr\server\scripts\cloud-scripts\zkcli.bat -zkhost localhost:9983 -cmd upconfig -confname MyTestWebsite -confdir ..\Configs\MyTestWebsite

"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" "http://localhost:8983/solr/#/~collections/MyTestWebsite"
echo Reload the MyTestWebsite Collection