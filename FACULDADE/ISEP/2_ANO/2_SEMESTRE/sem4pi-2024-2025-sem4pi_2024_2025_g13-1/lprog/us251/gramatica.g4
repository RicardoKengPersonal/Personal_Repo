grammar gramatica;

programa        : 'DSL' 'version' INTEIRO '.' INTEIRO '.' INTEIRO ';' (cont)+;

cont            : drone | posicaodecl | velocidadedecl | distanciadecl | figura | acao | grupo | pausa;

drone           : 'DroneType' IDENTIFICADOR ';';

posicaodecl     : 'Position' IDENTIFICADOR '=' '(' numero ',' numero ',' numero ')' ';';

velocidadedecl      : 'Velocity' IDENTIFICADOR '=' expressao ';';

distanciadecl       : 'Distance' IDENTIFICADOR '=' expressao ';';

figura          : 'Line' IDENTIFICADOR '(' posicao ',' distancia ',' dronetipo ')' ';'
                | 'Rectangle' IDENTIFICADOR '(' posicao ',' distancia ',' distancia ',' dronetipo ')' ';';

acao            : IDENTIFICADOR '.' 'lightsOn' '(' cor ')' ';'
                | IDENTIFICADOR '.' 'lightsOff' ';'
                | IDENTIFICADOR '.' 'move' '(' direcao ',' duracao ',' velocidade ')' ';'
                | IDENTIFICADOR '.' 'rotate' '(' centro ',' axis ',' angulo ',' velocidade ')' ';';

grupo           : 'group' (acao)+ 'endgroup';

pausa           : 'pause' '(' INTEIRO ')' ';';

numero          : sinal INTEIRO | sinal DECIMAL;
expressao       : termo (operacao termo)* ;
termo           : numero
                | 'PI'
                | '(' expressao ')'
                | sinal 'PI'
                | sinal numero ;
sinal           : ('+' | '-')?;
operacao        : '*' | '/' | '+' | '-';
nomefigura      : 'Line' | 'Rectangle';
posicao         : IDENTIFICADOR;
distancia       : IDENTIFICADOR;
dronetipo       : IDENTIFICADOR;
direcao         : '(' numero ',' numero ',' numero ')';
duracao         : IDENTIFICADOR | numero;
velocidade      : IDENTIFICADOR;
centro          : IDENTIFICADOR;
axis            : IDENTIFICADOR;
angulo          : expressao;
cor             : 'YELLOW' | 'RED' | 'GREEN';

IDENTIFICADOR   : [a-zA-Z][a-zA-Z0-9_]*;
INTEIRO         : [0-9]+;
DECIMAL         : [0-9]+ '.' [0-9]+;

WS : [ \t\r\n]+ -> skip;
