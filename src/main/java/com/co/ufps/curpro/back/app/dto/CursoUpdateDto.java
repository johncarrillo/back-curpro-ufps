package com.co.ufps.curpro.back.app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoUpdateDto {
	private Long id;

	private String nombre;

	private String descripcion;

	private String fechaInicio;

	private String fechaFin;

	private String fechaLimitePago;

	private String fechaLimiteRetiro;

	private Integer cantidadHoras;

	private String imagen;

	private Long idTipoCurso;

	private String otroTipoCurso;

	private String justificacionRechazo;

	private Long idEstadoCurso;

	private Long idPresupuesto;
}
