package com.entertainment.multiplexapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entertainment.multiplexapp.request.MultiplexDto;
import com.entertainment.multiplexapp.response.MultiplexDetailDto;
import com.entertainment.multiplexapp.service.MultiplexService;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/api")
public class MultiplexController {

	private MultiplexService service;

	public MultiplexController(MultiplexService service) {
		this.service = service;
	}

	@PostMapping("/multiplex")
	public ResponseEntity<MultiplexDetailDto> addMultiplex(@RequestBody MultiplexDto MultiplexDto) {
		MultiplexDetailDto multiplexDetailDto = service.addMultiplex(MultiplexDto);
		ResponseEntity<MultiplexDetailDto> response = new ResponseEntity<MultiplexDetailDto>(multiplexDetailDto,
				HttpStatus.OK);
		return response;
	}

	@GetMapping("/multiplex")
	public ResponseEntity<List<MultiplexDetailDto>> getAllMultiplexs() {
		List<MultiplexDetailDto> multiplexs = service.getAllMultiplexs(/* , userId */);

		return new ResponseEntity<List<MultiplexDetailDto>>(multiplexs, HttpStatus.OK);
	}

	@GetMapping("/multiplex/{multiplexId}")
	public ResponseEntity<MultiplexDetailDto> getMultiplex(@PathVariable("multiplexId") String multiplexId) {
		MultiplexDetailDto multiplex = service.getMultiplex(multiplexId/* , userId */);

		return new ResponseEntity<MultiplexDetailDto>(multiplex, HttpStatus.OK);
	}

	@PutMapping("/multiplex/{multiplexId}")
	public ResponseEntity<MultiplexDetailDto> updateMultiplex(@PathVariable("multiplexId") String multiplexId,
			@RequestBody MultiplexDto multiplexDto) {
		MultiplexDetailDto multiplexDetailDto = service.updateMultiplex(multiplexId, multiplexDto);
		return multiplexDetailDto != null ? new ResponseEntity<MultiplexDetailDto>(multiplexDetailDto, HttpStatus.OK)
				: new ResponseEntity<MultiplexDetailDto>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/multiplex/{multiplexId}")
	public ResponseEntity<String> deleteMultiplex(@PathVariable("multiplexId") String multiplexId) {
		String id = service.deleteMultiplex(multiplexId);
		return id != null ? new ResponseEntity<String>(id, HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<MultiplexDetailDto>> searchMultiplex(@RequestParam("search") String search) {
		List<MultiplexDetailDto> multiplexes = new ArrayList<MultiplexDetailDto>();
		if (!Strings.isNullOrEmpty(search))
			multiplexes = service.searchAllMultiplexes(search);
		return multiplexes.isEmpty() ? new ResponseEntity<List<MultiplexDetailDto>>(multiplexes, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<MultiplexDetailDto>>(multiplexes, HttpStatus.OK);

	}

}
