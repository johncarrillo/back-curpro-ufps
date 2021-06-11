package com.co.ufps.curpro.back.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Long>{

	public Rol findByNombre(String nombre);
}
