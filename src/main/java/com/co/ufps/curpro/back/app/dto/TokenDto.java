package com.co.ufps.curpro.back.app.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TokenDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private List<RolDto> roles;
	private EstudianteInfoDto estudianteInfo;

	public TokenDto () {
		roles = new ArrayList();
	}

	public void addRol (RolDto rolDto) {
		roles.add(rolDto);
	}
}
