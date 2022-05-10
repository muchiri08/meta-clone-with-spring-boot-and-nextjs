package com.muchiri.facebookservice.service;

import java.util.List;

import com.muchiri.facebookservice.model.Post;

public interface PostService {

	Post addPost(Post post) throws Exception;

	List<Post> getPost();

}
