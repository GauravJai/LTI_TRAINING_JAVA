package com.entertainment.movieapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entertainment.movieapp.request.MovieDto;
import com.entertainment.movieapp.response.MovieDetailDto;
import com.entertainment.movieapp.service.MovieService;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/api")
public class MovieController {

	private MovieService service;

	public MovieController(MovieService service) {
		this.service = service;
	}

	@PostMapping("/movie")
	public ResponseEntity<MovieDetailDto> addMovie(@RequestBody MovieDto movieDto) {
		MovieDetailDto movieDetailDto = service.addMovie(movieDto/* , userId */);
		ResponseEntity<MovieDetailDto> response = new ResponseEntity<MovieDetailDto>(movieDetailDto, HttpStatus.OK);
		return response;
	}

	@GetMapping("/movie")
	public ResponseEntity<List<MovieDetailDto>> getAllMovies() {
		List<MovieDetailDto> movies = service.getAllMovies();
		return movies.isEmpty() ? new ResponseEntity<List<MovieDetailDto>>(movies, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<MovieDetailDto>>(movies, HttpStatus.OK);
	}

	@GetMapping("/movie/{movieId}")
	public ResponseEntity<MovieDetailDto> getMovie(@PathVariable("movieId") String movieId) {
		MovieDetailDto movie = null;
		if (!Strings.isNullOrEmpty(movieId))
			movie = service.getMovie(movieId);
		return movie != null ? new ResponseEntity<MovieDetailDto>(movie, HttpStatus.OK)
				: new ResponseEntity<MovieDetailDto>(movie, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/movie/{movieId}")
	public ResponseEntity<MovieDetailDto> updateMovie(@PathVariable("movieId") String movieId,
			@RequestBody MovieDto movieDto) {
		MovieDetailDto movieDetailDto = null;
		if (!Strings.isNullOrEmpty(movieId))
			movieDetailDto = service.updateMovie(movieId, movieDto);
		return movieDetailDto != null ? new ResponseEntity<MovieDetailDto>(movieDetailDto, HttpStatus.OK)
				: new ResponseEntity<MovieDetailDto>(movieDetailDto, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/movie/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable("movieId") String movieId) {
		String id = null;
		if (!Strings.isNullOrEmpty(movieId))
			id = service.deleteMovie(movieId);
		return id != null ? new ResponseEntity<String>(id, HttpStatus.OK)
				: new ResponseEntity<String>(id, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search")
	public ResponseEntity<List<MovieDetailDto>> searchMovies(@RequestParam("search") String search) {
		List<MovieDetailDto> movies = new ArrayList<MovieDetailDto>();
		if (!Strings.isNullOrEmpty(search))
			movies = service.searchAllMovies(search);
		return movies.isEmpty() ? new ResponseEntity<List<MovieDetailDto>>(movies, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<MovieDetailDto>>(movies, HttpStatus.OK);

	}

}