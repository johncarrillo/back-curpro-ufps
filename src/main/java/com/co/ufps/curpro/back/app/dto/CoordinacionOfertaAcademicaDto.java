package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinacionOfertaAcademicaDto {

	private Long id;

	private String nombre;

	private String perfil;

	private String idoneidadCompetencia;

	private Double valorTotal;
}
