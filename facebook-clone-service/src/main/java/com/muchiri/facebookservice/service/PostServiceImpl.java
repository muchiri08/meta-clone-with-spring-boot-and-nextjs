package com.muchiri.facebookservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.muchiri.facebookservice.entity.PostEntity;
import com.muchiri.facebookservice.model.Post;
import com.muchiri.facebookservice.repository.PostEntityRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
	
	private PostEntityRepository postEntityRepository;

	@Override
	public Post addPost(Post post) throws Exception {
		
		try {
			
			PostEntity postEntity = new PostEntity();
			BeanUtils.copyProperties(post, postEntity);
			
			if (post.getFile() != null && !post.getFile().equalsIgnoreCase("null")) {
				postEntity.setImage(post.getFile());
			}
			else {
				postEntity.setImage(null);
			}
			
			postEntity = postEntityRepository.save(postEntity);
			
			post.setId(postEntity.getId());
			post.setFile(null);
			post.setImage(postEntity.getImage());
			
		} catch (Exception e) {
			
			throw new Exception("Could not save Post: "+e);
			
		}
		
		return post;
	}

	@Override
	public List<Post> getPost() {
		
		List<PostEntity> postEntities = postEntityRepository.findAll();
		List<Post> posts = new ArrayList<>();
		
		posts = postEntities.stream()
				.map((postEntity) -> 
					Post.builder()
						.id(postEntity.getId())
						.name(postEntity.getName())
						.email(postEntity.getEmail())
						.post(postEntity.getPost())
						.image(postEntity.getImage())
						.timeStamp(postEntity.getTimeStamp())
						.build())
				.collect(Collectors.toList());
		
		return posts;
	}
	
}
