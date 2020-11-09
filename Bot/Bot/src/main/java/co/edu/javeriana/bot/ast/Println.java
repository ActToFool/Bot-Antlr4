package co.edu.javeriana.bot.ast;

public class Println implements ASTNode {

	private ASTNode data;
	
	public Println(ASTNode data) {
		super();
		this.data = data;
	}



	@Override
	public Object execute(Context context) {
		System.out.println(data.execute(context));
		return null;
	}

}
