package tacos;

import lombok.Data;

//@Data tells Lombok to generate all missing getter and setter methods and a constructor 
//that accepts all final properties as arguments at compile time
//@Data
public class Ingredient {
	
	private final String id;
	private final String name;
	private final Type type;
	
	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

	public Ingredient(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

}
