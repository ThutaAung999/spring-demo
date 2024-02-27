package com.turingjavaee5batch.demo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingjavaee5batch.demo.dao.PostDao;
import com.turingjavaee5batch.demo.model.Post;
import com.turingjavaee5batch.demo.model.dto.PostDto;
import com.turingjavaee5batch.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;

	//ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public Iterable<Post> getAllPost() {
		return postDao.findAll();
	}

	@Override
	public PostDto savePost(PostDto postDto) {
	
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		
		post=this.postDao.save(post);
		
		PostDto savedDto= new PostDto();
		
		savedDto.setId(post.getId());
		savedDto.setTitle(post.getTitle());
		savedDto.setContent(post.getContent());
		
		return savedDto;
	}

	
	@Override
	public PostDto updatePost(PostDto postDto) {

		Post post=new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		
		post=this.postDao.save(post);
		
		PostDto savedDto= new PostDto();
		
		savedDto.setId(post.getId());
		savedDto.setTitle(post.getTitle());
		savedDto.setContent(post.getContent());
		
		return savedDto;
	}
	
	
	@Override
	public Optional<PostDto> getPostById(Long id) {
		
		Optional<Post> result =this.postDao.findById(id);
		
		if(result.isPresent())
		{
			Post post = result.get();
				
			PostDto postDto=new PostDto();
			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setContent(post.getContent());
			
			
			return Optional.of(postDto);
		}
		else
		{
			return Optional.empty();
		}
		
	}

	
	
	@Override
	public void deletePostById(Long postId) {
		this.postDao.deleteById(postId);
		
	}
	
}
