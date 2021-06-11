package com.co.ufps.curpro.back.app.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
public class CalificacionPdfDto {
	private String codigo;
	private String nombreCompleto;
	private Map<Long, Double> calificaciones;
	private Double notaFinal;
	private Double ponderado;

	public CalificacionPdfDto () {
		calificaciones = new HashMap<>();
	}

	public void addCalificacion(Long idActividad, Double calificacion) {
		calificaciones.put(idActividad, calificacion);
	}
}
