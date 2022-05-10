package com.muchiri.facebookservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muchiri.facebookservice.model.Post;
import com.muchiri.facebookservice.service.PostService;

import lombok.AllArgsConstructor;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
public class PostController {
	
	private PostService postService;
	
	@PostMapping
	public Post addPost(@RequestParam Map<String, String> requestParam) throws Exception {
	
		String strPost = requestParam.get("post");
		String strEmail = requestParam.get("email");
		String strName = requestParam.get("name");
		String strFile = requestParam.get("file");
		String strProfilePic = requestParam.get("profilePic");
		
		Post post = Post.builder()
				.file(strFile)
				.name(strName)
				.email(strEmail)
				.profilePic(strProfilePic)
				.post(strPost)
				.timeStamp(new Date().toString())
				.build();
		
		post = postService.addPost(post);
		
		return post;
		
	}
	
	@GetMapping
	public List<Post> getPost(){
		
		return postService.getPost();
		
	}

}
