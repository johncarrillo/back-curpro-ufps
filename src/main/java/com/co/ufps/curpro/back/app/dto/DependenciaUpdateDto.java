package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DependenciaUpdateDto {

	public Long id;
	public String nombre;
	public Long idUsuario;
}
