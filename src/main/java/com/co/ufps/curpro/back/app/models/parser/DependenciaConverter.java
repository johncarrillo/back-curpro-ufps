package com.co.ufps.curpro.back.app.models.parser;

import java.util.List;
import java.util.stream.Collectors;

import com.co.ufps.curpro.back.app.dto.DependenciaDto;
import com.co.ufps.curpro.back.app.dto.DependenciaSaveDto;
import com.co.ufps.curpro.back.app.dto.DependenciaUpdateDto;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;

public class DependenciaConverter {

	public static DependenciaDto entityToDto(Dependencia dependencia) {
		if (dependencia == null) {
			return null;
		}
		return DependenciaDto.builder()
				.id(dependencia.getId())
				.nombre(dependencia.getNombre())
				.rolUsuarioDto(UsuarioConverter.entityToDto(dependencia.getRolUsuario()))
				.build();
	}

	public static List<DependenciaDto> entitiesToDto(List<Dependencia> dependencias) {
		if (dependencias == null) {
			return null;
		}
		return dependencias
				.stream()
				.map(dependencia->entityToDto(dependencia))
				.collect(Collectors.toList());
	}

	public static Dependencia dtoToEntity (DependenciaSaveDto dependenciaSaveDto) {
		Dependencia dependencia = new Dependencia();
		dependencia.setNombre(dependenciaSaveDto.getNombre());
		return dependencia;
	}

	public static Dependencia dtoToEntity (Dependencia dependencia, DependenciaUpdateDto dependenciaUpdate, RolUsuario rolUsuario) {
		dependencia.setNombre(dependenciaUpdate.getNombre());
		dependencia.setRolUsuario(rolUsuario);
		return dependencia;
	}
}
