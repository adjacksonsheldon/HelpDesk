package com.sheldon.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sheldon.helpdesk.api.entity.ChangeStatus;
import com.sheldon.helpdesk.api.entity.Ticket;
import com.sheldon.helpdesk.api.repository.ChangeStatusRepository;
import com.sheldon.helpdesk.api.repository.TicketRepository;
import com.sheldon.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	ChangeStatusRepository changeStatusRepository;
	
	@Override
	public Ticket createOrUpdate(Ticket pTicket) {
		return this.ticketRepository.save(pTicket);
	}

	@Override
	public Ticket findById(Long id) {
		return this.ticketRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		Ticket ticket = new Ticket();
		ticket.setId(id);
		this.ticketRepository.delete(ticket);		
	}

	@Override
	public Page<Ticket> listTicket(int page, int count) {
		return this.ticketRepository.findAll(PageRequest.of(page, count));
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus pChangeStatus) {
		return this.changeStatusRepository.save(pChangeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(Long id) {
		return null;
	}

	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Ticket> findByParametersAndUser(int page, int count, String title, String status, String priority,
			String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Ticket> findByNumber(int page, int count, Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Ticket> findByParametersAndAssignedUser(int page, int count, String title, String status,
			String priority, String assignedId) {
		// TODO Auto-generated method stub
		return null;
	}

}
