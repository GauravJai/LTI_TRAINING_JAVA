package com.entertainment.mmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entertainment.mmc.request.MovieMultiplexDto;
import com.entertainment.mmc.service.IMovieMultiplexService;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/m2m")
public class MovieMultiplexController {
	@Autowired
	IMovieMultiplexService service;

	@RequestMapping("/assign")
	public ResponseEntity<String> assignMovieToMultiplex(@RequestBody MovieMultiplexDto request) {
		if (Strings.isNullOrEmpty(request.getMultiplexId()) && Strings.isNullOrEmpty(request.getMovieId())) {
			return new ResponseEntity<String>("movieId and multiplexId should not be null.", HttpStatus.NO_CONTENT);
		}
		boolean isAssigned = service.assignMovieToMultiplex(request.getMultiplexId(), request.getScreenNumber(), request.getMovieId());
		return isAssigned ? new ResponseEntity<String>("Success", HttpStatus.CREATED)
				: new ResponseEntity<String>("Failed", HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping("/search/{path}")
	public <T> ResponseEntity<T> searchMovies(@PathVariable("path") String path,
			@RequestParam("search") String searchString) {
		if (!Strings.isNullOrEmpty(path)) {
			return service.performSearch(path, searchString);
		}
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}
}
