package com.co.ufps.curpro.back.app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoSaveDto {
	public String nombre;

	public String descripcion;

	public String fechaInicio;

	public String fechaFin;

	public String fechaLimitePago;

	public String fechaLimiteRetiro;

	public Integer cantidadHoras;

	public String imagen;

	public Long idTipoCurso;

	public String otroTipoCurso;

}
