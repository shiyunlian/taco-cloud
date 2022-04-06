package tacos;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

//The IngredientRef class defines that linking between Taco and Ingredient
//@Data
@Table("Ingredient_Ref")
public class IngredientRef {
	
	private final String ingredient;

	public IngredientRef(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getIngredient() {
		return ingredient;
	}

}
