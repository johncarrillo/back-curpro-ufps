package com.co.ufps.curpro.back.app.models.security;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.co.ufps.curpro.back.app.models.dao.IRolDao;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.parser.RolConverter;

public class UsuarioPrincipalFactory {

	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities
				= usuario.getRolUsuarios()
					.stream()
						.map(rolUsuario -> new SimpleGrantedAuthority(rolUsuario.getRol().getNombre()))
							.collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getCorreo(), usuario.getContrasena(), authorities);
	};
}
