package eapli.shodrone.integrations.plugins.dsl.figure;


import eapli.shodrone.integrations.plugins.dsl.figure.validations.FigureSyntaxValidator;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FigurePlugin {

    public void interpretFigureScript(String path) {
        System.out.println("Interpreting Figure DSL:");
        if (!FigureSyntaxValidator.validate(path)) {
            System.out.println("Validation failed. Not interpreting.");
            return;
        }
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            figureLexer lexer = new figureLexer(CharStreams.fromString(content));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            figureParser parser = new figureParser(tokens);
            ParseTree tree = parser.start();
            FigureInterpreterVisitor visitor = new FigureInterpreterVisitor();
            visitor.visit(tree);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

}