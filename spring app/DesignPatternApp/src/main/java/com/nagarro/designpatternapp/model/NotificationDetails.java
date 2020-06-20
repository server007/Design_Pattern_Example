package com.nagarro.designpatternapp.model;


import java.io.File;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.jca.cci.connection.ConnectionFactoryUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="notification_details")
public class NotificationDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nid")
	private int nId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="type")
	private String type;
	
	@Column(name="recipient")
	private String recipient;
	
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	@JoinTable(name = "user_notif",
    joinColumns = { @JoinColumn(name = "nid") },
    inverseJoinColumns = { @JoinColumn(name = "uid") })
private List<UserDetails> users ;
	

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public List<UserDetails> getUsers() {
		return users;
	}

	public void setUsers(List<UserDetails> users) {
		this.users = users;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}