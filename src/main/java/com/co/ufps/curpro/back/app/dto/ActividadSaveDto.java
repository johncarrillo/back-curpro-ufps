package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadSaveDto {

	private String nombre;
	private String descripcion;
	private String fechaLimite;
	private String fechaCreacion;
	private Long idTipoActividad;
	private Long idModuloCurso;
}
