package tacos;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

//@Data
public class Taco {
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	
	@NotNull
	@Size(min=5, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	
	private Long id;
	
	private Date createdAt = new Date();
	

	public Taco() {}


	public Taco(@NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String name,
			@NotNull @Size(min = 5, message = "You must choose at least 1 ingredient") List<Ingredient> ingredients,
			Long id, Date createdAt) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.id = id;
		this.createdAt = createdAt;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
