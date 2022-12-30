package com.luv2code.springboot.BlogApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.BlogApp.CustomException.BlogApiExceptions;
import com.luv2code.springboot.BlogApp.CustomException.ResourceNotFoundException;
import com.luv2code.springboot.BlogApp.Dao.CommentRepository;
import com.luv2code.springboot.BlogApp.Dao.PostRepository;
import com.luv2code.springboot.BlogApp.Dto.CommentDto;
import com.luv2code.springboot.BlogApp.Entity.Comment;
import com.luv2code.springboot.BlogApp.Entity.Post;

@Service
public class CommentDtoImpl implements CommentService {

	private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;
    
    public CommentDtoImpl(CommentRepository commentRepository, PostRepository postRepository , ModelMapper mapper ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
        
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity to DB
        Comment newComment =  commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    

    private CommentDto mapToDTO(Comment comment){

        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        //commentDto.setId(comment.getId());
        //commentDto.setName(comment.getName());
        //commentDto.setEmail(comment.getEmail());
        //commentDto.setBody(comment.getBody());
        return  commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        //comment.setId(commentDto.getId());
        //comment.setName(commentDto.getName());
        //comment.setEmail(commentDto.getEmail());
        //comment.setBody(commentDto.getBody());
        return  comment;
    }

	@Override
	public void deletComment(Long postId, long commentId) {
		
		Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "id" , postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment" , "id" , commentId));
		if(comment.getPost().getId() != post.getId()) {
			throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		
		commentRepository.delete(comment);
		 
		 
				
		
		
	}

}
