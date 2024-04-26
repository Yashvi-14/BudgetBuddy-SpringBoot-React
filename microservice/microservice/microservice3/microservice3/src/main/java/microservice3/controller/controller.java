package microservice3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import microservice3.entity.Transaction;
import microservice3.service.ReportService;

@RestController
public class controller {

	
	@Autowired
	ReportService ser;
	
	
	@GetMapping("/view/{user_id}")
	public List<Transaction> viewTran(@PathVariable int user_id){
		return ser.viewTran(user_id);
	}
	
	@GetMapping("/expense/{user_id}")
	public double getExpenseByType(@PathVariable int user_id) {
		return ser.getExpenseByType(user_id);
	}
	
	@GetMapping("/income/{user_id}")
	public double getIncomeByType(@PathVariable int user_id) {
		return  ser.getIncomeByType(user_id);
	}
}
