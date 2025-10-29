//package lprog;
//import org.antlr.v4.runtime.*;
//import org.antlr.v4.runtime.tree.ParseTree;
//import lprog.us253.parser.droneLexer;
//import lprog.us253.parser.droneParser;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//public class Drone {
//    public static void main(String[] args) throws IOException {
//
////        System.out.println("Insira o caminho do ficheiro: ");
////        Scanner scanner = new Scanner(System.in);
////        String caminho = scanner.nextLine();
//        String caminho = "lprog/files input/DroneOne drone progamming language.txt";
////        String caminho = "lprog/files input/DroneTwo drone progamming language.txt";
//
//        try {
//            String conteudo = new String(Files.readAllBytes(Paths.get(caminho)));
//            droneLexer lexer = new droneLexer(CharStreams.fromString(conteudo));
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//            droneParser parser = new droneParser(tokens);
//            ParseTree tree = parser.program();
//            System.out.println(tree.toStringTree(parser));
//        } catch (IOException e) {
//            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
//        }
//    }
//}