package com.spring;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 
import com.spring.entity.Category;
import com.spring.entity.Transaction;
import com.spring.service.NotificationService;
 
class NotificationServiceApplicationTests {
 
	@Autowired
    private NotificationService notificationService;
 
    @BeforeEach
    void setUp() {
        notificationService = new NotificationService();
    }
 
    @Test
    void testCheckLimit_NoTransactions()
    {
    	List<Transaction> transactions = new ArrayList<>();
    	transactions.add(new Transaction(1, "2024-04-15", 100.0, "Test", 2, "Category1", 1, "Expense"));
    	notificationService.getTransaction = user_id -> transactions;
        notificationService.getCategory = user_id -> new ArrayList<>();
        String result = notificationService.checkLimit(1);
        assertEquals("No Limit exceeded", result);
    }
 
    @Test
    void testCheckLimit_NoCategoryLimit() 
    {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, "2024-04-15", 100.0, "Test", 1, "Category1", 1, "Expense"));
        notificationService.getTransaction = user_id -> transactions;
        notificationService.getCategory = user_id -> new ArrayList<>();
        String result = notificationService.checkLimit(1);
        assertEquals("No Limit exceeded", result);
    }
 
    @Test
    void testCheckLimit_LimitNotExceeded() 
    {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, "2024-04-15", 50.0, "Test", 1, "Category1", 1, "Expense"));
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category1", 100,"Expense"));
        notificationService.getTransaction = user_id -> transactions;
        notificationService.getCategory = user_id -> categories;
        String result = notificationService.checkLimit(1);
        assertEquals("No Limit exceeded", result);
    }
 
    @Test
    void testCheckLimit_LimitExceeded() 
    {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, "2024-04-15", 150.0, "Test", 1, "Category1", 1, "Expense"));
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category1", 100,"Expense"));
        notificationService.getTransaction = user_id -> transactions;
        notificationService.getCategory = user_id -> categories;
        String result = notificationService.checkLimit(1);
        assertEquals("For Category1 limit exceeded by 50.0. Your actual limit is 100. Your total expense in Category1 is 150.0", result);
    }
 
    @Test
    void testCheckDailyTransactions_ExpenseSubmitted() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, LocalDate.now().minusDays(1).toString(), 100.0, "Test", 1, "Category1", 1, "Expense"));
        notificationService.getTransaction = user_id -> transactions;
        String result = notificationService.checkDailyTransactions(1);
        assertEquals("Expense submitted for today.", result);
    }
 
    @Test
    void testCheckDailyTransactions_NoExpenseSubmitted() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, LocalDate.now().minusDays(2).toString(), 100.0, "Test", 1, "Category1", 1, "Expense"));
        notificationService.getTransaction = user_id -> transactions;
        String result = notificationService.checkDailyTransactions(1);
        assertEquals("You haven't submitted your expenses for today.", result);
    }
}
 
            