package com.co.ufps.curpro.back.app.dto;

import java.io.Serializable;
import java.util.List;

import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nombre;

	private String apellido;

	private String correo;

	private List<RolUsuarioDto> roles;

	private EstudianteInfoDto estudianteInfo;
}
