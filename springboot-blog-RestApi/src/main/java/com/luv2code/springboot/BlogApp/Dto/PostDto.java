package com.luv2code.springboot.BlogApp.Dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="Post Model for rest apis blog app")
public class PostDto {
    private long id;
    
    
    @ApiModelProperty(value="Blog Post Id")
    @NotEmpty
    @Size(min=2 , message="the title must have at least two charcters")
    private String title;
    
    @ApiModelProperty(value="Blog Post Description")
    @NotEmpty
    @Size(min=10 , message="the description must have at least two charcters")
    private String description;
    
    @ApiModelProperty(value="Blog Post set of contents")
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    
    
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PostDto(String title, String description, String content) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
	}
	@Override
	public String toString() {
		return "PostDto [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + "]";
	}
    
    
    public PostDto() {
    	
    }
    
    
    
    
    
    
    
    
    
}
