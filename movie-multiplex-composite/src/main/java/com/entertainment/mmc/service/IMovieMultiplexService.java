package com.entertainment.mmc.service;

public interface IMovieMultiplexService {

	public <T> T performSearch(String path, String searchString);

	public boolean assignMovieToMultiplex(String multiplexId, Integer screenId, String movieId);

}
