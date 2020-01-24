package com.sheldon.helpdesk.api.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String getUsernameFromToken(String token) {
		String usernameString;
		
		try {
			final Claims claims = this.getClaimsFromToken(token);
			usernameString = claims.getSubject();
		} catch (Exception e) {
			usernameString = null;
		}
		
		return usernameString;
	}
	
	public Date getExpirationFromToken(String token) {
		
		Date expiration;
		
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
		
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationFromToken(token);
		
		return expiration.before(new Date());		
	}
	
	public String generateToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		
		final Date createdDate = new Date();
		claims.put(CLAIM_KEY_CREATED, createdDate);
				
		return this.doGenerateToken(claims);
	}
	
	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expiratedDate = new Date(createdDate.getTime() + expiration * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expiratedDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!this.isTokenExpired(token));
	}
	
	public String refreshToken(String token) {
		String refreshToken;
		
		try {
			final Claims claims = this.getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshToken = this.doGenerateToken(claims);
		} catch (Exception e) {
			refreshToken = null;
		}
		
		return refreshToken;
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String userName = this.getUsernameFromToken(token);
		
		return (userName.equals(user.getUsername()) 
							&& !this.isTokenExpired(token));		
	}
}
