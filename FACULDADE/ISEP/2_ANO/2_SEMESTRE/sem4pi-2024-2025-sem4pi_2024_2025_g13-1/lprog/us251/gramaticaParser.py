# Generated from gramatica.g4 by ANTLR 4.13.2
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO

def serializedATN():
    return [
        4,1,33,250,2,0,7,0,2,1,7,1,2,2,7,2,2,3,7,3,2,4,7,4,2,5,7,5,2,6,7,
        6,2,7,7,7,2,8,7,8,2,9,7,9,2,10,7,10,2,11,7,11,2,12,7,12,2,13,7,13,
        2,14,7,14,2,15,7,15,2,16,7,16,2,17,7,17,2,18,7,18,2,19,7,19,2,20,
        7,20,2,21,7,21,2,22,7,22,2,23,7,23,2,24,7,24,2,25,7,25,1,0,1,0,1,
        0,1,0,1,0,1,0,1,0,1,0,1,0,4,0,62,8,0,11,0,12,0,63,1,1,1,1,1,1,1,
        1,1,1,1,1,1,1,1,1,3,1,74,8,1,1,2,1,2,1,2,1,2,1,3,1,3,1,3,1,3,1,3,
        1,3,1,3,1,3,1,3,1,3,1,3,1,3,1,4,1,4,1,4,1,4,1,4,1,4,1,5,1,5,1,5,
        1,5,1,5,1,5,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,
        1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,1,6,3,6,128,8,6,1,7,1,7,
        1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,
        1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,1,7,
        1,7,1,7,1,7,1,7,3,7,168,8,7,1,8,1,8,4,8,172,8,8,11,8,12,8,173,1,
        8,1,8,1,9,1,9,1,9,1,9,1,9,1,9,1,10,1,10,1,10,1,10,1,10,1,10,3,10,
        190,8,10,1,11,1,11,1,11,1,11,5,11,196,8,11,10,11,12,11,199,9,11,
        1,12,1,12,1,12,1,12,1,12,1,12,1,12,1,12,1,12,1,12,1,12,1,12,3,12,
        213,8,12,1,13,3,13,216,8,13,1,14,1,14,1,15,1,15,1,16,1,16,1,17,1,
        17,1,18,1,18,1,19,1,19,1,19,1,19,1,19,1,19,1,19,1,19,1,20,1,20,3,
        20,238,8,20,1,21,1,21,1,22,1,22,1,23,1,23,1,24,1,24,1,25,1,25,1,
        25,0,0,26,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,
        40,42,44,46,48,50,0,4,1,0,23,24,1,0,23,26,1,0,13,14,1,0,27,29,244,
        0,52,1,0,0,0,2,73,1,0,0,0,4,75,1,0,0,0,6,79,1,0,0,0,8,91,1,0,0,0,
        10,97,1,0,0,0,12,127,1,0,0,0,14,167,1,0,0,0,16,169,1,0,0,0,18,177,
        1,0,0,0,20,189,1,0,0,0,22,191,1,0,0,0,24,212,1,0,0,0,26,215,1,0,
        0,0,28,217,1,0,0,0,30,219,1,0,0,0,32,221,1,0,0,0,34,223,1,0,0,0,
        36,225,1,0,0,0,38,227,1,0,0,0,40,237,1,0,0,0,42,239,1,0,0,0,44,241,
        1,0,0,0,46,243,1,0,0,0,48,245,1,0,0,0,50,247,1,0,0,0,52,53,5,1,0,
        0,53,54,5,2,0,0,54,55,5,31,0,0,55,56,5,3,0,0,56,57,5,31,0,0,57,58,
        5,3,0,0,58,59,5,31,0,0,59,61,5,4,0,0,60,62,3,2,1,0,61,60,1,0,0,0,
        62,63,1,0,0,0,63,61,1,0,0,0,63,64,1,0,0,0,64,1,1,0,0,0,65,74,3,4,
        2,0,66,74,3,6,3,0,67,74,3,8,4,0,68,74,3,10,5,0,69,74,3,12,6,0,70,
        74,3,14,7,0,71,74,3,16,8,0,72,74,3,18,9,0,73,65,1,0,0,0,73,66,1,
        0,0,0,73,67,1,0,0,0,73,68,1,0,0,0,73,69,1,0,0,0,73,70,1,0,0,0,73,
        71,1,0,0,0,73,72,1,0,0,0,74,3,1,0,0,0,75,76,5,5,0,0,76,77,5,30,0,
        0,77,78,5,4,0,0,78,5,1,0,0,0,79,80,5,6,0,0,80,81,5,30,0,0,81,82,
        5,7,0,0,82,83,5,8,0,0,83,84,3,20,10,0,84,85,5,9,0,0,85,86,3,20,10,
        0,86,87,5,9,0,0,87,88,3,20,10,0,88,89,5,10,0,0,89,90,5,4,0,0,90,
        7,1,0,0,0,91,92,5,11,0,0,92,93,5,30,0,0,93,94,5,7,0,0,94,95,3,22,
        11,0,95,96,5,4,0,0,96,9,1,0,0,0,97,98,5,12,0,0,98,99,5,30,0,0,99,
        100,5,7,0,0,100,101,3,22,11,0,101,102,5,4,0,0,102,11,1,0,0,0,103,
        104,5,13,0,0,104,105,5,30,0,0,105,106,5,8,0,0,106,107,3,32,16,0,
        107,108,5,9,0,0,108,109,3,34,17,0,109,110,5,9,0,0,110,111,3,36,18,
        0,111,112,5,10,0,0,112,113,5,4,0,0,113,128,1,0,0,0,114,115,5,14,
        0,0,115,116,5,30,0,0,116,117,5,8,0,0,117,118,3,32,16,0,118,119,5,
        9,0,0,119,120,3,34,17,0,120,121,5,9,0,0,121,122,3,34,17,0,122,123,
        5,9,0,0,123,124,3,36,18,0,124,125,5,10,0,0,125,126,5,4,0,0,126,128,
        1,0,0,0,127,103,1,0,0,0,127,114,1,0,0,0,128,13,1,0,0,0,129,130,5,
        30,0,0,130,131,5,3,0,0,131,132,5,15,0,0,132,133,5,8,0,0,133,134,
        3,50,25,0,134,135,5,10,0,0,135,136,5,4,0,0,136,168,1,0,0,0,137,138,
        5,30,0,0,138,139,5,3,0,0,139,140,5,16,0,0,140,168,5,4,0,0,141,142,
        5,30,0,0,142,143,5,3,0,0,143,144,5,17,0,0,144,145,5,8,0,0,145,146,
        3,38,19,0,146,147,5,9,0,0,147,148,3,40,20,0,148,149,5,9,0,0,149,
        150,3,42,21,0,150,151,5,10,0,0,151,152,5,4,0,0,152,168,1,0,0,0,153,
        154,5,30,0,0,154,155,5,3,0,0,155,156,5,18,0,0,156,157,5,8,0,0,157,
        158,3,44,22,0,158,159,5,9,0,0,159,160,3,46,23,0,160,161,5,9,0,0,
        161,162,3,48,24,0,162,163,5,9,0,0,163,164,3,42,21,0,164,165,5,10,
        0,0,165,166,5,4,0,0,166,168,1,0,0,0,167,129,1,0,0,0,167,137,1,0,
        0,0,167,141,1,0,0,0,167,153,1,0,0,0,168,15,1,0,0,0,169,171,5,19,
        0,0,170,172,3,14,7,0,171,170,1,0,0,0,172,173,1,0,0,0,173,171,1,0,
        0,0,173,174,1,0,0,0,174,175,1,0,0,0,175,176,5,20,0,0,176,17,1,0,
        0,0,177,178,5,21,0,0,178,179,5,8,0,0,179,180,5,31,0,0,180,181,5,
        10,0,0,181,182,5,4,0,0,182,19,1,0,0,0,183,184,3,26,13,0,184,185,
        5,31,0,0,185,190,1,0,0,0,186,187,3,26,13,0,187,188,5,32,0,0,188,
        190,1,0,0,0,189,183,1,0,0,0,189,186,1,0,0,0,190,21,1,0,0,0,191,197,
        3,24,12,0,192,193,3,28,14,0,193,194,3,24,12,0,194,196,1,0,0,0,195,
        192,1,0,0,0,196,199,1,0,0,0,197,195,1,0,0,0,197,198,1,0,0,0,198,
        23,1,0,0,0,199,197,1,0,0,0,200,213,3,20,10,0,201,213,5,22,0,0,202,
        203,5,8,0,0,203,204,3,22,11,0,204,205,5,10,0,0,205,213,1,0,0,0,206,
        207,3,26,13,0,207,208,5,22,0,0,208,213,1,0,0,0,209,210,3,26,13,0,
        210,211,3,20,10,0,211,213,1,0,0,0,212,200,1,0,0,0,212,201,1,0,0,
        0,212,202,1,0,0,0,212,206,1,0,0,0,212,209,1,0,0,0,213,25,1,0,0,0,
        214,216,7,0,0,0,215,214,1,0,0,0,215,216,1,0,0,0,216,27,1,0,0,0,217,
        218,7,1,0,0,218,29,1,0,0,0,219,220,7,2,0,0,220,31,1,0,0,0,221,222,
        5,30,0,0,222,33,1,0,0,0,223,224,5,30,0,0,224,35,1,0,0,0,225,226,
        5,30,0,0,226,37,1,0,0,0,227,228,5,8,0,0,228,229,3,20,10,0,229,230,
        5,9,0,0,230,231,3,20,10,0,231,232,5,9,0,0,232,233,3,20,10,0,233,
        234,5,10,0,0,234,39,1,0,0,0,235,238,5,30,0,0,236,238,3,20,10,0,237,
        235,1,0,0,0,237,236,1,0,0,0,238,41,1,0,0,0,239,240,5,30,0,0,240,
        43,1,0,0,0,241,242,5,30,0,0,242,45,1,0,0,0,243,244,5,30,0,0,244,
        47,1,0,0,0,245,246,3,22,11,0,246,49,1,0,0,0,247,248,7,3,0,0,248,
        51,1,0,0,0,10,63,73,127,167,173,189,197,212,215,237
    ]

class gramaticaParser ( Parser ):

    grammarFileName = "gramatica.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'DSL'", "'version'", "'.'", "';'", "'DroneType'", 
                     "'Position'", "'='", "'('", "','", "')'", "'Velocity'", 
                     "'Distance'", "'Line'", "'Rectangle'", "'lightsOn'", 
                     "'lightsOff'", "'move'", "'rotate'", "'group'", "'endgroup'", 
                     "'pause'", "'PI'", "'+'", "'-'", "'*'", "'/'", "'YELLOW'", 
                     "'RED'", "'GREEN'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "IDENTIFICADOR", "INTEIRO", 
                      "DECIMAL", "WS" ]

    RULE_programa = 0
    RULE_cont = 1
    RULE_drone = 2
    RULE_posicaodecl = 3
    RULE_velocidadedecl = 4
    RULE_distanciadecl = 5
    RULE_figura = 6
    RULE_acao = 7
    RULE_grupo = 8
    RULE_pausa = 9
    RULE_numero = 10
    RULE_expressao = 11
    RULE_termo = 12
    RULE_sinal = 13
    RULE_operacao = 14
    RULE_nomefigura = 15
    RULE_posicao = 16
    RULE_distancia = 17
    RULE_dronetipo = 18
    RULE_direcao = 19
    RULE_duracao = 20
    RULE_velocidade = 21
    RULE_centro = 22
    RULE_axis = 23
    RULE_angulo = 24
    RULE_cor = 25

    ruleNames =  [ "programa", "cont", "drone", "posicaodecl", "velocidadedecl", 
                   "distanciadecl", "figura", "acao", "grupo", "pausa", 
                   "numero", "expressao", "termo", "sinal", "operacao", 
                   "nomefigura", "posicao", "distancia", "dronetipo", "direcao", 
                   "duracao", "velocidade", "centro", "axis", "angulo", 
                   "cor" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    T__5=6
    T__6=7
    T__7=8
    T__8=9
    T__9=10
    T__10=11
    T__11=12
    T__12=13
    T__13=14
    T__14=15
    T__15=16
    T__16=17
    T__17=18
    T__18=19
    T__19=20
    T__20=21
    T__21=22
    T__22=23
    T__23=24
    T__24=25
    T__25=26
    T__26=27
    T__27=28
    T__28=29
    IDENTIFICADOR=30
    INTEIRO=31
    DECIMAL=32
    WS=33

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.13.2")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class ProgramaContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def INTEIRO(self, i:int=None):
            if i is None:
                return self.getTokens(gramaticaParser.INTEIRO)
            else:
                return self.getToken(gramaticaParser.INTEIRO, i)

        def cont(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.ContContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.ContContext,i)


        def getRuleIndex(self):
            return gramaticaParser.RULE_programa

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPrograma" ):
                listener.enterPrograma(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPrograma" ):
                listener.exitPrograma(self)




    def programa(self):

        localctx = gramaticaParser.ProgramaContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_programa)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 52
            self.match(gramaticaParser.T__0)
            self.state = 53
            self.match(gramaticaParser.T__1)
            self.state = 54
            self.match(gramaticaParser.INTEIRO)
            self.state = 55
            self.match(gramaticaParser.T__2)
            self.state = 56
            self.match(gramaticaParser.INTEIRO)
            self.state = 57
            self.match(gramaticaParser.T__2)
            self.state = 58
            self.match(gramaticaParser.INTEIRO)
            self.state = 59
            self.match(gramaticaParser.T__3)
            self.state = 61 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 60
                self.cont()
                self.state = 63 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not ((((_la) & ~0x3f) == 0 and ((1 << _la) & 1076394080) != 0)):
                    break

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class ContContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def drone(self):
            return self.getTypedRuleContext(gramaticaParser.DroneContext,0)


        def posicaodecl(self):
            return self.getTypedRuleContext(gramaticaParser.PosicaodeclContext,0)


        def velocidadedecl(self):
            return self.getTypedRuleContext(gramaticaParser.VelocidadedeclContext,0)


        def distanciadecl(self):
            return self.getTypedRuleContext(gramaticaParser.DistanciadeclContext,0)


        def figura(self):
            return self.getTypedRuleContext(gramaticaParser.FiguraContext,0)


        def acao(self):
            return self.getTypedRuleContext(gramaticaParser.AcaoContext,0)


        def grupo(self):
            return self.getTypedRuleContext(gramaticaParser.GrupoContext,0)


        def pausa(self):
            return self.getTypedRuleContext(gramaticaParser.PausaContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_cont

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCont" ):
                listener.enterCont(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCont" ):
                listener.exitCont(self)




    def cont(self):

        localctx = gramaticaParser.ContContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_cont)
        try:
            self.state = 73
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [5]:
                self.enterOuterAlt(localctx, 1)
                self.state = 65
                self.drone()
                pass
            elif token in [6]:
                self.enterOuterAlt(localctx, 2)
                self.state = 66
                self.posicaodecl()
                pass
            elif token in [11]:
                self.enterOuterAlt(localctx, 3)
                self.state = 67
                self.velocidadedecl()
                pass
            elif token in [12]:
                self.enterOuterAlt(localctx, 4)
                self.state = 68
                self.distanciadecl()
                pass
            elif token in [13, 14]:
                self.enterOuterAlt(localctx, 5)
                self.state = 69
                self.figura()
                pass
            elif token in [30]:
                self.enterOuterAlt(localctx, 6)
                self.state = 70
                self.acao()
                pass
            elif token in [19]:
                self.enterOuterAlt(localctx, 7)
                self.state = 71
                self.grupo()
                pass
            elif token in [21]:
                self.enterOuterAlt(localctx, 8)
                self.state = 72
                self.pausa()
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DroneContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_drone

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDrone" ):
                listener.enterDrone(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDrone" ):
                listener.exitDrone(self)




    def drone(self):

        localctx = gramaticaParser.DroneContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_drone)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 75
            self.match(gramaticaParser.T__4)
            self.state = 76
            self.match(gramaticaParser.IDENTIFICADOR)
            self.state = 77
            self.match(gramaticaParser.T__3)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class PosicaodeclContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def numero(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.NumeroContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.NumeroContext,i)


        def getRuleIndex(self):
            return gramaticaParser.RULE_posicaodecl

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPosicaodecl" ):
                listener.enterPosicaodecl(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPosicaodecl" ):
                listener.exitPosicaodecl(self)




    def posicaodecl(self):

        localctx = gramaticaParser.PosicaodeclContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_posicaodecl)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 79
            self.match(gramaticaParser.T__5)
            self.state = 80
            self.match(gramaticaParser.IDENTIFICADOR)
            self.state = 81
            self.match(gramaticaParser.T__6)
            self.state = 82
            self.match(gramaticaParser.T__7)
            self.state = 83
            self.numero()
            self.state = 84
            self.match(gramaticaParser.T__8)
            self.state = 85
            self.numero()
            self.state = 86
            self.match(gramaticaParser.T__8)
            self.state = 87
            self.numero()
            self.state = 88
            self.match(gramaticaParser.T__9)
            self.state = 89
            self.match(gramaticaParser.T__3)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class VelocidadedeclContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def expressao(self):
            return self.getTypedRuleContext(gramaticaParser.ExpressaoContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_velocidadedecl

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterVelocidadedecl" ):
                listener.enterVelocidadedecl(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitVelocidadedecl" ):
                listener.exitVelocidadedecl(self)




    def velocidadedecl(self):

        localctx = gramaticaParser.VelocidadedeclContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_velocidadedecl)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 91
            self.match(gramaticaParser.T__10)
            self.state = 92
            self.match(gramaticaParser.IDENTIFICADOR)
            self.state = 93
            self.match(gramaticaParser.T__6)
            self.state = 94
            self.expressao()
            self.state = 95
            self.match(gramaticaParser.T__3)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DistanciadeclContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def expressao(self):
            return self.getTypedRuleContext(gramaticaParser.ExpressaoContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_distanciadecl

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDistanciadecl" ):
                listener.enterDistanciadecl(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDistanciadecl" ):
                listener.exitDistanciadecl(self)




    def distanciadecl(self):

        localctx = gramaticaParser.DistanciadeclContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_distanciadecl)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 97
            self.match(gramaticaParser.T__11)
            self.state = 98
            self.match(gramaticaParser.IDENTIFICADOR)
            self.state = 99
            self.match(gramaticaParser.T__6)
            self.state = 100
            self.expressao()
            self.state = 101
            self.match(gramaticaParser.T__3)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class FiguraContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def posicao(self):
            return self.getTypedRuleContext(gramaticaParser.PosicaoContext,0)


        def distancia(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.DistanciaContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.DistanciaContext,i)


        def dronetipo(self):
            return self.getTypedRuleContext(gramaticaParser.DronetipoContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_figura

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterFigura" ):
                listener.enterFigura(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitFigura" ):
                listener.exitFigura(self)




    def figura(self):

        localctx = gramaticaParser.FiguraContext(self, self._ctx, self.state)
        self.enterRule(localctx, 12, self.RULE_figura)
        try:
            self.state = 127
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [13]:
                self.enterOuterAlt(localctx, 1)
                self.state = 103
                self.match(gramaticaParser.T__12)
                self.state = 104
                self.match(gramaticaParser.IDENTIFICADOR)
                self.state = 105
                self.match(gramaticaParser.T__7)
                self.state = 106
                self.posicao()
                self.state = 107
                self.match(gramaticaParser.T__8)
                self.state = 108
                self.distancia()
                self.state = 109
                self.match(gramaticaParser.T__8)
                self.state = 110
                self.dronetipo()
                self.state = 111
                self.match(gramaticaParser.T__9)
                self.state = 112
                self.match(gramaticaParser.T__3)
                pass
            elif token in [14]:
                self.enterOuterAlt(localctx, 2)
                self.state = 114
                self.match(gramaticaParser.T__13)
                self.state = 115
                self.match(gramaticaParser.IDENTIFICADOR)
                self.state = 116
                self.match(gramaticaParser.T__7)
                self.state = 117
                self.posicao()
                self.state = 118
                self.match(gramaticaParser.T__8)
                self.state = 119
                self.distancia()
                self.state = 120
                self.match(gramaticaParser.T__8)
                self.state = 121
                self.distancia()
                self.state = 122
                self.match(gramaticaParser.T__8)
                self.state = 123
                self.dronetipo()
                self.state = 124
                self.match(gramaticaParser.T__9)
                self.state = 125
                self.match(gramaticaParser.T__3)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AcaoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def cor(self):
            return self.getTypedRuleContext(gramaticaParser.CorContext,0)


        def direcao(self):
            return self.getTypedRuleContext(gramaticaParser.DirecaoContext,0)


        def duracao(self):
            return self.getTypedRuleContext(gramaticaParser.DuracaoContext,0)


        def velocidade(self):
            return self.getTypedRuleContext(gramaticaParser.VelocidadeContext,0)


        def centro(self):
            return self.getTypedRuleContext(gramaticaParser.CentroContext,0)


        def axis(self):
            return self.getTypedRuleContext(gramaticaParser.AxisContext,0)


        def angulo(self):
            return self.getTypedRuleContext(gramaticaParser.AnguloContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_acao

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAcao" ):
                listener.enterAcao(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAcao" ):
                listener.exitAcao(self)




    def acao(self):

        localctx = gramaticaParser.AcaoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_acao)
        try:
            self.state = 167
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,3,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 129
                self.match(gramaticaParser.IDENTIFICADOR)
                self.state = 130
                self.match(gramaticaParser.T__2)
                self.state = 131
                self.match(gramaticaParser.T__14)
                self.state = 132
                self.match(gramaticaParser.T__7)
                self.state = 133
                self.cor()
                self.state = 134
                self.match(gramaticaParser.T__9)
                self.state = 135
                self.match(gramaticaParser.T__3)
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 137
                self.match(gramaticaParser.IDENTIFICADOR)
                self.state = 138
                self.match(gramaticaParser.T__2)
                self.state = 139
                self.match(gramaticaParser.T__15)
                self.state = 140
                self.match(gramaticaParser.T__3)
                pass

            elif la_ == 3:
                self.enterOuterAlt(localctx, 3)
                self.state = 141
                self.match(gramaticaParser.IDENTIFICADOR)
                self.state = 142
                self.match(gramaticaParser.T__2)
                self.state = 143
                self.match(gramaticaParser.T__16)
                self.state = 144
                self.match(gramaticaParser.T__7)
                self.state = 145
                self.direcao()
                self.state = 146
                self.match(gramaticaParser.T__8)
                self.state = 147
                self.duracao()
                self.state = 148
                self.match(gramaticaParser.T__8)
                self.state = 149
                self.velocidade()
                self.state = 150
                self.match(gramaticaParser.T__9)
                self.state = 151
                self.match(gramaticaParser.T__3)
                pass

            elif la_ == 4:
                self.enterOuterAlt(localctx, 4)
                self.state = 153
                self.match(gramaticaParser.IDENTIFICADOR)
                self.state = 154
                self.match(gramaticaParser.T__2)
                self.state = 155
                self.match(gramaticaParser.T__17)
                self.state = 156
                self.match(gramaticaParser.T__7)
                self.state = 157
                self.centro()
                self.state = 158
                self.match(gramaticaParser.T__8)
                self.state = 159
                self.axis()
                self.state = 160
                self.match(gramaticaParser.T__8)
                self.state = 161
                self.angulo()
                self.state = 162
                self.match(gramaticaParser.T__8)
                self.state = 163
                self.velocidade()
                self.state = 164
                self.match(gramaticaParser.T__9)
                self.state = 165
                self.match(gramaticaParser.T__3)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class GrupoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def acao(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.AcaoContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.AcaoContext,i)


        def getRuleIndex(self):
            return gramaticaParser.RULE_grupo

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGrupo" ):
                listener.enterGrupo(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGrupo" ):
                listener.exitGrupo(self)




    def grupo(self):

        localctx = gramaticaParser.GrupoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 16, self.RULE_grupo)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 169
            self.match(gramaticaParser.T__18)
            self.state = 171 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 170
                self.acao()
                self.state = 173 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==30):
                    break

            self.state = 175
            self.match(gramaticaParser.T__19)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class PausaContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def INTEIRO(self):
            return self.getToken(gramaticaParser.INTEIRO, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_pausa

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPausa" ):
                listener.enterPausa(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPausa" ):
                listener.exitPausa(self)




    def pausa(self):

        localctx = gramaticaParser.PausaContext(self, self._ctx, self.state)
        self.enterRule(localctx, 18, self.RULE_pausa)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 177
            self.match(gramaticaParser.T__20)
            self.state = 178
            self.match(gramaticaParser.T__7)
            self.state = 179
            self.match(gramaticaParser.INTEIRO)
            self.state = 180
            self.match(gramaticaParser.T__9)
            self.state = 181
            self.match(gramaticaParser.T__3)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class NumeroContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def sinal(self):
            return self.getTypedRuleContext(gramaticaParser.SinalContext,0)


        def INTEIRO(self):
            return self.getToken(gramaticaParser.INTEIRO, 0)

        def DECIMAL(self):
            return self.getToken(gramaticaParser.DECIMAL, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_numero

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterNumero" ):
                listener.enterNumero(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitNumero" ):
                listener.exitNumero(self)




    def numero(self):

        localctx = gramaticaParser.NumeroContext(self, self._ctx, self.state)
        self.enterRule(localctx, 20, self.RULE_numero)
        try:
            self.state = 189
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,5,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 183
                self.sinal()
                self.state = 184
                self.match(gramaticaParser.INTEIRO)
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 186
                self.sinal()
                self.state = 187
                self.match(gramaticaParser.DECIMAL)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class ExpressaoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def termo(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.TermoContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.TermoContext,i)


        def operacao(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.OperacaoContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.OperacaoContext,i)


        def getRuleIndex(self):
            return gramaticaParser.RULE_expressao

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterExpressao" ):
                listener.enterExpressao(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitExpressao" ):
                listener.exitExpressao(self)




    def expressao(self):

        localctx = gramaticaParser.ExpressaoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 22, self.RULE_expressao)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 191
            self.termo()
            self.state = 197
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while (((_la) & ~0x3f) == 0 and ((1 << _la) & 125829120) != 0):
                self.state = 192
                self.operacao()
                self.state = 193
                self.termo()
                self.state = 199
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class TermoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def numero(self):
            return self.getTypedRuleContext(gramaticaParser.NumeroContext,0)


        def expressao(self):
            return self.getTypedRuleContext(gramaticaParser.ExpressaoContext,0)


        def sinal(self):
            return self.getTypedRuleContext(gramaticaParser.SinalContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_termo

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterTermo" ):
                listener.enterTermo(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitTermo" ):
                listener.exitTermo(self)




    def termo(self):

        localctx = gramaticaParser.TermoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 24, self.RULE_termo)
        try:
            self.state = 212
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,7,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 200
                self.numero()
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 201
                self.match(gramaticaParser.T__21)
                pass

            elif la_ == 3:
                self.enterOuterAlt(localctx, 3)
                self.state = 202
                self.match(gramaticaParser.T__7)
                self.state = 203
                self.expressao()
                self.state = 204
                self.match(gramaticaParser.T__9)
                pass

            elif la_ == 4:
                self.enterOuterAlt(localctx, 4)
                self.state = 206
                self.sinal()
                self.state = 207
                self.match(gramaticaParser.T__21)
                pass

            elif la_ == 5:
                self.enterOuterAlt(localctx, 5)
                self.state = 209
                self.sinal()
                self.state = 210
                self.numero()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class SinalContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return gramaticaParser.RULE_sinal

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSinal" ):
                listener.enterSinal(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSinal" ):
                listener.exitSinal(self)




    def sinal(self):

        localctx = gramaticaParser.SinalContext(self, self._ctx, self.state)
        self.enterRule(localctx, 26, self.RULE_sinal)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 215
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,8,self._ctx)
            if la_ == 1:
                self.state = 214
                _la = self._input.LA(1)
                if not(_la==23 or _la==24):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class OperacaoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return gramaticaParser.RULE_operacao

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterOperacao" ):
                listener.enterOperacao(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitOperacao" ):
                listener.exitOperacao(self)




    def operacao(self):

        localctx = gramaticaParser.OperacaoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 28, self.RULE_operacao)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 217
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & 125829120) != 0)):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class NomefiguraContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return gramaticaParser.RULE_nomefigura

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterNomefigura" ):
                listener.enterNomefigura(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitNomefigura" ):
                listener.exitNomefigura(self)




    def nomefigura(self):

        localctx = gramaticaParser.NomefiguraContext(self, self._ctx, self.state)
        self.enterRule(localctx, 30, self.RULE_nomefigura)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 219
            _la = self._input.LA(1)
            if not(_la==13 or _la==14):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class PosicaoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_posicao

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPosicao" ):
                listener.enterPosicao(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPosicao" ):
                listener.exitPosicao(self)




    def posicao(self):

        localctx = gramaticaParser.PosicaoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 32, self.RULE_posicao)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 221
            self.match(gramaticaParser.IDENTIFICADOR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DistanciaContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_distancia

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDistancia" ):
                listener.enterDistancia(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDistancia" ):
                listener.exitDistancia(self)




    def distancia(self):

        localctx = gramaticaParser.DistanciaContext(self, self._ctx, self.state)
        self.enterRule(localctx, 34, self.RULE_distancia)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 223
            self.match(gramaticaParser.IDENTIFICADOR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DronetipoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_dronetipo

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDronetipo" ):
                listener.enterDronetipo(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDronetipo" ):
                listener.exitDronetipo(self)




    def dronetipo(self):

        localctx = gramaticaParser.DronetipoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 36, self.RULE_dronetipo)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 225
            self.match(gramaticaParser.IDENTIFICADOR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DirecaoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def numero(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(gramaticaParser.NumeroContext)
            else:
                return self.getTypedRuleContext(gramaticaParser.NumeroContext,i)


        def getRuleIndex(self):
            return gramaticaParser.RULE_direcao

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDirecao" ):
                listener.enterDirecao(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDirecao" ):
                listener.exitDirecao(self)




    def direcao(self):

        localctx = gramaticaParser.DirecaoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 38, self.RULE_direcao)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 227
            self.match(gramaticaParser.T__7)
            self.state = 228
            self.numero()
            self.state = 229
            self.match(gramaticaParser.T__8)
            self.state = 230
            self.numero()
            self.state = 231
            self.match(gramaticaParser.T__8)
            self.state = 232
            self.numero()
            self.state = 233
            self.match(gramaticaParser.T__9)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DuracaoContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def numero(self):
            return self.getTypedRuleContext(gramaticaParser.NumeroContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_duracao

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDuracao" ):
                listener.enterDuracao(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDuracao" ):
                listener.exitDuracao(self)




    def duracao(self):

        localctx = gramaticaParser.DuracaoContext(self, self._ctx, self.state)
        self.enterRule(localctx, 40, self.RULE_duracao)
        try:
            self.state = 237
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [30]:
                self.enterOuterAlt(localctx, 1)
                self.state = 235
                self.match(gramaticaParser.IDENTIFICADOR)
                pass
            elif token in [23, 24, 31, 32]:
                self.enterOuterAlt(localctx, 2)
                self.state = 236
                self.numero()
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class VelocidadeContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_velocidade

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterVelocidade" ):
                listener.enterVelocidade(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitVelocidade" ):
                listener.exitVelocidade(self)




    def velocidade(self):

        localctx = gramaticaParser.VelocidadeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 42, self.RULE_velocidade)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 239
            self.match(gramaticaParser.IDENTIFICADOR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CentroContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_centro

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCentro" ):
                listener.enterCentro(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCentro" ):
                listener.exitCentro(self)




    def centro(self):

        localctx = gramaticaParser.CentroContext(self, self._ctx, self.state)
        self.enterRule(localctx, 44, self.RULE_centro)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 241
            self.match(gramaticaParser.IDENTIFICADOR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AxisContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def IDENTIFICADOR(self):
            return self.getToken(gramaticaParser.IDENTIFICADOR, 0)

        def getRuleIndex(self):
            return gramaticaParser.RULE_axis

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAxis" ):
                listener.enterAxis(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAxis" ):
                listener.exitAxis(self)




    def axis(self):

        localctx = gramaticaParser.AxisContext(self, self._ctx, self.state)
        self.enterRule(localctx, 46, self.RULE_axis)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 243
            self.match(gramaticaParser.IDENTIFICADOR)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AnguloContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def expressao(self):
            return self.getTypedRuleContext(gramaticaParser.ExpressaoContext,0)


        def getRuleIndex(self):
            return gramaticaParser.RULE_angulo

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAngulo" ):
                listener.enterAngulo(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAngulo" ):
                listener.exitAngulo(self)




    def angulo(self):

        localctx = gramaticaParser.AnguloContext(self, self._ctx, self.state)
        self.enterRule(localctx, 48, self.RULE_angulo)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 245
            self.expressao()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CorContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return gramaticaParser.RULE_cor

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCor" ):
                listener.enterCor(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCor" ):
                listener.exitCor(self)




    def cor(self):

        localctx = gramaticaParser.CorContext(self, self._ctx, self.state)
        self.enterRule(localctx, 50, self.RULE_cor)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 247
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & 939524096) != 0)):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





