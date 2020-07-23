package com.lti.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lti.users.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}