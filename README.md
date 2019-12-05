# dtsearchparser
Open-Source dtSearch Parser and Solr/Lucene Compiler

# Background
I wanted the flexibility of the dtSearch language in Apache Solr/Lucene, so I wrote this implementation.

# Please excuse the mess
I apologize for the mess as this is my attempt to preserve the code.  It's been collecting dust on my computer and I wanted to make sure and preserve it.  Many of you have asked for the code and I was hesitant to post it because I knew it would take some time to get it working with the latest version of Solr and I didn't know a good way of separating it out from the rest of the mess (most of which you can see here on GitHub).
This is basically a snapshot of my working directory, minus the Solr and Lib directories.  I was afraid to include the Lib folder because I'm not sure if that's Kosher to do, but essentially the only things in there are:
o Antlr4
o Apache/ant-1.9.7
I didn't include the Solr directory for obvious reasons--It's already open sourced and it's huge.  You'd hate me if I included it.

Now that the code is preserved, I will try to manicure this code base into what I've wanted it to be all along.
If you're interested in seeing this mature, please communicate that with me and I'm sure I'll be more than happy to nudge it along.
Also, don't be shy of suggesting updates of your own.

# Where's the code I care about?
The source for the parser is in Plugins/.
