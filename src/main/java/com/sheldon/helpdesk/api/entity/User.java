package com.sheldon.helpdesk.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sheldon.helpdesk.api.enums.ProfileEnum;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String email;
	
	@Size(min = 6)
	private String password;
	
	private ProfileEnum profile;

	public User() {
	}

	public User(Long id, String email, @Size(min = 6) String password, ProfileEnum profile) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}
}
