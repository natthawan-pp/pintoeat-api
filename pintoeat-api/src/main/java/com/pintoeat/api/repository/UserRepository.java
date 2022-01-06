package com.pintoeat.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pintoeat.api.model.User;


public interface UserRepository extends CrudRepository<User, String> {
	
	public User findByid(String id);
	
	public User findByemail(String email);
	
	public User findByemailAndPassword(String email, String pass);
	
	@Query(value = "select * from user order by id asc", nativeQuery = true)
	public List<User> findAllOrderByAsc();
	
}
