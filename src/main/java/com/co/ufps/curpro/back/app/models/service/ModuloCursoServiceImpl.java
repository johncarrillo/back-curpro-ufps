package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.AsignarDocenteAModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.CursoWithModulosDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoCalificacionPdfDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.dto.NotasActividadesPorModuloDto;
import com.co.ufps.curpro.back.app.models.dao.IModuloCursoDao;
import com.co.ufps.curpro.back.app.models.dao.IModuloEstudianteDao;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.ModuloEstudiante;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.parser.ActividadConverter;
import com.co.ufps.curpro.back.app.models.parser.ModuloCursoConverter;

@Service
public class ModuloCursoServiceImpl implements IModuloCursoService{

	@Autowired
	private IModuloCursoDao moduloCursoDao;

	@Autowired
	private IUsuarioService usuarioServiceImpl;

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IEstudianteInfoService estudianteInfoService;

	@Autowired
	private IModuloEstudianteDao moduloEstudianteDao;

	@Autowired
	private IActividadService actividadService;

	@Override
	public List<ModuloCursoDto> findAll(Long idCurso) {
		Curso curso = new Curso();
		curso.setId(idCurso);
		List<ModuloCursoDto> listaModulosCurso = new ArrayList<ModuloCursoDto>(); 
		moduloCursoDao.findByCurso(curso).forEach(modulos -> {
			listaModulosCurso.add(ModuloCursoConverter.entityToDto(modulos, modulos.getCurso()));
		});
		
		return listaModulosCurso;
	}

	@Override
	public ModuloCursoDto findOne(Long idModuloCurso) throws Exception {
		ModuloCurso moduloCurso = moduloCursoDao.findById(idModuloCurso).orElseThrow(()-> new Exception ("Este modulo no existe"));
		return ModuloCursoConverter.entityToDto(moduloCurso, moduloCurso.getCurso());
	}

	@Override
	public void saveModuloCurso (ModuloCursoSaveDto moduloCursoDto) throws Exception {
		RolUsuario docente = usuarioServiceImpl.findRolUsuarioDocente(moduloCursoDto.getIdDocente());
		Curso curso = cursoService.findOneEntity(moduloCursoDto.getIdCurso());
		ModuloCurso moduloCurso = ModuloCursoConverter.dtoToEntity(moduloCursoDto, curso, docente);
		this.moduloCursoDao.save(moduloCurso);
		
	}

	@Override
	public void updateModuloCurso (ModuloCursoUpdateDto moduloCursoUpdateDto) throws Exception {
		ModuloCurso moduloCurso = moduloCursoDao.findById(moduloCursoUpdateDto.getId()).orElseThrow(()-> new Exception("Este modulo de curso no existe"));
		RolUsuario docente = usuarioServiceImpl.findRolUsuarioDocente(moduloCursoUpdateDto.getIdDocente());
		Curso curso = cursoService.findOneEntity(moduloCursoUpdateDto.getIdCurso());
		moduloCurso = ModuloCursoConverter.dtoToEntity(moduloCurso, moduloCursoUpdateDto, curso, docente);
		this.moduloCursoDao.save(moduloCurso);
	}

	@Override
	public void updateModuloCurso (ModuloCurso moduloCurso) {
		moduloCursoDao.save(moduloCurso);
	}

	@Override
	public void deleteById(Long id) {
		this.moduloCursoDao.deleteById(id);
	}

	@Override
	public void deleteDocente (ModuloCurso moduloCurso) {
		moduloCurso.setRolUsuario(null);
		this.moduloCursoDao.save(moduloCurso);
	}

	@Override
	public CursoWithModulosDto getCursoWithModulo(Long id) {
		return CursoWithModulosDto.builder().curso(cursoService.findOne(id)).modulos(findAll(id)).build();
	}

	@Override
	public void asignarDocenteAModuloCurso(AsignarDocenteAModuloCursoDto asignarDocenteAModuloCursoDto) throws Exception {
		ModuloCurso moduloCurso = moduloCursoDao.findById(asignarDocenteAModuloCursoDto.getIdModuloCurso()).orElseThrow(()-> new Exception("Este modulo de curso no existe"));
		RolUsuario docente = usuarioServiceImpl.findRolUsuarioDocente(asignarDocenteAModuloCursoDto.getIdDocente());
		moduloCurso.setRolUsuario(docente);
		moduloCursoDao.save(moduloCurso);
	}

	@Override
	public ModuloCurso findOneEntity(Long id) throws Exception {
		return moduloCursoDao.findById(id).orElseThrow(() -> new Exception("Este Modulo del Curso no existe"));
	}

	@Override
	public List<ModuloEstudianteDto> listarModuloEstudiante (Long idModuloCurso) throws Exception {
		List<ModuloEstudianteDto> listModuloEstudianteDto = new ArrayList<ModuloEstudianteDto>();
		ModuloCurso moduloCurso = moduloCursoDao.findById(idModuloCurso).orElseThrow(() -> new Exception("Este modulo no existe"));
		moduloCurso.modulosEstudiante.forEach(moduloEstudiante -> {
			listModuloEstudianteDto.add(ModuloCursoConverter.entityToDto(moduloEstudiante, moduloEstudiante.getEstudianteInfo().getRolUsuario().getUsuario()));
		});
		return listModuloEstudianteDto;
	}

	@Override
	public void saveModuloEstudiante (ModuloEstudianteSaveDto moduloEstudianteSaveDto) throws Exception {
		EstudianteInfo estudianteInfo = estudianteInfoService.findById(moduloEstudianteSaveDto.getIdEstudianteInfo());
		ModuloCurso moduloCurso = moduloCursoDao.findById(moduloEstudianteSaveDto.getIdModuloCurso()).orElseThrow(() -> new Exception("Este modulo no existe"));
		ModuloEstudiante moduloEstudiante = ModuloCursoConverter.dtoToEntity(moduloEstudianteSaveDto, estudianteInfo, moduloCurso);
		moduloEstudianteDao.save(moduloEstudiante);
	}

	@Override
	public void updateModuloEstudiante (ModuloEstudianteUpdateDto moduloEstudianteUpdateDto) throws Exception {
		ModuloEstudiante moduloEstudiante = moduloEstudianteDao.findById(moduloEstudianteUpdateDto.getId()).orElseThrow(()-> new Exception("Esta calificacion no existe"));
		moduloEstudiante = ModuloCursoConverter.dtoToEntity(moduloEstudiante, moduloEstudianteUpdateDto);
		moduloEstudianteDao.save(moduloEstudiante);
	}

	public ModuloCursoCalificacionPdfDto listarNotasPorModulo (Long idModuloCurso) throws Exception {
		ModuloCursoCalificacionPdfDto listaModulosCalificaciones = new ModuloCursoCalificacionPdfDto();
		ModuloCurso moduloCurso = findOneEntity(idModuloCurso);
		List<ActividadDto> listaActividadesDto = new ArrayList<ActividadDto>();
		moduloCurso.getActividades().forEach(actividad-> {
			listaActividadesDto.add(ActividadConverter.entityToDto(actividad));
		});
		listaModulosCalificaciones.setActividades(listaActividadesDto);
		
		NotasActividadesPorModuloDto notasActividadesPorModulo = actividadService.listarNotasByModulo(idModuloCurso);
		
		return listaModulosCalificaciones;
	}

	@Override
	public List<ModuloCursoDto> listarModuloPorDocente (Long idDocente) throws Exception {
		List<ModuloCursoDto> moduloCursosDto = new ArrayList<ModuloCursoDto>();
		RolUsuario rolUsuario = usuarioServiceImpl.findRolUsuarioDocente(idDocente);
		rolUsuario.getModuloCurso().forEach(moduloCurso -> {
			moduloCursosDto.add(ModuloCursoConverter.entityToDto(moduloCurso, moduloCurso.getCurso()));
		});
		return moduloCursosDto;
	}
}
