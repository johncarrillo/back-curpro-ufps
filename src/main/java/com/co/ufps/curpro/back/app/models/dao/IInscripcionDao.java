package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Inscripcion;
import com.co.ufps.curpro.back.app.models.entity.Matricula;

public interface IInscripcionDao extends CrudRepository <Inscripcion, Long> {

	public List<Inscripcion> findAllByCurso(Curso curso);

	@Query(value = "select * from inscripcion where esin_id = ?1 and curs_id = ?2 ", nativeQuery = true)
	public Inscripcion findByIdEstudianteInfoAndIdCurso(Long idEstudianteInfo, Long idCurso);

	public List<Inscripcion> findByEstudianteInfo (EstudianteInfo estudianteInfo);

}
