package com.Budgetbuddytranactions.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    private int cat_Id;
    private String name;
    private int budgetLimit;
    private String type;
    private int userId; 
    
}

