package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;

public interface IModuloCursoDao extends CrudRepository<ModuloCurso, Long>{

	public List<ModuloCurso> findByCurso(Curso curso);
}
