package com.entertainment.multiplexapp.service;

import java.util.List;

import com.entertainment.multiplexapp.request.MultiplexDto;
import com.entertainment.multiplexapp.response.MultiplexDetailDto;

public interface MultiplexService {

	MultiplexDetailDto addMultiplex(MultiplexDto multiplexDto);
	MultiplexDetailDto getMultiplex(String multiplexId);
	List<MultiplexDetailDto> getAllMultiplexs();
	MultiplexDetailDto updateMultiplex(String multiplexId, MultiplexDto movieDto);
	String deleteMultiplex(String multiplexId);
	List<MultiplexDetailDto> searchAllMultiplexes(String search);

}
