package eapli.shodrone.integrations.plugins.dsl.figure;// Generated from C:/Users/maria/Progama�ao/Teste_Branch/base.integrations.plugins.dsl/lprog/us251/figure.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class figureParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DSL=1, VERSION=2, SEMICOLON=3, DRONETYPE=4, POSITION=5, VELOCITY=6, EQUAL=7, 
		LPAREN=8, RPAREN=9, PI=10, PLUS=11, MINUS=12, MULTIPLICATION=13, DIVISION=14, 
		DISTANCE=15, LINE=16, RECTANGLE=17, CIRCLE=18, CIRCUNFERENCE=19, LIGHTSON=20, 
		MOVE=21, ROTATE=22, LIGHTSOFF=23, MOVEPOS=24, YELLOW=25, RED=26, GREEN=27, 
		GROUP=28, ENDGROUP=29, PAUSE=30, BEFORE=31, ENDBEFORE=32, AFTER=33, ENDAFTER=34, 
		POINT=35, COMMA=36, ID=37, DIGIT=38, NEWLINE=39, WS=40, UNKNOWN=41;
	public static final int
		RULE_start = 0, RULE_versionDecl = 1, RULE_versionNumber = 2, RULE_droneTypeDecl = 3, 
		RULE_drone = 4, RULE_id = 5, RULE_statement = 6, RULE_positionDecl = 7, 
		RULE_velocityDecl = 8, RULE_expression = 9, RULE_term = 10, RULE_distanceDecl = 11, 
		RULE_figureDecl = 12, RULE_actionStmt = 13, RULE_color = 14, RULE_coordinate = 15, 
		RULE_groupStmt = 16, RULE_pauseStmt = 17, RULE_beforeStmt = 18, RULE_afterStmt = 19, 
		RULE_signal = 20, RULE_operation = 21, RULE_number = 22, RULE_float = 23, 
		RULE_int = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "versionDecl", "versionNumber", "droneTypeDecl", "drone", "id", 
			"statement", "positionDecl", "velocityDecl", "expression", "term", "distanceDecl", 
			"figureDecl", "actionStmt", "color", "coordinate", "groupStmt", "pauseStmt", 
			"beforeStmt", "afterStmt", "signal", "operation", "number", "float", 
			"int"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'DSL'", "'version'", "';'", "'DroneType'", "'Position'", "'Velocity'", 
			"'='", "'('", "')'", "'PI'", "'+'", "'-'", "'*'", "'/'", "'Distance'", 
			"'Line'", "'Rectangle'", "'Circle'", "'Circumference'", "'lightsOn'", 
			"'move'", "'rotate'", "'lightsOff'", "'movePos'", "'YELLOW'", "'RED'", 
			"'GREEN'", "'group'", "'endgroup'", "'pause'", "'before'", "'endbefore'", 
			"'after'", "'endafter'", "'.'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DSL", "VERSION", "SEMICOLON", "DRONETYPE", "POSITION", "VELOCITY", 
			"EQUAL", "LPAREN", "RPAREN", "PI", "PLUS", "MINUS", "MULTIPLICATION", 
			"DIVISION", "DISTANCE", "LINE", "RECTANGLE", "CIRCLE", "CIRCUNFERENCE", 
			"LIGHTSON", "MOVE", "ROTATE", "LIGHTSOFF", "MOVEPOS", "YELLOW", "RED", 
			"GREEN", "GROUP", "ENDGROUP", "PAUSE", "BEFORE", "ENDBEFORE", "AFTER", 
			"ENDAFTER", "POINT", "COMMA", "ID", "DIGIT", "NEWLINE", "WS", "UNKNOWN"
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
	public String getGrammarFileName() { return "figure.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public figureParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public VersionDeclContext versionDecl() {
			return getRuleContext(VersionDeclContext.class,0);
		}
		public DroneTypeDeclContext droneTypeDecl() {
			return getRuleContext(DroneTypeDeclContext.class,0);
		}
		public TerminalNode EOF() { return getToken(figureParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(figureParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(figureParser.NEWLINE, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor) return ((figureVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			versionDecl();
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(51);
				match(NEWLINE);
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(56);
			droneTypeDecl();
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(57);
				match(NEWLINE);
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1249031192672L) != 0)) {
				{
				{
				setState(62);
				statement();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
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
	public static class VersionDeclContext extends ParserRuleContext {
		public TerminalNode DSL() { return getToken(figureParser.DSL, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public TerminalNode VERSION() { return getToken(figureParser.VERSION, 0); }
		public VersionNumberContext versionNumber() {
			return getRuleContext(VersionNumberContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public VersionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitVersionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionDeclContext versionDecl() throws RecognitionException {
		VersionDeclContext _localctx = new VersionDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_versionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(DSL);
			setState(71);
			match(WS);
			setState(72);
			match(VERSION);
			setState(73);
			match(WS);
			setState(74);
			versionNumber();
			setState(75);
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
	public static class VersionNumberContext extends ParserRuleContext {
		public List<IntContext> int_() {
			return getRuleContexts(IntContext.class);
		}
		public IntContext int_(int i) {
			return getRuleContext(IntContext.class,i);
		}
		public List<TerminalNode> POINT() { return getTokens(figureParser.POINT); }
		public TerminalNode POINT(int i) {
			return getToken(figureParser.POINT, i);
		}
		public VersionNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitVersionNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionNumberContext versionNumber() throws RecognitionException {
		VersionNumberContext _localctx = new VersionNumberContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_versionNumber);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			int_();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POINT) {
				{
				setState(78);
				match(POINT);
				setState(79);
				int_();
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==POINT) {
					{
					setState(80);
					match(POINT);
					setState(81);
					int_();
					}
				}

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
	public static class DroneTypeDeclContext extends ParserRuleContext {
		public DroneContext drone() {
			return getRuleContext(DroneContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public DroneTypeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_droneTypeDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitDroneTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DroneTypeDeclContext droneTypeDecl() throws RecognitionException {
		DroneTypeDeclContext _localctx = new DroneTypeDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_droneTypeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			drone();
			setState(87);
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
	public static class DroneContext extends ParserRuleContext {
		public TerminalNode DRONETYPE() { return getToken(figureParser.DRONETYPE, 0); }
		public TerminalNode WS() { return getToken(figureParser.WS, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DroneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drone; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitDrone(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DroneContext drone() throws RecognitionException {
		DroneContext _localctx = new DroneContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_drone);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(DRONETYPE);
			setState(90);
			match(WS);
			setState(91);
			id();
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
	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(figureParser.ID, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
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
	public static class StatementContext extends ParserRuleContext {
		public PositionDeclContext positionDecl() {
			return getRuleContext(PositionDeclContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(figureParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(figureParser.NEWLINE, i);
		}
		public VelocityDeclContext velocityDecl() {
			return getRuleContext(VelocityDeclContext.class,0);
		}
		public DistanceDeclContext distanceDecl() {
			return getRuleContext(DistanceDeclContext.class,0);
		}
		public FigureDeclContext figureDecl() {
			return getRuleContext(FigureDeclContext.class,0);
		}
		public ActionStmtContext actionStmt() {
			return getRuleContext(ActionStmtContext.class,0);
		}
		public GroupStmtContext groupStmt() {
			return getRuleContext(GroupStmtContext.class,0);
		}
		public PauseStmtContext pauseStmt() {
			return getRuleContext(PauseStmtContext.class,0);
		}
		public BeforeStmtContext beforeStmt() {
			return getRuleContext(BeforeStmtContext.class,0);
		}
		public AfterStmtContext afterStmt() {
			return getRuleContext(AfterStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				positionDecl();
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(96);
					match(NEWLINE);
					}
					}
					setState(99); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				velocityDecl();
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(102);
					match(NEWLINE);
					}
					}
					setState(105); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				distanceDecl();
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(108);
					match(NEWLINE);
					}
					}
					setState(111); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				figureDecl();
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(114);
					match(NEWLINE);
					}
					}
					setState(117); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(119);
				actionStmt();
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					match(NEWLINE);
					}
					}
					setState(123); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(125);
				groupStmt();
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(126);
					match(NEWLINE);
					}
					}
					setState(129); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(131);
				pauseStmt();
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(132);
					match(NEWLINE);
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(137);
				beforeStmt();
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(138);
					match(NEWLINE);
					}
					}
					setState(141); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(143);
				afterStmt();
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(144);
					match(NEWLINE);
					}
					}
					setState(149);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
	public static class PositionDeclContext extends ParserRuleContext {
		public TerminalNode POSITION() { return getToken(figureParser.POSITION, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(figureParser.EQUAL, 0); }
		public CoordinateContext coordinate() {
			return getRuleContext(CoordinateContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public PositionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positionDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitPositionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositionDeclContext positionDecl() throws RecognitionException {
		PositionDeclContext _localctx = new PositionDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_positionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(POSITION);
			setState(153);
			match(WS);
			setState(154);
			id();
			setState(155);
			match(WS);
			setState(156);
			match(EQUAL);
			setState(157);
			match(WS);
			setState(158);
			coordinate();
			setState(159);
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
	public static class VelocityDeclContext extends ParserRuleContext {
		public TerminalNode VELOCITY() { return getToken(figureParser.VELOCITY, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(figureParser.EQUAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public VelocityDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_velocityDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitVelocityDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VelocityDeclContext velocityDecl() throws RecognitionException {
		VelocityDeclContext _localctx = new VelocityDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_velocityDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(VELOCITY);
			setState(162);
			match(WS);
			setState(163);
			id();
			setState(164);
			match(WS);
			setState(165);
			match(EQUAL);
			setState(166);
			match(WS);
			setState(167);
			expression();
			setState(168);
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
	public static class ExpressionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			term();
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) {
				{
				{
				setState(171);
				operation();
				setState(172);
				term();
				}
				}
				setState(178);
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
	public static class TermContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public SignalContext signal() {
			return getRuleContext(SignalContext.class,0);
		}
		public TerminalNode PI() { return getToken(figureParser.PI, 0); }
		public TerminalNode LPAREN() { return getToken(figureParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(figureParser.RPAREN, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_term);
		try {
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				signal();
				setState(181);
				match(PI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				match(LPAREN);
				setState(184);
				expression();
				setState(185);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
				id();
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
	public static class DistanceDeclContext extends ParserRuleContext {
		public TerminalNode DISTANCE() { return getToken(figureParser.DISTANCE, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(figureParser.EQUAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public DistanceDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distanceDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitDistanceDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistanceDeclContext distanceDecl() throws RecognitionException {
		DistanceDeclContext _localctx = new DistanceDeclContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_distanceDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(DISTANCE);
			setState(191);
			match(WS);
			setState(192);
			id();
			setState(193);
			match(WS);
			setState(194);
			match(EQUAL);
			setState(195);
			match(WS);
			setState(196);
			expression();
			setState(197);
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
	public static class FigureDeclContext extends ParserRuleContext {
		public TerminalNode LINE() { return getToken(figureParser.LINE, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(figureParser.LPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(figureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(figureParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(figureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public TerminalNode RECTANGLE() { return getToken(figureParser.RECTANGLE, 0); }
		public TerminalNode CIRCLE() { return getToken(figureParser.CIRCLE, 0); }
		public TerminalNode CIRCUNFERENCE() { return getToken(figureParser.CIRCUNFERENCE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FigureDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_figureDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitFigureDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FigureDeclContext figureDecl() throws RecognitionException {
		FigureDeclContext _localctx = new FigureDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_figureDecl);
		int _la;
		try {
			setState(288);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(LINE);
				setState(200);
				match(WS);
				setState(201);
				id();
				setState(202);
				match(LPAREN);
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(203);
					match(WS);
					}
				}

				setState(206);
				id();
				setState(207);
				match(COMMA);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(208);
					match(WS);
					}
				}

				setState(211);
				id();
				setState(212);
				match(COMMA);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(213);
					match(WS);
					}
				}

				setState(216);
				id();
				setState(217);
				match(RPAREN);
				setState(218);
				match(SEMICOLON);
				}
				break;
			case RECTANGLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				match(RECTANGLE);
				setState(221);
				match(WS);
				setState(222);
				id();
				setState(223);
				match(LPAREN);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(224);
					match(WS);
					}
				}

				setState(227);
				id();
				setState(228);
				match(COMMA);
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(229);
					match(WS);
					}
				}

				setState(232);
				id();
				setState(233);
				match(COMMA);
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(234);
					match(WS);
					}
				}

				setState(237);
				id();
				setState(238);
				match(COMMA);
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(239);
					match(WS);
					}
				}

				setState(242);
				id();
				setState(243);
				match(RPAREN);
				setState(244);
				match(SEMICOLON);
				}
				break;
			case CIRCLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				match(CIRCLE);
				setState(247);
				match(WS);
				setState(248);
				id();
				setState(249);
				match(LPAREN);
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(250);
					match(WS);
					}
				}

				setState(253);
				id();
				setState(254);
				match(COMMA);
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(255);
					match(WS);
					}
				}

				setState(258);
				id();
				setState(259);
				match(COMMA);
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(260);
					match(WS);
					}
				}

				setState(263);
				id();
				setState(264);
				match(RPAREN);
				setState(265);
				match(SEMICOLON);
				}
				break;
			case CIRCUNFERENCE:
				enterOuterAlt(_localctx, 4);
				{
				setState(267);
				match(CIRCUNFERENCE);
				setState(268);
				match(WS);
				setState(269);
				id();
				setState(270);
				match(LPAREN);
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(271);
					match(WS);
					}
				}

				setState(274);
				id();
				setState(275);
				match(COMMA);
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(276);
					match(WS);
					}
				}

				setState(279);
				expression();
				setState(280);
				match(COMMA);
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(281);
					match(WS);
					}
				}

				setState(284);
				id();
				setState(285);
				match(RPAREN);
				setState(286);
				match(SEMICOLON);
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
	public static class ActionStmtContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode POINT() { return getToken(figureParser.POINT, 0); }
		public TerminalNode LIGHTSON() { return getToken(figureParser.LIGHTSON, 0); }
		public TerminalNode LPAREN() { return getToken(figureParser.LPAREN, 0); }
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(figureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public TerminalNode MOVE() { return getToken(figureParser.MOVE, 0); }
		public CoordinateContext coordinate() {
			return getRuleContext(CoordinateContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(figureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(figureParser.COMMA, i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode ROTATE() { return getToken(figureParser.ROTATE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LIGHTSOFF() { return getToken(figureParser.LIGHTSOFF, 0); }
		public TerminalNode MOVEPOS() { return getToken(figureParser.MOVEPOS, 0); }
		public ActionStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitActionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionStmtContext actionStmt() throws RecognitionException {
		ActionStmtContext _localctx = new ActionStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_actionStmt);
		int _la;
		try {
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(290);
					match(WS);
					}
				}

				setState(293);
				id();
				setState(294);
				match(POINT);
				setState(295);
				match(LIGHTSON);
				setState(296);
				match(LPAREN);
				setState(297);
				color();
				setState(298);
				match(RPAREN);
				setState(299);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(301);
					match(WS);
					}
				}

				setState(304);
				id();
				setState(305);
				match(POINT);
				setState(306);
				match(MOVE);
				setState(307);
				match(LPAREN);
				setState(308);
				coordinate();
				setState(309);
				match(COMMA);
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(310);
					match(WS);
					}
				}

				setState(313);
				number();
				setState(314);
				match(COMMA);
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(315);
					match(WS);
					}
				}

				setState(318);
				id();
				setState(319);
				match(RPAREN);
				setState(320);
				match(SEMICOLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(322);
					match(WS);
					}
				}

				setState(325);
				id();
				setState(326);
				match(POINT);
				setState(327);
				match(ROTATE);
				setState(328);
				match(LPAREN);
				setState(329);
				id();
				setState(330);
				match(COMMA);
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(331);
					match(WS);
					}
				}

				setState(334);
				id();
				setState(335);
				match(COMMA);
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(336);
					match(WS);
					}
				}

				setState(339);
				expression();
				setState(340);
				match(COMMA);
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(341);
					match(WS);
					}
				}

				setState(344);
				id();
				setState(345);
				match(RPAREN);
				setState(346);
				match(SEMICOLON);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(348);
					match(WS);
					}
				}

				setState(351);
				id();
				setState(352);
				match(POINT);
				setState(353);
				match(LIGHTSOFF);
				setState(354);
				match(SEMICOLON);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(356);
					match(WS);
					}
				}

				setState(359);
				id();
				setState(360);
				match(POINT);
				setState(361);
				match(MOVEPOS);
				setState(362);
				match(LPAREN);
				setState(363);
				id();
				setState(364);
				match(COMMA);
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(365);
					match(WS);
					}
				}

				setState(368);
				id();
				setState(369);
				match(RPAREN);
				setState(370);
				match(SEMICOLON);
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
	public static class ColorContext extends ParserRuleContext {
		public TerminalNode YELLOW() { return getToken(figureParser.YELLOW, 0); }
		public TerminalNode RED() { return getToken(figureParser.RED, 0); }
		public TerminalNode GREEN() { return getToken(figureParser.GREEN, 0); }
		public ColorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitColor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorContext color() throws RecognitionException {
		ColorContext _localctx = new ColorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_color);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 234881024L) != 0)) ) {
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
	public static class CoordinateContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(figureParser.LPAREN, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(figureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(figureParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(figureParser.RPAREN, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public CoordinateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitCoordinate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoordinateContext coordinate() throws RecognitionException {
		CoordinateContext _localctx = new CoordinateContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_coordinate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(LPAREN);
			setState(377);
			number();
			setState(378);
			match(COMMA);
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(379);
				match(WS);
				}
			}

			setState(382);
			number();
			setState(383);
			match(COMMA);
			setState(385);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(384);
				match(WS);
				}
			}

			setState(387);
			number();
			setState(388);
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
	public static class GroupStmtContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(figureParser.GROUP, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(figureParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(figureParser.NEWLINE, i);
		}
		public TerminalNode ENDGROUP() { return getToken(figureParser.ENDGROUP, 0); }
		public List<TerminalNode> WS() { return getTokens(figureParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(figureParser.WS, i);
		}
		public List<ActionStmtContext> actionStmt() {
			return getRuleContexts(ActionStmtContext.class);
		}
		public ActionStmtContext actionStmt(int i) {
			return getRuleContext(ActionStmtContext.class,i);
		}
		public GroupStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitGroupStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupStmtContext groupStmt() throws RecognitionException {
		GroupStmtContext _localctx = new GroupStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_groupStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(390);
				match(WS);
				}
			}

			setState(393);
			match(GROUP);
			setState(394);
			match(NEWLINE);
			setState(398); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(395);
					actionStmt();
					setState(396);
					match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(400); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(402);
				match(WS);
				}
			}

			setState(405);
			match(ENDGROUP);
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
	public static class PauseStmtContext extends ParserRuleContext {
		public TerminalNode PAUSE() { return getToken(figureParser.PAUSE, 0); }
		public TerminalNode LPAREN() { return getToken(figureParser.LPAREN, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(figureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(figureParser.SEMICOLON, 0); }
		public PauseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pauseStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitPauseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PauseStmtContext pauseStmt() throws RecognitionException {
		PauseStmtContext _localctx = new PauseStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pauseStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			match(PAUSE);
			setState(408);
			match(LPAREN);
			setState(409);
			number();
			setState(410);
			match(RPAREN);
			setState(411);
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
	public static class BeforeStmtContext extends ParserRuleContext {
		public TerminalNode BEFORE() { return getToken(figureParser.BEFORE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(figureParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(figureParser.NEWLINE, i);
		}
		public TerminalNode ENDBEFORE() { return getToken(figureParser.ENDBEFORE, 0); }
		public List<ActionStmtContext> actionStmt() {
			return getRuleContexts(ActionStmtContext.class);
		}
		public ActionStmtContext actionStmt(int i) {
			return getRuleContext(ActionStmtContext.class,i);
		}
		public List<GroupStmtContext> groupStmt() {
			return getRuleContexts(GroupStmtContext.class);
		}
		public GroupStmtContext groupStmt(int i) {
			return getRuleContext(GroupStmtContext.class,i);
		}
		public TerminalNode WS() { return getToken(figureParser.WS, 0); }
		public BeforeStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beforeStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitBeforeStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeforeStmtContext beforeStmt() throws RecognitionException {
		BeforeStmtContext _localctx = new BeforeStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_beforeStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(BEFORE);
			setState(414);
			match(NEWLINE);
			setState(421); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(421);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
					case 1:
						{
						setState(415);
						actionStmt();
						setState(416);
						match(NEWLINE);
						}
						break;
					case 2:
						{
						setState(418);
						groupStmt();
						setState(419);
						match(NEWLINE);
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(423); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(426);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(425);
				match(WS);
				}
			}

			setState(428);
			match(ENDBEFORE);
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
	public static class AfterStmtContext extends ParserRuleContext {
		public TerminalNode AFTER() { return getToken(figureParser.AFTER, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(figureParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(figureParser.NEWLINE, i);
		}
		public TerminalNode ENDAFTER() { return getToken(figureParser.ENDAFTER, 0); }
		public List<ActionStmtContext> actionStmt() {
			return getRuleContexts(ActionStmtContext.class);
		}
		public ActionStmtContext actionStmt(int i) {
			return getRuleContext(ActionStmtContext.class,i);
		}
		public List<GroupStmtContext> groupStmt() {
			return getRuleContexts(GroupStmtContext.class);
		}
		public GroupStmtContext groupStmt(int i) {
			return getRuleContext(GroupStmtContext.class,i);
		}
		public TerminalNode WS() { return getToken(figureParser.WS, 0); }
		public AfterStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_afterStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitAfterStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AfterStmtContext afterStmt() throws RecognitionException {
		AfterStmtContext _localctx = new AfterStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_afterStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(AFTER);
			setState(431);
			match(NEWLINE);
			setState(438); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(438);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
					case 1:
						{
						setState(432);
						actionStmt();
						setState(433);
						match(NEWLINE);
						}
						break;
					case 2:
						{
						setState(435);
						groupStmt();
						setState(436);
						match(NEWLINE);
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(440); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(443);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(442);
				match(WS);
				}
			}

			setState(445);
			match(ENDAFTER);
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
	public static class SignalContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(figureParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(figureParser.MINUS, 0); }
		public SignalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitSignal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignalContext signal() throws RecognitionException {
		SignalContext _localctx = new SignalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_signal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(447);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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
	public static class OperationContext extends ParserRuleContext {
		public TerminalNode MULTIPLICATION() { return getToken(figureParser.MULTIPLICATION, 0); }
		public TerminalNode DIVISION() { return getToken(figureParser.DIVISION, 0); }
		public TerminalNode PLUS() { return getToken(figureParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(figureParser.MINUS, 0); }
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) ) {
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
	public static class NumberContext extends ParserRuleContext {
		public SignalContext signal() {
			return getRuleContext(SignalContext.class,0);
		}
		public FloatContext float_() {
			return getRuleContext(FloatContext.class,0);
		}
		public IntContext int_() {
			return getRuleContext(IntContext.class,0);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			signal();
			setState(455);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(453);
				float_();
				}
				break;
			case 2:
				{
				setState(454);
				int_();
				}
				break;
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
	public static class FloatContext extends ParserRuleContext {
		public TerminalNode POINT() { return getToken(figureParser.POINT, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(figureParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(figureParser.DIGIT, i);
		}
		public FloatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatContext float_() throws RecognitionException {
		FloatContext _localctx = new FloatContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_float);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(457);
				match(DIGIT);
				}
				}
				setState(460); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(462);
			match(POINT);
			setState(464); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(463);
				match(DIGIT);
				}
				}
				setState(466); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
	public static class IntContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(figureParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(figureParser.DIGIT, i);
		}
		public IntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof figureVisitor ) return ((figureVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntContext int_() throws RecognitionException {
		IntContext _localctx = new IntContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_int);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(468);
				match(DIGIT);
				}
				}
				setState(471); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
		"\u0004\u0001)\u01da\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0001\u0000\u0001\u0000\u0004\u00005\b\u0000\u000b\u0000\f\u00006\u0001"+
		"\u0000\u0001\u0000\u0004\u0000;\b\u0000\u000b\u0000\f\u0000<\u0001\u0000"+
		"\u0005\u0000@\b\u0000\n\u0000\f\u0000C\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002S\b\u0002\u0003\u0002U\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0004\u0006b\b\u0006\u000b\u0006\f\u0006"+
		"c\u0001\u0006\u0001\u0006\u0004\u0006h\b\u0006\u000b\u0006\f\u0006i\u0001"+
		"\u0006\u0001\u0006\u0004\u0006n\b\u0006\u000b\u0006\f\u0006o\u0001\u0006"+
		"\u0001\u0006\u0004\u0006t\b\u0006\u000b\u0006\f\u0006u\u0001\u0006\u0001"+
		"\u0006\u0004\u0006z\b\u0006\u000b\u0006\f\u0006{\u0001\u0006\u0001\u0006"+
		"\u0004\u0006\u0080\b\u0006\u000b\u0006\f\u0006\u0081\u0001\u0006\u0001"+
		"\u0006\u0004\u0006\u0086\b\u0006\u000b\u0006\f\u0006\u0087\u0001\u0006"+
		"\u0001\u0006\u0004\u0006\u008c\b\u0006\u000b\u0006\f\u0006\u008d\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u0092\b\u0006\n\u0006\f\u0006\u0095\t\u0006"+
		"\u0003\u0006\u0097\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\u00af\b\t\n\t\f\t\u00b2\t\t\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00bd"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00cd\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d2\b\f"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u00d7\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00e2\b\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00e7\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u00ec\b\f"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u00f1\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00fc\b\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u0101\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u0106\b\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u0111\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u0116\b\f\u0001\f"+
		"\u0001\f\u0001\f\u0003\f\u011b\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u0121\b\f\u0001\r\u0003\r\u0124\b\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u012f\b\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0138\b\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u013d\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u0144\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u014d\b\r\u0001\r\u0001\r\u0001\r\u0003\r\u0152\b\r\u0001\r\u0001\r"+
		"\u0001\r\u0003\r\u0157\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u015e\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0166"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u016f"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0175\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u017d"+
		"\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0182\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0003\u0010\u0188\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010"+
		"\u018f\b\u0010\u000b\u0010\f\u0010\u0190\u0001\u0010\u0003\u0010\u0194"+
		"\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0004\u0012\u01a6"+
		"\b\u0012\u000b\u0012\f\u0012\u01a7\u0001\u0012\u0003\u0012\u01ab\b\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0004\u0013\u01b7\b\u0013"+
		"\u000b\u0013\f\u0013\u01b8\u0001\u0013\u0003\u0013\u01bc\b\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0003\u0014\u01c1\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01c8\b\u0016\u0001"+
		"\u0017\u0004\u0017\u01cb\b\u0017\u000b\u0017\f\u0017\u01cc\u0001\u0017"+
		"\u0001\u0017\u0004\u0017\u01d1\b\u0017\u000b\u0017\f\u0017\u01d2\u0001"+
		"\u0018\u0004\u0018\u01d6\b\u0018\u000b\u0018\f\u0018\u01d7\u0001\u0018"+
		"\u0000\u0000\u0019\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000\u0003\u0001\u0000\u0019"+
		"\u001b\u0001\u0000\u000b\f\u0001\u0000\u000b\u000e\u0209\u00002\u0001"+
		"\u0000\u0000\u0000\u0002F\u0001\u0000\u0000\u0000\u0004M\u0001\u0000\u0000"+
		"\u0000\u0006V\u0001\u0000\u0000\u0000\bY\u0001\u0000\u0000\u0000\n]\u0001"+
		"\u0000\u0000\u0000\f\u0096\u0001\u0000\u0000\u0000\u000e\u0098\u0001\u0000"+
		"\u0000\u0000\u0010\u00a1\u0001\u0000\u0000\u0000\u0012\u00aa\u0001\u0000"+
		"\u0000\u0000\u0014\u00bc\u0001\u0000\u0000\u0000\u0016\u00be\u0001\u0000"+
		"\u0000\u0000\u0018\u0120\u0001\u0000\u0000\u0000\u001a\u0174\u0001\u0000"+
		"\u0000\u0000\u001c\u0176\u0001\u0000\u0000\u0000\u001e\u0178\u0001\u0000"+
		"\u0000\u0000 \u0187\u0001\u0000\u0000\u0000\"\u0197\u0001\u0000\u0000"+
		"\u0000$\u019d\u0001\u0000\u0000\u0000&\u01ae\u0001\u0000\u0000\u0000("+
		"\u01c0\u0001\u0000\u0000\u0000*\u01c2\u0001\u0000\u0000\u0000,\u01c4\u0001"+
		"\u0000\u0000\u0000.\u01ca\u0001\u0000\u0000\u00000\u01d5\u0001\u0000\u0000"+
		"\u000024\u0003\u0002\u0001\u000035\u0005\'\u0000\u000043\u0001\u0000\u0000"+
		"\u000056\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000"+
		"\u0000\u000078\u0001\u0000\u0000\u00008:\u0003\u0006\u0003\u00009;\u0005"+
		"\'\u0000\u0000:9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<:\u0001"+
		"\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=A\u0001\u0000\u0000\u0000"+
		">@\u0003\f\u0006\u0000?>\u0001\u0000\u0000\u0000@C\u0001\u0000\u0000\u0000"+
		"A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BD\u0001\u0000\u0000"+
		"\u0000CA\u0001\u0000\u0000\u0000DE\u0005\u0000\u0000\u0001E\u0001\u0001"+
		"\u0000\u0000\u0000FG\u0005\u0001\u0000\u0000GH\u0005(\u0000\u0000HI\u0005"+
		"\u0002\u0000\u0000IJ\u0005(\u0000\u0000JK\u0003\u0004\u0002\u0000KL\u0005"+
		"\u0003\u0000\u0000L\u0003\u0001\u0000\u0000\u0000MT\u00030\u0018\u0000"+
		"NO\u0005#\u0000\u0000OR\u00030\u0018\u0000PQ\u0005#\u0000\u0000QS\u0003"+
		"0\u0018\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001"+
		"\u0000\u0000\u0000TN\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000"+
		"U\u0005\u0001\u0000\u0000\u0000VW\u0003\b\u0004\u0000WX\u0005\u0003\u0000"+
		"\u0000X\u0007\u0001\u0000\u0000\u0000YZ\u0005\u0004\u0000\u0000Z[\u0005"+
		"(\u0000\u0000[\\\u0003\n\u0005\u0000\\\t\u0001\u0000\u0000\u0000]^\u0005"+
		"%\u0000\u0000^\u000b\u0001\u0000\u0000\u0000_a\u0003\u000e\u0007\u0000"+
		"`b\u0005\'\u0000\u0000a`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000d\u0097\u0001\u0000"+
		"\u0000\u0000eg\u0003\u0010\b\u0000fh\u0005\'\u0000\u0000gf\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001"+
		"\u0000\u0000\u0000j\u0097\u0001\u0000\u0000\u0000km\u0003\u0016\u000b"+
		"\u0000ln\u0005\'\u0000\u0000ml\u0001\u0000\u0000\u0000no\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000p\u0097\u0001"+
		"\u0000\u0000\u0000qs\u0003\u0018\f\u0000rt\u0005\'\u0000\u0000sr\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000"+
		"uv\u0001\u0000\u0000\u0000v\u0097\u0001\u0000\u0000\u0000wy\u0003\u001a"+
		"\r\u0000xz\u0005\'\u0000\u0000yx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|\u0097"+
		"\u0001\u0000\u0000\u0000}\u007f\u0003 \u0010\u0000~\u0080\u0005\'\u0000"+
		"\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000"+
		"\u0082\u0097\u0001\u0000\u0000\u0000\u0083\u0085\u0003\"\u0011\u0000\u0084"+
		"\u0086\u0005\'\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0088"+
		"\u0001\u0000\u0000\u0000\u0088\u0097\u0001\u0000\u0000\u0000\u0089\u008b"+
		"\u0003$\u0012\u0000\u008a\u008c\u0005\'\u0000\u0000\u008b\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008b\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u0097\u0001"+
		"\u0000\u0000\u0000\u008f\u0093\u0003&\u0013\u0000\u0090\u0092\u0005\'"+
		"\u0000\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u0095\u0001\u0000"+
		"\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000"+
		"\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000"+
		"\u0000\u0000\u0096_\u0001\u0000\u0000\u0000\u0096e\u0001\u0000\u0000\u0000"+
		"\u0096k\u0001\u0000\u0000\u0000\u0096q\u0001\u0000\u0000\u0000\u0096w"+
		"\u0001\u0000\u0000\u0000\u0096}\u0001\u0000\u0000\u0000\u0096\u0083\u0001"+
		"\u0000\u0000\u0000\u0096\u0089\u0001\u0000\u0000\u0000\u0096\u008f\u0001"+
		"\u0000\u0000\u0000\u0097\r\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0005"+
		"\u0000\u0000\u0099\u009a\u0005(\u0000\u0000\u009a\u009b\u0003\n\u0005"+
		"\u0000\u009b\u009c\u0005(\u0000\u0000\u009c\u009d\u0005\u0007\u0000\u0000"+
		"\u009d\u009e\u0005(\u0000\u0000\u009e\u009f\u0003\u001e\u000f\u0000\u009f"+
		"\u00a0\u0005\u0003\u0000\u0000\u00a0\u000f\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a2\u0005\u0006\u0000\u0000\u00a2\u00a3\u0005(\u0000\u0000\u00a3\u00a4"+
		"\u0003\n\u0005\u0000\u00a4\u00a5\u0005(\u0000\u0000\u00a5\u00a6\u0005"+
		"\u0007\u0000\u0000\u00a6\u00a7\u0005(\u0000\u0000\u00a7\u00a8\u0003\u0012"+
		"\t\u0000\u00a8\u00a9\u0005\u0003\u0000\u0000\u00a9\u0011\u0001\u0000\u0000"+
		"\u0000\u00aa\u00b0\u0003\u0014\n\u0000\u00ab\u00ac\u0003*\u0015\u0000"+
		"\u00ac\u00ad\u0003\u0014\n\u0000\u00ad\u00af\u0001\u0000\u0000\u0000\u00ae"+
		"\u00ab\u0001\u0000\u0000\u0000\u00af\u00b2\u0001\u0000\u0000\u0000\u00b0"+
		"\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1"+
		"\u0013\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3"+
		"\u00bd\u0003,\u0016\u0000\u00b4\u00b5\u0003(\u0014\u0000\u00b5\u00b6\u0005"+
		"\n\u0000\u0000\u00b6\u00bd\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005\b"+
		"\u0000\u0000\u00b8\u00b9\u0003\u0012\t\u0000\u00b9\u00ba\u0005\t\u0000"+
		"\u0000\u00ba\u00bd\u0001\u0000\u0000\u0000\u00bb\u00bd\u0003\n\u0005\u0000"+
		"\u00bc\u00b3\u0001\u0000\u0000\u0000\u00bc\u00b4\u0001\u0000\u0000\u0000"+
		"\u00bc\u00b7\u0001\u0000\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bd\u0015\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u000f\u0000\u0000"+
		"\u00bf\u00c0\u0005(\u0000\u0000\u00c0\u00c1\u0003\n\u0005\u0000\u00c1"+
		"\u00c2\u0005(\u0000\u0000\u00c2\u00c3\u0005\u0007\u0000\u0000\u00c3\u00c4"+
		"\u0005(\u0000\u0000\u00c4\u00c5\u0003\u0012\t\u0000\u00c5\u00c6\u0005"+
		"\u0003\u0000\u0000\u00c6\u0017\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005"+
		"\u0010\u0000\u0000\u00c8\u00c9\u0005(\u0000\u0000\u00c9\u00ca\u0003\n"+
		"\u0005\u0000\u00ca\u00cc\u0005\b\u0000\u0000\u00cb\u00cd\u0005(\u0000"+
		"\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf\u0003\n\u0005\u0000"+
		"\u00cf\u00d1\u0005$\u0000\u0000\u00d0\u00d2\u0005(\u0000\u0000\u00d1\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d3\u00d4\u0003\n\u0005\u0000\u00d4\u00d6\u0005"+
		"$\u0000\u0000\u00d5\u00d7\u0005(\u0000\u0000\u00d6\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d9\u0003\n\u0005\u0000\u00d9\u00da\u0005\t\u0000\u0000"+
		"\u00da\u00db\u0005\u0003\u0000\u0000\u00db\u0121\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0005\u0011\u0000\u0000\u00dd\u00de\u0005(\u0000\u0000\u00de"+
		"\u00df\u0003\n\u0005\u0000\u00df\u00e1\u0005\b\u0000\u0000\u00e0\u00e2"+
		"\u0005(\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0003"+
		"\n\u0005\u0000\u00e4\u00e6\u0005$\u0000\u0000\u00e5\u00e7\u0005(\u0000"+
		"\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00e9\u0003\n\u0005\u0000"+
		"\u00e9\u00eb\u0005$\u0000\u0000\u00ea\u00ec\u0005(\u0000\u0000\u00eb\u00ea"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ed\u00ee\u0003\n\u0005\u0000\u00ee\u00f0\u0005"+
		"$\u0000\u0000\u00ef\u00f1\u0005(\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000"+
		"\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0003\n\u0005\u0000\u00f3\u00f4\u0005\t\u0000\u0000"+
		"\u00f4\u00f5\u0005\u0003\u0000\u0000\u00f5\u0121\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f7\u0005\u0012\u0000\u0000\u00f7\u00f8\u0005(\u0000\u0000\u00f8"+
		"\u00f9\u0003\n\u0005\u0000\u00f9\u00fb\u0005\b\u0000\u0000\u00fa\u00fc"+
		"\u0005(\u0000\u0000\u00fb\u00fa\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00fe\u0003"+
		"\n\u0005\u0000\u00fe\u0100\u0005$\u0000\u0000\u00ff\u0101\u0005(\u0000"+
		"\u0000\u0100\u00ff\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000"+
		"\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u0103\u0003\n\u0005\u0000"+
		"\u0103\u0105\u0005$\u0000\u0000\u0104\u0106\u0005(\u0000\u0000\u0105\u0104"+
		"\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0001\u0000\u0000\u0000\u0107\u0108\u0003\n\u0005\u0000\u0108\u0109\u0005"+
		"\t\u0000\u0000\u0109\u010a\u0005\u0003\u0000\u0000\u010a\u0121\u0001\u0000"+
		"\u0000\u0000\u010b\u010c\u0005\u0013\u0000\u0000\u010c\u010d\u0005(\u0000"+
		"\u0000\u010d\u010e\u0003\n\u0005\u0000\u010e\u0110\u0005\b\u0000\u0000"+
		"\u010f\u0111\u0005(\u0000\u0000\u0110\u010f\u0001\u0000\u0000\u0000\u0110"+
		"\u0111\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0003\n\u0005\u0000\u0113\u0115\u0005$\u0000\u0000\u0114\u0116"+
		"\u0005(\u0000\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0115\u0116\u0001"+
		"\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0003"+
		"\u0012\t\u0000\u0118\u011a\u0005$\u0000\u0000\u0119\u011b\u0005(\u0000"+
		"\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000"+
		"\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011d\u0003\n\u0005\u0000"+
		"\u011d\u011e\u0005\t\u0000\u0000\u011e\u011f\u0005\u0003\u0000\u0000\u011f"+
		"\u0121\u0001\u0000\u0000\u0000\u0120\u00c7\u0001\u0000\u0000\u0000\u0120"+
		"\u00dc\u0001\u0000\u0000\u0000\u0120\u00f6\u0001\u0000\u0000\u0000\u0120"+
		"\u010b\u0001\u0000\u0000\u0000\u0121\u0019\u0001\u0000\u0000\u0000\u0122"+
		"\u0124\u0005(\u0000\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0126"+
		"\u0003\n\u0005\u0000\u0126\u0127\u0005#\u0000\u0000\u0127\u0128\u0005"+
		"\u0014\u0000\u0000\u0128\u0129\u0005\b\u0000\u0000\u0129\u012a\u0003\u001c"+
		"\u000e\u0000\u012a\u012b\u0005\t\u0000\u0000\u012b\u012c\u0005\u0003\u0000"+
		"\u0000\u012c\u0175\u0001\u0000\u0000\u0000\u012d\u012f\u0005(\u0000\u0000"+
		"\u012e\u012d\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000"+
		"\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0003\n\u0005\u0000\u0131"+
		"\u0132\u0005#\u0000\u0000\u0132\u0133\u0005\u0015\u0000\u0000\u0133\u0134"+
		"\u0005\b\u0000\u0000\u0134\u0135\u0003\u001e\u000f\u0000\u0135\u0137\u0005"+
		"$\u0000\u0000\u0136\u0138\u0005(\u0000\u0000\u0137\u0136\u0001\u0000\u0000"+
		"\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000"+
		"\u0000\u0139\u013a\u0003,\u0016\u0000\u013a\u013c\u0005$\u0000\u0000\u013b"+
		"\u013d\u0005(\u0000\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013c\u013d"+
		"\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u0003\n\u0005\u0000\u013f\u0140\u0005\t\u0000\u0000\u0140\u0141\u0005"+
		"\u0003\u0000\u0000\u0141\u0175\u0001\u0000\u0000\u0000\u0142\u0144\u0005"+
		"(\u0000\u0000\u0143\u0142\u0001\u0000\u0000\u0000\u0143\u0144\u0001\u0000"+
		"\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145\u0146\u0003\n\u0005"+
		"\u0000\u0146\u0147\u0005#\u0000\u0000\u0147\u0148\u0005\u0016\u0000\u0000"+
		"\u0148\u0149\u0005\b\u0000\u0000\u0149\u014a\u0003\n\u0005\u0000\u014a"+
		"\u014c\u0005$\u0000\u0000\u014b\u014d\u0005(\u0000\u0000\u014c\u014b\u0001"+
		"\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014e\u0001"+
		"\u0000\u0000\u0000\u014e\u014f\u0003\n\u0005\u0000\u014f\u0151\u0005$"+
		"\u0000\u0000\u0150\u0152\u0005(\u0000\u0000\u0151\u0150\u0001\u0000\u0000"+
		"\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000"+
		"\u0000\u0153\u0154\u0003\u0012\t\u0000\u0154\u0156\u0005$\u0000\u0000"+
		"\u0155\u0157\u0005(\u0000\u0000\u0156\u0155\u0001\u0000\u0000\u0000\u0156"+
		"\u0157\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0003\n\u0005\u0000\u0159\u015a\u0005\t\u0000\u0000\u015a\u015b"+
		"\u0005\u0003\u0000\u0000\u015b\u0175\u0001\u0000\u0000\u0000\u015c\u015e"+
		"\u0005(\u0000\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015d\u015e\u0001"+
		"\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0160\u0003"+
		"\n\u0005\u0000\u0160\u0161\u0005#\u0000\u0000\u0161\u0162\u0005\u0017"+
		"\u0000\u0000\u0162\u0163\u0005\u0003\u0000\u0000\u0163\u0175\u0001\u0000"+
		"\u0000\u0000\u0164\u0166\u0005(\u0000\u0000\u0165\u0164\u0001\u0000\u0000"+
		"\u0000\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000"+
		"\u0000\u0167\u0168\u0003\n\u0005\u0000\u0168\u0169\u0005#\u0000\u0000"+
		"\u0169\u016a\u0005\u0018\u0000\u0000\u016a\u016b\u0005\b\u0000\u0000\u016b"+
		"\u016c\u0003\n\u0005\u0000\u016c\u016e\u0005$\u0000\u0000\u016d\u016f"+
		"\u0005(\u0000\u0000\u016e\u016d\u0001\u0000\u0000\u0000\u016e\u016f\u0001"+
		"\u0000\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0171\u0003"+
		"\n\u0005\u0000\u0171\u0172\u0005\t\u0000\u0000\u0172\u0173\u0005\u0003"+
		"\u0000\u0000\u0173\u0175\u0001\u0000\u0000\u0000\u0174\u0123\u0001\u0000"+
		"\u0000\u0000\u0174\u012e\u0001\u0000\u0000\u0000\u0174\u0143\u0001\u0000"+
		"\u0000\u0000\u0174\u015d\u0001\u0000\u0000\u0000\u0174\u0165\u0001\u0000"+
		"\u0000\u0000\u0175\u001b\u0001\u0000\u0000\u0000\u0176\u0177\u0007\u0000"+
		"\u0000\u0000\u0177\u001d\u0001\u0000\u0000\u0000\u0178\u0179\u0005\b\u0000"+
		"\u0000\u0179\u017a\u0003,\u0016\u0000\u017a\u017c\u0005$\u0000\u0000\u017b"+
		"\u017d\u0005(\u0000\u0000\u017c\u017b\u0001\u0000\u0000\u0000\u017c\u017d"+
		"\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017e\u017f"+
		"\u0003,\u0016\u0000\u017f\u0181\u0005$\u0000\u0000\u0180\u0182\u0005("+
		"\u0000\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000"+
		"\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183\u0184\u0003,\u0016"+
		"\u0000\u0184\u0185\u0005\t\u0000\u0000\u0185\u001f\u0001\u0000\u0000\u0000"+
		"\u0186\u0188\u0005(\u0000\u0000\u0187\u0186\u0001\u0000\u0000\u0000\u0187"+
		"\u0188\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000\u0000\u0189"+
		"\u018a\u0005\u001c\u0000\u0000\u018a\u018e\u0005\'\u0000\u0000\u018b\u018c"+
		"\u0003\u001a\r\u0000\u018c\u018d\u0005\'\u0000\u0000\u018d\u018f\u0001"+
		"\u0000\u0000\u0000\u018e\u018b\u0001\u0000\u0000\u0000\u018f\u0190\u0001"+
		"\u0000\u0000\u0000\u0190\u018e\u0001\u0000\u0000\u0000\u0190\u0191\u0001"+
		"\u0000\u0000\u0000\u0191\u0193\u0001\u0000\u0000\u0000\u0192\u0194\u0005"+
		"(\u0000\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000"+
		"\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000\u0195\u0196\u0005\u001d"+
		"\u0000\u0000\u0196!\u0001\u0000\u0000\u0000\u0197\u0198\u0005\u001e\u0000"+
		"\u0000\u0198\u0199\u0005\b\u0000\u0000\u0199\u019a\u0003,\u0016\u0000"+
		"\u019a\u019b\u0005\t\u0000\u0000\u019b\u019c\u0005\u0003\u0000\u0000\u019c"+
		"#\u0001\u0000\u0000\u0000\u019d\u019e\u0005\u001f\u0000\u0000\u019e\u01a5"+
		"\u0005\'\u0000\u0000\u019f\u01a0\u0003\u001a\r\u0000\u01a0\u01a1\u0005"+
		"\'\u0000\u0000\u01a1\u01a6\u0001\u0000\u0000\u0000\u01a2\u01a3\u0003 "+
		"\u0010\u0000\u01a3\u01a4\u0005\'\u0000\u0000\u01a4\u01a6\u0001\u0000\u0000"+
		"\u0000\u01a5\u019f\u0001\u0000\u0000\u0000\u01a5\u01a2\u0001\u0000\u0000"+
		"\u0000\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7\u01a5\u0001\u0000\u0000"+
		"\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8\u01aa\u0001\u0000\u0000"+
		"\u0000\u01a9\u01ab\u0005(\u0000\u0000\u01aa\u01a9\u0001\u0000\u0000\u0000"+
		"\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001\u0000\u0000\u0000"+
		"\u01ac\u01ad\u0005 \u0000\u0000\u01ad%\u0001\u0000\u0000\u0000\u01ae\u01af"+
		"\u0005!\u0000\u0000\u01af\u01b6\u0005\'\u0000\u0000\u01b0\u01b1\u0003"+
		"\u001a\r\u0000\u01b1\u01b2\u0005\'\u0000\u0000\u01b2\u01b7\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b4\u0003 \u0010\u0000\u01b4\u01b5\u0005\'\u0000"+
		"\u0000\u01b5\u01b7\u0001\u0000\u0000\u0000\u01b6\u01b0\u0001\u0000\u0000"+
		"\u0000\u01b6\u01b3\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000\u0000"+
		"\u0000\u01b8\u01b6\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000"+
		"\u0000\u01b9\u01bb\u0001\u0000\u0000\u0000\u01ba\u01bc\u0005(\u0000\u0000"+
		"\u01bb\u01ba\u0001\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000"+
		"\u01bc\u01bd\u0001\u0000\u0000\u0000\u01bd\u01be\u0005\"\u0000\u0000\u01be"+
		"\'\u0001\u0000\u0000\u0000\u01bf\u01c1\u0007\u0001\u0000\u0000\u01c0\u01bf"+
		"\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1)\u0001"+
		"\u0000\u0000\u0000\u01c2\u01c3\u0007\u0002\u0000\u0000\u01c3+\u0001\u0000"+
		"\u0000\u0000\u01c4\u01c7\u0003(\u0014\u0000\u01c5\u01c8\u0003.\u0017\u0000"+
		"\u01c6\u01c8\u00030\u0018\u0000\u01c7\u01c5\u0001\u0000\u0000\u0000\u01c7"+
		"\u01c6\u0001\u0000\u0000\u0000\u01c8-\u0001\u0000\u0000\u0000\u01c9\u01cb"+
		"\u0005&\u0000\u0000\u01ca\u01c9\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001"+
		"\u0000\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001"+
		"\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01d0\u0005"+
		"#\u0000\u0000\u01cf\u01d1\u0005&\u0000\u0000\u01d0\u01cf\u0001\u0000\u0000"+
		"\u0000\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2\u01d0\u0001\u0000\u0000"+
		"\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3/\u0001\u0000\u0000\u0000"+
		"\u01d4\u01d6\u0005&\u0000\u0000\u01d5\u01d4\u0001\u0000\u0000\u0000\u01d6"+
		"\u01d7\u0001\u0000\u0000\u0000\u01d7\u01d5\u0001\u0000\u0000\u0000\u01d7"+
		"\u01d8\u0001\u0000\u0000\u0000\u01d81\u0001\u0000\u0000\u0000;6<ARTci"+
		"ou{\u0081\u0087\u008d\u0093\u0096\u00b0\u00bc\u00cc\u00d1\u00d6\u00e1"+
		"\u00e6\u00eb\u00f0\u00fb\u0100\u0105\u0110\u0115\u011a\u0120\u0123\u012e"+
		"\u0137\u013c\u0143\u014c\u0151\u0156\u015d\u0165\u016e\u0174\u017c\u0181"+
		"\u0187\u0190\u0193\u01a5\u01a7\u01aa\u01b6\u01b8\u01bb\u01c0\u01c7\u01cc"+
		"\u01d2\u01d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}