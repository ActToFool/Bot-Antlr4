package co.edu.javeriana.bot.ast;

import java.util.List;

public class If implements ASTNode {
	private ASTNode condition;
	private List<ASTNode> body; 
	private List<ASTNode> elseBody;
	
	
	
	public If(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}
	
	

	@Override
	public Object execute(Context context) {
		
		
		
		Context tempContext = new Context(context);
		
		if((boolean)condition.execute(tempContext)) {
			for(ASTNode n: body) {
				n.execute(tempContext);
			}
		} else if(!elseBody.isEmpty()){
			for(ASTNode n: elseBody) {
				n.execute(tempContext);
			}
		}
		return null;

	}

}
