/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar SOQL;


query:  SELECT fields FROM obj (WHERE conditions)? (ORDER BY fields)?
     ;

fields: field (COMMA field)*
    ;

field: ID (DOT ID)*;

obj:
   ID;

conditions:
       condition (boolExp condition)*
         ;
boolExp: OR|AND (NOT)?;
        
condition:
       simpleCondition
         | LPAR simpleCondition RPAR;

simpleCondition:
       field (OPERATOR value| IN LPAR value (COMMA value)* RPAR);
    
value:
         field
     |  literal
     ;

literal:
           NUMERIC
       | STRING
       | BOOLEAN
       ;

SELECT: [sS][eE][lL][eE][cC][tT];
IN:[iI][nN];
COMMA: ',';
DOT: '.';

AND: [aA][nN][dD];
OR:[oO][rR];
NOT:[nN][oO][tT];

FROM: [fF][rR][oO][mM];

WHERE: [wW][hH][eE][rR][eE];

ORDER: [oO][rR][dD][eE][rR];
BY: [bB][yY];

ID: ('a'..'z'|'A'..'Z'|'_')+;
    
STRING: '\''(~('\\'|'\''))* '\'';

LPAR: '(';
RPAR: ')';


BOOLEAN: ([tT][rR][uU][eE] | [fF][aA][lL][sS][eE]);

NUMERIC:    ('1'..'9') ('0'..'9')* ('.' ('0'..'9')*)?;

OPERATOR: ('='|'<'|'<='|'>'|'>=');

WS  :       (' '|'\t'|'\r'|'\n')+ -> skip ;
