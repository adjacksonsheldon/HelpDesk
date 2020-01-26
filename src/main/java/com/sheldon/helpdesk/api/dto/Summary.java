package com.sheldon.helpdesk.api.dto;

import java.io.Serializable;

public class Summary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer amoutNew;
	private Integer amoutResolved;
	private Integer amoutApproved;
	private Integer amoutDisapproved;
	private Integer amoutClosed;
	private Integer amoutAssigned;
	
	public Integer getAmoutNew() {
		return amoutNew;
	}
	
	public void setAmoutNew(Integer amoutNew) {
		this.amoutNew = amoutNew;
	}
	
	public Integer getAmoutResolved() {
		return amoutResolved;
	}
	
	public void setAmoutResolved(Integer amoutResolved) {
		this.amoutResolved = amoutResolved;
	}
	
	public Integer getAmoutApproved() {
		return amoutApproved;
	}
	
	public void setAmoutApproved(Integer amoutApproved) {
		this.amoutApproved = amoutApproved;
	}
	
	public Integer getAmoutDisapproved() {
		return amoutDisapproved;
	}
	
	public void setAmoutDisapproved(Integer amoutDisapproved) {
		this.amoutDisapproved = amoutDisapproved;
	}
	
	public Integer getAmoutClosed() {
		return amoutClosed;
	}
	
	public void setAmoutClosed(Integer amoutClosed) {
		this.amoutClosed = amoutClosed;
	}

	public Integer getAmoutAssigned() {
		return amoutAssigned;
	}

	public void setAmoutAssigned(Integer amoutAssigned) {
		this.amoutAssigned = amoutAssigned;
	}
}
