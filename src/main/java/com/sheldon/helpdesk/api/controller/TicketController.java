package com.sheldon.helpdesk.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sheldon.helpdesk.api.service.TicketService;
import com.sheldon.helpdesk.api.service.UserService;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin("*")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired 
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

}
