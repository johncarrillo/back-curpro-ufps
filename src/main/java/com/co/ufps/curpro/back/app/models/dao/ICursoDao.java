package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.EstadoCurso;
import com.co.ufps.curpro.back.app.models.entity.TipoCurso;

public interface ICursoDao extends CrudRepository<Curso, Long>{

	@Query(value = "select * from curso where escu_id = ?1 and ticu_id = ?2 and depe_id = ?3", nativeQuery = true)
	public List<Curso> findByIdEstadoAndTipoAndDependencia(Long idEstadoCurso, Long idTipoCurso, Long idDependenciaCurso);

	@Query(value = "select * from curso where escu_id = ?1 and ticu_id = ?2 ", nativeQuery = true)
	public List<Curso> findByIdEstadoAndTipo(Long idEstadoCurso, Long idTipoCurso);

	@Query(value = "select * from curso where escu_id = ?1 and depe_id = ?2", nativeQuery = true)
	public List<Curso> findByIdEstadoAndDependencia(Long idEstadoCurso, Long idDependenciaCurso);

	@Query(value = "select * from curso where ticu_id = ?1 and depe_id = ?2", nativeQuery = true)
	public List<Curso> findByIdTipoAndDependencia(Long idTipoCurso, Long idDependenciaCurso);

	public List<Curso> findByEstadoCurso(EstadoCurso estadoCurso);

	public List<Curso> findByTipoCurso(TipoCurso tipoCurso);

	public List<Curso> findByDependencia(Dependencia dependencia);
}
