package com.Budgetbuddytranactions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;


import com.Budgetbuddytranactions.model.Transaction;
import com.Budgetbuddytranactions.repository.TransactionRepository;
import com.Budgetbuddytranactions.transactiondao.TransactionDaoImp;

@SpringBootTest
class BudgetbuddyTranactionsApplicationTests {

	 @Autowired
	    private TransactionDaoImp transactionDao;
	 
	 @Autowired
	 private TransactionRepository repo;
//
//	    @Before
//	    public void setUp() {
//
//	    }

	    @Test
	    public void testAddTransaction() {
	        Transaction transaction = new Transaction();
	        transaction.setUserId(1);
	        transaction.setCategoryName("Test Category");
	        transaction.setAmount(100.00);

	        Transaction result = transactionDao.addTransaction(transaction);

	        assertNotNull(result);
	        assertEquals("Test Category", result.getCategoryName());
//	        assertEquals(100.00, result.getAmount(), 0.001);
	    }

	    @Test
	    public void testUpdateTransaction() {
	        Transaction transaction = new Transaction();
	        transaction.setUserId(1);
	        transaction.setCategoryName("Test Category");
	        transaction.setAmount(100.00);

	        Transaction addedTransaction = transactionDao.addTransaction(transaction);

	        addedTransaction.setCategoryName("Updated Category");
//	        addedTransaction.setAmount(200.00);

	        Transaction updatedTransaction = transactionDao.updateTransaction(addedTransaction.getTransaction_id(), addedTransaction);

	        assertNotNull(updatedTransaction);
	        assertEquals("Updated Category", updatedTransaction.getCategoryName());
//	        assertEquals(200.00, updatedTransaction.getAmount());
	    }

	    @Test
	    public void testDeleteTransaction() {
	        Transaction transaction = new Transaction();
	        transaction.setUserId(1);
	        transaction.setCategoryName("Test Category");
	        transaction.setAmount(100.00);

	        Transaction addedTransaction = transactionDao.addTransaction(transaction);
	        assertNotNull(addedTransaction);

	        transactionDao.deleteTransaction(addedTransaction.getTransaction_id());

	        // Verify that the transaction has been deleted
	        Optional<Transaction> deletedTransactionOptional = repo.findById(addedTransaction.getTransaction_id());
	        Assertions.assertFalse(deletedTransactionOptional.isPresent());
	    }


	}


