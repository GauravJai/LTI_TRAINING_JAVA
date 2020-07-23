package com.entertainment.mmc.feignproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.entertainment.mmc.response.MultiplexDetailDto;

@FeignClient(name = "multiplex-app")
@RibbonClient(name = "multiplex-app")
public interface MultiplexFeignProxy {
	@GetMapping("api/multiplex/{multiplexId}")
	public ResponseEntity<MultiplexDetailDto> getMultiplexDetail(@PathVariable("multiplexId") String multiplexId);
	@GetMapping("api/search")
	public ResponseEntity<List<MultiplexDetailDto>> searchMultiplex(@RequestParam("search") String search);
}
