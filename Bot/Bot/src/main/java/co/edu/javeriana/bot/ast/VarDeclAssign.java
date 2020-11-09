package co.edu.javeriana.bot.ast;

public class VarDeclAssign implements ASTNode {
	
	private String name;
	private ASTNode value;
	
	
	public VarDeclAssign(String name, ASTNode value) {
		super();
		this.name = name;
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Declaracion y asignacion");
		context.setVariable(name, value.execute(context));
		return null;
	}

}
