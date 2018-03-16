package com.nea.ws.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.nea.ws.database.DatabaseOperaitons;
import com.nea.ws.exception.DataNotFoundException;
import com.nea.ws.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseOperaitons.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "aa", "aa"));
		messages.put(2L, new Message(2L, "bb", "bb"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(Long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId((long) (messages.size() + 1));
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMesage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(Long id) {
		return messages.remove(id);
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if (calendar.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesWithPagination(int start, int size) {
		List<Message> messagesWithPagination = new ArrayList<>(messages.values());
		if (start + size > messagesWithPagination.size()) {
			return new ArrayList<Message>();
		}
		return messagesWithPagination.subList(start, start + size);
	}
}
