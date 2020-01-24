package com.sheldon.helpdesk.api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sheldon.helpdesk.api.entity.User;
import com.sheldon.helpdesk.api.repository.UserRepository;
import com.sheldon.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User findById(Long id) {		
		return this.userRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		this.userRepository.delete(new User(id, null, null, null) );
	}

	@Override
	public Page<User> findAll(int page, int count) {		
		return userRepository.findAll(PageRequest.of(page, count));
	}

}
