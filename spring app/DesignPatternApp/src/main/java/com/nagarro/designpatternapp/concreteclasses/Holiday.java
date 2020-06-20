package com.nagarro.designpatternapp.concreteclasses;

import com.nagarro.designpatternapp.dto.NotificationDTO;
import com.nagarro.designpatternapp.interfaces.Notification;
import com.nagarro.designpatternapp.model.NotificationDetails;
import com.nagarro.designpatternapp.service.CommunicationService;

public class Holiday implements Notification {

	@Override
	public void sendNotification(NotificationDetails nd,NotificationDTO notif) {
		CommunicationService commun=new CommunicationService();
//		commun.SendToPortal(nd, notif);
//		return true;
	}

}
