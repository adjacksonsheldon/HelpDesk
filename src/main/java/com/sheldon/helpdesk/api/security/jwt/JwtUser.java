package com.sheldon.helpdesk.api.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2092738232806804366L;
	
	private final Long id; 
	private final String username; 
	private final String password;
	Collection<? extends GrantedAuthority> authorities;

	public JwtUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}	
	
	@JsonIgnore
	public Long getId() {
		return id;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.password;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.username;
	}
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
