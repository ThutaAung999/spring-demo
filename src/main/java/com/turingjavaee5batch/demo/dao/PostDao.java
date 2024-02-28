package com.turingjavaee5batch.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.turingjavaee5batch.demo.model.Post;

public interface PostDao extends   CrudRepository<Post, Long>{

}