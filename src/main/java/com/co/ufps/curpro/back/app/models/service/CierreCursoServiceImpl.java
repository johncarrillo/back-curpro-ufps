package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.dto.CierreCursoDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.SustentacionDto;
import com.co.ufps.curpro.back.app.dto.SustentacionSaveDto;
import com.co.ufps.curpro.back.app.dto.SustentacionUpdateDto;
import com.co.ufps.curpro.back.app.models.dao.ICierreCursoDao;
import com.co.ufps.curpro.back.app.models.dao.ISustentacionDao;
import com.co.ufps.curpro.back.app.models.entity.CierreCurso;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Sustentacion;
import com.co.ufps.curpro.back.app.models.parser.CierreCursoConverter;

@Service
public class CierreCursoServiceImpl implements ICierreCursoService {

	@Autowired
	private ISustentacionDao sustentacionDao;

	@Autowired
	private ICierreCursoDao cierreCursoDao;

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IEstudianteInfoService estudianteInfoServiceImpl;

	@Override
	public List<SustentacionDto> listSustentacionByIdCurso (Long idCurso) throws Exception {
		Curso curso = cursoService.findOneEntity(idCurso);
		List<Sustentacion> sustentaciones = sustentacionDao.findByCurso(curso);
		List<SustentacionDto> sustentacionesDto = new ArrayList<SustentacionDto>();
		sustentaciones.forEach(sustentacion -> {
			sustentacionesDto.add(CierreCursoConverter.entityToDto(sustentacion, sustentacion.getEstudianteInfo().getRolUsuario().getUsuario()));
		});
		return sustentacionesDto;
	}

	@Override
	public List<CierreCursoDto> listCierreCursoByIdCurso (Long idCurso) throws Exception {
		Curso curso = cursoService.findOneEntity(idCurso);
		List<CierreCurso> cierreCursos = cierreCursoDao.findByCurso(curso);
		List<CierreCursoDto> cierreCursosDto = new ArrayList<CierreCursoDto>();
		cierreCursos.forEach(sustentacion -> {
			cierreCursosDto.add(CierreCursoConverter.entityToDto(sustentacion, sustentacion.getEstudianteInfo().getRolUsuario().getUsuario()));
		});
		return cierreCursosDto;
	}

	@Override
	public CierreCursoDto findByIdCierreCurso (Long id) throws Exception {
		CierreCurso cierreCurso = cierreCursoDao.findById(id).orElseThrow(() -> new Exception ("Este cierre de curso del estudiante no existe."));
		return CierreCursoConverter.entityToDto(cierreCurso, cierreCurso.getEstudianteInfo().getRolUsuario().getUsuario());
	}

	@Override
	public SustentacionDto findByIdSustentacion (Long id) throws Exception {
		Sustentacion sustentacion = sustentacionDao.findById(id).orElseThrow(() -> new Exception ("La sustentacion no existe"));
		return CierreCursoConverter.entityToDto(sustentacion, sustentacion.getEstudianteInfo().getRolUsuario().getUsuario());
	}

	@Override
	public void saveSustentacion (SustentacionSaveDto sustentacionSave) throws Exception {
		Curso curso = cursoService.findOneEntity(sustentacionSave.getIdCurso());
		EstudianteInfo estudianteInfo = estudianteInfoServiceImpl.findById(sustentacionSave.getIdEstudianteInfo());
		Sustentacion sustentacion = CierreCursoConverter.dtoToEntity(sustentacionSave, estudianteInfo, curso);
		sustentacionDao.save(sustentacion);
	}

	@Override
	public void saveCierreCurso (CierreCursoSaveDto cierreCursoSave) throws Exception {
		Curso curso = cursoService.findOneEntity(cierreCursoSave.getIdCurso());
		EstudianteInfo estudianteInfo = estudianteInfoServiceImpl.findById(cierreCursoSave.getIdEstudianteInfo());
		CierreCurso cierreCurso = CierreCursoConverter.dtoToEntity(cierreCursoSave, estudianteInfo, curso);
		cierreCursoDao.save(cierreCurso);
	}

	@Override
	public void updateSustentacion (SustentacionUpdateDto sustentacionUpdate) throws Exception {
		Sustentacion sustentacion = sustentacionDao.findById(sustentacionUpdate.getId()).orElseThrow(()->new Exception("Esta sustentación no existe"));
		sustentacion = CierreCursoConverter.dtoToEntity(sustentacion, sustentacionUpdate);
		sustentacionDao.save(sustentacion);
	}

	@Override
	public void updateCierreCurso (CierreCursoUpdateDto cierreCursoUpdate) throws Exception {
		CierreCurso cierreCurso = cierreCursoDao.findById(cierreCursoUpdate.getId()).orElseThrow(()->new Exception("Este cierre del curso no existe"));
		cierreCurso = CierreCursoConverter.dtoToEntity(cierreCurso, cierreCursoUpdate);
		cierreCursoDao.save(cierreCurso);
	}

	@Override
	public void deleteCierreCurso (Long id) {
		cierreCursoDao.deleteById(id);
	}

	@Override
	public void deleteSustentacion (Long id) {
		sustentacionDao.deleteById(id);
	}
}
