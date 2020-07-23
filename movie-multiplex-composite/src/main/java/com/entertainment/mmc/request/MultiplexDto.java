package com.entertainment.mmc.request;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MultiplexDto {
	private String id;
	private String name;
	private String address;
	private int screens;
	private Map<Integer, MovieDto> screenMovieMap;

}
