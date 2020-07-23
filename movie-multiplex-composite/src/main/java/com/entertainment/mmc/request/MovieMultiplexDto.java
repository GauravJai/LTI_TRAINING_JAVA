package com.entertainment.mmc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieMultiplexDto {
	private String multiplexId;
	private Integer screenNumber;
	private String movieId;

}
