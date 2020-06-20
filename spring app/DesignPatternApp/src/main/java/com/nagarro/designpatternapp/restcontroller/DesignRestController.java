package com.nagarro.designpatternapp.restcontroller;

import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.designpatternapp.interfaces.Notification;
import com.nagarro.designpatternapp.dto.NotificationDTO;
import com.nagarro.designpatternapp.factory.NotifFactory;
import com.nagarro.designpatternapp.model.*;
import com.nagarro.designpatternapp.repository.*;
import com.nagarro.designpatternapp.service.LoginUserService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/api") 
public class DesignRestController {
	@Autowired
    private LoginUserService lService;

	@Autowired
    private LoginUserDetailsRepository udrepo;
	
	@Autowired
	private NotificationDetailsRepository tdrepo;
	
	 
	/*
	 * Registration
	 */
	@PostMapping("/user")  
    public UserDetails saveUser(@Valid @RequestBody UserDetails userdetails) { 
		System.out.println("running");        
		return udrepo.save(userdetails) ;
    }  

	/*
	 * clear notification
	 */
	
	@PostMapping("/removeData/{nid}")
	public List<NotificationDTO> removeNotif(@PathVariable(name="nid") int nid,@Valid @RequestBody int uid){

		UserDetails ud = udrepo.findById(uid);
		NotificationDetails nd=tdrepo.findById(nid);
		
		List<NotificationDetails> ndlist=new ArrayList<>();
		List<UserDetails> udlist=new ArrayList<>();
		
		ndlist=ud.getNotifications();
		ndlist.remove(nd);
		ud.setNotifications(ndlist);
		udrepo.save(ud) ;
		
		udlist=nd.getUsers();
		udlist.remove(ud);
		nd.setUsers(udlist);
		tdrepo.save(nd);
		
		Iterator<NotificationDetails> itr= ndlist.iterator();
		List<NotificationDTO> list=new ArrayList<NotificationDTO>();
		while(itr.hasNext())
		{	
			list.add(convertNotificationDetailsToNotification(itr.next()));
		}
		return list;
			
	}
	
	/*
	 * get notification
	 */
	@GetMapping("/getNotif/{uid}")
	public List<NotificationDTO> getNotif(@PathVariable(name="uid") int uid){
//		int id=Integer.parseInt(uid);  
		UserDetails u = lService.checkUserById(uid);
		List<NotificationDetails> notiflist=u.getNotifications();
		List<NotificationDTO> list=new ArrayList<NotificationDTO>();
		Iterator<NotificationDetails> itr= notiflist.iterator();
		 while(itr.hasNext())
		 {	
			 list.add(convertNotificationDetailsToNotification(itr.next()));
		 }
		 return list;
	    }
	

	public NotificationDTO convertNotificationDetailsToNotification(NotificationDetails nd){
		NotificationDTO notif=new NotificationDTO();
		notif.setnId(nd.getnId());
		notif.setType(nd.getType());
		notif.setRecipient(nd.getRecipient());
		notif.setDescription(nd.getDescription());
		return notif;
	}
	
	
	/*
	 * save notification
	 */
	@PostMapping("/sendNotif")  
    public NotificationDTO saveNotif(@Valid @RequestBody NotificationDTO notif) { 
			
		if(notif.getRecipient().equalsIgnoreCase("all")) {
			List<UserDetails> arr = new ArrayList<UserDetails>();
			arr=udrepo.findAll();
	        notif.setRecipientName("");
	        String str="";
			for (int j = 0; j < arr.size(); j++) { 
				str=str+arr.get(j).getEmail()+","; 
	        } 
			notif.setRecipientName(str);
		}
		else if(notif.getRecipient().equalsIgnoreCase("group")) {
			List<UserDetails> arr = new ArrayList<UserDetails>();
			List<UserDetails> arr2 = new ArrayList<UserDetails>();
			String[] arr3=notif.getRecipientName().split(",");
			for(String a:arr3) {
				arr2=udrepo.findByPost(a);
				arr.addAll(arr2);
				arr2=null;
			}
			notif.setRecipientName("");
			String str="";
			for (int j = 0; j < arr.size(); j++) { 
				str=str+arr.get(j).getEmail()+","; 
	        } 
			notif.setRecipientName(str);
		}
		
		NotificationDetails notification=convertNotifToDetail(notif);
		tdrepo.save(notification) ;
		NotifFactory nf=new NotifFactory();
		Notification notifIn=nf.getNotif(notif.getType());
		if(notif.getType()=="UrgentHelp")
		notifIn.sendNotification(notification, notif);
		
		String[] elements = notif.getRecipientName().split(",");
		List<UserDetails> ud=new ArrayList<>();
		UserDetails users=new UserDetails();
		for(String s:elements) {
			UserDetails u = lService.checkUser(s);
			List<NotificationDetails> l=new ArrayList<>();
			l=u.getNotifications();
			l.add(notification);
			u.setNotifications(l);
			udrepo.save(u) ;
			ud.add(u);
		}
		notification.setUsers(ud);
		tdrepo.save(notification) ;
		if(notif.getType()!="UrgentHelp")
		notifIn.sendNotification(notification, notif);
		
		return notif;
	}
	
	public NotificationDetails convertNotifToDetail(NotificationDTO notif){
		NotificationDetails nd=new NotificationDetails();
		nd.setnId(notif.getnId());
		nd.setType(notif.getType());
		nd.setRecipient(notif.getRecipient());
		nd.setDescription(notif.getDescription());
		return nd;
	}
	
	/*
	 * login user authentication
	 */
	@PostMapping("/loginUser")
	public int loginUser(@Valid @RequestBody UserDetails user) {
		String email=user.getEmail();
		String pass=user.getPassword();
		UserDetails u = lService.checkUser(email);
		if(u==null) {
			return 0;
		}
		else if(u.getPassword().equals(pass)) {
			return u.getuId();
		}
		else {
			return 0;
		}
	}
	
	
}
