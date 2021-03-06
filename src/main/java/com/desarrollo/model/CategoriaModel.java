package com.desarrollo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class CategoriaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_categoria;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "no_competidores")
	private Integer no_competidores;
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNo_competidores() {
		return no_competidores;
	}
	public void setNo_competidores(Integer no_competidores) {
		this.no_competidores = no_competidores;
	}

}

