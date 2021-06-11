package com.co.ufps.curpro.back.app.models.parser;


import com.co.ufps.curpro.back.app.dto.CursoDto;
import com.co.ufps.curpro.back.app.dto.CursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.EstadoCursoDto;
import com.co.ufps.curpro.back.app.dto.TipoCursoDto;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.EstadoCurso;
import com.co.ufps.curpro.back.app.models.entity.Presupuesto;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.TipoCurso;
import com.co.ufps.curpro.back.app.util.Common;

public class CursoConverter {

	public static Curso dtoToEntity(Curso curso, CursoUpdateDto cursoUpdateDto, EstadoCurso estadoCurso, TipoCurso tipoCurso) {
		if (curso == null) {
			return null;
		}
		curso.setNombre(cursoUpdateDto.getNombre());
		curso.setDescripcion(cursoUpdateDto.getDescripcion());
		curso.setFechaInicio(Common.stringToFecha(cursoUpdateDto.getFechaInicio()));
		curso.setFechaFin(Common.stringToFecha(cursoUpdateDto.getFechaFin()));
		curso.setFechaLimitePago(Common.stringToFecha(cursoUpdateDto.getFechaLimitePago()));
		curso.setFechaLimiteRetiro(Common.stringToFecha(cursoUpdateDto.getFechaLimiteRetiro()));
		curso.setCantidadHoras(cursoUpdateDto.getCantidadHoras());
		curso.setImagen(cursoUpdateDto.getImagen());
		curso.setEstadoCurso(estadoCurso);
		curso.setTipoCurso(tipoCurso);
		curso.setOtroTipoCurso(cursoUpdateDto.getOtroTipoCurso());
		curso.setJustificacionRechazo(cursoUpdateDto.getJustificacionRechazo());
		return curso;
	}

	public static Curso dtoToEntity (CursoSaveDto cursoSaveDto, EstadoCurso EstadoCurso, TipoCurso tipoCurso, Presupuesto presupuesto, Dependencia dependencia) {
		if (cursoSaveDto == null) {
			return null;
		}
		Curso curso = new Curso();
		curso.setNombre(cursoSaveDto.getNombre());
		curso.setDescripcion(cursoSaveDto.getDescripcion());
		curso.setFechaInicio(Common.stringToFecha(cursoSaveDto.getFechaInicio()));
		curso.setFechaFin(Common.stringToFecha(cursoSaveDto.getFechaFin()));
		curso.setFechaLimitePago(Common.stringToFecha(cursoSaveDto.getFechaLimitePago()));
		curso.setFechaLimiteRetiro(Common.stringToFecha(cursoSaveDto.getFechaLimiteRetiro()));
		curso.setCantidadHoras(cursoSaveDto.getCantidadHoras());
		curso.setImagen(cursoSaveDto.getImagen());
		curso.setOtroTipoCurso(cursoSaveDto.getOtroTipoCurso());
		curso.setEstadoCurso(EstadoCurso);
		curso.setTipoCurso(tipoCurso);
		curso.setPresupuesto(presupuesto);
		curso.setDependencia(dependencia);
		return curso;
	}

	public static CursoDto entityToDto (Curso curso) {
		if (curso == null) {
			return null;
		}
		return CursoDto.builder()
				.id(curso.getId())
				.nombre(curso.getNombre())
				.descripcion(curso.getDescripcion())
				.fechaInicio(Common.dateToString(curso.getFechaInicio()))
				.fechaFin(Common.dateToString(curso.getFechaFin()))
				.fechaLimitePago(Common.dateToString(curso.getFechaLimitePago()))
				.fechaLimiteRetiro(Common.dateToString(curso.getFechaLimiteRetiro()))
				.cantidadHoras(curso.getCantidadHoras())
				.imagen(curso.getImagen())
				.estadoCurso(entityToDto(curso.getEstadoCurso()))
				.tipoCurso(entityToDto(curso.getTipoCurso()))
				.otroTipoCurso(curso.getOtroTipoCurso())
				.justificacionRechazo(curso.getJustificacionRechazo())
				.dependencia(DependenciaConverter.entityToDto(curso.getDependencia()))
				.idPresupuesto(curso.getPresupuesto() == null ? null : curso.getPresupuesto().getId())
				.build();
	}

	public static TipoCursoDto entityToDto (TipoCurso tipoCurso) {
		if (tipoCurso == null) {
			return null;
		}
		return TipoCursoDto.builder().id(tipoCurso.getId()).nombre(tipoCurso.getNombre()).build();
	}

	public static EstadoCursoDto entityToDto (EstadoCurso estadoCurso) {
		if (estadoCurso == null) {
			return null;
		}
		return EstadoCursoDto.builder().id(estadoCurso.getId()).nombre(estadoCurso.getNombre()).build();
	}
}
