package com.co.ufps.curpro.back.app.models.parser;

import com.co.ufps.curpro.back.app.dto.ModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.ModuloEstudiante;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;

public class ModuloCursoConverter {

	public static ModuloCursoDto entityToDto (ModuloCurso moduloCurso, Curso curso) {
		if (moduloCurso == null) {
			return null;
		}
		return ModuloCursoDto.builder()
				.id(moduloCurso.getId())
				.nombre(moduloCurso.getNombre())
				.horasClase(moduloCurso.getHorasClase())
				.horasIndependientes(moduloCurso.getHorasIndependientes())
				.docente(UsuarioConverter.entityToDto(moduloCurso.getRolUsuario()))
				.curso(CursoConverter.entityToDto(curso))
				.build();
	}

	public static ModuloCurso dtoToEntity (ModuloCursoSaveDto moduloCursoSave, Curso curso, RolUsuario docente) {
		if (moduloCursoSave == null) {
			return null;
		}
		ModuloCurso moduloCurso = new ModuloCurso();
		moduloCurso.setNombre(moduloCursoSave.getNombre());
		moduloCurso.setHorasClase(moduloCursoSave.getHorasClase());
		moduloCurso.setHorasIndependientes(moduloCursoSave.getHorasIndependientes());
		moduloCurso.setCurso(curso);
		moduloCurso.setRolUsuario(docente);
		return moduloCurso;
	}

	public static ModuloCurso dtoToEntity (ModuloCurso moduloCurso, ModuloCursoUpdateDto moduloCursoUpdate, Curso curso, RolUsuario docente) {
		if (moduloCursoUpdate == null) {
			return null;
		}
		moduloCurso.setNombre(moduloCursoUpdate.getNombre());
		moduloCurso.setHorasClase(moduloCursoUpdate.getHorasClase());
		moduloCurso.setHorasIndependientes(moduloCursoUpdate.getHorasIndependientes());
		moduloCurso.setCurso(curso);
		moduloCurso.setRolUsuario(docente);
		return moduloCurso;
	}

	public static ModuloEstudianteDto entityToDto (ModuloEstudiante moduloEstudiante, Usuario usuario) {
		return ModuloEstudianteDto.builder()
				.id(moduloEstudiante.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(moduloEstudiante.getEstudianteInfo(), usuario))
				.notaFinal(moduloEstudiante.getNotaFinal())
				.build();
	}

	public static ModuloEstudiante dtoToEntity (ModuloEstudianteSaveDto moduloEstudianteSaveDto, EstudianteInfo estudianteInfo, ModuloCurso moduloCurso) {
		ModuloEstudiante moduloEstudiante = new ModuloEstudiante();
		moduloEstudiante.setEstudianteInfo(estudianteInfo);
		moduloEstudiante.setModuloCurso(moduloCurso);
		moduloEstudiante.setNotaFinal(moduloEstudianteSaveDto.getNotaFinal());
		return moduloEstudiante;
	}

	public static ModuloEstudiante dtoToEntity (ModuloEstudiante moduloEstudiante, ModuloEstudianteUpdateDto moduloEstudianteUpdateDto) {
		moduloEstudiante.setNotaFinal(moduloEstudianteUpdateDto.getNotaFinal());
		return moduloEstudiante;
	}
}
