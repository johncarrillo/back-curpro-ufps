package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadEstudianteCalificacionSaveDto {
	private Long idEstudianteInfo;
	private Double calificacion;
}
