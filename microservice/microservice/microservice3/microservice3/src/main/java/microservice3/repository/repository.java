package microservice3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice3.entity.Reports;
import microservice3.entity.Transaction;


@Repository
public interface repository extends JpaRepository<Reports,Integer> {


	
	

}
