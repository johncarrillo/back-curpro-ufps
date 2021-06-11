package com.co.ufps.curpro.back.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class RolAsignarUsuarioDto {

	private Long idUsuario;
	private Long idRol;
	private Boolean asignar;
}
