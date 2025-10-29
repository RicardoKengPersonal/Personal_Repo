US345 – Drone language Plugin
==============================

# Diagrams

System sequence diagram:

![system-sequence-diagram](/lprog/us345/doc/system-sequence-diagram.svg)

Use case diagram:

![use-case-diagram](/lprog/us345/doc/use-case-diagram.svg)

# Program

- The program supports two distinct versions: DroneOne and DroneTwo.

- The `Drone` class is responsible for reading the contents of the file containing the grammar. The file is loaded from a path that is entered into the program.

- The next step is to create a lexer that recognizes the language tokens. After that, the parser uses the tokens to build the syntax tree (parser tree) that represents the program hierarchy based on the defined grammar.

- Next, a visitor (`DroneInterpreterVisitor`) will traverse all the nodes in the tree. For each declaration of a variable or statement, the visitor executes associated logic and prints information to the console about what is being interpreted and the respective arguments.

# Code

Drone.java:

```java
    public class Drone {
    public static void main(String[] args) throws IOException {
            // DroneOne
            String caminho = "src/main/java/projeto/lprog/files input/DroneOne drone progamming language.txt";
            // DroneTwo
            // String caminho = "src/main/java/projeto/lprog/files input/DroneTwo drone progamming language.txt";
    
            try {
                String conteudo = new String(Files.readAllBytes(Paths.get(caminho)));
                DronesLexer lexer = new DronesLexer(CharStreams.fromString(conteudo));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                DronesParser parser = new DronesParser(tokens);
                ParseTree tree = parser.program();
                parseWithVisitor(tree);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
    }
    
    public static void parseWithVisitor(ParseTree tree) {
           DroneInterpreterVisitor visitor = new DroneInterpreterVisitor();
           visitor.visit(tree);
           System.out.println("Visit completed (visitor)");
    }
}
```

DroneInterpreterVisitor.java:

```java
public class DroneInterpreterVisitor extends DronesBaseVisitor<Void>{

    @Override
    public Void visitProgram(DronesParser.ProgramContext ctx) {
        System.out.println("Start of the program...");
        return visitChildren(ctx);
    }

    @Override
    public Void visitInfoInstDroneOne(DronesParser.InfoInstDroneOneContext ctx) {
        String instrucao = ctx.instructionDroneOne().getText();
        System.out.print("Instruction DroneOne: " + instrucao + " with arguments: ");
        if (!ctx.info().isEmpty()) {
            for (DronesParser.InfoContext infoCtx : ctx.info()) {
                System.out.print(infoCtx.getText() + " ");
            }
        }
        System.out.println();
        return super.visitChildren(ctx);
    }

    @Override
    public Void visitInfoVarDroneOne(DronesParser.InfoVarDroneOneContext ctx) {
        System.out.println("Variable DroneOne: " + ctx.getText());
        return visitChildren(ctx);
    }
    
    //    ...

}
```


