package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoCursoDto {

	private Long id;

	private String nombre;
}
