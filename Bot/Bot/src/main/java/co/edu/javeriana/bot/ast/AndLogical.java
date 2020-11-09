package co.edu.javeriana.bot.ast;

public class AndLogical implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;
	
	

	public AndLogical(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public Object execute(Context context) {
		return ((Boolean)operand1.execute(context) && (Boolean)operand2.execute(context));
	}

}
