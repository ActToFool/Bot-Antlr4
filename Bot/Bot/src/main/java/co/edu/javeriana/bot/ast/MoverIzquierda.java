package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;

public class MoverIzquierda implements ASTNode {
	
	private Bot bot;
	private ASTNode value;

	public MoverIzquierda(Object bot, ASTNode value) {
		super();
		this.bot = (Bot) bot;
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Izquierda");
		bot.left(Math.round((float) value.execute(context)));
		return null;
	}

}
