package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoActividadUpdateDto {

	private Long id;
	private String nombre;
	private String descripcion;
}
