package com.nea.ws.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nea.ws.model.Comment;
import com.nea.ws.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") Long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") Long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId) {
		return commentService.getComment(messageId, commentId);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void removeComment(@PathParam("messageId") Long messageId, 
			   				  @PathParam("commentId") Long commentId) {
		commentService.removeComment(messageId, commentId);
	}
	
	
}
