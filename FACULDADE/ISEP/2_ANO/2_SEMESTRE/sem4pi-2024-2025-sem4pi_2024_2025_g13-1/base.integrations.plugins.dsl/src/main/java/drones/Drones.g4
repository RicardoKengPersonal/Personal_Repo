grammar Drones;

program                 : droneVersion EOF;

droneVersion            : DRONEONE infoDrone NEWLINE+ typesDroneOne NEWLINE+ variablesDroneOne instructionsDroneOne
                        | DRONETWO infoDrone NEWLINE+ typesDroneTwo NEWLINE+ variablesDroneTwo instructionsDroneTwo;

infoDrone               : WS PROGRAMMING WS LANGUAGE WS VERSION WS version;


version                 : FLOAT;

typesDroneOne           : TYPE NEWLINE+ (position NEWLINE+)? (vector NEWLINE+)? (linearvelocity NEWLINE+)? (angularvelocity NEWLINE+)? (distance NEWLINE+)? (time NEWLINE+)?;

typesDroneTwo           : TYPE NEWLINE+ (point NEWLINE+)? (vector NEWLINE+)? (linearvelocity NEWLINE+)? (angularvelocity NEWLINE+)? (distance NEWLINE+)? (time NEWLINE+)?;

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

variablesDroneOne       : VARIABLES NEWLINE+ (infoVarDroneOne NEWLINE+)*;

infoVarDroneOne         : typeDroneOne WS name WS EQUALS WS? value* SEMICOLON;

typeDroneOne            : POSITION | VECTOR | LINEARVELOCITY | ANGULARVELOCITY;

variablesDroneTwo       : VARIABLES NEWLINE+ (infoVarDroneTwo NEWLINE+)*;

infoVarDroneTwo         : typeDroneTwo WS name WS EQUALS WS? value* SEMICOLON;

typeDroneTwo            : POINT | VECTOR | LINEARVELOCITY | ANGULARVELOCITY;

name                    : ID;

value                   : expression (op expression)*;

expression              : array | coordinates | INT | FLOAT | PI | ID ;

op                      : '+' | '-' | '*'| '/';

array                   : LPAREN coordinates (COMMA WS? coordinates)* RPAREN;

instructionsDroneOne    : INSTRUCTIONS NEWLINE+ (infoInstDroneOne NEWLINE*)+ ;

infoInstDroneOne        : instructionDroneOne LPAREN info* RPAREN SEMICOLON;

instructionDroneOne     : TAKEOFF | LAND | MOVE | MOVEPATH | HOOVER | LIGHTSON | LIGHTOFF | BLINK;

instructionsDroneTwo    : INSTRUCTIONS NEWLINE+ (infoInstDroneTwo NEWLINE*)+ ;

infoInstDroneTwo        : instructionDroneTwo LPAREN info* RPAREN SEMICOLON;

instructionDroneTwo     : TAKEOFF | LAND | MOVE | MOVEPATH | MOVECIRCLE | HOOVER | LIGHTSON | LIGHTOFF;

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
DRONEONE: 'DroneOne';
DRONETWO:  'DroneTwo';
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