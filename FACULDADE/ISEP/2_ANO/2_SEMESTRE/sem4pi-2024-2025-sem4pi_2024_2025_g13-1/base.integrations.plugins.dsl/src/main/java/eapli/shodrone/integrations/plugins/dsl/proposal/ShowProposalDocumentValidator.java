package eapli.shodrone.integrations.plugins.dsl.proposal;

import org.antlr.v4.runtime.*;

import java.io.InputStream;
import java.io.IOException;

public class ShowProposalDocumentValidator {

    public void validate(InputStream documentStream) throws InvalidDocumentException {
        try {
            CharStream input = CharStreams.fromStream(documentStream);
            proposalLexer lexer = new proposalLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            proposalParser parser = new proposalParser(tokens);

            lexer.removeErrorListeners();
            parser.removeErrorListeners();

            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg,
                                        RecognitionException e) {
                    throw new InvalidDocumentException("Erro de sintaxe no documento na linha " + line + ":" + charPositionInLine + " - " + msg);
                }
            });

            parser.start(); // ou outro nome da tua regra inicial

        } catch (IOException e) {
            throw new InvalidDocumentException("Erro ao ler o documento: " + e.getMessage());
        }
    }

    public static class InvalidDocumentException extends RuntimeException {
        public InvalidDocumentException(String message) {
            super(message);
        }
    }
}
