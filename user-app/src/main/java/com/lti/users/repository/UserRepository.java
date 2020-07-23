package com.lti.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lti.users.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}