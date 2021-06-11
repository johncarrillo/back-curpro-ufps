package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadEstudianteSaveDto {

	private Long idEstudianteInfo;
	private Long idActividad;
	private Double calificacion;

}
