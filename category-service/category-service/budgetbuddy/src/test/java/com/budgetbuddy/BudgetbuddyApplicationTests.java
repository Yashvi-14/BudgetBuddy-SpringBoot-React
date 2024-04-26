package com.budgetbuddy;
 
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
 
 
import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;
 
import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import java.util.Random;
 
import org.junit.Before;
 
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
 
//import com.budgetbuddy.Exception.NotAddedException;

import com.budgetbuddy.category.Category;

import com.budgetbuddy.categoryrepository.CategoryRepository;

import com.budgetbuddy.categoryservice.CategoryService;

import com.budgetbuddy.dao.CategoryDaoImp;
import com.budgetbuddy.exception.NotAddedException;
 


@SpringBootTest

class BudgetbuddyApplicationTests {

	@Autowired

	private CategoryDaoImp categoryDao;
 


	@Test

	public void testAddCategory() throws NotAddedException {

	    Category category = new Category();

	    Random random = new Random();

        int randomNum = random.nextInt(1000);

	    category.setName("Test Category" + randomNum);

	    int userId = 123;

	    Category result = categoryDao.addCategory(category, userId);

	    assertNotNull(result);

	    assertEquals("Test Category" + randomNum, result.getName());

	    assertEquals(userId, result.getUser_id());

	}

	@Test

	public void testDeleteCategory() throws NotAddedException {

	    int catId = 16;

	    int userId = 1;

	    boolean result = categoryDao.deleteCategory(catId, userId);

	    assertTrue(result);

	}

	@Test

	public void testDeleteCategory_NotFound() throws NotAddedException {

	    int catId = 99;

	    int userId = 123;

	    NotAddedException exception = assertThrows(NotAddedException.class, () -> {

	    	categoryDao.deleteCategory(catId, userId);

        });

        assertEquals("not deleted", exception.getMessage());

	}

	@Test

	public void testUpdateCategory() throws NotAddedException {

	    int catId = 16;

	    int budgetLimit = 1500;

	    int userId = 1;

	    Category result = categoryDao.updateCategory(catId, budgetLimit, userId);

	    assertNotNull(result);

	    assertEquals(catId, result.getCat_Id());

	    assertEquals(budgetLimit, result.getBudgetLimit());

	}

	@Test

	public void testUpdateCategory_NotFound() throws NotAddedException {

	    int catId = 1;

	    int budgetLimit = 1500;

	    int userId = 123;

	    NotAddedException exception = assertThrows(NotAddedException.class, () -> {

	    	categoryDao.updateCategory(catId, budgetLimit, userId);

        });

        assertEquals("not updated", exception.getMessage());

	}

	@Test

	public void testGetAllCategories() {

	    int userId = 123;

	    List<Category> result = categoryDao.getAllCategories(userId);

	    assertNotNull(result);

	    assertFalse(result.isEmpty());

	    }

	    @Test

	    public void testGetById() throws NotAddedException {

	        int catId = 16;

	        Category result = categoryDao.getById(catId);

	        assertNotNull(result);

	        assertEquals(catId, result.getCat_Id());

	    }

	    @Test

	    public void testGetById_NotFound() throws NotAddedException {

	        int catId = 5;

	        categoryDao.getById(catId);

	    }

	}


	//	  private CategoryDaoImp categoryDao;

	//	    private CategoryRepository categoryRepository;

	//

	//	    @Before

	//	    public void setUp() {

	////	        categoryRepository = new CategoryRepository(); // Initialize your CategoryRepository instance here

	////	        categoryDao = new CategoryDaoImp(categoryRepository); // Initialize your CategoryDaoImp instance here

	//	    }

	//

	//	    @Test

	//	    public void testAddCategory() throws NotAddedException {

	//	        Category category = new Category();

	//	        category.setName("Test Category");

	//	        category.setType("Test Type");

	//	        category.setBudgetLimit(1000);

	//

	//	        int userId = 1;

	//

	//	        Category result = categoryDao.addCategory(category, userId);

	//

	//	        assertNotNull(result);

	//	        assertEquals("Test Category", result.getName());

	//	        assertEquals("Test Type", result.getType());

	//	        assertEquals(1000, result.getBudgetLimit());

	//	        assertEquals(userId, result.getUser_id());

	//	    }

	//

	//	    @Test

	//	    public void testAddCategoryAlreadyExists() throws NotAddedException {

	//	        Category category = new Category();

	//	        category.setName("Existing Category");

	//	        category.setType("Test Type");

	//	        category.setBudgetLimit(1000);

	//

	//	        int userId = 1;

	//

	//	        categoryDao.addCategory(category, userId);

	//	        categoryDao.addCategory(category, userId); // This should throw NotAddedException

	//	    }

	//

	//	    @Test

	//	    public void testDeleteCategory() throws NotAddedException {

	//	        Category category = new Category();

	//	        category.setName("Test Category");

	//	        category.setType("Test Type");

	//	        category.setBudgetLimit(1000);

	//

	//	        int userId = 1;

	//

	//	        Category addedCategory = categoryDao.addCategory(category, userId);

	//	        int categoryId = addedCategory.getCat_Id();

	//

	//	        assertTrue(categoryDao.deleteCategory(categoryId, userId));

	//	    }

	//

	//	    @Test

	//	    public void testDeleteCategoryNotFound() throws NotAddedException {

	//	        int categoryId = 999; // Assuming this category ID doesn't exist

	//	        int userId = 1;

	//

	//	        categoryDao.deleteCategory(categoryId, userId); // This should throw NotAddedException

	//	    }

	//

	//	    @Test

	//	    public void testUpdateCategory() throws NotAddedException {

	//	        Category category = new Category();

	//	        category.setName("Test Category");

	//	        category.setType("Test Type");

	//	        category.setBudgetLimit(1000);

	//

	//	        int userId = 1;

	//

	//	        Category addedCategory = categoryDao.addCategory(category, userId);

	//	        int categoryId = addedCategory.getCat_Id();

	//

	//	        int newBudgetLimit = 1500;

	//

	//	        Category updatedCategory = categoryDao.updateCategory(categoryId, newBudgetLimit, userId);

	//

	//	        assertNotNull(updatedCategory);

	//	        assertEquals(newBudgetLimit, updatedCategory.getBudgetLimit());

	//	    }

	//

	//	    @Test

	//	    public void testUpdateCategoryNotFound() throws NotAddedException {

	//	        int categoryId = 999; // Assuming this category ID doesn't exist

	//	        int userId = 1;

	//	        int newBudgetLimit = 1500;

	//

	//	        categoryDao.updateCategory(categoryId, newBudgetLimit, userId); // This should throw NotAddedException

	//	    }

	//

	//	    @Test

	//	    public void testGetAllCategories() {

	//	        int userId = 1;

	//

	//	        List<Category> categories = categoryDao.getAllCategories(userId);

	//

	//	        assertNotNull(categories);

	//	        assertFalse(categories.isEmpty());

	//	    }

	//

	//	    @Test

	//	    public void testGetById() throws NotAddedException {

	//	        Category category = new Category();

	//	        category.setName("Test Category");

	//	        category.setType("Test Type");

	//	        category.setBudgetLimit(1000);

	//

	//	        int userId = 1;

	//

	//	        Category addedCategory = categoryDao.addCategory(category, userId);

	//	        int categoryId = addedCategory.getCat_Id();

	//

	//	        Category retrievedCategory = categoryDao.getById(categoryId);

	//

	//	        assertNotNull(retrievedCategory);

	//	        assertEquals(category.getName(), retrievedCategory.getName());

	//	        assertEquals(category.getType(), retrievedCategory.getType());

	//	        assertEquals(category.getBudgetLimit(), retrievedCategory.getBudgetLimit());

	//	    }

	//

	//	    @Test

	//	    public void testGetByIdNotFound() throws NotAddedException {

	//	        int categoryId = 999; // Assuming this category ID doesn't exist

	//

	//	        categoryDao.getById(categoryId); // This should throw NotAddedException

	//	    }	