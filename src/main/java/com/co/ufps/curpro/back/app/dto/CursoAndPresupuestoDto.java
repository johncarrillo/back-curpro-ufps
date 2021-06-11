package com.co.ufps.curpro.back.app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoAndPresupuestoDto {
	private CursoSaveDto curso;
	private PresupuestoDto presupuesto;
}
