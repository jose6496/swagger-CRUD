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
import com.desarrollo.model.CategoriaModel;
import com.desarrollo.service.CategoriaService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Controller
public class CategoriasController {
	
	@Autowired
	private CategoriaService categoriaServ;
	
	@ApiOperation (value = "Devuelve todas las categorias", notes="Devuelve el listado de todas las categorias en formato JSON.")
	@GetMapping(value = "/categorias")
	public ResponseEntity<String> categorias() {
		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		try {
			jsonResponse = gson.toJson(categoriaServ.findAll());
			//jsonResponse = "HOLA MUNDO";
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	
	@ApiOperation (value = "Devuelve la categoria especificada en el id", notes="Devuelve los datos de la categoria en formato JSON.")
	@GetMapping(value = "/categorias/{id}")
	public ResponseEntity<String> categoriasIds(@PathVariable("id") Integer id) {

		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;

		try {
			jsonResponse = gson.toJson(categoriaServ.findById(id));
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}

	@ApiOperation (value = "Creacion de una nueva categoria", notes="Creacion de una categoria, recibe el parametro en formato JSON,"
			+ " y devuelve el registro guardado en formato JSON")
	@PostMapping(value = "/categorias")
	public ResponseEntity<String> categoriasInsert(@RequestBody String json) {
		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		CategoriaModel categoria = gson.fromJson(json, CategoriaModel.class);
		try {
			jsonResponse = gson.toJson(categoriaServ.save(categoria));
			response = new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);

		} catch (Exception ex) {
			ex.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}

	@ApiOperation (value = "Actualizar una categoria", notes="Actualizar una categoria,recibe el parametro en formato JSON,"
			+ "y devuelve el registro actualizado en JSON.")
	@PutMapping(value = "/categorias/{id}")
	public ResponseEntity<String> categoriasPut(@PathVariable("id") Integer id, @RequestBody String json) {

		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		CategoriaModel categoria = gson.fromJson(json, CategoriaModel.class);
		categoria.setId_categoria(id);
		try {
			jsonResponse = gson.toJson(categoriaServ.save(categoria));
			response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;

	}

	@ApiOperation (value = "Eliminar una categoria", notes="Elimina una categoria especificada por el id,"
			+ " en caso de existir lo eliminara y regresara eliminado correctamente.")
	@DeleteMapping(value = "/categorias/{id}")
	public ResponseEntity<String> categoriasDelete(@PathVariable("id") Integer id) {

		ResponseEntity<String> response;
		Gson gson = new Gson();
		String jsonResponse;
		try {
			String respuesta = "";
			Boolean resp = categoriaServ.deleteById(id);
			if(resp) {
				respuesta = "Eliminado Correctamente.";
			}else {
				respuesta = "Registro a eliminar no existente.";

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
