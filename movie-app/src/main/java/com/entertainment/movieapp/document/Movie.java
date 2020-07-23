package com.entertainment.movieapp.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	@Id
	private String id;
	private String name;
	private String category;
	private String producer;
	private String director;
	private LocalDateTime releaseDate;
}
