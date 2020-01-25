package com.sheldon.helpdesk.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sheldon.helpdesk.api.entity.User;
import com.sheldon.helpdesk.api.response.Response;
import com.sheldon.helpdesk.api.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

	@Autowired 
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<Response<User>> create(HttpServletRequest request, 
				@RequestBody User user, BindingResult result){

		Response<User> response = new Response<User>();

		try {
			this.validateCreateUser(user, result);
			
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				ResponseEntity.badRequest().body(response);
			}

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = userService.createOrUpdate(user);
			response.setData(userPersisted);

		} catch (DuplicateKeyException e) {
			response.getErros().add("E-mail already registered");
			ResponseEntity.badRequest().body(response);
		} catch (Exception e) {			
			response.getErros().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}		

		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	private ResponseEntity<Response<User>> update(HttpServletRequest request, 
			@RequestBody User user, BindingResult result){
		
		Response<User> response = new Response<User>();
		
		try {
			
			this.validateUpdateUser(user, result);
			
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.toString()));				
				ResponseEntity.badRequest().body(response);
			}else {
				
				user.setPassword(passwordEncoder.encode(user.getPassword()));				
				User userPersisted = this.userService.createOrUpdate(user);
				
				response.setData(userPersisted);
			}
			
		} catch (Exception e) {
			ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<User>> findById(@PathVariable("id") String id) {
		Response<User> response = new Response<User>();
		
		try {
			User user = null;
			user = this.userService.findById(Long.valueOf(id));
			
			if(user == null) {
				response.getErros().add("Usuário não encontrado");
				ResponseEntity.badRequest().body(response);
			}
			
			response.setData(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		
		User userDeleted = this.userService.findById(Long.valueOf(id));
		
		if(userDeleted == null) {
			response.getErros().add("Usuário não encontrado");
			ResponseEntity.badRequest().body(response);
		}else {
			this.userService.delete(Long.valueOf(id));
		}
		
		return ResponseEntity.ok(response);
	}
	

	@GetMapping(value = "{page}/{count}")
	public ResponseEntity<Response<Page<User>>> findAll(@PathVariable("page") int page, @PathVariable("count") int count){
		Response<Page<User>> response = new Response<Page<User>>();
		
		Page<User> users = this.userService.findAll(page, count);;
		response.setData(users);		
		
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateUser(User user, BindingResult result) {
		if(user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email não informado"));
		}
	}
	
	private void validateUpdateUser(User user, BindingResult result) {
		if(user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email não informado"));
		}else {
			User userUpdated = this.userService.findByEmail(user.getEmail());
			if(userUpdated == null) {
				result.addError(new ObjectError("User", "Usuário não encontrado"));
			}	
		}		
	}
	
}
