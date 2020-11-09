package co.edu.javeriana.bot.ast;

public class Bools implements ASTNode {
	
	private String value;
	
	

	public Bools(String value) {
		super();
		this.value = value;
	}



	@Override
	public Object execute(Context context) {
		if(value.equals("@T")) {
			return Boolean.parseBoolean("true");
		} else {
			return Boolean.parseBoolean("false");
		}
	}

}
