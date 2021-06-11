package com.co.ufps.curpro.back.app.dto;

import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CierreCursoDto {
	private Long id;
	private EstudianteInfoDto estudianteInfo;
	private Double calificacion;
	public String actaSustentacion;
}
