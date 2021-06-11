package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CierreCursoUpdateDto {

	private Long id;
	private Double calificacionFinal;
	private String actaSustentacion;
}
