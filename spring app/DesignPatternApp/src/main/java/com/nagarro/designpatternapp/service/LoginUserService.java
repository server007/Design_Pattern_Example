package com.nagarro.designpatternapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.nagarro.designpatternapp.model.User;
import com.nagarro.designpatternapp.model.UserDetails;
import com.nagarro.designpatternapp.repository.LoginUserDetailsRepository;
//import com.nagarro.designpatternapp.repository.LoginUserRepository;

@Service
@Transactional
public class LoginUserService {
//	@Autowired
//	private LoginUserRepository repo;
	
	@Autowired
	private LoginUserDetailsRepository repo2;

//	public void save(User user)
//	{
//		repo.save(user);
//	}
	
	public void save(UserDetails user)
	{
		repo2.save(user);
	}
	
	public UserDetails checkUserById(int uid) {
		return repo2.findById(uid);
	}
	
	public UserDetails checkUser(String email) {
		return repo2.findByEmail(email);
	}
//	public List<User> listAll()
//	{
//		return (List<User>) repo.findAll();
//	}
//	public User find(int id)
//	{
//		return repo.findById(id).get();
//	}
//	public void delete(int id)
//	{
//		repo.deleteById(id);
//	}
}
