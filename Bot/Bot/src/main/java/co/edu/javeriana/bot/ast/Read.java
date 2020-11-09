package co.edu.javeriana.bot.ast;

import java.util.Scanner;

public class Read implements ASTNode {
	
	private String name;
	private Scanner data;
	
	public Read(String name) {
		super();
		this.name = name;
		this.data = new Scanner(System.in);
	}



	@Override
	public Object execute(Context context) {
		String entrada = data.nextLine();
		context.modifyOrSet(name, entrada);
		return null;
	}

}
