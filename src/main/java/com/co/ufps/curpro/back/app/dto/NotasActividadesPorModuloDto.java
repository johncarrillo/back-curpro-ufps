package com.co.ufps.curpro.back.app.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data

public class NotasActividadesPorModuloDto {
	private ModuloCursoDto moduloCursoDto;
	private List<ActividadDto> actividadesInfo;
	private List<NotasActividadDto> listaNotasActividades;
}
