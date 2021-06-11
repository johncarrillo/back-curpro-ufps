package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtroAnexoDto {

	private Long id;

	private Integer numero;

	private String descripcion;

	private Double valorTotal;

}
