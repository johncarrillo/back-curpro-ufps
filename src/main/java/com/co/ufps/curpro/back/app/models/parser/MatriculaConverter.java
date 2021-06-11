package com.co.ufps.curpro.back.app.models.parser;

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
import com.co.ufps.curpro.back.app.models.entity.Banco;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstadoMatricula;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Inscripcion;
import com.co.ufps.curpro.back.app.models.entity.Matricula;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.util.Common;

public class MatriculaConverter {

	public static MatriculaDto entityToDto (Matricula matricula, Usuario usuario) {
		if (matricula == null) {
			return null;
		}
		return MatriculaDto.builder()
				.id(matricula.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(matricula.getEstudianteInformacion(), usuario))
				.curso(CursoConverter.entityToDto(matricula.getCurso()))
				.estadoMatricula(entityToDto(matricula.getEstadoMatricula()))
				.numeroReciboBanco(matricula.getNumeroReciboBanco())
				.referenciaBanco(matricula.getReferenciaBanco())
				.banco(entityToDto(matricula.getBanco()))
				.costo(matricula.getCosto())
				.soporteBanco(matricula.getSoporteBanco())
				.fechaRetiro(Common.dateToString(matricula.getFechaRetiro()))
				.justificacionRetiro(matricula.getJustificacionRetiro())
				.build();
	}

	public static MatriculaPdfDto entityToDtoPdf (Matricula matricula, Usuario usuario) {
		if (matricula == null) {
			return null;
		}
		return MatriculaPdfDto.builder()
				.id(matricula.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(matricula.getEstudianteInformacion(), usuario))
				.curso(CursoConverter.entityToDto(matricula.getCurso()))
				.estadoMatricula(entityToDto(matricula.getEstadoMatricula()))
				.numeroReciboBanco(matricula.getNumeroReciboBanco())
				.referenciaBanco(matricula.getReferenciaBanco())
				.banco(entityToDto(matricula.getBanco()))
				.costo(matricula.getCosto())
				.soporteBanco(matricula.getSoporteBanco())
				.fechaRetiro(Common.dateToString(matricula.getFechaRetiro()))
				.justificacionRetiro(matricula.getJustificacionRetiro())
				.nombreCompleto(usuario.getNombres() + " " + usuario.getApellidos())
				.build();
	}
	
	public static InscripcionDto entityToDto (Inscripcion inscripcion, Usuario usuario) {
		if (inscripcion == null) {
			return null;
		}
		return InscripcionDto.builder()
				.id(inscripcion.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(inscripcion.getEstudianteInfo(), usuario))
				.curso(CursoConverter.entityToDto(inscripcion.getCurso()))
				.numeroReciboBanco(inscripcion.getNumeroReciboBanco())
				.referenciaBanco(inscripcion.getReferenciaBanco())
				.banco(entityToDto(inscripcion.getBanco()))
				.costo(inscripcion.getCosto())
				.soporteBanco(inscripcion.getSoporteBanco())
				.build();
	}

	public static InscripcionPdfDto entityToDtoPdf (Inscripcion inscripcion, Usuario usuario) {
		if (inscripcion == null) {
			return null;
		}
		return InscripcionPdfDto.builder()
				.id(inscripcion.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(inscripcion.getEstudianteInfo(), usuario))
				.curso(CursoConverter.entityToDto(inscripcion.getCurso()))
				.numeroReciboBanco(inscripcion.getNumeroReciboBanco())
				.referenciaBanco(inscripcion.getReferenciaBanco())
				.banco(entityToDto(inscripcion.getBanco()))
				.costo(inscripcion.getCosto())
				.soporteBanco(inscripcion.getSoporteBanco())
				.nombreCompleto(usuario.getNombres() + " " + usuario.getApellidos())
				.build();
	}

	public static EstadoMatriculaDto entityToDto (EstadoMatricula estadoMatricula) {
		return EstadoMatriculaDto.builder().id(estadoMatricula.getId()).nombre(estadoMatricula.getNombre()).build();
	}

	public static BancoDto entityToDto (Banco banco) {
		return BancoDto.builder().id(banco.getId()).nombre(banco.getNombre()).build();
	}

	public static Inscripcion dtoToEntity(InscripcionSaveDto inscripcionDto, EstudianteInfo estudiante, Curso curso, Banco banco) {
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setEstudianteInfo(estudiante);
		inscripcion.setCurso(curso);
		inscripcion.setNumeroReciboBanco(inscripcionDto.getNumeroReciboBanco());
		inscripcion.setReferenciaBanco(inscripcionDto.getReferenciaBanco());
		inscripcion.setBanco(banco);
		inscripcion.setCosto(inscripcionDto.getCosto());
		inscripcion.setSoporteBanco(inscripcionDto.getSoporteBanco());
		return inscripcion;
	}

	public static Matricula dtoToEntity(MatriculaSaveDto matriculaDto, EstudianteInfo estudiante, Curso curso, Banco banco, EstadoMatricula estadoMatricula) {
		Matricula matricula = new Matricula();
		matricula.setEstudianteInformacion(estudiante);
		matricula.setCurso(curso);
		matricula.setEstadoMatricula(estadoMatricula);
		matricula.setNumeroReciboBanco(matriculaDto.getNumeroReciboBanco());
		matricula.setReferenciaBanco(matriculaDto.getReferenciaBanco());
		matricula.setBanco(banco);
		matricula.setCosto(matriculaDto.getCosto());
		matricula.setSoporteBanco(matriculaDto.getSoporteBanco());
		return matricula;
	}

	public static Inscripcion dtoToEntity(Inscripcion inscripcion, InscripcionUpdateDto inscripcionDto, Banco banco) {
		inscripcion.setNumeroReciboBanco(inscripcionDto.getNumeroReciboBanco());
		inscripcion.setReferenciaBanco(inscripcionDto.getReferenciaBanco());
		inscripcion.setBanco(banco);
		inscripcion.setCosto(inscripcionDto.getCosto());
		inscripcion.setSoporteBanco(inscripcionDto.getSoporteBanco());
		return inscripcion;
	}

	public static Matricula dtoToEntity(Matricula matricula, MatriculaUpdateDto matriculaDto, Banco banco, EstadoMatricula estadoMatricula) {
		matricula.setNumeroReciboBanco(matriculaDto.getNumeroReciboBanco());
		matricula.setReferenciaBanco(matriculaDto.getReferenciaBanco());
		matricula.setBanco(banco);
		matricula.setCosto(matriculaDto.getCosto());
		matricula.setSoporteBanco(matriculaDto.getSoporteBanco());
		matricula.setFechaRetiro(Common.stringToFecha(matriculaDto.getFechaRetiro()));
		matricula.setJustificacionRetiro(matriculaDto.getJustificacionRetiro());
		return matricula;
	}
}
