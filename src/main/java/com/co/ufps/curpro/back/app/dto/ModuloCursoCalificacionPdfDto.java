package com.co.ufps.curpro.back.app.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModuloCursoCalificacionPdfDto {
	private List<CalificacionPdfDto> calificaciones;
	private List<ActividadDto> actividades;
}
