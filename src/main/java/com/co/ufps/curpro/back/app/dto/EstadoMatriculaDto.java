package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EstadoMatriculaDto {

	private Long id;
	private String nombre;
}
