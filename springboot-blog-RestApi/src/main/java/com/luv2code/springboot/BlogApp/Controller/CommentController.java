package com.luv2code.springboot.BlogApp.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.BlogApp.Dto.CommentDto;
import com.luv2code.springboot.BlogApp.Service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="Crud Operations For Comment Rest Api of Blog App")
@RestController
@RequestMapping("/api")
public class CommentController {

	
	private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value="Create New Comment Of Blog App For Rest Apis")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @ApiOperation(value="Get all comments for rest apis blog app")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @ApiOperation(value="Get individual comment for rest apis blog app")
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @ApiOperation(value="update individual comment for rest apis blog app")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                   @Valid@RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @ApiOperation(value="delete individual comment for rest apis blog app")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deletById(@PathVariable(value="postId") long postId , 
    		@PathVariable(value="commentId") long commentId){
    	
    	commentService.deletComment(postId, commentId);
    	return new ResponseEntity<>("delete comment of " + commentId + " that included to post " + postId , HttpStatus.OK);
    	
    }
	
}
