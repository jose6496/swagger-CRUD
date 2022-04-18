package com.desarrollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.desarrollo.service.VentaService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Controller
public class VentasController {
	
	@Autowired
	private VentaService ventaServ;
	
	@ApiOperation (value = "Devuelve todos los productos llamando un microservicio", notes="Devuelve el listado de todos los productos en formato JSON, "
			+ "obtenidos del microservicio de productos.")
	@GetMapping(value = "/venta")
	public ResponseEntity<String> productos() {
		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		try {
			jsonResponse = gson.toJson(ventaServ.listaProductos());
			//jsonResponse = "HOLA MUNDO";
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	
}
