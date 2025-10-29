package drones;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Drone {

    public static boolean droneOnePlugin(String path) {
        System.out.println("Drone One:");
        if (drones.validations.DroneSyntaxValidator.validate(path)) {
            lexerAndParser(path);
            return true;
        } else {
            System.out.println("Validation failed. Not parsing.");
            return false;
        }
    }


    public static boolean droneTwoPlugin (String path) {
        System.out.println("Drone Two: ");
        if (drones.validations.DroneSyntaxValidator.validate(path)) {
            lexerAndParser(path);
            return true;
        } else {
            System.out.println("Validation failed. Not parsing.");
            return false;
        }
    }

    public static void lexerAndParser(String path){
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            DronesLexer lexer = new DronesLexer(CharStreams.fromString(content));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DronesParser parser = new DronesParser(tokens);
            ParseTree tree = parser.program();
            parseWithVisitor(tree);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void parseWithVisitor(ParseTree tree) {
        DroneInterpreterVisitor visitor = new DroneInterpreterVisitor();
        visitor.visit(tree);
        System.out.println("Visit ended (visitor)");
    }
}