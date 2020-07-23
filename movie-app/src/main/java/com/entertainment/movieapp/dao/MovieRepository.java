package com.entertainment.movieapp.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entertainment.movieapp.document.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>{
	
	List<Movie> findByNameContainingIgnoreCase(String search);

}
