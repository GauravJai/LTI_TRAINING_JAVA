package com.entertainment.movieapp.service;

import java.util.List;

import com.entertainment.movieapp.request.MovieDto;
import com.entertainment.movieapp.response.MovieDetailDto;

public interface MovieService {

	MovieDetailDto addMovie(MovieDto movieDto);
	MovieDetailDto getMovie(String movieId);
	List<MovieDetailDto> getAllMovies();
	MovieDetailDto updateMovie(String movieId, MovieDto movieDto);
	String deleteMovie(String movieId);
	List<MovieDetailDto> searchAllMovies(String search);

}
