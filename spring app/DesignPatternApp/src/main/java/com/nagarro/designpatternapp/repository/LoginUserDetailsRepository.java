package com.nagarro.designpatternapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nagarro.designpatternapp.model.NotificationDetails;
import com.nagarro.designpatternapp.model.UserDetails;

public interface LoginUserDetailsRepository extends CrudRepository<UserDetails, Integer> {
	public UserDetails findByEmail(String email);
	
	@Query(value="Select * from user_details where uId =?1", nativeQuery=true)
	public UserDetails findById(int uId);
	
	@Query(value="Select * from user_details ", nativeQuery=true)
	public List<UserDetails> findAll();
	
	@Query(value="Select * from user_details where post =?1", nativeQuery=true)
	public List<UserDetails> findByPost(String post );
}
