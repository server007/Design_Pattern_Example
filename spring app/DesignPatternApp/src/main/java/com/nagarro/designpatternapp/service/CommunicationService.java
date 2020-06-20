package com.nagarro.designpatternapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.designpatternapp.dto.NotificationDTO;
import com.nagarro.designpatternapp.model.NotificationDetails;
import com.nagarro.designpatternapp.model.UserDetails;
import com.nagarro.designpatternapp.repository.LoginUserDetailsRepository;
import com.nagarro.designpatternapp.repository.NotificationDetailsRepository;

@Service
@Transactional
public class CommunicationService {

	@Autowired
    private LoginUserService lService;
	
	@Autowired
    private LoginUserDetailsRepository udrepo;
	
	@Autowired
	private NotificationDetailsRepository tdrepo;


	public void SendToPortal(NotificationDetails notification,NotificationDTO notif) {
		
		String[] elements = notif.getRecipientName().split(",");
		List<UserDetails> ud=new ArrayList<>();
		for(String s:elements) {
			LoginUserService lserv=new LoginUserService();
			UserDetails u=udrepo.findByEmail(s);					
//					
			List<NotificationDetails> l=new ArrayList<>();
			l=u.getNotifications();
			l.add(notification);
			u.setNotifications(l);
			udrepo.save(u) ;
			ud.add(u);
			System.out.println(u.getNotifications().get(0).getnId());
			System.out.println("===========================================================================\n");
		}
		notification.setUsers(ud);
		tdrepo.save(notification) ;
	}
	
	public void SendToEmail(NotificationDTO notif) {
		
		String[] elements = notif.getRecipientName().split(",");
		for(String s:elements) {
			
			System.out.println("Email is sent to the :"+s);
			System.out.println("Notification type :"+notif.getType());
			System.out.println("Notification Description :"+notif.getDescription()+"\n");
			System.out.println("=======================================================================");
		}
	}
	
	public void SendToMessage(NotificationDTO notif) {
		String[] elements = notif.getRecipientName().split(",");
		for(String s:elements) {
			
			System.out.println("Message is sent to the :"+s);
			System.out.println("Notification type :"+notif.getType());
			System.out.println("Notification Description :"+notif.getDescription()+"\n");
			System.out.println("=======================================================================");
		}
	}
}
