package com.nagarro.designpatternapp.concreteclasses;

import com.nagarro.designpatternapp.dto.NotificationDTO;
import com.nagarro.designpatternapp.interfaces.Notification;
import com.nagarro.designpatternapp.model.NotificationDetails;
import com.nagarro.designpatternapp.service.CommunicationService;

public class Event implements Notification {

	@Override
	public void sendNotification(NotificationDetails nd,NotificationDTO notif) {
		System.out.println("=======================================================================\n");	
		CommunicationService commun=new CommunicationService();
		commun.SendToEmail(notif);
//		return true;
	}

}
