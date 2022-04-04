package tacos.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import tacos.Ingredient;
import tacos.IngredientRef;
import tacos.Taco;
import tacos.TacoOrder;

public class JdbcOrderRepository implements OrderRepository{
	
	private JdbcOperations jdbcOperations;
	
	public JdbcOrderRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

//	First, you create a PreparedStatementCreatorFactory
//	that describes the insert query along with the types of the query’s input fields. Because you’ll
//	later need to fetch the saved order’s ID, you also will need to call setReturnGeneratedKeys(true).
//	After defining the PreparedStatementCreatorFactory, you use it to create a
//	PreparedStatementCreator, passing in the values from the TacoOrder object that will be
//	persisted. The last field given to the PreparedStatementCreator is the date that the order is
//	created, which you’ll also need to set on the TacoOrder object itself so that the returned
//	TacoOrder will have that information available.
//	Now that you have a PreparedStatementCreator in hand, you’re ready to actually save the
//	order data by calling the update() method on JdbcTemplate, passing in the
//	PreparedStatementCreator and a GeneratedKeyHolder. After the order data has been saved,
//	the GeneratedKeyHolder will contain the value of the id field as assigned by the database and
//	should be copied into the TacoOrder object’s id property.
//	At this point, the order has been saved, but you need to also save the Taco objects associated
//	with the order. You can do that by calling saveTaco() for each Taco in the order.
	@Override
	@Transactional
	public TacoOrder save(TacoOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Taco_Order "+"(delivery_name, delivery_street, delivery_city, "+"delivery_state, delivery_zip, cc_number, "
				+"cc_expiration, cc_cvv, placed_at) "+"values (?,?,?,?,?,?,?,?,?)", 
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
				);
		
		pscf.setReturnGeneratedKeys(true);
		
		order.setPlaceAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
				order.getDeliveryName(),
				order.getDeliveryStreet(),
				order.getDeliveryCity(),
				order.getDeliveryState(),
				order.getDeliveryZip(),
				order.getCcNumber(),
				order.getCcExpiration(),
				order.getCcCVV(),
				order.getPlaceAt()));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long orderId = keyHolder.getKey().longValue();
		order.setId(orderId);
		
		List<Taco> tacos = order.getTacos();
		int i = 0;
		for (Taco taco: tacos) {
			saveTaco(orderId, i++, taco);
		}
		
		return order;
	}
	
	private long saveTaco(Long orderId, int orderKey, Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco "+
				"(name, created_at, taco_order, taco_order_key) "+"values (?, ?, ?, ?)", 
				Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
		
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId, orderKey));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long tacoId = keyHolder.getKey().longValue();
		taco.setId(tacoId);
		
		//create a row in the Ingredient_Ref table to link the Taco row to an Ingredient row.
		saveIngredientRefs(tacoId, taco.getIngredients());
		
		return tacoId;
		
	}

	private void saveIngredientRefs(long tacoId, List<Ingredient> Ingredients) {
		
		int key = 0;
		for (Ingredient ingredient: Ingredients) {
			jdbcOperations.update("insert into Ingredient_Ref (ingredient, taco, taco_key) "+"values (?, ?, ?)", ingredient, tacoId, key++);
		}
	}
	
	

}
