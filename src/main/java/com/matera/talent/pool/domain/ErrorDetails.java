package com.matera.talent.pool.domain;

/**
 * This class is a custom error response 
 */
public class ErrorDetails {
	
	private Long status;
	
	private Long timestamp;
	
	private String message;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
