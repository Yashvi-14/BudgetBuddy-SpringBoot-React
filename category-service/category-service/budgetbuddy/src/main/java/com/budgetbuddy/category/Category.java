package com.budgetbuddy.category;
 
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.List;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name="category")
public class Category {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cat_Id;
	private String name;
	private int  budgetLimit;
	private String type;
	private int user_id;
//	private String icon;
//	private int user_id;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
public Category(int cat_Id, String name, int budgetLimit, String type, int user_id) {
	super();
	this.cat_Id = cat_Id;
	this.name = name;
	this.budgetLimit = budgetLimit;
	this.type = type;
	this.user_id = user_id;
}
public int getCat_Id() {
	return cat_Id;
}
public void setCat_Id(int cat_Id) {
	this.cat_Id = cat_Id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getBudgetLimit() {
	return budgetLimit;
}
public void setBudgetLimit(int budgetLimit) {
	this.budgetLimit = budgetLimit;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}

 
}		
