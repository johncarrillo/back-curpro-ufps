package com.co.ufps.curpro.back.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.TipoActividad;

public interface ITipoActividadDao extends CrudRepository<TipoActividad, Long>{

	public TipoActividad findByNombre(String nombre);
}
