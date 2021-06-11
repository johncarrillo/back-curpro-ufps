package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.CursoAndPresupuestoDto;
import com.co.ufps.curpro.back.app.dto.CursoDto;
import com.co.ufps.curpro.back.app.dto.CursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.EstadoCursoDto;
import com.co.ufps.curpro.back.app.dto.EvaluacionModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.TipoCursoDto;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstadoCurso;
import com.co.ufps.curpro.back.app.models.entity.TipoCurso;

public interface ICursoService {

	public List<CursoDto> findAll(Long idEstadoCurso, Long idTipoCurso, Long idDependencia);

	public CursoDto findOne(Long id);

	public void save(CursoAndPresupuestoDto cursoAndPresupuestoDto) throws Exception;

	public void update(CursoUpdateDto cursoUpdateDto);

	public void delete(Long id);

	public List<TipoCursoDto> listTipoCurso();

	public List<EstadoCursoDto> listEstadoCurso();

	public Curso findOneEntity(Long id) throws Exception;

	public void publicarEvaluacionCurso (EvaluacionModuloCursoDto evaluacionCursoDto)  throws Exception;
}
