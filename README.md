# dtsearchparser
Open-Source dtSearch Parser and Solr/Lucene Compiler

# Background
I wanted the flexibility of the dtSearch language in Apache Solr/Lucene, so I wrote this implementation.

# Please excuse the mess
I apologize for the mess as this is my attempt to preserve the code.  It's been collecting dust on my computer and I wanted to make sure and preserve it.  Many of you have asked for the code and I was hesitant to post it because I knew it would take some time to get it working with the latest version of Solr and I didn't know a good way of separating it out from the rest of the mess (most of which you can see here on GitHub).
This is basically a snapshot of my working directory, minus the Solr and Lib directories.  I was afraid to include the Lib folder because I'm not sure if that's Kosher to do, but essentially the only things in there are:
* Antlr4
* Apache/ant-1.9.7

I didn't include the Solr directory for obvious reasons--It's already open sourced and it's huge.  You'd hate me if I included it.

Now that the code is preserved, I will try to manicure this code base into what I've wanted it to be all along.
If you're interested in seeing this mature, please communicate that with me and I'm sure I'll be more than happy to nudge it along.
Also, don't be shy of suggesting updates of your own.

# Where's the code I care about?
The source for the parser is in Plugins/.
It was designed to work with Solr 6.1.0.  See Versions.txt for more information.

# Setup
Use this as a guide if you get lost below: http://coding-art.blogspot.com/2016/05/compiling-and-running-apache-solr-600.html

Install:
* JDK (64-bit)
* Eclipse (64-bit)

Setup:
1. Clone this repo.
2. Set up Environment Variables (see below)
	(NOTE: My TFS Workspace is located at D:\Millersoft\TFS, so adjust accordingly)
	CLASSPATH: .;C:\Antlr4\antlr-4.5.3-complete.jar;C:\Antlr4
	PATH: C:\Antlr4;C:\ant-1.9.7\bin;C:\Program Files\Java\jdk-13.0.1\bin\
3. You will need to reference the Solr 6.1.0 source in order to build the parser.  I'm not exactly sure how you do that at this time, but I was able to access an archived version of solr here: http://archive.apache.org/dist/lucene/solr/6.1.0/
3. Build

## Test Solr Run
Change your directory to the Solr source directory.

`cd solr`

`ant server`

`bin\solr.cmd start -e techproducts -noprompt`

Open http://localhost:8983/solr in a web browser and kick the tires.

`bin\solr.cmd stop -all`

## Install the dtSearch Solr Plugins
Use for reference: http://coding-art.blogspot.com/2016/05/writing-custom-solr-query-parser-for.html

All you should need to do is export the JAR for the MillersoftSolrPlugins, copy the JAR to the right folders, and edit the solrconfig.xml, then start solr.

Put JAR file here (if you're using the techproducts example): `Solr\solr\example\techproducts\solr\lib\solrconfig.xml`: Edit the one located here: `Solr\solr\example\techproducts\solr\techproducts\conf\solrconfig.xml`.

For the solrconfig.xml:
- To register the dtSearch parser:
	`<queryParser name="dtsearch" class="com.millersoft.solr.parsers.dtsearch.DtSearchQParserPlugin"/>`
- To make the default search parser for `/select`
	- Find `<requestHandler name="/select"`...
	- Locate the `<lst name="defaults"`...
	- Add the following:
		`<str name="defType">dtsearch</str>`

NOTE: if you want to install for SolrCore, follow the instructions at the link above. :D

FYI:
* Antlr4 compiles .g4 files into some target language, Java by default, which is what we are compiling into now for use in Solr.
		We have a DtSearch.g4 file located in the MillersoftSolrPlugins project.  If you don't need to compile the .g4 file, don't worry so much about this.
* Apache Ant is used to compile the solr project using the command line, which you will likely need to do.
