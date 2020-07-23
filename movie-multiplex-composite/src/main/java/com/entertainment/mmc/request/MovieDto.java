package com.entertainment.mmc.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {
	private String Id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private Date releaseDate;

}
