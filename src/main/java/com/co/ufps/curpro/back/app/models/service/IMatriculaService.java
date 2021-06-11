package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.BancoDto;
import com.co.ufps.curpro.back.app.dto.EstadoMatriculaDto;
import com.co.ufps.curpro.back.app.dto.InscripcionDto;
import com.co.ufps.curpro.back.app.dto.InscripcionPdfDto;
import com.co.ufps.curpro.back.app.dto.InscripcionSaveDto;
import com.co.ufps.curpro.back.app.dto.InscripcionUpdateDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.MatriculaPdfDto;
import com.co.ufps.curpro.back.app.dto.MatriculaSaveDto;
import com.co.ufps.curpro.back.app.dto.MatriculaUpdateDto;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

public interface IMatriculaService {

	public InscripcionDto findInscripcionById(Long idEstudiante, Long idCurso) throws Exception;

	public List<InscripcionDto> listInscripcionByIdCurso(Long idCurso);

	public MatriculaDto findMatriculaById(Long idEstudiante, Long idCurso) throws Exception;

	public List<MatriculaDto> listMatriculaByIdCurso(Long idCurso);

	public void saveInscripcion (InscripcionSaveDto inscripcionSave) throws Exception;

	public void saveMatricula (MatriculaSaveDto matriculaSave) throws Exception;

	public void updateInscripcion (InscripcionUpdateDto inscripcionUpdate) throws Exception;

	public void updateMatricula (MatriculaUpdateDto matriculaUpdate) throws Exception;

	public List<EstadoMatriculaDto> listEstadoMatricula();

	public List<BancoDto> listBanco();

	public void deleteMatriculaById(Long id);

	public void deleteInscripcionById(Long id);

	public List<EstudianteInfo> listMatriculaByIdCursoActiveEntities(Long idCurso);

	public List<MatriculaPdfDto> listMatriculaByIdCursoFromPdf(Long idCurso);

	public List<InscripcionPdfDto> listInscripcionByIdCursoFromPdf(Long idCurso);

	public List<InscripcionDto> findByEstudianteInfo (EstudianteInfo estudianteInfo);

	public List<MatriculaDto> findByMatriculaInfo (EstudianteInfo estudianteInfo);
}
