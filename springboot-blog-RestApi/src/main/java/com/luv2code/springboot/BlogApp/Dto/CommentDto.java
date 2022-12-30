package com.luv2code.springboot.BlogApp.Dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.luv2code.springboot.BlogApp.Entity.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Model For comment Rest Api Blog App")
public class CommentDto {
	
	
	@ApiModelProperty(value="Comment Blog rest api id")
    private long id;
    
    
	@ApiModelProperty(value="Comment Blog rest api body")
	@NotEmpty
	@Size(min = 10 , message="The body  is required")
	private String body;
	
	@ApiModelProperty(value="Comment Blog rest api message")
	@NotEmpty(message="Email should not be null or empty")
	@Email
	private String email;
	
	@ApiModelProperty(value="Comment Blog rest api name")
	@NotEmpty
	@Size(min = 2 , message="The name  is required")
	private String name;

	
	
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

	public CommentDto(String body, String email, String name) {
		super();
		this.body = body;
		this.email = email;
		this.name = name;
	}
	
    public CommentDto() {
    	
    }

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", body=" + body + ", email=" + email + ", name=" + name + "]";
	}
	
    
    
	
	

}
