package com.co.ufps.curpro.back.app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoDto {
	public Long id;

	public String nombre;

	public String descripcion;

	public String imagen;

	public String fechaInicio;

	public String fechaFin;

	public String fechaLimitePago;

	public String fechaLimiteRetiro;

	public Integer cantidadHoras;

	public TipoCursoDto tipoCurso;

	public EstadoCursoDto estadoCurso;

	public String otroTipoCurso;

	public String justificacionRechazo;

	public Long idPresupuesto;

	public DependenciaDto dependencia;
}
