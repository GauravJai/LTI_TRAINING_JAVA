package com.entertainment.mmc.feignproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.entertainment.mmc.response.MovieDetailDto;

@FeignClient(name = "movie-app")
@RibbonClient(name = "movie-app")
public interface MovieFeignProxy {
	@GetMapping("api/movie/{movieId}")
	public ResponseEntity<MovieDetailDto> getMovieDetail(@PathVariable("movieId") String movieId);
	@GetMapping("api/search")
	public ResponseEntity<List<MovieDetailDto>> searchMovies(@RequestParam("search") String search);
}
