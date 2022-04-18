package com.desarrollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.desarrollo.model.ProductoModel;
import com.desarrollo.service.ProductoService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Controller
public class ProductosController {
	
	@Autowired
	private ProductoService ProductoServ;
	
	@ApiOperation (value = "Devuelve todos los productos", notes="Devuelve el listado de todos los productos en formato JSON.")
	@GetMapping(value = "/productos")
	public ResponseEntity<String> productos() {
		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		try {
			jsonResponse = gson.toJson(ProductoServ.findAll());
			//jsonResponse = "HOLA MUNDO";
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	
	@ApiOperation (value = "Devuelve el producto especificado en el id", notes="Devuelve los datos de el producto en formato JSON.")
	@GetMapping(value = "/productos/{id}")
	public ResponseEntity<String> productosIds(@PathVariable("id") Integer id) {

		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;

		try {
			jsonResponse = gson.toJson(ProductoServ.findById(id));
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	@ApiOperation (value = "Creacion de un nuevo producto", notes="Creacion de un producto, recibe el parametro en formato JSON,"
			+ " y devuelve el registro guardado en formato JSON")
	@PostMapping(value = "/productos")
	public ResponseEntity<String> productosInsert(@RequestBody String json) {
		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		ProductoModel producto = gson.fromJson(json, ProductoModel.class);
		try {
			jsonResponse = gson.toJson(ProductoServ.save(producto));
			response = new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);

		} catch (Exception ex) {
			ex.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}

	@ApiOperation (value = "Actualizar un producto", notes="Actualizar un producto,recibe el parametro en formato JSON,"
			+ "y devuelve el registro actualizado en JSON.")
	@PutMapping(value = "/productos/{id}")
	public ResponseEntity<String> productosPut(@PathVariable("id") Integer id, @RequestBody String json) {

		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		ProductoModel producto = gson.fromJson(json, ProductoModel.class);
		producto.setId_producto(id);
		try {
			jsonResponse = gson.toJson(ProductoServ.save(producto));
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;

	}

	@ApiOperation (value = "Eliminar un producto", notes="Elimina un producto especificado por el id,"
			+ " en caso de existir lo eliminara y regresara eliminado correctamente.")
	@DeleteMapping(value = "/productos/{id}")
	public ResponseEntity<String> productosDelete(@PathVariable("id") Integer id) {

		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		try {
			String respuesta = "";
			Boolean resp = ProductoServ.deleteById(id);
			if(resp) {
				respuesta = "Eliminado Correctamente";
			}
			jsonResponse = gson.toJson(respuesta);

			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;

	}
}
