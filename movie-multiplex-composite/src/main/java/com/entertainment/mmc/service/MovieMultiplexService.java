package com.entertainment.mmc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entertainment.mmc.dao.MovieMultipleRepository;
import com.entertainment.mmc.document.MovieMultiplex;
import com.entertainment.mmc.feignproxy.MovieFeignProxy;
import com.entertainment.mmc.feignproxy.MultiplexFeignProxy;
import com.entertainment.mmc.response.MovieDetailDto;
import com.entertainment.mmc.response.MultiplexDetailDto;

@Service
public class MovieMultiplexService implements IMovieMultiplexService {
	@Autowired
	private MovieFeignProxy movieProxy;
	@Autowired
	private MultiplexFeignProxy multiplexProxy;
	@Autowired
	private MovieMultipleRepository repository;

	@Override
	public <T> T performSearch(String path, String searchString) {
		if ("movie".equals(path))
			return (T) movieProxy.searchMovies(searchString);
		else if ("multiplex".equals(path))
			return (T) multiplexProxy.searchMultiplex(searchString);

		return null;
	}

	@Override
	public boolean assignMovieToMultiplex(String multiplexId, Integer screenId, String movieId) {
		MultiplexDetailDto multiplex = multiplexProxy.getMultiplexDetail(multiplexId).getBody();
		MovieDetailDto movie = movieProxy.getMovieDetail(movieId).getBody();
		MovieMultiplex dao = null;
		if (checkIfScreenOccupied(multiplexId, screenId) && movie != null && multiplex != null & screenId <= multiplex.getScreens()) {
			dao = repository.save(new MovieMultiplex("", movie.getId(), multiplex.getId(), screenId));
		}
		return dao != null ? true : false;
	}

	private boolean checkIfScreenOccupied(String multiplexId, Integer screenId) {
		List<MovieMultiplex> daoList = repository.findByMultiplexIdAndScreenNumber(multiplexId, screenId);
		return (daoList.isEmpty() || daoList == null);
	}

}
