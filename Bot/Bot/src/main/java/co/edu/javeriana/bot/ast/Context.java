package co.edu.javeriana.bot.ast;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private Map<String, Object> table;
	protected Context previous;
	
	public Context(Context previous) {
		table = new HashMap<String, Object>();
		this.previous = previous;
	}

	public Context() {
		table = new HashMap<String, Object>();
		this.previous = null;
	}
	
	public void setTable(Map<String, Object> table) {
		this.table = table;
	}

	public void setVariable(String symbol, Object data) {
		if(this.table.containsKey(symbol)) {
			this.table.replace(symbol, data);
		}else {
			this.table.put(symbol, data);
		}
		
	}
	
	public void modifyOrSet(String symbol, Object data) {
		Object buscar = null;
		for(Context context = this; context != null; context = context.previous) {
			buscar = (Object) (context.table.get(symbol));
			if(buscar != null) {
				context.table.put(symbol, data);
				return;
			}
		}
	}
	
	
	
	public Object get(String symbol) {
		Object buscar = null;
		for (Context context = this; context != null; context = context.previous) {
			buscar = (Object) (context.table.get(symbol));
			if (buscar != null) {
				return buscar;
			}
		}
		return null;
	}
	
}
