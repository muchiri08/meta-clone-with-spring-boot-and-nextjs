package com.muchiri.facebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchiri.facebookservice.entity.PostEntity;

@Repository
public interface PostEntityRepository extends JpaRepository<PostEntity, String>{

}
