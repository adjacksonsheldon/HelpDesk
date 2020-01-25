package com.sheldon.helpdesk.api.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sheldon.helpdesk.api.enums.PriorityEnum;
import com.sheldon.helpdesk.api.enums.StatusEnum;

@Entity
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	private Date date;
	
	private String title;
	
	private Integer number;
	
	private StatusEnum status;
	
	private PriorityEnum priority;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "assigned_user_id", referencedColumnName = "id")
	private User assignedUser;
	
	private String description;
	
	private String image;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "ticket_id")
	private List<ChangeStatus> changes;	

	public Ticket(Long id, User user, Date date, String title, Integer number, StatusEnum status, PriorityEnum priority,
			User assignedUser, String description, String image, List<ChangeStatus> changes) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.title = title;
		this.number = number;
		this.status = status;
		this.priority = priority;
		this.assignedUser = assignedUser;
		this.description = description;
		this.image = image;
		this.changes = changes;
	}

	public Ticket() {
		super();
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public Integer getNumber() {
		return number;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public List<ChangeStatus> getChanges() {
		return changes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setChanges(List<ChangeStatus> changes) {
		this.changes = changes;
	}	
}
