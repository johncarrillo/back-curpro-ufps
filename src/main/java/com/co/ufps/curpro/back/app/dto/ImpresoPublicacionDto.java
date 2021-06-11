package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImpresoPublicacionDto {

	private Long id;

	private Integer numero;

	private String publicacion;

	private Integer cantidad;

	private Double valorUnitario;

	private Double valorTotal;
}
