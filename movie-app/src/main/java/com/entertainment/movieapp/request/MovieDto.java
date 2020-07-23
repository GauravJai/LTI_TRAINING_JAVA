package com.entertainment.movieapp.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {
	private String name;
	private String category;
	private String producer;
	private String director;
	private Date releaseDate;

}
