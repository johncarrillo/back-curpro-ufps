package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SustentacionUpdateDto {

	private Long id;
	private Double calificacion;

}
