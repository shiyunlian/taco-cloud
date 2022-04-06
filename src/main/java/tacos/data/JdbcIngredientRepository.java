//package tacos.data;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import tacos.Ingredient;
//
////By annotating JdbcIngredientRepository with @Repository, you declare that
////it should be automatically discovered by Spring component scanning and instantiated as a bean
////in the Spring application context
////When Spring creates the JdbcIngredientRepository bean, it injects it with JdbcTemplate.
////That’s because when there’s only one constructor, Spring implicitly applies autowiring of
////dependencies through that constructor’s parameters. If there were more than one constructors, or
////if you just want autowiring to be explicitly stated, then you can annotate the constructor with @Autowired
////
//@Repository
//public class JdbcIngredientRepository implements IngredientRepository{
//	
//	private JdbcTemplate jdbcTemplate;
//	
//	//The constructor assigns JdbcTemplate to an instance variable that will be used in other methods to query and insert into the database.
//	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//
////	The findAll() method, expecting to return a collection of objects, uses JdbcTemplate ’s query() method. The
////	query() method accepts the SQL for the query as well as an implementation of Spring’s
////	RowMapper for the purpose of mapping each row in the result set to an object. query() also
////	accepts as its final argument(s) a list of any parameters required in the query. But, in this case,
////	there aren’t any required parameters.
//	@Override
//	public Iterable<Ingredient> findAll() {
//		return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
//	}
//
////	the findById() method will need to include a where clause in its query to compare
////	the value of the id column with the value of the id parameter passed into the method. Therefore,
////	the call to query() includes, as its final parameter, the id parameter. When the query is
////	performed, the ? will be replaced with this value.
//	@Override
//	public Optional<Ingredient> findById(String id) {
//		List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
//		return results.size()==0?Optional.empty():Optional.of(results.get(0));
//	}
//
//	@Override
//	public Ingredient save(Ingredient ingredient) {
//		jdbcTemplate.update("insert into Ingredient(id, name, type) values (?, ?, ?)", ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
//		return ingredient;
//	}
//	
//	private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException{
//		return new Ingredient(row.getString("id"), row.getString("name"), Ingredient.Type.valueOf(row.getString("type")));
//	}
//
//}
