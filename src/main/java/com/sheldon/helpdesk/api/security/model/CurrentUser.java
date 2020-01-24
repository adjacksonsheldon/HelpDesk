package com.sheldon.helpdesk.api.security.model;

import com.sheldon.helpdesk.api.entity.User;

public class CurrentUser {

	private String token;
	private User user;
	
	public CurrentUser(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setUser(User user) {
		this.user = user;
	}	
}
