package com.entertainment.movieapp.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entertainment.movieapp.dao.MovieRepository;
import com.entertainment.movieapp.document.Movie;
import com.entertainment.movieapp.request.MovieDto;
import com.entertainment.movieapp.response.MovieDetailDto;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository repository;

	public MovieServiceImpl(MovieRepository repository) {
		this.repository = repository;
	}

	@Override
	public MovieDetailDto addMovie(MovieDto movieDto) {
		Movie movieDb = new Movie(null, movieDto.getName(), movieDto.getCategory(), movieDto.getProducer(),
				movieDto.getDirector(), new java.sql.Timestamp(movieDto.getReleaseDate().getTime()).toLocalDateTime());

		movieDb = repository.save(movieDb);
		// UserDetailDto userDetailDto = this.restTemplate.getForObject(USER_URL +
		// "/get/" + userId, UserDetailDto.class);
		// MovieDetailDto movieDetailDto = this.proxy.getUserDetail(userId).getBody();

		MovieDetailDto movieDetailDto = new MovieDetailDto(movieDb.getId(), movieDb.getName(), movieDb.getCategory(),
				movieDb.getProducer(), movieDb.getDirector(), Date.from(movieDb.getReleaseDate().atZone(ZoneId.systemDefault()).toInstant()));
		return movieDetailDto;
	}

	@Override
	public List<MovieDetailDto> getAllMovies() {
		final List<MovieDetailDto> response = new ArrayList<MovieDetailDto>();
		repository.findAll().forEach(movie -> response.add(new MovieDetailDto(movie.getId(), movie.getName(),
				movie.getCategory(), movie.getProducer(), movie.getDirector(), Date.from(movie.getReleaseDate().atZone(ZoneId.systemDefault()).toInstant()))));

		return response;
	}

	@Override
	public MovieDetailDto getMovie(String movieId) {
		Movie movie = repository.findById(movieId).orElse(null);
		if (movie != null)
			return new MovieDetailDto(movie.getId(), movie.getName(), movie.getCategory(), movie.getProducer(),
					movie.getDirector(), Date.from(movie.getReleaseDate().atZone(ZoneId.systemDefault()).toInstant()));
		return null;
	}

	@Override
	public MovieDetailDto updateMovie(String movieId, MovieDto movieDto) {
		if (repository.existsById(movieId)) {
			Movie movieDb = repository.findById(movieId).get();
			movieDb = buildMovieDaoForUpdate(movieDb, movieDto);
			movieDb = repository.save(movieDb);
			MovieDetailDto movieDetailDto = new MovieDetailDto(movieDb.getId(), movieDb.getName(),
					movieDb.getCategory(), movieDb.getProducer(), movieDb.getDirector(), Date.from(movieDb.getReleaseDate().atZone(ZoneId.systemDefault()).toInstant()));
			return movieDetailDto;
		}
		return null;
	}

	private Movie buildMovieDaoForUpdate(Movie movieDb, MovieDto movieDto) {
		movieDb.setName(movieDto.getName());
		movieDb.setCategory(movieDto.getCategory());
		movieDb.setDirector(movieDto.getDirector());
		movieDb.setProducer(movieDto.getProducer());
		movieDb.setReleaseDate(new java.sql.Timestamp(movieDto.getReleaseDate().getTime()).toLocalDateTime());
		return movieDb;
	}

	@Override
	public String deleteMovie(String movieId) {
		if (repository.existsById(movieId)) {
			repository.deleteById(movieId);
			return movieId;
		}
		return null;
	}

	@Override
	public List<MovieDetailDto> searchAllMovies(String search) {
		List<MovieDetailDto> movieList = new ArrayList<MovieDetailDto>();
		List<Movie> movies = repository.findByNameContainingIgnoreCase(search);
		movies.forEach(movie -> {
			MovieDetailDto movieResponse = new MovieDetailDto(movie.getId(), movie.getName(), movie.getCategory(),
					movie.getProducer(), movie.getDirector(), Date.from(movie.getReleaseDate().atZone(ZoneId.systemDefault()).toInstant()));
			movieList.add(movieResponse);
		});
		return movieList;
	}

}
