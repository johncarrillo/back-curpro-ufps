package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.AsignarDocenteAModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.CursoWithModulosDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;

public interface IModuloCursoService {

	public List<ModuloCursoDto> findAll(Long idCurso);

	public ModuloCursoDto findOne(Long idModuloCurso) throws Exception;

	public void saveModuloCurso (ModuloCursoSaveDto moduloCursoDto) throws Exception;

	public void updateModuloCurso (ModuloCursoUpdateDto moduloCursoUpdateDto) throws Exception ;

	public void deleteById(Long id);

	public void deleteDocente (ModuloCurso moduloCurso);

	public CursoWithModulosDto getCursoWithModulo(Long id);

	public void asignarDocenteAModuloCurso(AsignarDocenteAModuloCursoDto asignarDocenteAModuloCursoDto) throws Exception;

	public ModuloCurso findOneEntity(Long id) throws Exception;

	public List<ModuloEstudianteDto> listarModuloEstudiante (Long idModuloCurso) throws Exception;

	public void saveModuloEstudiante (ModuloEstudianteSaveDto moduloEstudianteSaveDto) throws Exception;

	public void updateModuloEstudiante (ModuloEstudianteUpdateDto moduloEstudianteUpdateDto) throws Exception;

	public void updateModuloCurso (ModuloCurso moduloCurso);

	public List<ModuloCursoDto> listarModuloPorDocente (Long idDocente) throws Exception;
}
