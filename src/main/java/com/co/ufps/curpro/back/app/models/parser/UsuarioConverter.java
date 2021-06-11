package com.co.ufps.curpro.back.app.models.parser;

import java.util.List;
import java.util.stream.Collectors;

import com.co.ufps.curpro.back.app.constante.ConstantesEntity;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoSaveDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoUpdateDto;
import com.co.ufps.curpro.back.app.dto.RolDto;
import com.co.ufps.curpro.back.app.dto.RolUsuarioDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;

public class UsuarioConverter {

	public static List<UsuarioDto> listUsuarioToDto(List<Usuario> listUsuarios) {
		return listUsuarios.stream().map(usuario -> {
			EstudianteInfo estudianteInfo = null;
			for (RolUsuario rolUsuario : usuario.getRolUsuarios()) {
				if (ConstantesEntity.ROL_ESTUDIANTE.equalsIgnoreCase(rolUsuario.getRol().getNombre())) {
					estudianteInfo = rolUsuario.getEstudianteInfo();
				}
			}
			return usuarioToDto(usuario, estudianteInfo);
		}).collect(Collectors.toList());
	}

	public static UsuarioDto usuarioToDto (Usuario usuario, EstudianteInfo estudianteInfo) {
		return UsuarioDto.builder()
				.id(usuario.getId())
				.nombre(usuario.getNombres())
				.apellido(usuario.getApellidos())
				.correo(usuario.getCorreo())
				.roles(usuarioRolToDto(usuario.getRolUsuarios()))
				.estudianteInfo(entityToDto(estudianteInfo, usuario))
				.build();
	}

	public static List<RolUsuarioDto> usuarioRolToDto (List<RolUsuario> rolUsuarios) {
		return rolUsuarios.stream().map(rolUsu -> {
			return RolUsuarioDto.builder()
					.id(rolUsu.getId())
					.idRol(rolUsu.getRol().getId())
					.nombreRol(rolUsu.getRol().getNombre())
					.idUsuario(rolUsu.getUsuario().getId())
					.nombreUsuario(rolUsu.getUsuario().getNombres() + " " + rolUsu.getUsuario().getApellidos())
					.build();
		}).collect(Collectors.toList());
	}

	public static Usuario usuarioDtoToEntity(UsuarioDto usuarioDto, Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		usuario.setNombres(usuarioDto.getNombre());
		usuario.setApellidos(usuarioDto.getApellido());
		usuario.setCorreo(usuarioDto.getCorreo());
		return usuario;
	}

	public static RolUsuarioDto entityToDto(RolUsuario rolUsuario) {
		if (rolUsuario == null) {
			return null;
		}
		return RolUsuarioDto.builder()
				.id(rolUsuario.getId())
				.idRol(rolUsuario.getRol() != null ? rolUsuario.getRol().getId() : null)
				.nombreRol(rolUsuario.getRol() != null ? rolUsuario.getRol().getNombre() : null)
				.idUsuario(rolUsuario.getUsuario() != null ? rolUsuario.getUsuario().getId() : null)
				.nombreUsuario(rolUsuario.getUsuario() != null ? rolUsuario.getUsuario().getNombres() + " " + rolUsuario.getUsuario().getApellidos() : null)
				.build();
	}

	public static UsuarioDto entityToDto(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		return UsuarioDto.builder()
				.id(usuario.getId())
				.nombre(usuario.getNombres())
				.apellido(usuario.getApellidos())
				.correo(usuario.getCorreo())
				.build();
	}

	public static RolDto entityToDto (Rol rol) {
		return RolDto.builder().id(rol.getId()).nombre(rol.getNombre()).build();
	}

	public static EstudianteInfoDto entityToDto(EstudianteInfo estudianteInfo, Usuario usuario) {
		if (estudianteInfo == null) {
			return null;
		}
		String nombreCompleto = null;
		if (usuario != null) {
			nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
		}
		return EstudianteInfoDto.builder()
				.id(estudianteInfo.getId())
				.codigo(estudianteInfo.getCodigo())
				.cedula(estudianteInfo.getCedula())
				.direccion(estudianteInfo.getDireccion())
				.telefonoPersonal(estudianteInfo.getTelefonoPersonal())
				.telefonoFamiliar(estudianteInfo.getTelefonoFamiliar())
				.nombreCompleto(nombreCompleto)
				.build();
	}

	public static EstudianteInfo dtoToEntity (EstudianteInfoSaveDto estudianteInfoSaveDto, RolUsuario rolUsuario) {
		EstudianteInfo estudianteInfo = new EstudianteInfo();
		estudianteInfo.setCodigo(estudianteInfoSaveDto.getCodigo());
		estudianteInfo.setCedula(estudianteInfoSaveDto.getCedula());
		estudianteInfo.setDireccion(estudianteInfoSaveDto.getDireccion());
		estudianteInfo.setTelefonoPersonal(estudianteInfoSaveDto.getTelefonoPersonal());
		estudianteInfo.setTelefonoFamiliar(estudianteInfoSaveDto.getTelefonoFamiliar());
		estudianteInfo.setRolUsuario(rolUsuario);
		return estudianteInfo;
	}

	public static EstudianteInfo dtoToEntity (EstudianteInfo estudianteInfo, EstudianteInfoUpdateDto estudianteInfoUpdateDto) {
		estudianteInfo.setCodigo(estudianteInfoUpdateDto.getCodigo());
		estudianteInfo.setCedula(estudianteInfoUpdateDto.getCedula());
		estudianteInfo.setDireccion(estudianteInfoUpdateDto.getDireccion());
		estudianteInfo.setTelefonoPersonal(estudianteInfoUpdateDto.getTelefonoPersonal());
		estudianteInfo.setTelefonoFamiliar(estudianteInfoUpdateDto.getTelefonoFamiliar());
		return estudianteInfo;
	}
}
