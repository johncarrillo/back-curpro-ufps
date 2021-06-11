package com.co.ufps.curpro.back.app.models.parser;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.RolDto;
import com.co.ufps.curpro.back.app.models.entity.Rol;

public class RolConverter {

	public static RolDto entityToDto(Rol rol) {
		if (rol == null) {
			return null;
		}
		return RolDto.builder().id(rol.getId()).nombre(rol.getNombre()).build();
	}

}
