package com.co.ufps.curpro.back.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;

public interface IDependenciaDao extends CrudRepository<Dependencia, Long>{

}
