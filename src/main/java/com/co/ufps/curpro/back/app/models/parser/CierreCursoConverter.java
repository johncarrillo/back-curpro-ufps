package com.co.ufps.curpro.back.app.models.parser;

import com.co.ufps.curpro.back.app.dto.CierreCursoDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.SustentacionDto;
import com.co.ufps.curpro.back.app.dto.SustentacionSaveDto;
import com.co.ufps.curpro.back.app.dto.SustentacionUpdateDto;
import com.co.ufps.curpro.back.app.models.entity.CierreCurso;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Sustentacion;
import com.co.ufps.curpro.back.app.models.entity.Usuario;

public class CierreCursoConverter {

	public static SustentacionDto entityToDto (Sustentacion sustentacion, Usuario usuario) {
		return SustentacionDto.builder()
				.id(sustentacion.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(sustentacion.getEstudianteInfo(), usuario))
				.calificacion(sustentacion.getCalificacion())
				.build();
	}

	public static Sustentacion dtoToEntity (SustentacionSaveDto sustentacionSaveDto, EstudianteInfo estudianteInfo, Curso curso) {
		Sustentacion sustentacion = new Sustentacion();
		sustentacion.setCurso(curso);
		sustentacion.setEstudianteInfo(estudianteInfo);
		sustentacion.setCalificacion(sustentacionSaveDto.getCalificacion());
		return sustentacion;
	}

	public static Sustentacion dtoToEntity (Sustentacion sustentacion, SustentacionUpdateDto sustentacionUpdateDto) {
		sustentacion.setCalificacion(sustentacionUpdateDto.getCalificacion());
		return sustentacion;
	}

	public static CierreCursoDto entityToDto (CierreCurso cierreCurso, Usuario usuario) {
		return CierreCursoDto.builder()
				.id(cierreCurso.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(cierreCurso.getEstudianteInfo(), usuario))
				.calificacion(cierreCurso.getCalificacionFinal())
				.actaSustentacion(cierreCurso.getActaSustentacion())
				.build();
	}

	public static CierreCurso dtoToEntity (CierreCursoSaveDto cierreCursoSaveDto, EstudianteInfo estudianteInfo, Curso curso) {
		CierreCurso cierreCurso = new CierreCurso();
		cierreCurso.setCurso(curso);
		cierreCurso.setEstudianteInfo(estudianteInfo);
		cierreCurso.setCalificacionFinal(cierreCursoSaveDto.getCalificacionFinal());
		cierreCurso.setActaSustentacion(cierreCurso.getActaSustentacion());
		return cierreCurso;
	}

	public static CierreCurso dtoToEntity (CierreCurso cierreCurso, CierreCursoUpdateDto cierreCursoUpdateDto) {
		cierreCurso.setCalificacionFinal(cierreCursoUpdateDto.getCalificacionFinal());
		cierreCurso.setActaSustentacion(cierreCursoUpdateDto.getActaSustentacion());
		return cierreCurso;
	}
}
