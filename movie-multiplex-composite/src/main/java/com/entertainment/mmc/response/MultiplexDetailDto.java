package com.entertainment.mmc.response;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiplexDetailDto {
	private String id;
	private String name;
	private String address;
	private int screens;
	private Map<Integer, MovieDetailDto> screenMovieMap;
	//private Map<Integer, String> screenMovieMap;
	public void addScreenMovieMap(Map<Integer, MovieDetailDto> screenMovieMap) {
		if(this.screenMovieMap != null)
			this.screenMovieMap = new HashMap<Integer, MovieDetailDto>();
		this.screenMovieMap.putAll(screenMovieMap);
	}

}
