package com.entertainment.multiplexapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entertainment.multiplexapp.dao.MultiplexRepository;
import com.entertainment.multiplexapp.document.Multiplex;
import com.entertainment.multiplexapp.request.MultiplexDto;
import com.entertainment.multiplexapp.response.MultiplexDetailDto;

@Service
public class MultiplexServiceImpl implements MultiplexService {

	MultiplexRepository repository;

	public MultiplexServiceImpl(MultiplexRepository repository) {
		this.repository = repository;
	}

	@Override
	public MultiplexDetailDto addMultiplex(MultiplexDto multiplexDto) {
		// Dto->Document
		Multiplex multiplexDb = new Multiplex(null, multiplexDto.getName(), multiplexDto.getAddress(),
				multiplexDto.getScreens());
		multiplexDb = repository.save(multiplexDb);
		// MultiplexDetailDto MultiplexDetailDto =
		// this.proxy.getUserDetail(userId).getBody();

		MultiplexDetailDto multiplexDetailDto = new MultiplexDetailDto(multiplexDb.getId(), multiplexDb.getName(),
				multiplexDb.getAddress(), multiplexDb.getScreenCount());
		return multiplexDetailDto;
	}

	@Override
	public List<MultiplexDetailDto> getAllMultiplexs() {
		final List<MultiplexDetailDto> response = new ArrayList<MultiplexDetailDto>();
		Iterable<Multiplex> multiplexs = repository.findAll();
		if (multiplexs.iterator() != null) {
			multiplexs.forEach(multiplex -> response.add(new MultiplexDetailDto(multiplex.getId(), multiplex.getName(),
					multiplex.getAddress(), multiplex.getScreenCount())));
		}

		return response;
	}

	@Override
	public MultiplexDetailDto getMultiplex(String multiplexId) {
		Multiplex multiplex = repository.findById(multiplexId).orElse(null);
		if (multiplex != null)
			return new MultiplexDetailDto(multiplex.getId(), multiplex.getName(), multiplex.getAddress(),
					multiplex.getScreenCount());
		return null;
	}

	@Override
	public MultiplexDetailDto updateMultiplex(String multiplexId, MultiplexDto multiplexDto) {
		if (repository.existsById(multiplexId)) {
			Multiplex multiplex = repository.findById(multiplexId).get();
			multiplex = buildMultiplexDaoForUpdate(multiplex, multiplexDto);
			multiplex = repository.save(multiplex);
			return new MultiplexDetailDto(multiplexId, multiplex.getName(), multiplex.getAddress(),
					multiplex.getScreenCount());
		}
		return null;
	}

	private Multiplex buildMultiplexDaoForUpdate(Multiplex multiplex, MultiplexDto multiplexDto) {
		multiplex.setName(multiplexDto.getName());
		multiplex.setAddress(multiplexDto.getAddress());
		multiplex.setScreenCount(multiplexDto.getScreens());
		return multiplex;
	}

	@Override
	public String deleteMultiplex(String multiplexId) {
		if (repository.existsById(multiplexId)) {
			repository.deleteById(multiplexId);
			return multiplexId;
		}
		return null;
	}

	@Override
	public List<MultiplexDetailDto> searchAllMultiplexes(String search) {
		List<MultiplexDetailDto> multiplexList = new ArrayList<MultiplexDetailDto>();
		List<Multiplex> multiplexes = repository.findByNameContainingIgnoreCase(search);
		multiplexes.forEach(multiplex -> {
			MultiplexDetailDto multiplexResponse = new MultiplexDetailDto(multiplex.getId(), multiplex.getName(), multiplex.getAddress(), multiplex.getScreenCount());
			multiplexList.add(multiplexResponse);
		});
		return multiplexList;
	}
	

}
