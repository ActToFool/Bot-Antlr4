package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;

public class MoverArriba implements ASTNode {
	
	private Bot bot;
	private ASTNode value;

	public MoverArriba(Object bot, ASTNode value) {
		super();
		this.bot = (Bot) bot;
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Arriba");
		bot.up(Math.round((float) value.execute(context)));
		return null;
	}

}
