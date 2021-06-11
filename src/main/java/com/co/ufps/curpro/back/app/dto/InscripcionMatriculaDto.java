package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InscripcionMatriculaDto {

	private Long id;

	private String tipoParticipante;

	private Integer cantidad;

	private Double valorUnitario;

	private Double valorTotal;

}
