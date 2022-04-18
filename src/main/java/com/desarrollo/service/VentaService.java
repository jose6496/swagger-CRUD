package com.desarrollo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.desarrollo.model.ProductoModel;
import com.desarrollo.repository.VentaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class VentaService {
	
	@Autowired
	private VentaRepository ventaRepo;

	public List<ProductoModel> listaProductos(){
		List<ProductoModel> lista = new ArrayList<>();
		Gson gson = new Gson();
		lista = gson.fromJson(ventaRepo.productos(), new TypeToken<ArrayList<ProductoModel>>() {
        }.getType());
		return lista;
	}
	
}
