package com.nagarro.designpatternapp.concreteclasses;

import com.nagarro.designpatternapp.dto.NotificationDTO;
import com.nagarro.designpatternapp.interfaces.Notification;
import com.nagarro.designpatternapp.model.NotificationDetails;
import com.nagarro.designpatternapp.service.CommunicationService;

public class NewPolicy implements Notification {

	@Override
	public void sendNotification(NotificationDetails nd,NotificationDTO notif) {
		CommunicationService commun=new CommunicationService();
		System.out.println("=======================================================================\n");
		commun.SendToEmail(notif);
	}

}
