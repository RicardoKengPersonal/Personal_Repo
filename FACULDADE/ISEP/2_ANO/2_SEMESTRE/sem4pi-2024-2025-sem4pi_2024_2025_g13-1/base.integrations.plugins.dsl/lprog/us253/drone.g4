grammar drone;

program                 : intro NEWLINE+ types NEWLINE+ variables instructions EOF;

intro                   : DRONE WS PROGRAMMING WS LANGUAGE WS VERSION WS version;

version                 : FLOAT;

types                   : TYPE NEWLINE+ (position NEWLINE+)? (point NEWLINE+)? (vector NEWLINE+)? (linearvelocity NEWLINE+)? (angularvelocity NEWLINE+)? (distance NEWLINE+)? (time NEWLINE+)?;

position                : POSITION WS '-' WS infoPositionPointVector;

infoPositionPointVector : COORDENATES_LETTERS ',' (ID | WS | ',')+ TYPEDATA (ID | WS | ',')+ UNIT;

point                   : POINT WS '-' WS infoPositionPointVector;

vector                  : VECTOR WS '-' WS infoPositionPointVector;

linearvelocity          : LINEARVELOCITY WS '-' WS TYPEDATA (ID | WS | ',')+ UNIT;

angularvelocity         : ANGULARVELOCITY WS '-' WS TYPEDATA (ID | WS | ',')+ UNIT;

distance                : DISTANCE WS '-' WS TYPEDATA (ID | WS | ',')+ UNIT;

time                    : TIME WS '-' WS TYPEDATA (ID | WS | ',')+ UNIT;

coordinates             : LPAREN number (WS UNIT)? COMMA WS? number (WS UNIT)? COMMA WS? number UNIT? RPAREN;

number                  : '-'? INT | '-'? FLOAT;

variables               : VARIABLES NEWLINE+ (infoVar NEWLINE+)*;

infoVar                 : type WS name WS EQUALS WS? value* SEMICOLON;

type                    : POSITION | POINT | VECTOR | LINEARVELOCITY | ANGULARVELOCITY | DISTANCE | TIME;

name                    : ID;

value                   : expression (op expression)*;

expression              : array | coordinates | INT | FLOAT | PI | ID ;

op                      : '+' | '-' | '*'| '/';

array                   : LPAREN coordinates (COMMA WS? coordinates)* RPAREN;

instructions            : INSTRUCTIONS NEWLINE+ (infoInst NEWLINE*)+ ;

infoInst                : instruction LPAREN info* RPAREN SEMICOLON;

instruction             : TAKEOFF | LAND | MOVE | MOVEPATH | HOOVER | LIGHTSON | LIGHTOFF | BLINK | MOVECIRCLE;

info                    : expression (COMMA WS? expression)*;

// Tokens
COORDENATES_LETTERS : '(x, y, z)';
TYPEDATA: 'floating point numbers' | 'floating point number' | 'integer number';
TAKEOFF: 'takeOff';
LAND: 'land';
MOVE: 'move';
MOVEPATH: 'movePath';
HOOVER: 'hoover';
LIGHTSON: 'lightsOn';
LIGHTOFF: 'lightsOff';
BLINK: 'blink';
MOVECIRCLE: 'moveCircle';
PI: 'PI';
EQUALS : '=';
SEMICOLON : ';';
LPAREN : '(' ;
RPAREN : ')' ;
COMMA  : ',';
DRONE       : 'DroneOne' | 'DroneTwo';
PROGRAMMING : 'programming';
LANGUAGE    : 'language';
VERSION     : 'version';
TYPE        : 'Types';
POSITION    : 'Position';
POINT : 'Point';
VECTOR      : 'Vector';
LINEARVELOCITY : 'LinearVelocity';
ANGULARVELOCITY : 'AngularVelocity';
DISTANCE: 'Distance';
TIME: 'Time';
VARIABLES: 'Variables';
INSTRUCTIONS: 'Instructions';
UNIT : 'meters' | 'ms' | 'm' | 'rad/s' | 'm/s' ;

INT                     : [0-9]+ ;

FLOAT                   : INT+ '.' INT+;

ID                      : [a-zA-Z_][a-zA-Z0-9_]*;

NEWLINE                 : '\r'? '\n';

WS                      : [ \t]+;

UNKNOWN                 : . -> skip;