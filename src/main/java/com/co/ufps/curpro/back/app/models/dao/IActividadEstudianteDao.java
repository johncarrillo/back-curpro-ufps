package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Actividad;
import com.co.ufps.curpro.back.app.models.entity.ActividadEstudiante;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

public interface IActividadEstudianteDao extends CrudRepository<ActividadEstudiante, Long>{

	@Query(value = "select * from actividad_estudiante where acti_id = ?1 ", nativeQuery = true)
	public List<ActividadEstudiante> aaasssaaa(Long id);
	public ActividadEstudiante findOneByEstudianteInfoAndActividad(EstudianteInfo estudianteInfo, Actividad actividad);

}
