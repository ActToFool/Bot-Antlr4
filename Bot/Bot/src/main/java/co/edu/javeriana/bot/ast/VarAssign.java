package co.edu.javeriana.bot.ast;

public class VarAssign implements ASTNode {
	
	private String name;
	private ASTNode expression;
	
	
	
	public VarAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Asignacion");
		context.modifyOrSet(name, expression.execute(context));
		return null;
	}

}
