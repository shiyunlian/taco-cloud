package tacos;

import lombok.Data;

//The IngredientRef class defines that linking between Taco and Ingredient
//@Data
public class IngredientRef {
	
	private final String ingredient;

	public IngredientRef(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getIngredient() {
		return ingredient;
	}

}
