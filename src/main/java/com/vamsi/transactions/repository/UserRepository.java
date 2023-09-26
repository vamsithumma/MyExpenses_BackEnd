package com.vamsi.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vamsi.transactions.entity.User;




@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.email=:emailid")
	public User findByEmail(@Param("emailid") String email);
	
	@Query("SELECT u FROM User u WHERE u.username=:name")
	public User findByUserName(@Param("name") String username);
	
	@Query("SELECT u FROM User u WHERE u.username=:name AND u.password=:pass")
	public User getLoginStatus(@Param("name") String username,@Param("pass") String password);

}
