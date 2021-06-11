package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Actividad;
import com.co.ufps.curpro.back.app.models.entity.ActividadEstudiante;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;

public interface IActividadDao extends CrudRepository<Actividad, Long>{
	public List<Actividad> findByModuloCursoOrderByIdAsc(ModuloCurso moduloCurso);

}
