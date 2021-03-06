package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;

public class MoverDerecha implements ASTNode {
	
	private Bot bot;
	private ASTNode value;

	public MoverDerecha(Object bot, ASTNode value) {
		super();
		this.bot = (Bot) bot;
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Derecha");
		bot.right(Math.round((float)value.execute(context)));
		return null;
	}

}
