package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuloEstudianteSaveDto {
	private Long idEstudianteInfo;
	private Long idModuloCurso;
	private Double notaFinal;
}
