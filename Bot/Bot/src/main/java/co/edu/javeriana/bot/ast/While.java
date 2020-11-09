package co.edu.javeriana.bot.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class While implements ASTNode {
	private ASTNode condition;
	private List<ASTNode> body;
	
	

	public While(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}



	@Override
	public Object execute(Context context) {
		
		Context tempContext = new Context(context);
		
		
		//Map<String, Object> temp = new HashMap<String, Object>();
		
		
		/*for (Map.Entry<String, Object> entry : context.entrySet()) {
			temp.put(entry.getKey(), context.get(entry.getKey()));
		}*/
		
		while((boolean) condition.execute(tempContext)) {
			for(ASTNode n: body) {
				n.execute(tempContext);
			}
		}
		
		/*for (Map.Entry<String, Object> entry : context.entrySet()) {
			context.put(entry.getKey(), temp.get(entry.getKey()));
		}*/
		
		return null;
	}

}
