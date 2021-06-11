package com.co.ufps.curpro.back.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.EstadoMatricula;

public interface IEstadoMatriculaDao extends CrudRepository<EstadoMatricula, Long>{
	public EstadoMatricula findByNombre (String nombre);
}
