grammar SOQL;

query		: SELECT terms FROM obj (WHERE conditions)? (GROUP BY fields)? (HAVING conditions)? (ORDER BY ordering)? (LIMIT nRows)?
     		;

terms		: term (COMMA term)*
    		;

fields		: field (COMMA field)*
			;

ordering	: orderingTerm (COMMA orderingTerm)
			;

orderingTerm: field (ASC | DESC)
			;

term		: fieldTerm | subquery
			;

fieldTerm	: field | funcRef (field)?
			;
	
funcRef		: ID LPAR term RPAR 
			;
	
field		: ID (DOT ID)*
			;

subquery	: LPAR query RPAR
			;

obj			: ID
			;

conditions	: condition (boolExp condition)*
         	;
         	
boolExp		: OR|AND (NOT)?
			;
        
condition	: simpleCondition
     		| LPAR simpleCondition RPAR
     		;

simpleCondition
			: field (OPERATOR value| IN LPAR value (COMMA value)* RPAR)
			;
    
value		: field
 			| literal
 			;

literal		: NUMERIC
       		| STRING
       		| BOOLEAN
   			;

nRows		: INTEGER
			;

SELECT		: [sS][eE][lL][eE][cC][tT];
IN			: [iI][nN];
COMMA		: ',';
DOT			: '.';

AND			: [aA][nN][dD];
OR			: [oO][rR];
NOT			: [nN][oO][tT];

FROM		: [fF][rR][oO][mM];

WHERE		: [wW][hH][eE][rR][eE];

ORDER		: [oO][rR][dD][eE][rR];
BY			: [bB][yY];

HAVING		: [hH][aA][vV][iI][nN][gG];

GROUP		: [gG][rR][oO][uU][pP];
ASC			: [aA][sS][cC];
DESC		: [dD][eE][sS][cC];
LIMIT		: [lL][iI][mM][iI][tT];

ID			: ('a'..'z'|'A'..'Z'|'_')+;
ALIAS		: ('a'..'z'|'A'..'Z'|'_')+;
    
STRING		: '\''(~('\\'|'\''))* '\'';

LPAR		: '(';
RPAR		: ')';


BOOLEAN		: ([tT][rR][uU][eE] | [fF][aA][lL][sS][eE]);

NUMERIC		: ('1'..'9') ('0'..'9')* ('.' ('0'..'9')*)?;
INTEGER		: ('1'..'9') ('0'..'9')*;

OPERATOR	: ('='|'<'|'<='|'>'|'>=');

WS  		: (' '|'\t'|'\r'|'\n')+ -> skip ;
