package com.luv2code.springboot.BlogApp.Service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.BlogApp.CustomException.ResourceNotFoundException;
import com.luv2code.springboot.BlogApp.Dao.PostRepository;
import com.luv2code.springboot.BlogApp.Dto.PostDto;
import com.luv2code.springboot.BlogApp.Dto.PostResponse;
import com.luv2code.springboot.BlogApp.Entity.Post;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
	
	private ModelMapper mapper;
	
	

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository , ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

   
    

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // convert Entity into DTO
    private PostDto mapToDTO(Post post){
    	PostDto postdto = mapper.map(post, PostDto.class);
    	
       // PostDto postDto = new PostDto();
       // postDto.setId(post.getId());
       // postDto.setTitle(post.getTitle());
       // postDto.setDescription(post.getDescription());
       // postDto.setContent(post.getContent());
        return postdto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
       // post.setTitle(postDto.getTitle());
        //post.setDescription(postDto.getDescription());
        //post.setContent(postDto.getContent());
        return post;
    }

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
		
		Pageable pageaple = PageRequest.of(pageNo, pageSize , sort);
		
		Page<Post> posts1 = postRepository.findAll(pageaple);
		
		List<Post> posts2 = posts1.getContent();
		
		List<PostDto> content = posts2.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		
		PostResponse postresponse = new PostResponse();
		
		postresponse.setContent(content);
		postresponse.setPageNo(posts1.getNumber());
		postresponse.setPageSize(posts1.getSize());
		postresponse.setTotalPages(posts1.getTotalPages());
		postresponse.setTotalElements(posts1.getTotalElements());
		postresponse.setLast(posts1.isLast());
		
		return postresponse;
		
		
		
		
		
		
		
		
		
		
	}
}
