package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;

public class MoverAbajo implements ASTNode {
	
	private Bot bot;
	private ASTNode value;

	public MoverAbajo(Object bot, ASTNode value) {
		super();
		this.bot = (Bot) bot;
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		System.out.println("Abajo");
		bot.down(Math.round((float) value.execute(context)));
		return null;
	}

}
