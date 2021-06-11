package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.CierreCurso;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.ModuloEstudiante;
import com.co.ufps.curpro.back.app.models.entity.Sustentacion;

public interface ICierreCursoDao extends CrudRepository<CierreCurso, Long>{

	public List<CierreCurso> findByCurso(Curso curso);
}
