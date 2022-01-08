package com.pintoeat.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pintoeat.api.model.Image;



public interface ImageRepository extends CrudRepository<Image, String> {

	public Image findByid(String id);
	
	public List<Image> findBypinId(String pinId);

}
