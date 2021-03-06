package com.desarrollo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.desarrollo.model.ProductoModel;
import com.desarrollo.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepo;

	public List<ProductoModel> findAll(){
		List<ProductoModel> lista = new ArrayList<>();
		lista = productoRepo.findAll();
		return lista;
	}
	
	public ProductoModel findById(Integer id){
		return productoRepo.findById(id).get();
	}
	
	public ProductoModel save(ProductoModel producto){
		return productoRepo.save(producto);
	}
	
	public ProductoModel put(ProductoModel producto){
		ProductoModel model = findById(producto.getId_producto());
		if(model==null) {
			return null;
		}
		model.setMarca(producto.getMarca());
		model.setNombre(producto.getNombre());
		model.setPrecio(producto.getPrecio());
		return productoRepo.save(producto);
	}
	
	public Boolean deleteById(Integer id){
		ProductoModel model = findById(id);
		if(model==null) {
			return null;
		}
		productoRepo.deleteById(id);
		return true;
	}
}
