package com.nagarro.designpatternapp.interfaces;

import com.nagarro.designpatternapp.dto.NotificationDTO;
import com.nagarro.designpatternapp.model.NotificationDetails;
//import com.nagarro.designpatternapp.model.NotificationDetails;

public interface Notification {

	void sendNotification(NotificationDetails nd,NotificationDTO notif);
}
