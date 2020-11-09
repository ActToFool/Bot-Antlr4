package co.edu.javeriana.bot.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunFunction implements ASTNode {
	
	//RunFunction($ID.text, arguments)
	private String name;
	private List<ASTNode> arguments;
	
	
	public RunFunction(String name, List<ASTNode> arguments) {
		super();
		this.name = name;
		this.arguments = arguments;
	}


	//crea la tabla de datos de la funcion
	@Override
	public Object execute(Context context) {
		Functionn function = (Functionn)context.get(name);
		List<String> parameters = function.getParameters();
		List<ASTNode> body = function.getBody();
		
		
		
		Context TempContext = new Context();
		int counter0 = 0;
		for(ASTNode a : arguments) {
			context.setVariable(parameters.get(counter0), a.execute(context));
			counter0++;
		}
		
		if(parameters.size() != arguments.size()) {
			System.out.println("Cantidad de argumentos incorrecto");
		}else {
			int counter = 0;
			for(ASTNode a : arguments) {
				context.setVariable(parameters.get(counter),a.execute(context));
				counter++;
			}
			for(ASTNode b : body) {
				b.execute(context);
				
			}
		}
		
		return null;
	}

}
