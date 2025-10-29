package eapli.shodrone.integrations.plugins.dsl.figure.validations;

import eapli.shodrone.integrations.plugins.dsl.figure.figureLexer;
import eapli.shodrone.integrations.plugins.dsl.figure.figureParser;
import org.antlr.v4.runtime.*;
import java.nio.file.*;

public class FigureSyntaxValidator {

    public static boolean validate(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            figureLexer lexer = new figureLexer(CharStreams.fromString(content));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            figureParser parser = new figureParser(tokens);

            SyntaxValidatorErrorListener errorListener = new SyntaxValidatorErrorListener();
            parser.removeErrorListeners(); // disable default error printing
            parser.addErrorListener(errorListener);

            parser.start(); // entry point in your grammar

            if (errorListener.hasErrors()) {
                System.out.println("SYNTAX ERRORS in file: " + filePath);
                errorListener.getErrors().forEach(e -> System.out.println(" - " + e));
                return false;
            }

            System.out.println("SYNTAX OK in file: " + filePath);
            return true;

        } catch (Exception e) {
            System.err.println("Failed to validate file: " + e.getMessage());
            return false;
        }
    }
}
