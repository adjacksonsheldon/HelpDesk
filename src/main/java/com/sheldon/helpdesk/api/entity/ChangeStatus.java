package com.sheldon.helpdesk.api.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.sheldon.helpdesk.api.enums.StatusEnum;

@Entity
public class ChangeStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ticket_id", referencedColumnName = "id")
	private Ticket ticket;

	@ManyToOne(fetch = FetchType.LAZY)
	private User userChange;
	
	private Date dateChangeStatus;
	
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

	public Date getDateChangeStatus() {
		return dateChangeStatus;
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

	public void setDateChangeStatus(Date dateChangeStatus) {
		this.dateChangeStatus = dateChangeStatus;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}	
}
