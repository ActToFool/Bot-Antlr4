package co.edu.javeriana.bot.ast;

public class NotLogical implements ASTNode {
	
	private ASTNode value;
	

	public NotLogical(ASTNode value) {
		super();
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		return !((Boolean)value.execute(context));
	}

}
