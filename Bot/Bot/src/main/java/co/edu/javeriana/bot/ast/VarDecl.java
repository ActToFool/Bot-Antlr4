package co.edu.javeriana.bot.ast;

public class VarDecl implements ASTNode {
	
	private String name;
	
	
	
	public VarDecl(String name) {
		super();
		this.name = name;
	}



	@Override
	public Object execute(Context context) {
		context.setVariable(name,  new Object());
		return null;
	}

}
