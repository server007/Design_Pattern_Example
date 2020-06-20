package com.nagarro.designpatternapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nagarro.designpatternapp.model.NotificationDetails;

public interface NotificationDetailsRepository extends CrudRepository<NotificationDetails, Integer>{
	@Query(value="Select * from notification_details where uid =?1 ", nativeQuery=true)
	public List<NotificationDetails> find(int uId);
	
	@Query(value="Select * from notification_details where nid =?1 ", nativeQuery=true)
	public NotificationDetails findById(int tId);
	
	@Query(value="Select * from notification_details ", nativeQuery=true)
	public List<NotificationDetails> findAll();

}