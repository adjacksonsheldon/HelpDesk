package com.sheldon.helpdesk.api.entity;

import java.util.Date;

import com.sheldon.helpdesk.api.enums.StatusEnum;

public class ChangeStatus {
	private String id;
	
	private Ticket ticket;
	
	private User userChange;
	
	private Date dataChangeStatus;
	
	private StatusEnum status;

	public String getId() {
		return id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public User getUserChange() {
		return userChange;
	}

	public Date getDataChangeStatus() {
		return dataChangeStatus;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setUserChange(User userChange) {
		this.userChange = userChange;
	}

	public void setDataChangeStatus(Date dataChangeStatus) {
		this.dataChangeStatus = dataChangeStatus;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}	
}
