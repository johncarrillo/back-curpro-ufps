package com.co.ufps.curpro.back.app.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoWithModulosDto {
	private CursoDto curso;
	private List<ModuloCursoDto> modulos;
}
