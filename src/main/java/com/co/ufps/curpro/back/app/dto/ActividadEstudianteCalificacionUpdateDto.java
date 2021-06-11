package com.co.ufps.curpro.back.app.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadEstudianteCalificacionUpdateDto implements Serializable {
	private Long idActividadEstudiante;
	private Double calificacion;
}
