package com.entertainment.movieapp.response;

import java.util.Date;

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
public class MovieDetailDto {
	private String id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private Date releaseDate;

}
