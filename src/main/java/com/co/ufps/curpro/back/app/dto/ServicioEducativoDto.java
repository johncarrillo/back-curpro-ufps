package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicioEducativoDto {

	private Long id;

	private String nombreDocente;

	private String escolaridad;

	private String escalafon;

	private Double puntaje;

	private Double valorPunto;

	private Double valorHora;

	private Integer cantidadHora;

	private Double valorTotal;

}
