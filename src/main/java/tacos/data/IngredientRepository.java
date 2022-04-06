package tacos.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import tacos.Ingredient;

//Ingredient repository needs to perform these operations:
//Query for all ingredients into a collection of Ingredient objects
//Query for a single Ingredient by its id
//Save an Ingredient object

//Spring Data will automatically generate implementations for our repository interfaces at runtime.
//But it will only do that for interfaces that extends one of the repository interfaces provided by
//Spring Data. At the very least, our repository interfaces will need to extend Repository so that
//Spring Data knows to create the implementation automatically
//the Repository interface is parameterized. The first parameter is the type of the
//object to be persisted by this repository— in this case, Ingredient. The second parameter is the
//type of the persisted object’s ID field. For Ingredient, that’s String.

//public interface IngredientRepository extends Repository<Ingredient, String>{
//	
//	Iterable<Ingredient> findAll();
//	Optional<Ingredient> findById(String id);
//	Ingredient save(Ingredient ingredient);
//
//}

//While IngredientRepository will work as shown here by extending Repository, Spring
//Data also offers CrudRepository as a base interface for common operations, including the three
//methods we’ve defined in IngredientRepository. So, instead of extending Repository, it’s
//often easier to extend CrudRepository. Because CrudRepository already defines the methods you need, 
//there’s no need to explicitly define them in the IngredientRepository and OrderRepository interfaces.

//You might be thinking that you need to write the implementations for both repositories,
//including the dozen methods defined in CrudRepository.
//But that’s the good news about Spring Data—there’s no need to write an implementation! When
//the application starts, Spring Data will automatically generate an implementation on the fly. This
//means the repositories are ready to use from the get-go. Just inject them into the controllers and
//you’re done.
//What’s more, because Spring Data will automatically create implementations of these interfaces
//at runtime, you no longer need the explicit implementations in JdbcIngredientRepository
//and JdbcOrderRepository.
public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
}
