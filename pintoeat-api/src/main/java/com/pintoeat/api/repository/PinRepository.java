package com.pintoeat.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pintoeat.api.model.Pin;


public interface PinRepository extends CrudRepository<Pin, String> {

	public Pin findByid(String id);
	
	
}
