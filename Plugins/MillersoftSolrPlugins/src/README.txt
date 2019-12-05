I used this as a guide to figure out what we should support to support every feature, which we may not need to do:
http://support.dtsearch.com/dtSearch_Desktop.pdf#xml=http://support.dtsearch.com/Support/dtSearch/dtisapi6.dll?cmd=getpdfhits&u=583a40&DocId=131&Index=c%3a%5cinetpub%5csupport%5cdemos%5cindexes%5cfaqs&HitCount=4&hits=3175+3178+3194+319b+&SearchForm=%2fsearch%5fform%2ehtml&.pdf

This encapsulates all known unimplemented tests that should eventually work.
They are here to guide us as to what we should support in the future.
Here's a full list of features that must be supported:
Search Request Meaning
PHRASES
  [x] "a* *b c*"			Wildcard matching for consecutive words
  [x] a* *b c*				Wildcard matching for unquoted consecutive words
  [x] a b c					consecutive words without quotes
  
REGULAR EXPRESSIONS
  [x]  "##[a-b]{3}"			Regular expression support

BOOLEAN
  [x] a and b 				both words must be present
  [x] a and not b 			only a must be present
  [x] a or b				either word can be present
  [x] a or not b			either a or anything that's not b
  [x] a* or *b				Pre and post wildcard matching
  
  
PROXIMITY (pre/ and w/ should both be supported to have an [x])
  [x] a w/5 b 				a must occur within 5 words of b
  [x] a not w/12 b 			a must occur, but not within 12 words of b
  [x] not (a w/5 b)   		must not have a within 5 of b
  [x] a w/5 xfirstword 		a must occur in the first five words
  [ ] a w/5 xlastword 		a must occur in the last five words
  [x] (a pre/3 b) w/7 c		nested proximity
  [x] "a b" w/5 b			quoted literals in proximity
  [x] a w/5 b*				Wildcard support in proximity
  [x] a w/5 not b			not within proximity
  [x] (a or b) w/5 c		Proximity OR
  [x] (a and b) w/5 c		Proximity AND
  
FIELD	
  [x] name contains smith 	the field name must contain smith
  [x] name::smith			alternate field contains syntax

TERM
  [ ] a:5 and b:1			weigh a 5-times as much as b
   ^--- Works for non-proximity, haven't tested with nested queries, doesn't appear to work correctly with proximity
   
Special Characters
  [x] *		Matches any number of characters, including none
  [x] ?		Matches any single character
  [ ] =		Matches any single digit
  [ ] %		Fuzzy Search
  [ ] #		Phonic Search
  [ ] ~		Stemming
  [ ] &		Synonym Search
  [ ] ~~	Numeric Range
  [x] ##	Regular Expression
  [ ] :		Term Weighting
  [ ] @     Macro Expansion
  
Misc
  [ ] XML field name		XML path support for XML documents
  [ ] Segment Searches: (beginning to end) contains (something)
  
  
  
 COMPILING DtSearch.g4
   Open a command prompt to .g4 folder
   antlr4 DtSearch.g4 -visitor