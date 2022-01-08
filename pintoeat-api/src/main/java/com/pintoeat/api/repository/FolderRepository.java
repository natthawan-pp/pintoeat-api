package com.pintoeat.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pintoeat.api.model.Folder;
import com.pintoeat.api.model.User;


public interface FolderRepository extends CrudRepository<Folder, String> {

	public Folder findByid(String id);
	
	public Folder findByname(String name);
	
	List<Folder> findByuserId(String userid);
}
