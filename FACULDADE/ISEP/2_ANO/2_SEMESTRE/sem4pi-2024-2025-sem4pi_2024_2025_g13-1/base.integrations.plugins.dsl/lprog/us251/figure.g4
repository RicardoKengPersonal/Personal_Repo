grammar figure;

start               : versionDecl NEWLINE+ droneTypeDecl NEWLINE+ statement* EOF;

versionDecl         : DSL WS VERSION WS versionNumber SEMICOLON;

versionNumber       : int (POINT int (POINT int)?)?;

droneTypeDecl       : drone SEMICOLON ;

drone               : DRONETYPE WS id;

id                  : ID;

statement           : positionDecl NEWLINE+ | velocityDecl NEWLINE+ | distanceDecl NEWLINE+ | figureDecl NEWLINE+ | actionStmt NEWLINE+ | groupStmt NEWLINE+ | pauseStmt NEWLINE+ | beforeStmt NEWLINE+ | afterStmt NEWLINE*;

positionDecl        : POSITION WS id WS EQUAL WS coordinate SEMICOLON ;

velocityDecl        : VELOCITY WS id WS EQUAL WS expression SEMICOLON;

expression          : term (operation term)* ;

term                : number | signal PI | LPAREN expression RPAREN | id ;

distanceDecl        : DISTANCE WS id WS EQUAL WS expression SEMICOLON ;

figureDecl          : LINE WS id LPAREN WS? id COMMA WS? id COMMA WS? id RPAREN SEMICOLON
                    | RECTANGLE WS id LPAREN WS? id COMMA WS? id COMMA WS? id COMMA WS? id RPAREN SEMICOLON
                    | CIRCLE WS id LPAREN WS? id COMMA WS? id COMMA WS? id RPAREN SEMICOLON
                    | CIRCUNFERENCE WS id LPAREN WS? id COMMA WS? expression COMMA WS? id RPAREN SEMICOLON
                    ;

actionStmt          : WS? id POINT LIGHTSON LPAREN color RPAREN SEMICOLON
                    | WS? id POINT MOVE LPAREN coordinate COMMA WS? number COMMA WS? id RPAREN SEMICOLON
                    | WS? id POINT ROTATE LPAREN id COMMA WS? id COMMA WS? expression COMMA WS? id RPAREN SEMICOLON
                    | WS? id POINT LIGHTSOFF SEMICOLON
                    | WS? id POINT MOVEPOS LPAREN id COMMA WS? id RPAREN SEMICOLON
                    ;

color               : YELLOW | RED | GREEN ;

coordinate          : LPAREN number COMMA WS? number COMMA WS? number RPAREN;

groupStmt           : WS? GROUP NEWLINE (actionStmt NEWLINE)+ WS? ENDGROUP ;

pauseStmt           : PAUSE LPAREN number RPAREN SEMICOLON;

beforeStmt          : BEFORE NEWLINE (actionStmt NEWLINE | groupStmt NEWLINE)+ WS? ENDBEFORE ;

afterStmt           : AFTER NEWLINE (actionStmt NEWLINE | groupStmt NEWLINE)+ WS? ENDAFTER ;

signal              : (PLUS | MINUS)?;

operation           : MULTIPLICATION | DIVISION | PLUS | MINUS;

number              : signal (float | int);

float               : DIGIT+ POINT DIGIT+;

int                 : DIGIT+;

DSL: 'DSL';
VERSION: 'version';
SEMICOLON: ';';
DRONETYPE: 'DroneType';
POSITION: 'Position';
VELOCITY:'Velocity';
EQUAL: '=';
LPAREN : '(' ;
RPAREN : ')' ;
PI: 'PI';
PLUS: '+';
MINUS: '-';
MULTIPLICATION: '*';
DIVISION: '/';
DISTANCE: 'Distance';
LINE: 'Line';
RECTANGLE: 'Rectangle';
CIRCLE: 'Circle';
CIRCUNFERENCE: 'Circumference';
LIGHTSON: 'lightsOn';
MOVE: 'move';
ROTATE: 'rotate';
LIGHTSOFF: 'lightsOff';
MOVEPOS: 'movePos';
YELLOW: 'YELLOW';
RED: 'RED';
GREEN: 'GREEN';
GROUP: 'group';
ENDGROUP: 'endgroup';
PAUSE: 'pause';
BEFORE: 'before';
ENDBEFORE: 'endbefore';
AFTER: 'after';
ENDAFTER: 'endafter';
POINT: '.';
COMMA: ',';

ID                  : [a-zA-Z_][a-zA-Z0-9_]*;

DIGIT               : [0-9];

NEWLINE             : '\r'? '\n';

WS                  : [ \t]+;

UNKNOWN             : . -> skip;