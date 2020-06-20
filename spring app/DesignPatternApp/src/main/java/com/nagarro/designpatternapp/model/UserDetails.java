package com.nagarro.designpatternapp.model;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.jca.cci.connection.ConnectionFactoryUtils;

@Entity
@Table(name="user_details")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uid")
	private int uId;
	
	@Column(name="post")
	private String post;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToMany(mappedBy="users", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	private List<NotificationDetails> notifications;
	//	private List<NotificationDetails> notification;



	public int getuId() {
		return uId;
	}

	public List<NotificationDetails> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationDetails> notifications) {
		this.notifications = notifications;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}



