grammar proposal;

start               : greetingVersion;

version1            : intro NEWLINE referencePart NEWLINE+ proposalVersion1 NEWLINE* SEPARATOR+ NEWLINE* extra EOF;

version2            : customerName NEWLINE intro NEWLINE referencePart NEWLINE+ proposalVersion2 NEWLINE* SEPARATOR+ NEWLINE* extra EOF;

proposalVersion1    : title NEWLINE+ proposalBody1 signature;

proposalVersion2    : title NEWLINE+ proposalBody2 signature;

title               : PROPOSALTITLE;

intro               : companyName NEWLINE streetAddress WS? COMMA WS postalCode WS? COMMA WS country NEWLINE vatNumber NEWLINE;

referencePart       : REFERENCE WS referenceNumber WS BAR WS referenceDate;

companyNameClient   : (LETTER | WS | PUNCT)+;

proposalBody1       : paragraph+;

proposalBody2       : companyNameClient WS? VIPMARKER paragraph+;

valuePart           : VALUE WS amount;

paragraph           : text+ NEWLINE+;

amount              : (number WS? COIN) | (COIN WS? number);

link                : HTTPS LETTER*;

signature           : CUMP NEWLINE+ signatureName NEWLINE signatureTitle;

extra               : attachmentTitle NEWLINE+ showLocation NEWLINE showDate NEWLINE showTime NEWLINE showDuration NEWLINE* list;

list                : droneList NEWLINE+ figureList;

attachmentTitle     : ANEXO WS HIFEN WS info WS proposalNumber;

info                : (LETTER)* (WS (LETTER)*)+;

proposalNumber      : number;

showLocation        : LOCATION WS HIFEN WS streetAddress WS? COMMA WS postalCode WS? COMMA WS country;

coordinateConj      : coordinateGeo WS coordinateGeo;

coordinateGeo       : number DEGREECOORDENATE number MINUTECOORDENATE number SECONDCOORDENATE LETTER ;

showDate            : DATE WS HIFEN WS date;

showTime            : TIME WS HIFEN WS time WS?;

showDuration        : DURATION WS HIFEN WS number WS? MINUTES;

droneList           : LISTDRONESENTENCE NEWLINE (droneItem NEWLINE)+;

droneItem           : model WS HIFEN WS intVal WS UNITSDRONES;

model               : (LETTER | DIGIT)+;

figureList          : LISTFIGURESENTENCE NEWLINE (figureItem (NEWLINE)?)+;

figureItem          : intVal WS HIFEN WS text;

greetingVersion     : (EXMOS (WS SIRS)? COMMA? NEWLINE version1) | (EXMOS COMMA NEWLINE version2);

customerName        : text+;

companyName         : text;

streetAddress       : ((LETTER | DIGIT)* WS (LETTER | DIGIT)+)+;

postalCode          : DIGIT+ SEPARATOR DIGIT+ ;

city                : text NEWLINE;

country             : text;

vatNumber : LETTER LETTER (LETTER | DIGIT)+;

referenceNumber     : number;

referenceDate       : date;

signatureName       : text;

signatureTitle      : text;

date                : DIGIT+ BAR DIGIT+ BAR DIGIT+;

time                : DIGIT+ hour? TWOPOINTS DIGIT+ hour?;

hour                : LETTER+;

number              : floatVal | intVal;

floatVal            : DIGIT+ POINT DIGIT+;

intVal              : DIGIT+;

text                : link | valuePart | CUMP | ANEXO | (LETTER | DIGIT | WS | PUNCT | BAR | COMMA | POINT | SEPARATOR | TWOPOINTS)+;

POINT: '.';
TWOPOINTS: ':';
HIFEN: '–';
SEPARATOR: '-';
COMMA: ',';
BAR: '/';
DEGREECOORDENATE: '°';
MINUTECOORDENATE: '\'';
SECONDCOORDENATE: '"';
REFERENCE: 'Referência' | 'Reference';
HTTPS: 'https://';
PROPOSALTITLE: 'Proposta de Show' | 'Show Proposal';
VALUE: 'valor de' | 'amount of';
CUMP: 'Melhores cumprimentos,' | 'Best regards,';
ANEXO: 'Anexo' | 'Attachment';
LOCATION: 'Local de realização' | 'Location';
DATE: 'Data' | 'Date';
TIME: 'Hora' | 'Time';
DURATION: 'Duração' | 'Duration';
MINUTES: 'minutos' | 'minutes';
LISTDRONESENTENCE: '#Lista de drones utilizados' | '#List of used drones';
LISTFIGURESENTENCE: '#Lista de figuras' | '#List of figures';
UNITSDRONES: 'unidades.' | 'units.';
EXMOS: 'Exmos.' | 'Dear';
SIRS: 'Senhores' | 'Sirs';
VIPMARKER: WS 'is a VIP client';

DIGIT               : [0-9];

COIN                : [€£$];

LETTER              : [a-zA-Z];

NEWLINE             : '\r'? '\n';

PUNCT               : [.,!?;:()/\-@#%&€£$*"'_©];

WS                  : [ \t]+;

UNKNOWN             : . -> skip;