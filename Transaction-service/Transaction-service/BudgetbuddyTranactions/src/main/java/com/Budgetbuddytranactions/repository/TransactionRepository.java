package com.Budgetbuddytranactions.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Budgetbuddytranactions.model.Transaction;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	public List<Transaction> findByUserId(int user_id);

}

