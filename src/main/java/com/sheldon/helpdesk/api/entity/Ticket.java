package com.sheldon.helpdesk.api.entity;


import java.util.Date;
import java.util.List;

import com.sheldon.helpdesk.api.enums.PriorityEnum;
import com.sheldon.helpdesk.api.enums.StatusEnum;


public class Ticket {

	private String id;
	
	private User user;
	
	private Date date;
	
	private String title;
	
	private Integer number;
	
	private StatusEnum status;
	
	private PriorityEnum priority;
	
	private User assignedUser;
	
	private String description;
	
	private String image;
	
	private List<ChangeStatus> changes;

	public String getId() {
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

	public void setId(String id) {
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
