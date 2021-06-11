package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuloCursoUpdateDto {

	private Long id;
	private String nombre;
	private Integer horasClase;
	private Integer horasIndependientes;
	private Long idCurso;
	private Long idDocente;

}
