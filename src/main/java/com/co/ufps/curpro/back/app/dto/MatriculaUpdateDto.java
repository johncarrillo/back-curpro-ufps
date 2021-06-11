package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatriculaUpdateDto {

	private Long id; 
	private String numeroReciboBanco;
	private String referenciaBanco;
	private Long idBanco;
	private Double costo;
	private String soporteBanco;
	private Long idEstadoMatricula;
	private String fechaRetiro;
	private String justificacionRetiro;

}
