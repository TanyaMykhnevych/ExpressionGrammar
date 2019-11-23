/**
 * Define a grammar called ExpressionGrammar
 */
grammar ExpressionGrammar;

@header {    // Define output package.
    package mykhnevych.a1;
}


start : methodDeclaration;

// ------------------------- PARSER -------------------------
primitiveType
    : BOOLEAN
    | CHAR
    | STRING
    | INT
    | FLOAT
    ;

arrayType
    : CHAR
    | INT
    | FLOAT
    ;

literal
    : FLOAT_LITERAL
    | INTEGER_LITERAL
    | CHAR_LITERAL
    | STRING_LITERAL
    | BOOL_LITERAL
    | NULL_LITERAL
    ;

methodDeclaration
    : returnValue IDENTIFIER params ('[' ']')*
      methodBody
    ;

methodBody
    : block
    | ';'
    ;

params
    : '(' paramsList? ')'
    ;

paramsList
    : param (',' param)*
    ;

param
    : type IDENTIFIER
    ;
    
variableDeclarator
    : IDENTIFIER ('=' variableInitializer)?
    ;

returnValue
    : type
    | VOID
    ;

type
    : primitiveType ('[' ']')*
    ;

variableInitializer
    : arrayInitializer
    | expression
    ;
    
arrayInitializer
    : '{' (variableInitializer (',' variableInitializer)* (',')? )? '}'
    ;
    
creator: createdName arrayCreator
    ;

createdName
    : IDENTIFIER
    | arrayType
    ;

arrayCreator
    : '[' (']' ('[' ']')* arrayInitializer | expression ']' ('[' expression ']')* ('[' ']')*)
    ;
   
 block
    : '{' blockStatement* '}'
    ;

blockStatement
    : variableDeclaration ';'
    | statement
    ;

variableDeclaration
    : type variableDeclarators
    ;
    
variableDeclarators
    : variableDeclarator (',' variableDeclarator)*
    ;

methodCall
    : IDENTIFIER '(' expressionList? ')'
    ;

arguments
    : '(' expressionList? ')'
    ;

expressionList
    : expression (',' expression)*
    ;

conditionExpression
    : '(' expression ')'
    ;

statement
    : block
    | IF conditionExpression statement (ELSE statement)?
    | WHILE conditionExpression statement
    | RETURN expression? ';'
    | SEMI
    | expression ';'
    | IDENTIFIER ':' statement
    ;

expression
    : '(' expression ')'
    | literal
    | IDENTIFIER
    | expression DOT
      ( IDENTIFIER
      | methodCall     
      )
    | expression '[' expression ']'
    | methodCall
    | NEW creator
    | '(' type ')' expression
    | (SUB) expression
    | expression (MUL | DIV) expression
    | expression (ADD | SUB) expression
    | expression (GT | LT) expression
    | expression (EQUAL) expression
    | <assoc=right> expression 
      ASSIGN
      expression
    ;


// ------------------------- LEXER -------------------------
// TYPES
BOOLEAN: 'bool';
CHAR: 'char';
FLOAT: 'float';
INT: 'int';
STRING: 'string';
VOID: 'void';

// CONTROL
ELSE: 'else';
IF: 'if';
RETURN: 'return';
WHILE: 'while';

NEW: 'new';

// SEPARATORS
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';
SEMI: ';';
COMMA: ',';
DOT: '.';

// OPERATIONS
ASSIGN: '=';
GT: '>';
LT: '<';
EQUAL: '==';
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';

// LITERALS
NULL_LITERAL: 'null';
INTEGER_LITERAL: ('0' | [1-9] (Digits?)) [lL]?;
FLOAT_LITERAL: (Digits '.' Digits) [fFdD]?;
BOOL_LITERAL: 'true' | 'false';
CHAR_LITERAL: '\'' (~['\\\r\n]) '\'';
STRING_LITERAL: '"' (~["\\\r\n])* '"';

// WHITESPACE, COMMENTS
WS: [ \t\r\n]+ -> skip ;
COMMENT: '#' ~[\r\n]* -> skip;

// IDENTIFIER
IDENTIFIER:         Letter Letter*;

// FRAGMENTS
fragment Digits
    : [0-9] ([0-9_]* [0-9])?
    ;
fragment Letter
    : [a-zA-Z$_]
    ;
