package com.luv2code.springboot.BlogApp.Controller;



import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.BlogApp.Dto.PostDto;
import com.luv2code.springboot.BlogApp.Dto.PostResponse;
import com.luv2code.springboot.BlogApp.Service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="Crud Rest Api Operations for Post of Blog App")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    
    @ApiOperation(value="Get All posts Rest api")
    @GetMapping
    public PostResponse GetAllPosts(@RequestParam(value="pageNo" , defaultValue="0" , required=false) int pageNo , 
    		@RequestParam(value="pageSize" , defaultValue="10" , required=false) int pageSize , 
    		@RequestParam(value="sortBy" , defaultValue="id" , required=false) String sortBy , 
    		@RequestParam(value="sortDir" , defaultValue="asc" , required=false) String sortDir ) {
    	
    	return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
    
    
    
    

    // create blog post rest api
   @ApiOperation(value="Create A new post for Rest Api Blog App")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
   

    // get post by id
   @ApiOperation(value="Get individual post Rest api")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post by id rest api
   @ApiOperation(value="update individual Rest api")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid@RequestBody PostDto postDto, @PathVariable(name = "id") long id){

       PostDto postResponse = postService.updatePost(postDto, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
   
   @ApiOperation(value="Delete One Post Rest api")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
