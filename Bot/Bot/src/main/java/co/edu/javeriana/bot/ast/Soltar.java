package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

public class Soltar implements ASTNode {
	
	private Bot bot;	

	public Soltar(Bot bot) {
		super();
		this.bot = bot;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Soltar");
		bot.drop();
		return null;
	}

}
