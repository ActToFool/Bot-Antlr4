package co.edu.javeriana.bot.ast;

public class NotEqualNodes implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	
	public NotEqualNodes(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public Object execute(Context context) {
		return ((Float)operand1.execute(context) != (Float)operand2.execute(context));
	}

}
