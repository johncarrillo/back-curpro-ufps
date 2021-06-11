package com.co.ufps.curpro.back.app.dto;

import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CierreCursoSaveDto {

	private Long idCurso;
	private Long idEstudianteInfo;
	private Double calificacionFinal;
	private String actaSustentacion;
}
