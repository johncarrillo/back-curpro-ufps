package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresupuestoDto {

	private Long id;

	private Double valorIngreso; 

	private Double valorGasto; 

	private Double utilidad;

	private Double fondoInvestigacionUniversitario;

	private Double utilidadNeta;

	private ItemGastoDto itemGasto;

	private ItemIngresoDto itemIngreso;
}
