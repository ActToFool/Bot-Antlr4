package co.edu.javeriana.bot.ast;

public class Inverse implements ASTNode {
	
	private float value;
	

	public Inverse(float value) {
		super();
		this.value = value;
	}


	@Override
	public Object execute(Context context) {
		return (value * (-1));
	}

}
