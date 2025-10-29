package drones.validations;

import drones.DronesLexer;
import drones.DronesParser;
import org.antlr.v4.runtime.*;
import java.nio.file.*;

public class DroneSyntaxValidator {

    public static boolean validate(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            DronesLexer lexer = new DronesLexer(CharStreams.fromString(content));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DronesParser parser = new DronesParser(tokens);

            SyntaxValidatorErrorListener errorListener = new SyntaxValidatorErrorListener();
            parser.removeErrorListeners(); // disable default error printing
            parser.addErrorListener(errorListener);

            parser.program(); // entry point in your grammar

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
