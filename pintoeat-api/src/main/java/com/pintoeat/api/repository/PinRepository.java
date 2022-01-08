package com.pintoeat.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pintoeat.api.model.Folder;
import com.pintoeat.api.model.Pin;



public interface PinRepository extends CrudRepository<Pin, String> {

	public Pin findByid(String id);
	
	public Pin findByname(String name);
	
	List<Pin> findByfolderId(String folder);
	
	@Query(value = "select pin.id, pin.folder_id, pin.name, pin.description, pin.location, pin.is_favorite, pin.is_bookmark, pin.created_at, pin.updated_at from pin join folder on folder.id = pin.folder_id \r\n"
			+ "where is_bookmark = true and user_id =:userId", nativeQuery = true)
	List<Pin> findByBookmark(@Param("userId") String userId);
	
	@Query(value = "select pin.id,  pin.folder_id, pin.name, pin.description, pin.location, pin.is_favorite, pin.is_bookmark, pin.created_at, pin.updated_at from pin join folder on folder.id = pin.folder_id \r\n"
			+ "where user_id =:userId", nativeQuery = true)
	List<Pin> findPinByUserId(@Param("userId") String userId);
	

}
