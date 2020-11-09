grammar Bot;

@parser::header {
	import org.jpavlich.bot.*;
	import java.util.Map;
	import java.util.HashMap;
	import java.util.List;
	import java.util.ArrayList;
	import co.edu.javeriana.bot.ast.*;
}

@parser::members {
	
	//Map<String, Object> symbolTable = new HashMap<String, Object>();
	Context context = new Context();
	
	
	private Bot bot;

	public BotParser(TokenStream input, Bot bot) {
    	this(input);
    	this.bot = bot;
	}
}

program: PROGRAM ID BRACKET_OPEN 
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
		//Map<String, Object> symbolTable = new HashMap<String, Object>();
		Context context = new Context();
	}
	(sentence {body.add($sentence.node);})*
	BRACKET_CLOSE {
	for(ASTNode n : body){
		n.execute(context);
	}
};

sentence returns [ASTNode node]: moverarriba {$node = $moverarriba.node;} | moverabajo {$node = $moverabajo.node;} 
								| moverderecha {$node = $moverderecha.node;} | moverizquierda {$node = $moverizquierda.node;} 
								| agarrar {$node = $agarrar.node;} | soltar {$node = $soltar.node;}
								| conditional {$node = $conditional.node;} | println {$node = $println.node;}
								| var_decl {$node = $var_decl.node;}
								| var_assign {$node = $var_assign.node;}
								| var_decl_assign {$node = $var_decl_assign.node;}
								| ciclo_while {$node = $ciclo_while.node;}
								| function {$node = $function.node;}
								| run_function {$node = $run_function.node;}
								| read {$node = $read.node;} | print{$node = $print.node;};

println returns [ASTNode node]: PRINTLN expression SEMICOLON
		{$node = new Println($expression.node);};
		
moverarriba returns [ASTNode node]: UP expression SEMICOLON {$node = new MoverArriba(bot, $expression.node);};

moverabajo returns [ASTNode node]: DOWN expression SEMICOLON {$node = new MoverAbajo(bot, $expression.node);};

moverderecha returns [ASTNode node]: GT expression SEMICOLON {$node = new MoverDerecha(bot, $expression.node);};

moverizquierda returns [ASTNode node]: LT expression SEMICOLON {$node = new MoverIzquierda(bot, $expression.node);};

agarrar returns [ASTNode node]: PICK SEMICOLON {$node = new Agarrar(bot);};

soltar returns [ASTNode node]: DROP SEMICOLON {$node = new Soltar(bot);};


conditional returns [ASTNode node]: IF (PAR_OPEN)? expression (PAR_CLOSE)?
			 {
			 	List<ASTNode> body = new ArrayList<ASTNode>();
			 	List<ASTNode> elseBody = new ArrayList<ASTNode>();
			 }
			 ASSIGN_FUNC (s1=sentence  {body.add($s1.node);})* 
			 (ELSE
			 {
			 	//List<ASTNode> elseBody = new ArrayList<ASTNode>();
			 }
			 (s2=sentence  {elseBody.add($s2.node);})* )? END SEMICOLON
			 {
			 	$node = new If($expression.node, body, elseBody);
			 };

ciclo_while returns [ASTNode node]: WHILE PAR_OPEN expression PAR_CLOSE
			{
				List<ASTNode> body = new ArrayList<ASTNode>(); 
			}
			ASSIGN_FUNC (s1=sentence {body.add($s1.node);})* 
			END SEMICOLON
			{
				$node = new While($expression.node, body);
			};
		
var_decl_assign returns [ASTNode node]: DECL ID ASSIGN expression SEMICOLON {$node = new VarDeclAssign($ID.text, $expression.node);};

var_decl returns [ASTNode node]: DECL ID SEMICOLON {$node = new VarDecl($ID.text);};

var_assign returns [ASTNode node]: ID ASSIGN expression SEMICOLON{$node = new VarAssign($ID.text, $expression.node);};

read returns [ASTNode node]: READ ID SEMICOLON
		{$node = new Read($ID.text);};
		
print returns [ASTNode node]: PRINTLN expression SEMICOLON
		{$node = new Println($expression.node);};

function returns [ASTNode node]: DEF t1=ID PAR_OPEN
		{
			List<String> parameters = new ArrayList<String>();
			List<ASTNode> body = new ArrayList<ASTNode>();
		}
		(DECL t2 = ID {parameters.add($t2.text);})?
		(COMMA t3 = ID {parameters.add($t3.text);})*
		PAR_CLOSE ASSIGN_FUNC (s1 = sentence{body.add($s1.node);})*
		END SEMICOLON
		{
			$node = new Functionn($t1.text, parameters, body);
		};

run_function returns [ASTNode node]:
		{
			List<ASTNode> arguments = new ArrayList<ASTNode>();
		} 
		ID PAR_OPEN (a1 = expression {arguments.add($a1.node);})?
		(COMMA a2 = expression {arguments.add($a2.node);})*
		PAR_CLOSE SEMICOLON
		{
			$node = new RunFunction($ID.text, arguments);
		};

expression returns [ASTNode node]:
		t1 = and_logical {$node = $t1.node;}
			(OR t2=and_logical {$node = new OrLogical($node, $t2.node);})*;

and_logical returns [ASTNode node]:
		t1 = equal {$node = $t1.node;}
		(AND t2=equal {$node = new AndLogical($node, $t2.node);})*;
			
equal returns [ASTNode node]:
		t1 = comparison {$node = $t1.node;}
		(EQ t2=comparison {$node = new EqualNodes($node, $t2.node);}
		|NEQ t3=comparison {$node = new NotEqualNodes($node, $t3.node);})*;

comparison returns [ASTNode node]:
		t1= addition {$node = $t1.node;}
		(GT t2=addition {$node = new GreatherThan($node, $t2.node);}
		|LT t3=addition {$node = new SmallerThan($node, $t3.node);}
		|GEQ t4=addition {$node = new GreatherEqual($node, $t4.node);}
		|LEQ t5=addition {$node = new SmallerEqual($node, $t5.node);})*;
		

addition returns [ASTNode node]:
		t1=factor {$node = $t1.node;}
		(PLUS t2=factor {$node = new Addition($node, $t2.node);}
		|MINUS t3=factor {$node = new Subtraction($node, $t3.node);})*;

factor returns [ASTNode node]:	
		t1=not_logical {$node = $t1.node;} 
		(MULT 	t2=not_logical {$node = new Multiplication($node, $t2.node);}
		|DIV t3=not_logical {$node = new Division($node, $t3.node);})*;

not_logical returns [ASTNode node]:
		t1=term {$node = $t1.node;}
		|NOT t2=term{$node = new NotLogical($t2.node);};
		
term returns [ASTNode node]: 
		NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
		| BOOLEAN {$node = new Bools($BOOLEAN.text);}
		| MINUS NUMBER {$node = new Inverse(Float.parseFloat($NUMBER.text));}
		| ID {$node = new VarRef($ID.text);}
		| PAR_OPEN expression {$node = $expression.node; } PAR_CLOSE;


PROGRAM: 'program';
DEF: 'define';
READ: '?';
PRINTLN: '$';
WHILE: 'while';
IF: 'if';
ELSE: 'else';
END: 'end';
BOOLEAN: '@T' | '@F';

/* *********** ASIGNACIÓN ************ */

ASSIGN: '<-';
DECL: '\'';
ASSIGN_FUNC: '->';

/* *********** COMPARACIÓN ************ */

GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
EQ: '==';
NEQ: '<>';

/* *********** COMANDOS ************ */

UP: '^';
DOWN: 'V';
PICK: 'P';
DROP: 'D';

/* *********** ARITMÉTICAS ************ */

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

/* *********** LÓGICAS ************ */

AND: '&';
OR: '|';
NOT: '!';




/* *********** CONTROL ************ */

BRACKET_OPEN: '{';
BRACKET_CLOSE: '}';
PAR_OPEN: '(';
PAR_CLOSE: ')';

SEMICOLON: ';';
COMMA: ',';


/* *********** TIPOS DE DATOS ************ */

ID: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+('.'[0-9]+)?;
STRING: '"' ( '\\"' | . )*? '"';

WS: [ \t\n\r]+ -> skip;


/*GRUPO 14 */
/*EMANUEL ALVAREZ SANCHEZ && NICOLÁS PUERTO */