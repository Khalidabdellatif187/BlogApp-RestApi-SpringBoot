package com.luv2code.springboot.BlogApp.Service;



import java.util.List;

import com.luv2code.springboot.BlogApp.Dto.PostDto;
import com.luv2code.springboot.BlogApp.Dto.PostResponse;


public interface PostService {
	
	
    
	PostDto createPost(PostDto postDto);

    public PostResponse getAllPosts(int pageNo , int pageSize , String sortBy , String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
    
    
    
}
