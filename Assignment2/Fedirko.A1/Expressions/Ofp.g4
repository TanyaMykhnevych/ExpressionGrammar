/**
 * Define a grammar called Expressions
 */
grammar Ofp;

@header {    // Define output package.
    package Fedirko.A1;
}

/*
// Todo:
// 1. Single file, '.ofp' extension                                              +
// 2. One or more functions
// 3. Root of exec is 'void main()'                                              
// 4. No fields or global vars                                                   -
// 5. Functions must return value or be void                                     +
// 6. No overloading

// Statements

// 7. 'while' statement                                                           +
// 8. 'if' statement --> todo: bool expressions                                   +
// 9. Supported types: 
//    int,                                                                        +
//    float,                                                                      +
//    bool,                                                                       +
//    char,                                                                       +
//    string,                                                                     +
//    int[],                                                                      +
//    float[],                                                                    +
//    char[]                                                                      +

// 10. Variables visibility                                                       -
// 11. Integers regexp                                                            +
// 12. Floats regexp                                                              +
// 13. Strings are immutable                                                      -
// 14. Using str.length                                                           
// 15. String concat is not allowed                                               -
// 16. Comment	                                                                  +
// 17. Identifiers consist of a-zA-Z                                              +
// 18. Strings consist of a-z, A-Z, '!', '.', ',', ' ', '?', '=', ':', '(', ')'   +
// 19. Char same values as string                                                 +
// 20. Binary Operators + - * / < > ==                                            +
// 21. '==' operator can't be applied on strings                                  -
// 22. '-' can be applied only for numeric expr                                       
*/


/////////////////
// Parser spec //
/////////////////

start : funcDecl;

type : INTEGER
     | BOOLEAN
     | FLOAT
     | CHARACTER
     | STRING
     ;

arrType : INTEGER
		| FLOAT
		| CHARACTER
		;
		
typeOrArrType : type
              | arrType
              ;
              

literal : INTEGER_LITERAL
        | BOOLEAN_LITERAL
        | FLOAT_LITERAL
        | CHARACTER_LITERAL
        | STRING_LITERAL
        ;



// Variable declaration
varDecl : (type | arrType'[]') ID ('=' varInit)?;



// Variable initializer
varInit : expr
		| arrInit;


// Array initializer 'x = new int[]' or 'x = { 1, 2}'
arrInit : '{' (expr (',' expr)* (',')? )? '}'
        | NEW arrType'[' INTEGER_LITERAL ']'
        ;        


// Function declaration
funcDecl : (type | VOID | arrType '[]') ID '(' funcParams ')' codeBlock;
// Function params
funcParams : (funcParam ID (',' funcParam ID)*) | (funcParam ID)? ;
//Type or array
funcParam: (type | (arrType '[]' ));

// Function call
funcCall : ID '(' funcArgs ')';
// Function Args
funcArgs : expr (',' expr)*;


// Assign  ex: 'x = 213'
assgnStat : ID '=' expr;

// if(x > y){ }
ifStat : IF '(' expr ')' stat (ELSE stat)?;

//while (x > 1)
whileStat : WHILE '(' expr ')' stat;

// Return 
returnStat : RETURN expr? ';';

codeBlock : '{' blockStat* '}';

blockStat : varDecl ';'
          | stat
          ;

// Statement
stat : expr ';' 
     | assgnStat
     | codeBlock
     | ifStat
     | whileStat
     | ifStat
     | returnStat
     | ';'
     ;


// Expression
expr : '(' expr ')' 
     | expr (MULT | DIV) expr
     | expr (PLUS | MINUS) expr
     | expr (LT | GT) expr
     | expr EQ expr
     | assgnStat
     | literal
     | arrInit
     | expr '[' expr ']' // Array index
     | ID
     | <assoc=right> expr '=' expr
     | funcCall
     | expr DOT (ID | funcCall)
     ;

// Code block like
// .. {
//
// }


			


////////////////
// Lexer Spec //
////////////////


// Keywords
NEW    : 'new';

WHILE  : 'while';
IF     : 'if';
ELSE   : 'else';

RETURN : 'return';


DOT : '.';

// Types
INTEGER   : 'int';
BOOLEAN   : 'bool';
FLOAT     : 'float';
CHARACTER : 'char';
STRING    : 'string';
VOID      : 'void';


// Literals

INTEGER_LITERAL : ('0'..'9') | (('1'..'9')('0'..'9')+);

BOOLEAN_LITERAL : 'true'
                | 'false'
                ;

FLOAT_LITERAL : INTEGER_LITERAL 
              | (INTEGER_LITERAL '.' Digits? | '0'.Digits) [fFdD]?
              ;
              
CHARACTER_LITERAL : '\'' ([a-zA-Z!., ?=:()]) '\'';


//STRING_LITERAL : '"' CHARACTER_LITERAL+ '"'; //for some reason dont work, todo: investigate
STRING_LITERAL : '"' ([a-zA-Z!., ?=:()])+ '"';



// Operators
	
ASSGN : '=';
PLUS : '+' ;
MINUS : '-' ;
MULT : '*' ;
DIV : '/' ;
LT : '<' ;
GT : '>' ;
EQ : '==';


// Identifiers, variable names
ID  : [a-zA-Z]+;

// Comment
CMNT : '#' ~[\r\n]* -> skip;

// Etc
WS : [ \t\r\n]+ -> skip ;

// File extension
r : 'ofp' ID;


// Fragments:

fragment Digits : [0-9] ([0-9_]* [0-9])?;

 