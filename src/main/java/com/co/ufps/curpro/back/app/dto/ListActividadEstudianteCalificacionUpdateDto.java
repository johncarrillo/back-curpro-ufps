package com.co.ufps.curpro.back.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListActividadEstudianteCalificacionUpdateDto implements Serializable{
	private List<ActividadEstudianteCalificacionUpdateDto> calificacionEstudiante;
}
