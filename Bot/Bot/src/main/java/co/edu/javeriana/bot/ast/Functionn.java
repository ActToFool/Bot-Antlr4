package co.edu.javeriana.bot.ast;

import java.util.List;

public class Functionn implements ASTNode {
	
	private String name;
	private List<String> parameters;
	private List<ASTNode> body;
	
	
	public Functionn(String name, List<String> parameters, List<ASTNode> body) {
		super();
		this.name = name;
		this.parameters = parameters;
		this.body = body;
	}
	
	
	
	//Inserta La funcion completa en la tabla de símbolos
	
	@Override
	public Object execute(Context context) {
		System.out.println("Declarando función");
		context.setVariable(name, this);
		return null;
	}



	public String getName() {
		return name;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public List<ASTNode> getBody() {
		return body;
	}

	
	

}
