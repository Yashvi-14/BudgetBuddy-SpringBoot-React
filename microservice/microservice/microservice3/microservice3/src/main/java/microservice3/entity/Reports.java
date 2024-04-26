package microservice3.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="main")
public class Reports {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int report_id;
    private int userId;
    
    public Reports() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int name;

	public Reports(int report_id, int userId, int name) {
		super();
		this.report_id = report_id;
		this.userId = userId;
		this.name = name;
	}

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

		
}