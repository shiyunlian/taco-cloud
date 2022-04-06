package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;

//public interface OrderRepository {
//	TacoOrder save(TacoOrder order);
//}

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
	
}