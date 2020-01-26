package com.sheldon.helpdesk.api.controller;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.sheldon.helpdesk.api.entity.Ticket;
import com.sheldon.helpdesk.api.entity.User;
import com.sheldon.helpdesk.api.enums.ProfileEnum;
import com.sheldon.helpdesk.api.enums.StatusEnum;
import com.sheldon.helpdesk.api.response.Response;
import com.sheldon.helpdesk.api.security.jwt.JwtTokenUtil;
import com.sheldon.helpdesk.api.service.TicketService;
import com.sheldon.helpdesk.api.service.UserService;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin("*")
public class TicketController {

	@Autowired
	TicketService ticketService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired 
	private UserService userService;

	@PostMapping
	public ResponseEntity<Response<Ticket>> create(HttpServletRequest request, 
				@RequestBody Ticket ticket, BindingResult result){

		Response<Ticket> response = new Response<Ticket>();
		
		try {
			
			this.validateCreateTicket(ticket, result);
			
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.toString()));				
				return ResponseEntity.badRequest().body(response);
			}
				
			ticket.setStatus(StatusEnum.New);
			ticket.setUser(this.userFromRequest(request));
			ticket.setDate(new Date());
			ticket.setNumber(this.generateNumber());
			Ticket ticketPersisted = this.ticketService.createOrUpdate(ticket);
			
			response.setData(ticketPersisted);
		} catch (Exception e) {
			ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		
		try {
			
			Ticket ticketDeleted = this.ticketService.findById(Long.valueOf(id));
			
			if(ticketDeleted == null) {
				response.getErros().add("Id não encontrado");
				return ResponseEntity.badRequest().body(response);
			}
			
			this.ticketService.delete(Long.valueOf(id));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}		
		
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<Response<Ticket>> update(HttpServletRequest request, 
				@RequestBody Ticket ticket, BindingResult result){

		Response<Ticket> response = new Response<Ticket>();
		
		try {
			
			this.validateUpdateTicket(ticket, result);
			
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.toString()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Ticket ticketCurrent = this.ticketService.findById(ticket.getId());

			ticket.setStatus(StatusEnum.New);
			ticket.setUser(this.userFromRequest(request));
			ticket.setDate(new Date());
			ticket.setNumber(this.generateNumber());
			
			if(ticketCurrent.getAssignedUser() != null) {
				ticket.setAssignedUser(ticketCurrent.getAssignedUser());
			}
			
			this.ticketService.createOrUpdate(ticket);
			
			response.setData(ticket);
		} catch (Exception e) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Ticket>> findById(@PathVariable("id") String id){
		Response<Ticket> response = new Response<Ticket>();
		
		Ticket ticket = this.ticketService.findById(Long.valueOf(id));
		response.setData(ticket);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	public ResponseEntity<Response<Page<Ticket>>> findAll(HttpServletRequest request, 
			@PathVariable("page") int page, @PathVariable("count") int count){
		
		Response<Page<Ticket>> response = new Response<>();
		Page<Ticket> tickets;
		
		User user = this.userFromRequest(request);
		
		if(user.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
			tickets = this.ticketService.listTicket(page, count);
		}else {
			tickets = this.ticketService.findByCurrentUser(page, count, user.getId());
		}
		
		response.setData(tickets);
		
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateTicket(Ticket ticket, BindingResult result) {	
		if(ticket.getTitle() == null) {
			result.addError(new ObjectError("Ticket", "Título não informado"));
		}		
	}
	
	private void validateUpdateTicket(Ticket ticket, BindingResult result) {	
		if(ticket.getId() == null) {
			result.addError(new ObjectError("Ticket", "Id não informado"));
		}
		if(ticket.getTitle() == null) {
			result.addError(new ObjectError("Ticket", "Título não informado"));
		}
	}
	
	private User userFromRequest(HttpServletRequest request) {
		User user = null;
		
		String token = request.getHeader("Authorization");
		String email = this.jwtTokenUtil.getUsernameFromToken(token);
		user = this.userService.findByEmail(email);
		
		return user;
	}
	
	private Integer generateNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
}
