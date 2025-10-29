package drones;

public class DroneInterpreterVisitor extends DronesBaseVisitor<Void>{

    @Override
    public Void visitProgram(DronesParser.ProgramContext ctx) {
        System.out.println("Start of the program (visitor)...");
        return visitChildren(ctx);
    }

    @Override
    public Void visitInfoInstDroneOne(DronesParser.InfoInstDroneOneContext ctx) {
        String instrucao = ctx.instructionDroneOne().getText();
        System.out.print("Instruction: " + instrucao + " with arguments: ");
        if (!ctx.info().isEmpty()) {
            for (DronesParser.InfoContext infoCtx : ctx.info()) {
                System.out.print(infoCtx.getText() + " ");
            }
        }
        System.out.println();
        return super.visitChildren(ctx);
    }

    @Override
    public Void visitInfoInstDroneTwo(DronesParser.InfoInstDroneTwoContext ctx) {
        String instrucao = ctx.instructionDroneTwo().getText();
        System.out.print("Instruction: " + instrucao + " with arguments: ");
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
        System.out.println("Variable: " + ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitInfoVarDroneTwo(DronesParser.InfoVarDroneTwoContext ctx) {
        System.out.println("Variable: " + ctx.getText());
        return visitChildren(ctx);
    }

}
