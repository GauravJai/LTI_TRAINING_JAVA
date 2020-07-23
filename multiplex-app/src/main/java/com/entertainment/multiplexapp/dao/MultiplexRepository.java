package com.entertainment.multiplexapp.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entertainment.multiplexapp.document.Multiplex;

public interface MultiplexRepository extends MongoRepository<Multiplex, String>{

	List<Multiplex> findByNameContainingIgnoreCase(String search);
	
}
