package com.mmd.hr.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logging")
public class Logging {

	@Column(name = "log_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;

	private String type;

	private String username;

	private LocalDateTime time = LocalDateTime.now();

	private String accessed;

	private String description;

	public Logging(){}

	public Logging(String type, String username, String accessed) {
		this.type = type;
		this.username = username;
		this.time = LocalDateTime.now();
		this.accessed = accessed;
	}

	public Logging(String type, String username, String accessed, String description) {
		this.type = type;
		this.username = username;
		this.accessed = accessed;
		this.description = description;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getAccessed() {
		return accessed;
	}

	public void setAccessed(String accessed) {
		this.accessed = accessed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}