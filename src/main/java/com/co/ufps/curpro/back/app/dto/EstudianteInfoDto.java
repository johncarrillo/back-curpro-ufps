package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstudianteInfoDto {

	private Long id;
	private String codigo;
	private String cedula;
	private String direccion;
	private String telefonoPersonal;
	private String telefonoFamiliar;
	private String nombreCompleto;

}
