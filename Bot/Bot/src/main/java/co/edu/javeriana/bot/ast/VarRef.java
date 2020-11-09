package co.edu.javeriana.bot.ast;

public class VarRef implements ASTNode {
	
	private String name;
	
	 
	
	public VarRef(String name) {
		super();
		this.name = name;
	}



	@Override
	public Object execute(Context context) {
		return context.get(name);
	}

}
