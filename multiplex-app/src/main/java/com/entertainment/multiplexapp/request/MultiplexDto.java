package com.entertainment.multiplexapp.request;

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

}
