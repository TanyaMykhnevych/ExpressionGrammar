/**
 * Define a grammar called ExpressionGrammar
 */
grammar Ofp;

@header {    // Define output package.
    package FedirkoMykhnevych.A2;
}

// subrules  
// as optional (?) 
// and as zero-or-more (*)
// or one-or-more (+)


start : (functionDeclaration)* mainFunctionDeclaration (functionDeclaration)*;

// ------------------------- PARSER -----------------------
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
    #floatLiteralExpression
    | INTEGER_LITERAL
    #intLiteralExpression
    | CHAR_LITERAL
    #charLiteralExpression
    | STRING_LITERAL
    #stringLiteralExpression
    | BOOL_LITERAL
    #boolLiteralExpression
    ;

functionDeclaration
    : returnValue IDENTIFIER params
      functionBody
    ;
    
mainFunctionDeclaration
    : 'void main()' functionBody
    ;

builtinFunctionCall
    : builtinFunction '(' builtintFunctionArgument ')'
    ;

builtinFunction
    : PRINT
    | PRINT_LINE
    ;    

functionBody
    : statement*
    | returnStatement*
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
    : primitiveType
    | arrayType '[]'
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

functionCall
    : IDENTIFIER arguments
    ;

arguments
    : '(' expressionList? ')'
    ;

expressionList
    : expression (',' expression)*
    ;

conditionExpression
    : '(' booleanExpression ')'
    ;
    
booleanExpression
    : functionCall
    # functionCallBooleanExpression
    | expression (LT | GT) expression
    # gtLtBooleanExpression
    | expression (EQUAL) expression
    # booleanEqualsExpression
    | BOOL_LITERAL
    # booleanLiteralExpression
    | IDENTIFIER
    # identifierBooleanExpression
    ;


ifStatement
	: IF '(' conditionExpression ')'
		statement
	 ('else'
		statement)?;

unarySubstract
    : SUB (IDENTIFIER | INTEGER_LITERAL | FLOAT_LITERAL)
    ;

assignStatement 
    : variableAssignStatement
    | arrayElementAssignStatement
    ;


// arr[i] = 2;
arrayElementAssignStatement 
    :  IDENTIFIER '[' expression ']' '=' expression
    ;

variableAssignStatement
	: IDENTIFIER '=' expression
	;


statement
    : '{' statement* '}'
    | builtinFunctionCall SEMI
    | functionCall SEMI
    | ifStatement
    | whileStatement
    | IDENTIFIER ':' statement
    | assignStatement
    | variableDeclaration
	| returnStatement
    ;
    
returnStatement 
	: RETURN expression? ';';
    
whileStatement
	: WHILE conditionExpression statement;
   

expression
    : '(' expression ')'
    # bracketsExpression
    | literal
    # literalExpression
    | IDENTIFIER
    # identifierExpression
    | expression DOT IDENTIFIER
    # memberExpression
    | expression '[' expression ']'
    # arrayIndexExpression
    | functionCall
    # functionCallExpression    
    | '(' type ')' expression
    # typeExpression
    | (SUB) expression
    # unaryMinusExpression
    | expression (MUL | DIV) expression
    # mulDivExpression
    | expression (ADD | SUB) expression
    # addSubExpression
    | expression (GT | LT) expression
    # gtLtExpression
    | expression (EQUAL) expression
    # equalsExpression
    | NEW creator
    # newCreatorExpression
    ;

arrayIndexExpr 
	: expression '[' expression ']'
	;

builtintFunctionArgument
    : expression
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

// Built-in functions
PRINT      : 'print'  ;
PRINT_LINE : 'println';

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
INTEGER_LITERAL: ('0' | [1-9] (Digits?));
FLOAT_LITERAL: (Digits '.' Digits);
BOOL_LITERAL: 'true' | 'false';
CHAR_LITERAL: '\'' [a-zA-Z !.,?=:()] '\'';
STRING_LITERAL: '"' [a-zA-Z !.,?=:()]+ '"';

// WHITESPACE, COMMENTS
WS: [ \t\r\n]+ -> skip ;
COMMENT: '#' ~[\r\n]* -> skip;

// IDENTIFIER
IDENTIFIER:         Letter Letter*;

// FRAGMENTS
fragment Digits
    : [0-9] ([0-9]* [0-9])?
    ;
fragment Letter
    : [a-zA-Z]
    ;
