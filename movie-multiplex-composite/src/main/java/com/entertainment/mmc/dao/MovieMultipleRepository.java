package com.entertainment.mmc.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entertainment.mmc.document.MovieMultiplex;

public interface MovieMultipleRepository extends MongoRepository<MovieMultiplex, Integer>{

	List<MovieMultiplex> findByMultiplexIdAndScreenNumber(String multiplexId, Integer screenNumber);
}
