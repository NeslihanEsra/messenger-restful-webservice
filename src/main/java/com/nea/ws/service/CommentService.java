package com.nea.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nea.ws.database.DatabaseOperaitons;
import com.nea.ws.model.Comment;
import com.nea.ws.model.ErrorMessage;
import com.nea.ws.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseOperaitons.getMessages();
	
	public List<Comment> getAllComments(Long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<>(comments.values());
	}
	
	public Comment getComment(Long messageId, Long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Bulunamadı", 404, "Hatanın açıklaması");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);  
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(response); 
		}
		return comment;
	}
	
	public Comment addComment(Long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId((long) (comments.size() + 1));
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(Long messageId, Comment comment) {
		Map<Long, Comment> commets = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		commets.put(comment.getId(), comment);
		return comment;
	}
	
	public void removeComment(Long messageId, Long commentId) {
		Map<Long, Comment> commets = messages.get(messageId).getComments();
		commets.remove(commentId);
	}
}
