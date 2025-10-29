package eapli.shodrone.integrations.plugins.dsl.figure;

public class FigureInterpreterVisitor extends figureBaseVisitor<Void> {

    @Override
    public Void visitVersionDecl(figureParser.VersionDeclContext ctx) {
        System.out.println("Version: " + ctx.versionNumber().getText());
        return null;
    }

    @Override
    public Void visitDrone(figureParser.DroneContext ctx) {
        System.out.println("Drone ID: " + ctx.id().getText());
        return null;
    }

    @Override
    public Void visitPositionDecl(figureParser.PositionDeclContext ctx) {
        System.out.println("Position declaration: " + ctx.id().getText() +
                " = " + ctx.coordinate().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitVelocityDecl(figureParser.VelocityDeclContext ctx) {
        System.out.println("Velocity declaration: " + ctx.id().getText() +
                " = " + ctx.expression().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitDistanceDecl(figureParser.DistanceDeclContext ctx) {
        System.out.println("Distance declaration: " + ctx.id().getText() +
                " = " + ctx.expression().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitFigureDecl(figureParser.FigureDeclContext ctx) {
        System.out.println("figure declaration: " + ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitActionStmt(figureParser.ActionStmtContext ctx) {
        System.out.println("Action: " + ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitGroupStmt(figureParser.GroupStmtContext ctx) {
        System.out.println("Group Start:");
        visitChildren(ctx);
        System.out.println("Group End.");
        return null;
    }

    @Override
    public Void visitPauseStmt(figureParser.PauseStmtContext ctx) {
        System.out.println("Pause: " + ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitBeforeStmt(figureParser.BeforeStmtContext ctx) {
        System.out.println("Before Block Start:");
        visitChildren(ctx);
        System.out.println("Before Block End.");
        return null;
    }

    @Override
    public Void visitAfterStmt(figureParser.AfterStmtContext ctx) {
        System.out.println("After Block Start:");
        visitChildren(ctx);
        System.out.println("After Block End.");
        return null;
    }
}

