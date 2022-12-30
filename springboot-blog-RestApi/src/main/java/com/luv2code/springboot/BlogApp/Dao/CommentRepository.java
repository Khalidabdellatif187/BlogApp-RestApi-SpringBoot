package com.luv2code.springboot.BlogApp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.BlogApp.Entity.Comment;

public interface CommentRepository extends JpaRepository <Comment , Long>{
	
	public List<Comment> findByPostId(long postId);
	
	

}
