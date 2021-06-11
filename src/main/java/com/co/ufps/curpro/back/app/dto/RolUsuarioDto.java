package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolUsuarioDto {

	private String nombreRol;
	private Long idRol;
	private String nombreUsuario;
	private Long idUsuario;
	private Long id;
}
