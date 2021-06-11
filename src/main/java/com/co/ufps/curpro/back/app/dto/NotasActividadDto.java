package com.co.ufps.curpro.back.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

import lombok.Builder;
import lombok.Data;

@Data
public class NotasActividadDto {
	private EstudianteInfoDto estudianteInfo;
	private Map<Long, Double> calificaciones;

	public NotasActividadDto () {
		calificaciones = new HashMap<Long, Double>();
	}

	public void addCalificacion (Long id, Double calificacion) {
		calificaciones.put(id, calificacion);
	}

	public Double getCalificacion (Long id) {
		return calificaciones.get(id);
	}
}
