package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialSuministroDto {

	private Long id;

	private Integer numero;

	private String bienServicio;

	private Integer cantidad;

	private Double valorUnitario;

	private Double valorTotal;
}
