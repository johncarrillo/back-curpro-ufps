package com.co.ufps.curpro.back.app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatriculaPdfDto {
	private Long id;
	private CursoDto curso;
	private EstudianteInfoDto estudianteInfo;
	private EstadoMatriculaDto estadoMatricula; 
	private String numeroReciboBanco;
	private String referenciaBanco;
	private BancoDto banco;
	private Double costo;
	private String soporteBanco;
	private String fechaRetiro;
	private String justificacionRetiro;
	private String nombreCompleto;
	
}
