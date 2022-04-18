package com.desarrollo.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "venta", url = "http://localhost:8080")
public interface VentaRepository{
	
	@GetMapping(value = "/productos")
	String productos();
}
