package com.co.ufps.curpro.back.app.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemIngresoDto {

	private Long id;

	private String nombre;

	private Integer consecutivo;

	private Double valor;

	private List<ItemIngresoDto> itemIngreso;

	private List<InscripcionMatriculaDto> inscripcionMatricula;
}
