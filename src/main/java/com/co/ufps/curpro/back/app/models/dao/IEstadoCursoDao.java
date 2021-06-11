package com.co.ufps.curpro.back.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.EstadoCurso;

public interface IEstadoCursoDao extends CrudRepository<EstadoCurso, Long>{
	public EstadoCurso findByNombre (String nombre);
}
