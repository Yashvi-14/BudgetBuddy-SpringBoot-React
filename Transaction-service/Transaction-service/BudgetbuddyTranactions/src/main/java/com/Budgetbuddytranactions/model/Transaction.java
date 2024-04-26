package com.Budgetbuddytranactions.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;
    private String tran_Date;
    private double amount;
    private String notes;
    private int userId;
    private String categoryName;
    private int cat_Id;
    private String type;
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCat_Id() {
		return cat_Id;
	}

	public void setCat_Id(int cat_Id) {
		this.cat_Id = cat_Id;
	}

	public Transaction() {
        super();
    }

	

	public Transaction(int transaction_id, String tran_Date, double amount, String notes, int userId,
			String categoryName, int cat_Id, String type) {
		super();
		this.transaction_id = transaction_id;
		this.tran_Date = tran_Date;
		this.amount = amount;
		this.notes = notes;
		this.userId = userId;
		this.categoryName = categoryName;
		this.cat_Id = cat_Id;
		this.type = type;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTran_Date() {
		return tran_Date;
	}

	public void setTran_Date(String tran_Date) {
		this.tran_Date = tran_Date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

    
}

