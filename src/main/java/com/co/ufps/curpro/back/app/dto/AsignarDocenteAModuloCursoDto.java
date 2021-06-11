package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsignarDocenteAModuloCursoDto {
	private Long idModuloCurso;
	private Long idDocente;
}
