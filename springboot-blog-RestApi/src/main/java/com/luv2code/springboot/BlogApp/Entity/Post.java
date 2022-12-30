package com.luv2code.springboot.BlogApp.Entity;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;




@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "describtion", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;
    
    @OneToMany(mappedBy="post")
    private Set<Comment> comments = new HashSet<>();

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Post(String title, String description, String content) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
	}
    
    
    public Post() {
    	
    }

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}




  






