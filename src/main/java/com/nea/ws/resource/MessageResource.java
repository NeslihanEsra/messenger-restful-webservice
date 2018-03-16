package com.nea.ws.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.nea.ws.model.Message;
import com.nea.ws.resource.bean.MessageFilterBean;
import com.nea.ws.service.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	private MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize()> 0) {
			return messageService.getAllMessagesWithPagination(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");	
		message.addLink(getUriForProfile(uriInfo, message), "profile");	
		message.addLink(getUriForComment(uriInfo, message), "comment");	
		return message;	
	}

	private String getUriForComment(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
					  .path(MessageResource.class)
					  .path(MessageResource.class, "getCommentResource")
					  .path(CommentResource.class)
					  .resolveTemplate("messageId", message.getId())
					  .build()
					  .toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
					  .path(ProfileResource.class)
					  .path(message.getAuthor())
					  .build()
					  .toString();
		 
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(String.valueOf(message.getId()))
							.build()
							.toString();
	}

	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(message.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response //.status(Status.CREATED)
				       .created(uri)
					   .entity(newMessage)
					   .build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long id, Message message) {
		message.setId(id);
		return messageService.updateMesage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") Long id) {
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}