package microservice3.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import microservice3.entity.Transaction;



@FeignClient(name="BudgetbuddyTranactions",url="http://localhost:6500")
public interface RandomService {
	
	
	@GetMapping("/viewTransaction/{user_id}")
	public List<Transaction> viewTransaction(@PathVariable int user_id);
	

}
