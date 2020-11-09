package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

public class Agarrar implements ASTNode {
	
	private Bot bot;
	

	public Agarrar(Bot bot) {
		super();
		this.bot = bot;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Agarrar");
		bot.pick();
		return null;
	}

}
