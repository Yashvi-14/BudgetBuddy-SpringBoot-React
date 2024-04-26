package microservice3.entity;

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
public class Transaction {

	
	private int transaction_id;
    private String tran_Date;
    private double amount;
    private String notes;
    private int userId;
    private String categoryName;
    private int cat_Id;
    private String type;


}
