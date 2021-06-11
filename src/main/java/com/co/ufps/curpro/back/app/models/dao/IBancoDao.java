package com.co.ufps.curpro.back.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Banco;

public interface IBancoDao extends CrudRepository<Banco, Long>{
	public Banco findByNombre (String nombre);
}
