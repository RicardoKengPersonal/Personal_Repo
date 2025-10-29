package eapli.shodrone.integrations.plugins.dsl.figure;// Generated from C:/Users/maria/Progama�ao/Teste_Branch/base.integrations.plugins.dsl/lprog/us251/figure.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link figureParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface figureVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link figureParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(figureParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#versionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersionDecl(figureParser.VersionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#versionNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersionNumber(figureParser.VersionNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#droneTypeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDroneTypeDecl(figureParser.DroneTypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#drone}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrone(figureParser.DroneContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(figureParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(figureParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#positionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionDecl(figureParser.PositionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#velocityDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVelocityDecl(figureParser.VelocityDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(figureParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(figureParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#distanceDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistanceDecl(figureParser.DistanceDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#figureDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFigureDecl(figureParser.FigureDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#actionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionStmt(figureParser.ActionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(figureParser.ColorContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#coordinate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoordinate(figureParser.CoordinateContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#groupStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupStmt(figureParser.GroupStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#pauseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPauseStmt(figureParser.PauseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#beforeStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeforeStmt(figureParser.BeforeStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#afterStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfterStmt(figureParser.AfterStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#signal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignal(figureParser.SignalContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(figureParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(figureParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(figureParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by {@link figureParser#int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(figureParser.IntContext ctx);
}