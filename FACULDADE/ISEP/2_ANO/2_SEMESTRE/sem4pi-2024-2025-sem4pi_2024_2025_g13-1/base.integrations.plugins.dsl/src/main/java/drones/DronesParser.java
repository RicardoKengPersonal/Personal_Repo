// Generated from C:/Users/Maria Póvoas/IdeaProjects/sem4pi-2024-2025-sem4pi_2024_2025_g13/base.integrations.plugins.dsl/src/main/java/drones/Drones.g4 by ANTLR 4.13.2
package drones;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DronesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, COORDENATES_LETTERS=5, TYPEDATA=6, TAKEOFF=7, 
		LAND=8, MOVE=9, MOVEPATH=10, HOOVER=11, LIGHTSON=12, LIGHTOFF=13, BLINK=14, 
		MOVECIRCLE=15, PI=16, EQUALS=17, SEMICOLON=18, LPAREN=19, RPAREN=20, COMMA=21, 
		DRONEONE=22, DRONETWO=23, PROGRAMMING=24, LANGUAGE=25, VERSION=26, TYPE=27, 
		POSITION=28, POINT=29, VECTOR=30, LINEARVELOCITY=31, ANGULARVELOCITY=32, 
		DISTANCE=33, TIME=34, VARIABLES=35, INSTRUCTIONS=36, UNIT=37, INT=38, 
		FLOAT=39, ID=40, NEWLINE=41, WS=42, UNKNOWN=43;
	public static final int
		RULE_program = 0, RULE_droneVersion = 1, RULE_infoDrone = 2, RULE_version = 3, 
		RULE_typesDroneOne = 4, RULE_typesDroneTwo = 5, RULE_position = 6, RULE_infoPositionPointVector = 7, 
		RULE_point = 8, RULE_vector = 9, RULE_linearvelocity = 10, RULE_angularvelocity = 11, 
		RULE_distance = 12, RULE_time = 13, RULE_coordinates = 14, RULE_number = 15, 
		RULE_variablesDroneOne = 16, RULE_infoVarDroneOne = 17, RULE_typeDroneOne = 18, 
		RULE_variablesDroneTwo = 19, RULE_infoVarDroneTwo = 20, RULE_typeDroneTwo = 21, 
		RULE_name = 22, RULE_value = 23, RULE_expression = 24, RULE_op = 25, RULE_array = 26, 
		RULE_instructionsDroneOne = 27, RULE_infoInstDroneOne = 28, RULE_instructionDroneOne = 29, 
		RULE_instructionsDroneTwo = 30, RULE_infoInstDroneTwo = 31, RULE_instructionDroneTwo = 32, 
		RULE_info = 33;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "droneVersion", "infoDrone", "version", "typesDroneOne", "typesDroneTwo", 
			"position", "infoPositionPointVector", "point", "vector", "linearvelocity", 
			"angularvelocity", "distance", "time", "coordinates", "number", "variablesDroneOne", 
			"infoVarDroneOne", "typeDroneOne", "variablesDroneTwo", "infoVarDroneTwo", 
			"typeDroneTwo", "name", "value", "expression", "op", "array", "instructionsDroneOne", 
			"infoInstDroneOne", "instructionDroneOne", "instructionsDroneTwo", "infoInstDroneTwo", 
			"instructionDroneTwo", "info"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'+'", "'*'", "'/'", "'(x, y, z)'", null, "'takeOff'", "'land'", 
			"'move'", "'movePath'", "'hoover'", "'lightsOn'", "'lightsOff'", "'blink'", 
			"'moveCircle'", "'PI'", "'='", "';'", "'('", "')'", "','", "'DroneOne'", 
			"'DroneTwo'", "'programming'", "'language'", "'version'", "'Types'", 
			"'Position'", "'Point'", "'Vector'", "'LinearVelocity'", "'AngularVelocity'", 
			"'Distance'", "'Time'", "'Variables'", "'Instructions'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "COORDENATES_LETTERS", "TYPEDATA", "TAKEOFF", 
			"LAND", "MOVE", "MOVEPATH", "HOOVER", "LIGHTSON", "LIGHTOFF", "BLINK", 
			"MOVECIRCLE", "PI", "EQUALS", "SEMICOLON", "LPAREN", "RPAREN", "COMMA", 
			"DRONEONE", "DRONETWO", "PROGRAMMING", "LANGUAGE", "VERSION", "TYPE", 
			"POSITION", "POINT", "VECTOR", "LINEARVELOCITY", "ANGULARVELOCITY", "DISTANCE", 
			"TIME", "VARIABLES", "INSTRUCTIONS", "UNIT", "INT", "FLOAT", "ID", "NEWLINE", 
			"WS", "UNKNOWN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Drones.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DronesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public DroneVersionContext droneVersion() {
			return getRuleContext(DroneVersionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(DronesParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			droneVersion();
			setState(69);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DroneVersionContext extends ParserRuleContext {
		public TerminalNode DRONEONE() { return getToken(DronesParser.DRONEONE, 0); }
		public InfoDroneContext infoDrone() {
			return getRuleContext(InfoDroneContext.class,0);
		}
		public TypesDroneOneContext typesDroneOne() {
			return getRuleContext(TypesDroneOneContext.class,0);
		}
		public VariablesDroneOneContext variablesDroneOne() {
			return getRuleContext(VariablesDroneOneContext.class,0);
		}
		public InstructionsDroneOneContext instructionsDroneOne() {
			return getRuleContext(InstructionsDroneOneContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public TerminalNode DRONETWO() { return getToken(DronesParser.DRONETWO, 0); }
		public TypesDroneTwoContext typesDroneTwo() {
			return getRuleContext(TypesDroneTwoContext.class,0);
		}
		public VariablesDroneTwoContext variablesDroneTwo() {
			return getRuleContext(VariablesDroneTwoContext.class,0);
		}
		public InstructionsDroneTwoContext instructionsDroneTwo() {
			return getRuleContext(InstructionsDroneTwoContext.class,0);
		}
		public DroneVersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_droneVersion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitDroneVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DroneVersionContext droneVersion() throws RecognitionException {
		DroneVersionContext _localctx = new DroneVersionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_droneVersion);
		int _la;
		try {
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DRONEONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(DRONEONE);
				setState(72);
				infoDrone();
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(73);
					match(NEWLINE);
					}
					}
					setState(76); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				setState(78);
				typesDroneOne();
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(79);
					match(NEWLINE);
					}
					}
					setState(82); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				setState(84);
				variablesDroneOne();
				setState(85);
				instructionsDroneOne();
				}
				break;
			case DRONETWO:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(DRONETWO);
				setState(88);
				infoDrone();
				setState(90); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(89);
					match(NEWLINE);
					}
					}
					setState(92); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				setState(94);
				typesDroneTwo();
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(95);
					match(NEWLINE);
					}
					}
					setState(98); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				setState(100);
				variablesDroneTwo();
				setState(101);
				instructionsDroneTwo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoDroneContext extends ParserRuleContext {
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public TerminalNode PROGRAMMING() { return getToken(DronesParser.PROGRAMMING, 0); }
		public TerminalNode LANGUAGE() { return getToken(DronesParser.LANGUAGE, 0); }
		public TerminalNode VERSION() { return getToken(DronesParser.VERSION, 0); }
		public VersionContext version() {
			return getRuleContext(VersionContext.class,0);
		}
		public InfoDroneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoDrone; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfoDrone(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoDroneContext infoDrone() throws RecognitionException {
		InfoDroneContext _localctx = new InfoDroneContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_infoDrone);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(WS);
			setState(106);
			match(PROGRAMMING);
			setState(107);
			match(WS);
			setState(108);
			match(LANGUAGE);
			setState(109);
			match(WS);
			setState(110);
			match(VERSION);
			setState(111);
			match(WS);
			setState(112);
			version();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VersionContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(DronesParser.FLOAT, 0); }
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypesDroneOneContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(DronesParser.TYPE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public PositionContext position() {
			return getRuleContext(PositionContext.class,0);
		}
		public VectorContext vector() {
			return getRuleContext(VectorContext.class,0);
		}
		public LinearvelocityContext linearvelocity() {
			return getRuleContext(LinearvelocityContext.class,0);
		}
		public AngularvelocityContext angularvelocity() {
			return getRuleContext(AngularvelocityContext.class,0);
		}
		public DistanceContext distance() {
			return getRuleContext(DistanceContext.class,0);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public TypesDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typesDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitTypesDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesDroneOneContext typesDroneOne() throws RecognitionException {
		TypesDroneOneContext _localctx = new TypesDroneOneContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typesDroneOne);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(TYPE);
			setState(118); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(117);
					match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(120); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POSITION) {
				{
				setState(122);
				position();
				setState(124); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(123);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(126); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VECTOR) {
				{
				setState(130);
				vector();
				setState(132); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(131);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(134); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINEARVELOCITY) {
				{
				setState(138);
				linearvelocity();
				setState(140); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(139);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(142); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANGULARVELOCITY) {
				{
				setState(146);
				angularvelocity();
				setState(148); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(147);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(150); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTANCE) {
				{
				setState(154);
				distance();
				setState(156); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(155);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(158); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TIME) {
				{
				setState(162);
				time();
				setState(164); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(163);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(166); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypesDroneTwoContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(DronesParser.TYPE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public VectorContext vector() {
			return getRuleContext(VectorContext.class,0);
		}
		public LinearvelocityContext linearvelocity() {
			return getRuleContext(LinearvelocityContext.class,0);
		}
		public AngularvelocityContext angularvelocity() {
			return getRuleContext(AngularvelocityContext.class,0);
		}
		public DistanceContext distance() {
			return getRuleContext(DistanceContext.class,0);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public TypesDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typesDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitTypesDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesDroneTwoContext typesDroneTwo() throws RecognitionException {
		TypesDroneTwoContext _localctx = new TypesDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_typesDroneTwo);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(TYPE);
			setState(172); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(171);
					match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(174); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POINT) {
				{
				setState(176);
				point();
				setState(178); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(177);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(180); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VECTOR) {
				{
				setState(184);
				vector();
				setState(186); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(185);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(188); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINEARVELOCITY) {
				{
				setState(192);
				linearvelocity();
				setState(194); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(193);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(196); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANGULARVELOCITY) {
				{
				setState(200);
				angularvelocity();
				setState(202); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(201);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(204); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTANCE) {
				{
				setState(208);
				distance();
				setState(210); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(209);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(212); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TIME) {
				{
				setState(216);
				time();
				setState(218); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(217);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(220); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PositionContext extends ParserRuleContext {
		public TerminalNode POSITION() { return getToken(DronesParser.POSITION, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public InfoPositionPointVectorContext infoPositionPointVector() {
			return getRuleContext(InfoPositionPointVectorContext.class,0);
		}
		public PositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_position; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitPosition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositionContext position() throws RecognitionException {
		PositionContext _localctx = new PositionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_position);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(POSITION);
			setState(225);
			match(WS);
			setState(226);
			match(T__0);
			setState(227);
			match(WS);
			setState(228);
			infoPositionPointVector();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoPositionPointVectorContext extends ParserRuleContext {
		public TerminalNode COORDENATES_LETTERS() { return getToken(DronesParser.COORDENATES_LETTERS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public TerminalNode TYPEDATA() { return getToken(DronesParser.TYPEDATA, 0); }
		public TerminalNode UNIT() { return getToken(DronesParser.UNIT, 0); }
		public List<TerminalNode> ID() { return getTokens(DronesParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DronesParser.ID, i);
		}
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public InfoPositionPointVectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoPositionPointVector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfoPositionPointVector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoPositionPointVectorContext infoPositionPointVector() throws RecognitionException {
		InfoPositionPointVectorContext _localctx = new InfoPositionPointVectorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_infoPositionPointVector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(COORDENATES_LETTERS);
			setState(231);
			match(COMMA);
			setState(233); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(232);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(235); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0) );
			setState(237);
			match(TYPEDATA);
			setState(239); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(238);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(241); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0) );
			setState(243);
			match(UNIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PointContext extends ParserRuleContext {
		public TerminalNode POINT() { return getToken(DronesParser.POINT, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public InfoPositionPointVectorContext infoPositionPointVector() {
			return getRuleContext(InfoPositionPointVectorContext.class,0);
		}
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(POINT);
			setState(246);
			match(WS);
			setState(247);
			match(T__0);
			setState(248);
			match(WS);
			setState(249);
			infoPositionPointVector();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VectorContext extends ParserRuleContext {
		public TerminalNode VECTOR() { return getToken(DronesParser.VECTOR, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public InfoPositionPointVectorContext infoPositionPointVector() {
			return getRuleContext(InfoPositionPointVectorContext.class,0);
		}
		public VectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitVector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VectorContext vector() throws RecognitionException {
		VectorContext _localctx = new VectorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_vector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(VECTOR);
			setState(252);
			match(WS);
			setState(253);
			match(T__0);
			setState(254);
			match(WS);
			setState(255);
			infoPositionPointVector();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LinearvelocityContext extends ParserRuleContext {
		public TerminalNode LINEARVELOCITY() { return getToken(DronesParser.LINEARVELOCITY, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public TerminalNode TYPEDATA() { return getToken(DronesParser.TYPEDATA, 0); }
		public TerminalNode UNIT() { return getToken(DronesParser.UNIT, 0); }
		public List<TerminalNode> ID() { return getTokens(DronesParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DronesParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public LinearvelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linearvelocity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitLinearvelocity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinearvelocityContext linearvelocity() throws RecognitionException {
		LinearvelocityContext _localctx = new LinearvelocityContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_linearvelocity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(LINEARVELOCITY);
			setState(258);
			match(WS);
			setState(259);
			match(T__0);
			setState(260);
			match(WS);
			setState(261);
			match(TYPEDATA);
			setState(263); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(262);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(265); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0) );
			setState(267);
			match(UNIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AngularvelocityContext extends ParserRuleContext {
		public TerminalNode ANGULARVELOCITY() { return getToken(DronesParser.ANGULARVELOCITY, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public TerminalNode TYPEDATA() { return getToken(DronesParser.TYPEDATA, 0); }
		public TerminalNode UNIT() { return getToken(DronesParser.UNIT, 0); }
		public List<TerminalNode> ID() { return getTokens(DronesParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DronesParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public AngularvelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_angularvelocity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitAngularvelocity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AngularvelocityContext angularvelocity() throws RecognitionException {
		AngularvelocityContext _localctx = new AngularvelocityContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_angularvelocity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(ANGULARVELOCITY);
			setState(270);
			match(WS);
			setState(271);
			match(T__0);
			setState(272);
			match(WS);
			setState(273);
			match(TYPEDATA);
			setState(275); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(274);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(277); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0) );
			setState(279);
			match(UNIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DistanceContext extends ParserRuleContext {
		public TerminalNode DISTANCE() { return getToken(DronesParser.DISTANCE, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public TerminalNode TYPEDATA() { return getToken(DronesParser.TYPEDATA, 0); }
		public TerminalNode UNIT() { return getToken(DronesParser.UNIT, 0); }
		public List<TerminalNode> ID() { return getTokens(DronesParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DronesParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public DistanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distance; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitDistance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistanceContext distance() throws RecognitionException {
		DistanceContext _localctx = new DistanceContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_distance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(DISTANCE);
			setState(282);
			match(WS);
			setState(283);
			match(T__0);
			setState(284);
			match(WS);
			setState(285);
			match(TYPEDATA);
			setState(287); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0) );
			setState(291);
			match(UNIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TimeContext extends ParserRuleContext {
		public TerminalNode TIME() { return getToken(DronesParser.TIME, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public TerminalNode TYPEDATA() { return getToken(DronesParser.TYPEDATA, 0); }
		public TerminalNode UNIT() { return getToken(DronesParser.UNIT, 0); }
		public List<TerminalNode> ID() { return getTokens(DronesParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DronesParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_time);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(TIME);
			setState(294);
			match(WS);
			setState(295);
			match(T__0);
			setState(296);
			match(WS);
			setState(297);
			match(TYPEDATA);
			setState(299); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(298);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(301); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5497560236032L) != 0) );
			setState(303);
			match(UNIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CoordinatesContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DronesParser.LPAREN, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(DronesParser.RPAREN, 0); }
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public List<TerminalNode> UNIT() { return getTokens(DronesParser.UNIT); }
		public TerminalNode UNIT(int i) {
			return getToken(DronesParser.UNIT, i);
		}
		public CoordinatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinates; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitCoordinates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoordinatesContext coordinates() throws RecognitionException {
		CoordinatesContext _localctx = new CoordinatesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_coordinates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(LPAREN);
			setState(306);
			number();
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(307);
				match(WS);
				setState(308);
				match(UNIT);
				}
			}

			setState(311);
			match(COMMA);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(312);
				match(WS);
				}
			}

			setState(315);
			number();
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(316);
				match(WS);
				setState(317);
				match(UNIT);
				}
			}

			setState(320);
			match(COMMA);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(321);
				match(WS);
				}
			}

			setState(324);
			number();
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UNIT) {
				{
				setState(325);
				match(UNIT);
				}
			}

			setState(328);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(DronesParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(DronesParser.FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_number);
		int _la;
		try {
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(330);
					match(T__0);
					}
				}

				setState(333);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(334);
					match(T__0);
					}
				}

				setState(337);
				match(FLOAT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariablesDroneOneContext extends ParserRuleContext {
		public TerminalNode VARIABLES() { return getToken(DronesParser.VARIABLES, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public List<InfoVarDroneOneContext> infoVarDroneOne() {
			return getRuleContexts(InfoVarDroneOneContext.class);
		}
		public InfoVarDroneOneContext infoVarDroneOne(int i) {
			return getRuleContext(InfoVarDroneOneContext.class,i);
		}
		public VariablesDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variablesDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitVariablesDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariablesDroneOneContext variablesDroneOne() throws RecognitionException {
		VariablesDroneOneContext _localctx = new VariablesDroneOneContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_variablesDroneOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(VARIABLES);
			setState(342); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(341);
				match(NEWLINE);
				}
				}
				setState(344); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7784628224L) != 0)) {
				{
				{
				setState(346);
				infoVarDroneOne();
				setState(348); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(347);
					match(NEWLINE);
					}
					}
					setState(350); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoVarDroneOneContext extends ParserRuleContext {
		public TypeDroneOneContext typeDroneOne() {
			return getRuleContext(TypeDroneOneContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DronesParser.EQUALS, 0); }
		public TerminalNode SEMICOLON() { return getToken(DronesParser.SEMICOLON, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public InfoVarDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoVarDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfoVarDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoVarDroneOneContext infoVarDroneOne() throws RecognitionException {
		InfoVarDroneOneContext _localctx = new InfoVarDroneOneContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_infoVarDroneOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			typeDroneOne();
			setState(358);
			match(WS);
			setState(359);
			name();
			setState(360);
			match(WS);
			setState(361);
			match(EQUALS);
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(362);
				match(WS);
				}
			}

			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924145938432L) != 0)) {
				{
				{
				setState(365);
				value();
				}
				}
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(371);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDroneOneContext extends ParserRuleContext {
		public TerminalNode POSITION() { return getToken(DronesParser.POSITION, 0); }
		public TerminalNode VECTOR() { return getToken(DronesParser.VECTOR, 0); }
		public TerminalNode LINEARVELOCITY() { return getToken(DronesParser.LINEARVELOCITY, 0); }
		public TerminalNode ANGULARVELOCITY() { return getToken(DronesParser.ANGULARVELOCITY, 0); }
		public TypeDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitTypeDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDroneOneContext typeDroneOne() throws RecognitionException {
		TypeDroneOneContext _localctx = new TypeDroneOneContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeDroneOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7784628224L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariablesDroneTwoContext extends ParserRuleContext {
		public TerminalNode VARIABLES() { return getToken(DronesParser.VARIABLES, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public List<InfoVarDroneTwoContext> infoVarDroneTwo() {
			return getRuleContexts(InfoVarDroneTwoContext.class);
		}
		public InfoVarDroneTwoContext infoVarDroneTwo(int i) {
			return getRuleContext(InfoVarDroneTwoContext.class,i);
		}
		public VariablesDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variablesDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitVariablesDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariablesDroneTwoContext variablesDroneTwo() throws RecognitionException {
		VariablesDroneTwoContext _localctx = new VariablesDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_variablesDroneTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(VARIABLES);
			setState(377); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(376);
				match(NEWLINE);
				}
				}
				setState(379); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(389);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) {
				{
				{
				setState(381);
				infoVarDroneTwo();
				setState(383); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(382);
					match(NEWLINE);
					}
					}
					setState(385); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoVarDroneTwoContext extends ParserRuleContext {
		public TypeDroneTwoContext typeDroneTwo() {
			return getRuleContext(TypeDroneTwoContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DronesParser.EQUALS, 0); }
		public TerminalNode SEMICOLON() { return getToken(DronesParser.SEMICOLON, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public InfoVarDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoVarDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfoVarDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoVarDroneTwoContext infoVarDroneTwo() throws RecognitionException {
		InfoVarDroneTwoContext _localctx = new InfoVarDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_infoVarDroneTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			typeDroneTwo();
			setState(393);
			match(WS);
			setState(394);
			name();
			setState(395);
			match(WS);
			setState(396);
			match(EQUALS);
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(397);
				match(WS);
				}
			}

			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924145938432L) != 0)) {
				{
				{
				setState(400);
				value();
				}
				}
				setState(405);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(406);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDroneTwoContext extends ParserRuleContext {
		public TerminalNode POINT() { return getToken(DronesParser.POINT, 0); }
		public TerminalNode VECTOR() { return getToken(DronesParser.VECTOR, 0); }
		public TerminalNode LINEARVELOCITY() { return getToken(DronesParser.LINEARVELOCITY, 0); }
		public TerminalNode ANGULARVELOCITY() { return getToken(DronesParser.ANGULARVELOCITY, 0); }
		public TypeDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitTypeDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDroneTwoContext typeDroneTwo() throws RecognitionException {
		TypeDroneTwoContext _localctx = new TypeDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeDroneTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DronesParser.ID, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<OpContext> op() {
			return getRuleContexts(OpContext.class);
		}
		public OpContext op(int i) {
			return getRuleContext(OpContext.class,i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			expression();
			setState(418);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0)) {
				{
				{
				setState(413);
				op();
				setState(414);
				expression();
				}
				}
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public CoordinatesContext coordinates() {
			return getRuleContext(CoordinatesContext.class,0);
		}
		public TerminalNode INT() { return getToken(DronesParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(DronesParser.FLOAT, 0); }
		public TerminalNode PI() { return getToken(DronesParser.PI, 0); }
		public TerminalNode ID() { return getToken(DronesParser.ID, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expression);
		try {
			setState(427);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				coordinates();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(423);
				match(INT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(424);
				match(FLOAT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(425);
				match(PI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(426);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DronesParser.LPAREN, 0); }
		public List<CoordinatesContext> coordinates() {
			return getRuleContexts(CoordinatesContext.class);
		}
		public CoordinatesContext coordinates(int i) {
			return getRuleContext(CoordinatesContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(DronesParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(LPAREN);
			setState(432);
			coordinates();
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(433);
				match(COMMA);
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(434);
					match(WS);
					}
				}

				setState(437);
				coordinates();
				}
				}
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(443);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionsDroneOneContext extends ParserRuleContext {
		public TerminalNode INSTRUCTIONS() { return getToken(DronesParser.INSTRUCTIONS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public List<InfoInstDroneOneContext> infoInstDroneOne() {
			return getRuleContexts(InfoInstDroneOneContext.class);
		}
		public InfoInstDroneOneContext infoInstDroneOne(int i) {
			return getRuleContext(InfoInstDroneOneContext.class,i);
		}
		public InstructionsDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionsDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInstructionsDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionsDroneOneContext instructionsDroneOne() throws RecognitionException {
		InstructionsDroneOneContext _localctx = new InstructionsDroneOneContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_instructionsDroneOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			match(INSTRUCTIONS);
			setState(447); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(446);
				match(NEWLINE);
				}
				}
				setState(449); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(458); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(451);
				infoInstDroneOne();
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(452);
					match(NEWLINE);
					}
					}
					setState(457);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(460); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 32640L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoInstDroneOneContext extends ParserRuleContext {
		public InstructionDroneOneContext instructionDroneOne() {
			return getRuleContext(InstructionDroneOneContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DronesParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DronesParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(DronesParser.SEMICOLON, 0); }
		public List<InfoContext> info() {
			return getRuleContexts(InfoContext.class);
		}
		public InfoContext info(int i) {
			return getRuleContext(InfoContext.class,i);
		}
		public InfoInstDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoInstDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfoInstDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoInstDroneOneContext infoInstDroneOne() throws RecognitionException {
		InfoInstDroneOneContext _localctx = new InfoInstDroneOneContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_infoInstDroneOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			instructionDroneOne();
			setState(463);
			match(LPAREN);
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924145938432L) != 0)) {
				{
				{
				setState(464);
				info();
				}
				}
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(470);
			match(RPAREN);
			setState(471);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionDroneOneContext extends ParserRuleContext {
		public TerminalNode TAKEOFF() { return getToken(DronesParser.TAKEOFF, 0); }
		public TerminalNode LAND() { return getToken(DronesParser.LAND, 0); }
		public TerminalNode MOVE() { return getToken(DronesParser.MOVE, 0); }
		public TerminalNode MOVEPATH() { return getToken(DronesParser.MOVEPATH, 0); }
		public TerminalNode HOOVER() { return getToken(DronesParser.HOOVER, 0); }
		public TerminalNode LIGHTSON() { return getToken(DronesParser.LIGHTSON, 0); }
		public TerminalNode LIGHTOFF() { return getToken(DronesParser.LIGHTOFF, 0); }
		public TerminalNode BLINK() { return getToken(DronesParser.BLINK, 0); }
		public InstructionDroneOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionDroneOne; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInstructionDroneOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionDroneOneContext instructionDroneOne() throws RecognitionException {
		InstructionDroneOneContext _localctx = new InstructionDroneOneContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_instructionDroneOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32640L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionsDroneTwoContext extends ParserRuleContext {
		public TerminalNode INSTRUCTIONS() { return getToken(DronesParser.INSTRUCTIONS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DronesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DronesParser.NEWLINE, i);
		}
		public List<InfoInstDroneTwoContext> infoInstDroneTwo() {
			return getRuleContexts(InfoInstDroneTwoContext.class);
		}
		public InfoInstDroneTwoContext infoInstDroneTwo(int i) {
			return getRuleContext(InfoInstDroneTwoContext.class,i);
		}
		public InstructionsDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionsDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInstructionsDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionsDroneTwoContext instructionsDroneTwo() throws RecognitionException {
		InstructionsDroneTwoContext _localctx = new InstructionsDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_instructionsDroneTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(INSTRUCTIONS);
			setState(477); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(476);
				match(NEWLINE);
				}
				}
				setState(479); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(488); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(481);
				infoInstDroneTwo();
				setState(485);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(482);
					match(NEWLINE);
					}
					}
					setState(487);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(490); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 49024L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoInstDroneTwoContext extends ParserRuleContext {
		public InstructionDroneTwoContext instructionDroneTwo() {
			return getRuleContext(InstructionDroneTwoContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DronesParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DronesParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(DronesParser.SEMICOLON, 0); }
		public List<InfoContext> info() {
			return getRuleContexts(InfoContext.class);
		}
		public InfoContext info(int i) {
			return getRuleContext(InfoContext.class,i);
		}
		public InfoInstDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoInstDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfoInstDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoInstDroneTwoContext infoInstDroneTwo() throws RecognitionException {
		InfoInstDroneTwoContext _localctx = new InfoInstDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_infoInstDroneTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			instructionDroneTwo();
			setState(493);
			match(LPAREN);
			setState(497);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924145938432L) != 0)) {
				{
				{
				setState(494);
				info();
				}
				}
				setState(499);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(500);
			match(RPAREN);
			setState(501);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionDroneTwoContext extends ParserRuleContext {
		public TerminalNode TAKEOFF() { return getToken(DronesParser.TAKEOFF, 0); }
		public TerminalNode LAND() { return getToken(DronesParser.LAND, 0); }
		public TerminalNode MOVE() { return getToken(DronesParser.MOVE, 0); }
		public TerminalNode MOVEPATH() { return getToken(DronesParser.MOVEPATH, 0); }
		public TerminalNode MOVECIRCLE() { return getToken(DronesParser.MOVECIRCLE, 0); }
		public TerminalNode HOOVER() { return getToken(DronesParser.HOOVER, 0); }
		public TerminalNode LIGHTSON() { return getToken(DronesParser.LIGHTSON, 0); }
		public TerminalNode LIGHTOFF() { return getToken(DronesParser.LIGHTOFF, 0); }
		public InstructionDroneTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionDroneTwo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInstructionDroneTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionDroneTwoContext instructionDroneTwo() throws RecognitionException {
		InstructionDroneTwoContext _localctx = new InstructionDroneTwoContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_instructionDroneTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 49024L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfoContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DronesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DronesParser.COMMA, i);
		}
		public List<TerminalNode> WS() { return getTokens(DronesParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DronesParser.WS, i);
		}
		public InfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_info; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DronesVisitor ) return ((DronesVisitor<? extends T>)visitor).visitInfo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoContext info() throws RecognitionException {
		InfoContext _localctx = new InfoContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_info);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			expression();
			setState(513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(506);
				match(COMMA);
				setState(508);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(507);
					match(WS);
					}
				}

				setState(510);
				expression();
				}
				}
				setState(515);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001+\u0205\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001K\b"+
		"\u0001\u000b\u0001\f\u0001L\u0001\u0001\u0001\u0001\u0004\u0001Q\b\u0001"+
		"\u000b\u0001\f\u0001R\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0004\u0001[\b\u0001\u000b\u0001\f\u0001\\\u0001"+
		"\u0001\u0001\u0001\u0004\u0001a\b\u0001\u000b\u0001\f\u0001b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001h\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0004\u0004"+
		"w\b\u0004\u000b\u0004\f\u0004x\u0001\u0004\u0001\u0004\u0004\u0004}\b"+
		"\u0004\u000b\u0004\f\u0004~\u0003\u0004\u0081\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0004\u0004\u0085\b\u0004\u000b\u0004\f\u0004\u0086\u0003\u0004"+
		"\u0089\b\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u008d\b\u0004\u000b"+
		"\u0004\f\u0004\u008e\u0003\u0004\u0091\b\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004\u0095\b\u0004\u000b\u0004\f\u0004\u0096\u0003\u0004\u0099"+
		"\b\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u009d\b\u0004\u000b\u0004"+
		"\f\u0004\u009e\u0003\u0004\u00a1\b\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004\u00a5\b\u0004\u000b\u0004\f\u0004\u00a6\u0003\u0004\u00a9\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0004\u0005\u00ad\b\u0005\u000b\u0005\f\u0005"+
		"\u00ae\u0001\u0005\u0001\u0005\u0004\u0005\u00b3\b\u0005\u000b\u0005\f"+
		"\u0005\u00b4\u0003\u0005\u00b7\b\u0005\u0001\u0005\u0001\u0005\u0004\u0005"+
		"\u00bb\b\u0005\u000b\u0005\f\u0005\u00bc\u0003\u0005\u00bf\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0004\u0005\u00c3\b\u0005\u000b\u0005\f\u0005\u00c4"+
		"\u0003\u0005\u00c7\b\u0005\u0001\u0005\u0001\u0005\u0004\u0005\u00cb\b"+
		"\u0005\u000b\u0005\f\u0005\u00cc\u0003\u0005\u00cf\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0004\u0005\u00d3\b\u0005\u000b\u0005\f\u0005\u00d4\u0003"+
		"\u0005\u00d7\b\u0005\u0001\u0005\u0001\u0005\u0004\u0005\u00db\b\u0005"+
		"\u000b\u0005\f\u0005\u00dc\u0003\u0005\u00df\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0004\u0007\u00ea\b\u0007\u000b\u0007\f\u0007\u00eb"+
		"\u0001\u0007\u0001\u0007\u0004\u0007\u00f0\b\u0007\u000b\u0007\f\u0007"+
		"\u00f1\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0004\n\u0108\b\n\u000b\n\f\n\u0109"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0004\u000b\u0114\b\u000b\u000b\u000b\f\u000b\u0115"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0004\f\u0120\b\f\u000b\f\f\f\u0121\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0004\r\u012c\b\r\u000b\r\f\r\u012d\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u0136\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u013a\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u013f\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u0143\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0147"+
		"\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0003\u000f\u014c\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u0150\b\u000f\u0001\u000f\u0003\u000f"+
		"\u0153\b\u000f\u0001\u0010\u0001\u0010\u0004\u0010\u0157\b\u0010\u000b"+
		"\u0010\f\u0010\u0158\u0001\u0010\u0001\u0010\u0004\u0010\u015d\b\u0010"+
		"\u000b\u0010\f\u0010\u015e\u0005\u0010\u0161\b\u0010\n\u0010\f\u0010\u0164"+
		"\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u016c\b\u0011\u0001\u0011\u0005\u0011\u016f\b\u0011"+
		"\n\u0011\f\u0011\u0172\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0004\u0013\u017a\b\u0013\u000b\u0013\f"+
		"\u0013\u017b\u0001\u0013\u0001\u0013\u0004\u0013\u0180\b\u0013\u000b\u0013"+
		"\f\u0013\u0181\u0005\u0013\u0184\b\u0013\n\u0013\f\u0013\u0187\t\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u018f\b\u0014\u0001\u0014\u0005\u0014\u0192\b\u0014\n\u0014"+
		"\f\u0014\u0195\t\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0005\u0017\u01a1\b\u0017\n\u0017\f\u0017\u01a4\t\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01ac"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u01b4\b\u001a\u0001\u001a\u0005\u001a\u01b7\b\u001a"+
		"\n\u001a\f\u001a\u01ba\t\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0004\u001b\u01c0\b\u001b\u000b\u001b\f\u001b\u01c1\u0001\u001b"+
		"\u0001\u001b\u0005\u001b\u01c6\b\u001b\n\u001b\f\u001b\u01c9\t\u001b\u0004"+
		"\u001b\u01cb\b\u001b\u000b\u001b\f\u001b\u01cc\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u01d2\b\u001c\n\u001c\f\u001c\u01d5\t\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0004\u001e\u01de\b\u001e\u000b\u001e\f\u001e\u01df\u0001\u001e"+
		"\u0001\u001e\u0005\u001e\u01e4\b\u001e\n\u001e\f\u001e\u01e7\t\u001e\u0004"+
		"\u001e\u01e9\b\u001e\u000b\u001e\f\u001e\u01ea\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0005\u001f\u01f0\b\u001f\n\u001f\f\u001f\u01f3\t\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0003"+
		"!\u01fd\b!\u0001!\u0005!\u0200\b!\n!\f!\u0203\t!\u0001!\u0000\u0000\""+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@B\u0000\u0006\u0003\u0000\u0015\u0015(("+
		"**\u0002\u0000\u001c\u001c\u001e \u0001\u0000\u001d \u0001\u0000\u0001"+
		"\u0004\u0001\u0000\u0007\u000e\u0002\u0000\u0007\r\u000f\u000f\u022b\u0000"+
		"D\u0001\u0000\u0000\u0000\u0002g\u0001\u0000\u0000\u0000\u0004i\u0001"+
		"\u0000\u0000\u0000\u0006r\u0001\u0000\u0000\u0000\bt\u0001\u0000\u0000"+
		"\u0000\n\u00aa\u0001\u0000\u0000\u0000\f\u00e0\u0001\u0000\u0000\u0000"+
		"\u000e\u00e6\u0001\u0000\u0000\u0000\u0010\u00f5\u0001\u0000\u0000\u0000"+
		"\u0012\u00fb\u0001\u0000\u0000\u0000\u0014\u0101\u0001\u0000\u0000\u0000"+
		"\u0016\u010d\u0001\u0000\u0000\u0000\u0018\u0119\u0001\u0000\u0000\u0000"+
		"\u001a\u0125\u0001\u0000\u0000\u0000\u001c\u0131\u0001\u0000\u0000\u0000"+
		"\u001e\u0152\u0001\u0000\u0000\u0000 \u0154\u0001\u0000\u0000\u0000\""+
		"\u0165\u0001\u0000\u0000\u0000$\u0175\u0001\u0000\u0000\u0000&\u0177\u0001"+
		"\u0000\u0000\u0000(\u0188\u0001\u0000\u0000\u0000*\u0198\u0001\u0000\u0000"+
		"\u0000,\u019a\u0001\u0000\u0000\u0000.\u019c\u0001\u0000\u0000\u00000"+
		"\u01ab\u0001\u0000\u0000\u00002\u01ad\u0001\u0000\u0000\u00004\u01af\u0001"+
		"\u0000\u0000\u00006\u01bd\u0001\u0000\u0000\u00008\u01ce\u0001\u0000\u0000"+
		"\u0000:\u01d9\u0001\u0000\u0000\u0000<\u01db\u0001\u0000\u0000\u0000>"+
		"\u01ec\u0001\u0000\u0000\u0000@\u01f7\u0001\u0000\u0000\u0000B\u01f9\u0001"+
		"\u0000\u0000\u0000DE\u0003\u0002\u0001\u0000EF\u0005\u0000\u0000\u0001"+
		"F\u0001\u0001\u0000\u0000\u0000GH\u0005\u0016\u0000\u0000HJ\u0003\u0004"+
		"\u0002\u0000IK\u0005)\u0000\u0000JI\u0001\u0000\u0000\u0000KL\u0001\u0000"+
		"\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NP\u0003\b\u0004\u0000OQ\u0005)\u0000\u0000PO\u0001"+
		"\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0003 \u0010\u0000"+
		"UV\u00036\u001b\u0000Vh\u0001\u0000\u0000\u0000WX\u0005\u0017\u0000\u0000"+
		"XZ\u0003\u0004\u0002\u0000Y[\u0005)\u0000\u0000ZY\u0001\u0000\u0000\u0000"+
		"[\\\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]^\u0001\u0000\u0000\u0000^`\u0003\n\u0005\u0000_a\u0005)"+
		"\u0000\u0000`_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b`\u0001"+
		"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000"+
		"de\u0003&\u0013\u0000ef\u0003<\u001e\u0000fh\u0001\u0000\u0000\u0000g"+
		"G\u0001\u0000\u0000\u0000gW\u0001\u0000\u0000\u0000h\u0003\u0001\u0000"+
		"\u0000\u0000ij\u0005*\u0000\u0000jk\u0005\u0018\u0000\u0000kl\u0005*\u0000"+
		"\u0000lm\u0005\u0019\u0000\u0000mn\u0005*\u0000\u0000no\u0005\u001a\u0000"+
		"\u0000op\u0005*\u0000\u0000pq\u0003\u0006\u0003\u0000q\u0005\u0001\u0000"+
		"\u0000\u0000rs\u0005\'\u0000\u0000s\u0007\u0001\u0000\u0000\u0000tv\u0005"+
		"\u001b\u0000\u0000uw\u0005)\u0000\u0000vu\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000"+
		"y\u0080\u0001\u0000\u0000\u0000z|\u0003\f\u0006\u0000{}\u0005)\u0000\u0000"+
		"|{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000"+
		"\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0081\u0001\u0000\u0000\u0000"+
		"\u0080z\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081"+
		"\u0088\u0001\u0000\u0000\u0000\u0082\u0084\u0003\u0012\t\u0000\u0083\u0085"+
		"\u0005)\u0000\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001"+
		"\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001"+
		"\u0000\u0000\u0000\u0087\u0089\u0001\u0000\u0000\u0000\u0088\u0082\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u0090\u0001"+
		"\u0000\u0000\u0000\u008a\u008c\u0003\u0014\n\u0000\u008b\u008d\u0005)"+
		"\u0000\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000"+
		"\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000"+
		"\u0000\u0000\u008f\u0091\u0001\u0000\u0000\u0000\u0090\u008a\u0001\u0000"+
		"\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0098\u0001\u0000"+
		"\u0000\u0000\u0092\u0094\u0003\u0016\u000b\u0000\u0093\u0095\u0005)\u0000"+
		"\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000"+
		"\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000"+
		"\u0000\u0097\u0099\u0001\u0000\u0000\u0000\u0098\u0092\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u00a0\u0001\u0000\u0000"+
		"\u0000\u009a\u009c\u0003\u0018\f\u0000\u009b\u009d\u0005)\u0000\u0000"+
		"\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000"+
		"\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000"+
		"\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009a\u0001\u0000\u0000\u0000"+
		"\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a8\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a4\u0003\u001a\r\u0000\u00a3\u00a5\u0005)\u0000\u0000\u00a4"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a8\u00a2\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a9\t\u0001\u0000\u0000\u0000\u00aa\u00ac"+
		"\u0005\u001b\u0000\u0000\u00ab\u00ad\u0005)\u0000\u0000\u00ac\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b0\u00b2\u0003\u0010\b\u0000\u00b1\u00b3\u0005)"+
		"\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b7\u0001\u0000\u0000\u0000\u00b6\u00b0\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00be\u0001\u0000"+
		"\u0000\u0000\u00b8\u00ba\u0003\u0012\t\u0000\u00b9\u00bb\u0005)\u0000"+
		"\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bd\u00bf\u0001\u0000\u0000\u0000\u00be\u00b8\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c2\u0003\u0014\n\u0000\u00c1\u00c3\u0005)\u0000\u0000"+
		"\u00c2\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c7\u0001\u0000\u0000\u0000\u00c6\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00ce\u0001\u0000\u0000\u0000"+
		"\u00c8\u00ca\u0003\u0016\u000b\u0000\u00c9\u00cb\u0005)\u0000\u0000\u00ca"+
		"\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd"+
		"\u00cf\u0001\u0000\u0000\u0000\u00ce\u00c8\u0001\u0000\u0000\u0000\u00ce"+
		"\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d6\u0001\u0000\u0000\u0000\u00d0"+
		"\u00d2\u0003\u0018\f\u0000\u00d1\u00d3\u0005)\u0000\u0000\u00d2\u00d1"+
		"\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d2"+
		"\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d7"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d0\u0001\u0000\u0000\u0000\u00d6\u00d7"+
		"\u0001\u0000\u0000\u0000\u00d7\u00de\u0001\u0000\u0000\u0000\u00d8\u00da"+
		"\u0003\u001a\r\u0000\u00d9\u00db\u0005)\u0000\u0000\u00da\u00d9\u0001"+
		"\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00da\u0001"+
		"\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u00df\u0001"+
		"\u0000\u0000\u0000\u00de\u00d8\u0001\u0000\u0000\u0000\u00de\u00df\u0001"+
		"\u0000\u0000\u0000\u00df\u000b\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005"+
		"\u001c\u0000\u0000\u00e1\u00e2\u0005*\u0000\u0000\u00e2\u00e3\u0005\u0001"+
		"\u0000\u0000\u00e3\u00e4\u0005*\u0000\u0000\u00e4\u00e5\u0003\u000e\u0007"+
		"\u0000\u00e5\r\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005\u0005\u0000\u0000"+
		"\u00e7\u00e9\u0005\u0015\u0000\u0000\u00e8\u00ea\u0007\u0000\u0000\u0000"+
		"\u00e9\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000"+
		"\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ef\u0005\u0006\u0000\u0000"+
		"\u00ee\u00f0\u0007\u0000\u0000\u0000\u00ef\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000"+
		"\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f4\u0005%\u0000\u0000\u00f4\u000f\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f6\u0005\u001d\u0000\u0000\u00f6\u00f7\u0005*\u0000\u0000\u00f7\u00f8"+
		"\u0005\u0001\u0000\u0000\u00f8\u00f9\u0005*\u0000\u0000\u00f9\u00fa\u0003"+
		"\u000e\u0007\u0000\u00fa\u0011\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005"+
		"\u001e\u0000\u0000\u00fc\u00fd\u0005*\u0000\u0000\u00fd\u00fe\u0005\u0001"+
		"\u0000\u0000\u00fe\u00ff\u0005*\u0000\u0000\u00ff\u0100\u0003\u000e\u0007"+
		"\u0000\u0100\u0013\u0001\u0000\u0000\u0000\u0101\u0102\u0005\u001f\u0000"+
		"\u0000\u0102\u0103\u0005*\u0000\u0000\u0103\u0104\u0005\u0001\u0000\u0000"+
		"\u0104\u0105\u0005*\u0000\u0000\u0105\u0107\u0005\u0006\u0000\u0000\u0106"+
		"\u0108\u0007\u0000\u0000\u0000\u0107\u0106\u0001\u0000\u0000\u0000\u0108"+
		"\u0109\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u0109"+
		"\u010a\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0005%\u0000\u0000\u010c\u0015\u0001\u0000\u0000\u0000\u010d\u010e"+
		"\u0005 \u0000\u0000\u010e\u010f\u0005*\u0000\u0000\u010f\u0110\u0005\u0001"+
		"\u0000\u0000\u0110\u0111\u0005*\u0000\u0000\u0111\u0113\u0005\u0006\u0000"+
		"\u0000\u0112\u0114\u0007\u0000\u0000\u0000\u0113\u0112\u0001\u0000\u0000"+
		"\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000"+
		"\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000"+
		"\u0000\u0117\u0118\u0005%\u0000\u0000\u0118\u0017\u0001\u0000\u0000\u0000"+
		"\u0119\u011a\u0005!\u0000\u0000\u011a\u011b\u0005*\u0000\u0000\u011b\u011c"+
		"\u0005\u0001\u0000\u0000\u011c\u011d\u0005*\u0000\u0000\u011d\u011f\u0005"+
		"\u0006\u0000\u0000\u011e\u0120\u0007\u0000\u0000\u0000\u011f\u011e\u0001"+
		"\u0000\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u011f\u0001"+
		"\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0123\u0001"+
		"\u0000\u0000\u0000\u0123\u0124\u0005%\u0000\u0000\u0124\u0019\u0001\u0000"+
		"\u0000\u0000\u0125\u0126\u0005\"\u0000\u0000\u0126\u0127\u0005*\u0000"+
		"\u0000\u0127\u0128\u0005\u0001\u0000\u0000\u0128\u0129\u0005*\u0000\u0000"+
		"\u0129\u012b\u0005\u0006\u0000\u0000\u012a\u012c\u0007\u0000\u0000\u0000"+
		"\u012b\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000"+
		"\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000"+
		"\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0130\u0005%\u0000\u0000\u0130"+
		"\u001b\u0001\u0000\u0000\u0000\u0131\u0132\u0005\u0013\u0000\u0000\u0132"+
		"\u0135\u0003\u001e\u000f\u0000\u0133\u0134\u0005*\u0000\u0000\u0134\u0136"+
		"\u0005%\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001"+
		"\u0000\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000\u0137\u0139\u0005"+
		"\u0015\u0000\u0000\u0138\u013a\u0005*\u0000\u0000\u0139\u0138\u0001\u0000"+
		"\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000"+
		"\u0000\u0000\u013b\u013e\u0003\u001e\u000f\u0000\u013c\u013d\u0005*\u0000"+
		"\u0000\u013d\u013f\u0005%\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000"+
		"\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000"+
		"\u0140\u0142\u0005\u0015\u0000\u0000\u0141\u0143\u0005*\u0000\u0000\u0142"+
		"\u0141\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143"+
		"\u0144\u0001\u0000\u0000\u0000\u0144\u0146\u0003\u001e\u000f\u0000\u0145"+
		"\u0147\u0005%\u0000\u0000\u0146\u0145\u0001\u0000\u0000\u0000\u0146\u0147"+
		"\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000\u0000\u0148\u0149"+
		"\u0005\u0014\u0000\u0000\u0149\u001d\u0001\u0000\u0000\u0000\u014a\u014c"+
		"\u0005\u0001\u0000\u0000\u014b\u014a\u0001\u0000\u0000\u0000\u014b\u014c"+
		"\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u0153"+
		"\u0005&\u0000\u0000\u014e\u0150\u0005\u0001\u0000\u0000\u014f\u014e\u0001"+
		"\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0151\u0001"+
		"\u0000\u0000\u0000\u0151\u0153\u0005\'\u0000\u0000\u0152\u014b\u0001\u0000"+
		"\u0000\u0000\u0152\u014f\u0001\u0000\u0000\u0000\u0153\u001f\u0001\u0000"+
		"\u0000\u0000\u0154\u0156\u0005#\u0000\u0000\u0155\u0157\u0005)\u0000\u0000"+
		"\u0156\u0155\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000"+
		"\u0158\u0156\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000"+
		"\u0159\u0162\u0001\u0000\u0000\u0000\u015a\u015c\u0003\"\u0011\u0000\u015b"+
		"\u015d\u0005)\u0000\u0000\u015c\u015b\u0001\u0000\u0000\u0000\u015d\u015e"+
		"\u0001\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000\u015e\u015f"+
		"\u0001\u0000\u0000\u0000\u015f\u0161\u0001\u0000\u0000\u0000\u0160\u015a"+
		"\u0001\u0000\u0000\u0000\u0161\u0164\u0001\u0000\u0000\u0000\u0162\u0160"+
		"\u0001\u0000\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163!\u0001"+
		"\u0000\u0000\u0000\u0164\u0162\u0001\u0000\u0000\u0000\u0165\u0166\u0003"+
		"$\u0012\u0000\u0166\u0167\u0005*\u0000\u0000\u0167\u0168\u0003,\u0016"+
		"\u0000\u0168\u0169\u0005*\u0000\u0000\u0169\u016b\u0005\u0011\u0000\u0000"+
		"\u016a\u016c\u0005*\u0000\u0000\u016b\u016a\u0001\u0000\u0000\u0000\u016b"+
		"\u016c\u0001\u0000\u0000\u0000\u016c\u0170\u0001\u0000\u0000\u0000\u016d"+
		"\u016f\u0003.\u0017\u0000\u016e\u016d\u0001\u0000\u0000\u0000\u016f\u0172"+
		"\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0170\u0171"+
		"\u0001\u0000\u0000\u0000\u0171\u0173\u0001\u0000\u0000\u0000\u0172\u0170"+
		"\u0001\u0000\u0000\u0000\u0173\u0174\u0005\u0012\u0000\u0000\u0174#\u0001"+
		"\u0000\u0000\u0000\u0175\u0176\u0007\u0001\u0000\u0000\u0176%\u0001\u0000"+
		"\u0000\u0000\u0177\u0179\u0005#\u0000\u0000\u0178\u017a\u0005)\u0000\u0000"+
		"\u0179\u0178\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000"+
		"\u017b\u0179\u0001\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000"+
		"\u017c\u0185\u0001\u0000\u0000\u0000\u017d\u017f\u0003(\u0014\u0000\u017e"+
		"\u0180\u0005)\u0000\u0000\u017f\u017e\u0001\u0000\u0000\u0000\u0180\u0181"+
		"\u0001\u0000\u0000\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0181\u0182"+
		"\u0001\u0000\u0000\u0000\u0182\u0184\u0001\u0000\u0000\u0000\u0183\u017d"+
		"\u0001\u0000\u0000\u0000\u0184\u0187\u0001\u0000\u0000\u0000\u0185\u0183"+
		"\u0001\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\'\u0001"+
		"\u0000\u0000\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188\u0189\u0003"+
		"*\u0015\u0000\u0189\u018a\u0005*\u0000\u0000\u018a\u018b\u0003,\u0016"+
		"\u0000\u018b\u018c\u0005*\u0000\u0000\u018c\u018e\u0005\u0011\u0000\u0000"+
		"\u018d\u018f\u0005*\u0000\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018e"+
		"\u018f\u0001\u0000\u0000\u0000\u018f\u0193\u0001\u0000\u0000\u0000\u0190"+
		"\u0192\u0003.\u0017\u0000\u0191\u0190\u0001\u0000\u0000\u0000\u0192\u0195"+
		"\u0001\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193\u0194"+
		"\u0001\u0000\u0000\u0000\u0194\u0196\u0001\u0000\u0000\u0000\u0195\u0193"+
		"\u0001\u0000\u0000\u0000\u0196\u0197\u0005\u0012\u0000\u0000\u0197)\u0001"+
		"\u0000\u0000\u0000\u0198\u0199\u0007\u0002\u0000\u0000\u0199+\u0001\u0000"+
		"\u0000\u0000\u019a\u019b\u0005(\u0000\u0000\u019b-\u0001\u0000\u0000\u0000"+
		"\u019c\u01a2\u00030\u0018\u0000\u019d\u019e\u00032\u0019\u0000\u019e\u019f"+
		"\u00030\u0018\u0000\u019f\u01a1\u0001\u0000\u0000\u0000\u01a0\u019d\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a4\u0001\u0000\u0000\u0000\u01a2\u01a0\u0001"+
		"\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000\u0000\u01a3/\u0001\u0000"+
		"\u0000\u0000\u01a4\u01a2\u0001\u0000\u0000\u0000\u01a5\u01ac\u00034\u001a"+
		"\u0000\u01a6\u01ac\u0003\u001c\u000e\u0000\u01a7\u01ac\u0005&\u0000\u0000"+
		"\u01a8\u01ac\u0005\'\u0000\u0000\u01a9\u01ac\u0005\u0010\u0000\u0000\u01aa"+
		"\u01ac\u0005(\u0000\u0000\u01ab\u01a5\u0001\u0000\u0000\u0000\u01ab\u01a6"+
		"\u0001\u0000\u0000\u0000\u01ab\u01a7\u0001\u0000\u0000\u0000\u01ab\u01a8"+
		"\u0001\u0000\u0000\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000\u01ab\u01aa"+
		"\u0001\u0000\u0000\u0000\u01ac1\u0001\u0000\u0000\u0000\u01ad\u01ae\u0007"+
		"\u0003\u0000\u0000\u01ae3\u0001\u0000\u0000\u0000\u01af\u01b0\u0005\u0013"+
		"\u0000\u0000\u01b0\u01b8\u0003\u001c\u000e\u0000\u01b1\u01b3\u0005\u0015"+
		"\u0000\u0000\u01b2\u01b4\u0005*\u0000\u0000\u01b3\u01b2\u0001\u0000\u0000"+
		"\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000\u01b4\u01b5\u0001\u0000\u0000"+
		"\u0000\u01b5\u01b7\u0003\u001c\u000e\u0000\u01b6\u01b1\u0001\u0000\u0000"+
		"\u0000\u01b7\u01ba\u0001\u0000\u0000\u0000\u01b8\u01b6\u0001\u0000\u0000"+
		"\u0000\u01b8\u01b9\u0001\u0000\u0000\u0000\u01b9\u01bb\u0001\u0000\u0000"+
		"\u0000\u01ba\u01b8\u0001\u0000\u0000\u0000\u01bb\u01bc\u0005\u0014\u0000"+
		"\u0000\u01bc5\u0001\u0000\u0000\u0000\u01bd\u01bf\u0005$\u0000\u0000\u01be"+
		"\u01c0\u0005)\u0000\u0000\u01bf\u01be\u0001\u0000\u0000\u0000\u01c0\u01c1"+
		"\u0001\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2"+
		"\u0001\u0000\u0000\u0000\u01c2\u01ca\u0001\u0000\u0000\u0000\u01c3\u01c7"+
		"\u00038\u001c\u0000\u01c4\u01c6\u0005)\u0000\u0000\u01c5\u01c4\u0001\u0000"+
		"\u0000\u0000\u01c6\u01c9\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001\u0000"+
		"\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01cb\u0001\u0000"+
		"\u0000\u0000\u01c9\u01c7\u0001\u0000\u0000\u0000\u01ca\u01c3\u0001\u0000"+
		"\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01ca\u0001\u0000"+
		"\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd7\u0001\u0000\u0000"+
		"\u0000\u01ce\u01cf\u0003:\u001d\u0000\u01cf\u01d3\u0005\u0013\u0000\u0000"+
		"\u01d0\u01d2\u0003B!\u0000\u01d1\u01d0\u0001\u0000\u0000\u0000\u01d2\u01d5"+
		"\u0001\u0000\u0000\u0000\u01d3\u01d1\u0001\u0000\u0000\u0000\u01d3\u01d4"+
		"\u0001\u0000\u0000\u0000\u01d4\u01d6\u0001\u0000\u0000\u0000\u01d5\u01d3"+
		"\u0001\u0000\u0000\u0000\u01d6\u01d7\u0005\u0014\u0000\u0000\u01d7\u01d8"+
		"\u0005\u0012\u0000\u0000\u01d89\u0001\u0000\u0000\u0000\u01d9\u01da\u0007"+
		"\u0004\u0000\u0000\u01da;\u0001\u0000\u0000\u0000\u01db\u01dd\u0005$\u0000"+
		"\u0000\u01dc\u01de\u0005)\u0000\u0000\u01dd\u01dc\u0001\u0000\u0000\u0000"+
		"\u01de\u01df\u0001\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000"+
		"\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0\u01e8\u0001\u0000\u0000\u0000"+
		"\u01e1\u01e5\u0003>\u001f\u0000\u01e2\u01e4\u0005)\u0000\u0000\u01e3\u01e2"+
		"\u0001\u0000\u0000\u0000\u01e4\u01e7\u0001\u0000\u0000\u0000\u01e5\u01e3"+
		"\u0001\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000\u01e6\u01e9"+
		"\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001\u0000\u0000\u0000\u01e8\u01e1"+
		"\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000\u01ea\u01e8"+
		"\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb=\u0001"+
		"\u0000\u0000\u0000\u01ec\u01ed\u0003@ \u0000\u01ed\u01f1\u0005\u0013\u0000"+
		"\u0000\u01ee\u01f0\u0003B!\u0000\u01ef\u01ee\u0001\u0000\u0000\u0000\u01f0"+
		"\u01f3\u0001\u0000\u0000\u0000\u01f1\u01ef\u0001\u0000\u0000\u0000\u01f1"+
		"\u01f2\u0001\u0000\u0000\u0000\u01f2\u01f4\u0001\u0000\u0000\u0000\u01f3"+
		"\u01f1\u0001\u0000\u0000\u0000\u01f4\u01f5\u0005\u0014\u0000\u0000\u01f5"+
		"\u01f6\u0005\u0012\u0000\u0000\u01f6?\u0001\u0000\u0000\u0000\u01f7\u01f8"+
		"\u0007\u0005\u0000\u0000\u01f8A\u0001\u0000\u0000\u0000\u01f9\u0201\u0003"+
		"0\u0018\u0000\u01fa\u01fc\u0005\u0015\u0000\u0000\u01fb\u01fd\u0005*\u0000"+
		"\u0000\u01fc\u01fb\u0001\u0000\u0000\u0000\u01fc\u01fd\u0001\u0000\u0000"+
		"\u0000\u01fd\u01fe\u0001\u0000\u0000\u0000\u01fe\u0200\u00030\u0018\u0000"+
		"\u01ff\u01fa\u0001\u0000\u0000\u0000\u0200\u0203\u0001\u0000\u0000\u0000"+
		"\u0201\u01ff\u0001\u0000\u0000\u0000\u0201\u0202\u0001\u0000\u0000\u0000"+
		"\u0202C\u0001\u0000\u0000\u0000\u0203\u0201\u0001\u0000\u0000\u0000EL"+
		"R\\bgx~\u0080\u0086\u0088\u008e\u0090\u0096\u0098\u009e\u00a0\u00a6\u00a8"+
		"\u00ae\u00b4\u00b6\u00bc\u00be\u00c4\u00c6\u00cc\u00ce\u00d4\u00d6\u00dc"+
		"\u00de\u00eb\u00f1\u0109\u0115\u0121\u012d\u0135\u0139\u013e\u0142\u0146"+
		"\u014b\u014f\u0152\u0158\u015e\u0162\u016b\u0170\u017b\u0181\u0185\u018e"+
		"\u0193\u01a2\u01ab\u01b3\u01b8\u01c1\u01c7\u01cc\u01d3\u01df\u01e5\u01ea"+
		"\u01f1\u01fc\u0201";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}