package com.vamsi.transactions.repository;

import org.springframework.stereotype.Repository;

import com.vamsi.transactions.entity.Transactions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;



@Repository
public interface TransactionRepository extends CrudRepository<Transactions, String>{
	//ASC DESC
	@Query(nativeQuery = true,value="SELECT * FROM Transactions WHERE user_id= :userid ORDER BY date DESC")
	public List<Transactions> getTransactionsByUserId(@Param("userid") Long userid);
	
	@Query(nativeQuery = true,value="SELECT * FROM Transactions WHERE date>= :start_date AND date<= :end_date AND user_id= :userid ORDER BY date ASC")
	public List<Transactions> getTransactionsByDates(@Param("start_date") Long startdate,@Param("end_date") Long enddate,@Param("userid") Long userid);

}