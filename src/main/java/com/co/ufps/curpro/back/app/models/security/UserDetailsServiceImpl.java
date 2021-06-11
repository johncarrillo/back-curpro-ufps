package com.co.ufps.curpro.back.app.models.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.models.dao.IUsuarioDao;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.service.IUsuarioService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUsuarioDao usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByCorreo(email);
		if (usuario == null) {
			new UsernameNotFoundException("Email no encontrado");
		}

		return UsuarioPrincipalFactory.build(usuario);
	}

}
