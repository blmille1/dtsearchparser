grammar DtSearch;

@header {
package com.millersoft.solr.parsers.dtsearch;
}

query : expr;

expr:
        NOT expr                            # not_expr
    |   left=expr NOT? W slop=number right=expr     # w_expr
    |   left=expr NOT? PRE slop=number right=expr   # pre_expr
    |	regexp (COLON termWeight=floatingPoint)?	# regexp_expr
    |   phrase (COLON termWeight=floatingPoint)?    # phrase_expr
    |   FIELD_NAME CONTAINS expr    # field_contains_expr
    |   left=expr AND right=expr    # and_expr
    |   left=expr OR right=expr     # or_expr
    |   '(' expr ')'                # sub_expr
;

regexp: REGEXP ;
phrase: (INT | STRING_LITERAL | FIELD_NAME | SEARCH_STRING)+ ; // can be either quoted or unquoted
//field_contains: FIELD_NAME CONTAINS phrase;
number: INT;
floatingPoint: INT ('.' INT)?;


// TOKENS
INT: DIGIT+;

AND: [aA][nN][dD];
OR: [oO][rR];
NOT: [nN][oO][tT];
CONTAINS: [cC][oO][nN][tT][aA][iI][nN][sS] | '::';

W: [wW] '/' ;
PRE: [pP][rR][eE] '/' ;
COLON: ':';

REGEXP: ('"' | '“') '##' ~('"' | '”')+ ('"' | '”');

FIELD_NAME: ALPHABET (ALPHABET | DIGIT | [_] )*; // TODO: this is a guess--deguessify this

SEARCH_STRING: SEARCH_STRING_WORD+;
fragment SEARCH_STRING_WORD: (ALPHABET | DIGIT | WILDCARD | SEARCH_STRING_ALLOWED_SPECIAL_CHARACTERS)+;
fragment SEARCH_STRING_ALLOWED_SPECIAL_CHARACTERS: [&\-]; // Add to the list as we find them.
STRING_LITERAL:  ('"' | '“') ~('"' | '”')+ ('"' | '”');

fragment ALPHABET: [a-zA-Z];
fragment DIGIT: [0-9];

WS: [ \t\r\n]+ -> skip;

fragment WILDCARD: [?*~%#&];