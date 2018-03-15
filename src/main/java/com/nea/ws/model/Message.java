package com.nea.ws.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
public class Message {
    private Long id;
    private String author;
    private String message;
    private Date created;
   // private Map<Long, Comment> comments = new HashMap<>();

    public Message() {
    }

    public Message(Long id, String author, String message) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
//    @XmlTransient
//    public Map<Long, Comment> getComments() {
//		return comments;
//	}
//    
//    public void setComments(Map<Long, Comment> comments) {
//		this.comments = comments;
//	}
}
