package com.luv2code.springboot.BlogApp.Service;

import java.util.List;

import com.luv2code.springboot.BlogApp.Dto.CommentDto;

public interface CommentService {
	
	    CommentDto createComment(long postId, CommentDto commentDto);

	    List<CommentDto> getCommentsByPostId(long postId);

	    CommentDto getCommentById(Long postId, Long commentId);

	    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);
	    
	    public void deletComment(Long postId , long commentId);

}
