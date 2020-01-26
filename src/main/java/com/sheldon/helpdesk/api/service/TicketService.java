package com.sheldon.helpdesk.api.service;

import org.springframework.data.domain.Page;

import com.sheldon.helpdesk.api.entity.ChangeStatus;
import com.sheldon.helpdesk.api.entity.Ticket;

public interface TicketService {
	Ticket createOrUpdate(Ticket pTicket);
	
	Ticket findById(Long id);
	
	void delete(Long id);
	
	Page<Ticket> listTicket(int page, int count);
	
	ChangeStatus createChangeStatus(ChangeStatus pChangeStatus);
	
	Iterable<ChangeStatus> listChangeStatus(Long id);
	
	Page<Ticket> findByCurrentUser(int page, int count, Long userId);
	
	Page<Ticket> findByParameters(int page, int count, String title, String status, String priority);
	
	Page<Ticket> findByParametersAndUser(int page, int count, String title, String status, String priority, Long userId);
	
	Page<Ticket> findByNumber(int page, int count, Integer number);
	
	Iterable<Ticket> findAll();
	
	Page<Ticket> findByParametersAndAssignedUser(int page, int count, String title, String status, String priority, Long assignedUserId);
}
