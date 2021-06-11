package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadEstudianteDto {

	private Long id;
	private EstudianteInfoDto estudianteInfo;
	private Double calificacion;
	
}
