package com.luv2code.springboot.BlogApp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id" , nullable=false)
	private long id;
	
	@Column(name="body" , nullable=false)
	private String body;
	
	@Column(name="email" , nullable=false)
	private String email;
	
	@Column(name="name" , nullable=false)
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name="post_id" , nullable=false)
	private Post post;
     
	public Comment() {
		
	}

	public Comment(String body, String email, String name) {
		super();
		this.body = body;
		this.email = email;
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", body=" + body + ", email=" + email + ", name=" + name + "]";
	}
	
	
	
	
	
	
	
	
	

}
