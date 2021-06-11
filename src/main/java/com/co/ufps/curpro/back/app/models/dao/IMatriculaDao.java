package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstadoMatricula;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Matricula;

public interface IMatriculaDao extends CrudRepository <Matricula, Long> {

	public List<Matricula> findAllByCurso(Curso curso);

	@Query(value = "select * from matricula where esin_id = ?1 and curs_id = ?2 ", nativeQuery = true)
	public Matricula findByIdEstudianteInfoAndIdCurso(Long idEstudianteInfo, Long idCurso);

	public List<Matricula> findAllByCursoAndEstadoMatricula(Curso curso, EstadoMatricula estadoMatricula);

	public List<Matricula> findByEstudianteInformacion (EstudianteInfo estudianteInformacion);
}
