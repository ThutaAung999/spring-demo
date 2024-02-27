package com.turingjavaee5batch.demo.services;

import java.util.Optional;

import com.turingjavaee5batch.demo.model.Post;
import com.turingjavaee5batch.demo.model.dto.PostDto;

public interface PostService {
	Iterable<Post>  getAllPost();
	Optional<PostDto> getPostById(Long id);
	PostDto savePost(PostDto post);
	PostDto updatePost(PostDto post);
	void deletePostById(Long id);

}
