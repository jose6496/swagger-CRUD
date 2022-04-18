package com.desarrollo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.desarrollo.model.CategoriaModel;
import com.desarrollo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	public List<CategoriaModel> findAll(){
		List<CategoriaModel> lista = new ArrayList<>();
		lista = categoriaRepo.findAll();
		return lista;
	}
	
	
	public CategoriaModel findById(Integer id){
		return categoriaRepo.findById(id).get();
	}
	
	public CategoriaModel save(CategoriaModel categoria){
		return categoriaRepo.save(categoria);
	}
	
	public CategoriaModel put(CategoriaModel categoria){
		CategoriaModel model = findById(categoria.getId_categoria());
		if(model==null) {
			return null;
		}
		model.setNombre(categoria.getNombre());
		model.setNo_competidores(categoria.getNo_competidores());
		return categoriaRepo.save(model);
	}
	
	public Boolean deleteById(Integer id){
		CategoriaModel model = findById(id);
		if(model==null) {
			return false;
		}
		categoriaRepo.deleteById(id);
		return true;
	}
	
}
