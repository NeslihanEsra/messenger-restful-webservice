package com.nea.ws.database;

import java.util.HashMap;
import java.util.Map;

import com.nea.ws.model.Message;
import com.nea.ws.model.Profile;

public class DatabaseOperaitons {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}
