package com.nagarro.designpatternapp.factory;

import com.nagarro.designpatternapp.concreteclasses.Event;
import com.nagarro.designpatternapp.concreteclasses.Holiday;
import com.nagarro.designpatternapp.concreteclasses.NagarroNews;
import com.nagarro.designpatternapp.concreteclasses.NewPolicy;
import com.nagarro.designpatternapp.concreteclasses.UrgentHelp;
import com.nagarro.designpatternapp.interfaces.Notification;
import com.nagarro.designpatternapp.model.NotificationDetails;

public class NotifFactory {

	public Notification getNotif(String notif) {
		switch(notif) {
		case("Event"):return new Event();
		case("Holiday"):return new Holiday();
		case("NagarroNews"):return new NagarroNews();
		case("NewPolicy"):return new NewPolicy();
		case("UrgentHelp"):return new UrgentHelp();
		default:return null;
		}
	}
}
