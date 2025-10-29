# Generated from gramatica.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .gramaticaParser import gramaticaParser
else:
    from gramaticaParser import gramaticaParser

# This class defines a complete listener for a parse tree produced by gramaticaParser.
class gramaticaListener(ParseTreeListener):

    # Enter a parse tree produced by gramaticaParser#programa.
    def enterPrograma(self, ctx:gramaticaParser.ProgramaContext):
        pass

    # Exit a parse tree produced by gramaticaParser#programa.
    def exitPrograma(self, ctx:gramaticaParser.ProgramaContext):
        pass


    # Enter a parse tree produced by gramaticaParser#cont.
    def enterCont(self, ctx:gramaticaParser.ContContext):
        pass

    # Exit a parse tree produced by gramaticaParser#cont.
    def exitCont(self, ctx:gramaticaParser.ContContext):
        pass


    # Enter a parse tree produced by gramaticaParser#drone.
    def enterDrone(self, ctx:gramaticaParser.DroneContext):
        pass

    # Exit a parse tree produced by gramaticaParser#drone.
    def exitDrone(self, ctx:gramaticaParser.DroneContext):
        pass


    # Enter a parse tree produced by gramaticaParser#posicaodecl.
    def enterPosicaodecl(self, ctx:gramaticaParser.PosicaodeclContext):
        pass

    # Exit a parse tree produced by gramaticaParser#posicaodecl.
    def exitPosicaodecl(self, ctx:gramaticaParser.PosicaodeclContext):
        pass


    # Enter a parse tree produced by gramaticaParser#velocidadedecl.
    def enterVelocidadedecl(self, ctx:gramaticaParser.VelocidadedeclContext):
        pass

    # Exit a parse tree produced by gramaticaParser#velocidadedecl.
    def exitVelocidadedecl(self, ctx:gramaticaParser.VelocidadedeclContext):
        pass


    # Enter a parse tree produced by gramaticaParser#distanciadecl.
    def enterDistanciadecl(self, ctx:gramaticaParser.DistanciadeclContext):
        pass

    # Exit a parse tree produced by gramaticaParser#distanciadecl.
    def exitDistanciadecl(self, ctx:gramaticaParser.DistanciadeclContext):
        pass


    # Enter a parse tree produced by gramaticaParser#figura.
    def enterFigura(self, ctx:gramaticaParser.FiguraContext):
        pass

    # Exit a parse tree produced by gramaticaParser#figura.
    def exitFigura(self, ctx:gramaticaParser.FiguraContext):
        pass


    # Enter a parse tree produced by gramaticaParser#acao.
    def enterAcao(self, ctx:gramaticaParser.AcaoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#acao.
    def exitAcao(self, ctx:gramaticaParser.AcaoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#grupo.
    def enterGrupo(self, ctx:gramaticaParser.GrupoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#grupo.
    def exitGrupo(self, ctx:gramaticaParser.GrupoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#pausa.
    def enterPausa(self, ctx:gramaticaParser.PausaContext):
        pass

    # Exit a parse tree produced by gramaticaParser#pausa.
    def exitPausa(self, ctx:gramaticaParser.PausaContext):
        pass


    # Enter a parse tree produced by gramaticaParser#numero.
    def enterNumero(self, ctx:gramaticaParser.NumeroContext):
        pass

    # Exit a parse tree produced by gramaticaParser#numero.
    def exitNumero(self, ctx:gramaticaParser.NumeroContext):
        pass


    # Enter a parse tree produced by gramaticaParser#expressao.
    def enterExpressao(self, ctx:gramaticaParser.ExpressaoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#expressao.
    def exitExpressao(self, ctx:gramaticaParser.ExpressaoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#termo.
    def enterTermo(self, ctx:gramaticaParser.TermoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#termo.
    def exitTermo(self, ctx:gramaticaParser.TermoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#sinal.
    def enterSinal(self, ctx:gramaticaParser.SinalContext):
        pass

    # Exit a parse tree produced by gramaticaParser#sinal.
    def exitSinal(self, ctx:gramaticaParser.SinalContext):
        pass


    # Enter a parse tree produced by gramaticaParser#operacao.
    def enterOperacao(self, ctx:gramaticaParser.OperacaoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#operacao.
    def exitOperacao(self, ctx:gramaticaParser.OperacaoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#nomefigura.
    def enterNomefigura(self, ctx:gramaticaParser.NomefiguraContext):
        pass

    # Exit a parse tree produced by gramaticaParser#nomefigura.
    def exitNomefigura(self, ctx:gramaticaParser.NomefiguraContext):
        pass


    # Enter a parse tree produced by gramaticaParser#posicao.
    def enterPosicao(self, ctx:gramaticaParser.PosicaoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#posicao.
    def exitPosicao(self, ctx:gramaticaParser.PosicaoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#distancia.
    def enterDistancia(self, ctx:gramaticaParser.DistanciaContext):
        pass

    # Exit a parse tree produced by gramaticaParser#distancia.
    def exitDistancia(self, ctx:gramaticaParser.DistanciaContext):
        pass


    # Enter a parse tree produced by gramaticaParser#dronetipo.
    def enterDronetipo(self, ctx:gramaticaParser.DronetipoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#dronetipo.
    def exitDronetipo(self, ctx:gramaticaParser.DronetipoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#direcao.
    def enterDirecao(self, ctx:gramaticaParser.DirecaoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#direcao.
    def exitDirecao(self, ctx:gramaticaParser.DirecaoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#duracao.
    def enterDuracao(self, ctx:gramaticaParser.DuracaoContext):
        pass

    # Exit a parse tree produced by gramaticaParser#duracao.
    def exitDuracao(self, ctx:gramaticaParser.DuracaoContext):
        pass


    # Enter a parse tree produced by gramaticaParser#velocidade.
    def enterVelocidade(self, ctx:gramaticaParser.VelocidadeContext):
        pass

    # Exit a parse tree produced by gramaticaParser#velocidade.
    def exitVelocidade(self, ctx:gramaticaParser.VelocidadeContext):
        pass


    # Enter a parse tree produced by gramaticaParser#centro.
    def enterCentro(self, ctx:gramaticaParser.CentroContext):
        pass

    # Exit a parse tree produced by gramaticaParser#centro.
    def exitCentro(self, ctx:gramaticaParser.CentroContext):
        pass


    # Enter a parse tree produced by gramaticaParser#axis.
    def enterAxis(self, ctx:gramaticaParser.AxisContext):
        pass

    # Exit a parse tree produced by gramaticaParser#axis.
    def exitAxis(self, ctx:gramaticaParser.AxisContext):
        pass


    # Enter a parse tree produced by gramaticaParser#angulo.
    def enterAngulo(self, ctx:gramaticaParser.AnguloContext):
        pass

    # Exit a parse tree produced by gramaticaParser#angulo.
    def exitAngulo(self, ctx:gramaticaParser.AnguloContext):
        pass


    # Enter a parse tree produced by gramaticaParser#cor.
    def enterCor(self, ctx:gramaticaParser.CorContext):
        pass

    # Exit a parse tree produced by gramaticaParser#cor.
    def exitCor(self, ctx:gramaticaParser.CorContext):
        pass



del gramaticaParser