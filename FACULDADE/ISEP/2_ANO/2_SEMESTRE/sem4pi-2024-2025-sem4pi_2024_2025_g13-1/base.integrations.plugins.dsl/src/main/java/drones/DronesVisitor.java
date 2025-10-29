// Generated from C:/Users/Maria Póvoas/IdeaProjects/sem4pi-2024-2025-sem4pi_2024_2025_g13/base.integrations.plugins.dsl/src/main/java/drones/Drones.g4 by ANTLR 4.13.2
package drones;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DronesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DronesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DronesParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DronesParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#droneVersion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDroneVersion(DronesParser.DroneVersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#infoDrone}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfoDrone(DronesParser.InfoDroneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion(DronesParser.VersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#typesDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypesDroneOne(DronesParser.TypesDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#typesDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypesDroneTwo(DronesParser.TypesDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#position}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPosition(DronesParser.PositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#infoPositionPointVector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfoPositionPointVector(DronesParser.InfoPositionPointVectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoint(DronesParser.PointContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#vector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVector(DronesParser.VectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#linearvelocity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinearvelocity(DronesParser.LinearvelocityContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#angularvelocity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAngularvelocity(DronesParser.AngularvelocityContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#distance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistance(DronesParser.DistanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime(DronesParser.TimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#coordinates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoordinates(DronesParser.CoordinatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(DronesParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#variablesDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariablesDroneOne(DronesParser.VariablesDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#infoVarDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfoVarDroneOne(DronesParser.InfoVarDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#typeDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDroneOne(DronesParser.TypeDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#variablesDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariablesDroneTwo(DronesParser.VariablesDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#infoVarDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfoVarDroneTwo(DronesParser.InfoVarDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#typeDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDroneTwo(DronesParser.TypeDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(DronesParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(DronesParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(DronesParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(DronesParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(DronesParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#instructionsDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionsDroneOne(DronesParser.InstructionsDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#infoInstDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfoInstDroneOne(DronesParser.InfoInstDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#instructionDroneOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionDroneOne(DronesParser.InstructionDroneOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#instructionsDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionsDroneTwo(DronesParser.InstructionsDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#infoInstDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfoInstDroneTwo(DronesParser.InfoInstDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#instructionDroneTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionDroneTwo(DronesParser.InstructionDroneTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DronesParser#info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfo(DronesParser.InfoContext ctx);
}