package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadDto {

	private Long id;
	private String nombre;
	private String descripcion;
	private String fechaLimite;
	private String fechaCreacion;
	private TipoActividadDto tipoActividad;
}
