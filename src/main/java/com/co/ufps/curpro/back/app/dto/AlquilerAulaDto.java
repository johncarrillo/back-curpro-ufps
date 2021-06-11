package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlquilerAulaDto {

	private Long id;

	private Integer numero;

	private String dependenciaPrestaServicio;

	private Integer cantidad;

	private Double valorUnitario;

	private Double valorTotal;
}
