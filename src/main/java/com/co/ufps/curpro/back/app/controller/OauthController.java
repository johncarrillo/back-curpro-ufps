package com.co.ufps.curpro.back.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.co.ufps.curpro.back.app.constante.ConstantesEntity;
import com.co.ufps.curpro.back.app.dto.TokenDto;
import com.co.ufps.curpro.back.app.models.dao.IRolDao;
import com.co.ufps.curpro.back.app.models.dao.IRolUsuarioDao;
import com.co.ufps.curpro.back.app.models.dao.IUsuarioDao;
import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.parser.RolConverter;
import com.co.ufps.curpro.back.app.models.parser.UsuarioConverter;
import com.co.ufps.curpro.back.app.models.security.jwt.JwtProvider;
import com.co.ufps.curpro.back.app.models.service.IUsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;

@RestController
@RequestMapping("oauth")
@CrossOrigin
public class OauthController {

	@Value("${google.clientId}")
	String googleId;

	@Value("${secretPsw}")
	String secretPsw;

	@Value("${emailRoot}")
	String emailRoot;

	@Autowired
	PasswordEncoder passwordEncode;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	IRolDao rolDao;

	@Autowired
	IRolUsuarioDao rolUsuarioDao;

	@Autowired
	IUsuarioDao usuarioDao;

	@PostMapping("/google")
	public ResponseEntity<TokenDto> google(@RequestBody TokenDto tokenDto) throws IOException{
		final NetHttpTransport transport = new NetHttpTransport();
		final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
		GoogleIdTokenVerifier.Builder verifier
				= new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
					.setAudience(Collections.singletonList(googleId));
		final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
		final GoogleIdToken.Payload payload = googleIdToken.getPayload();
		Usuario usuario = usuarioDao.findByCorreo(payload.getEmail());
		if (usuario == null) {
			usuario = saveUsuario(payload.get("given_name").toString(), payload.get("family_name").toString(), payload.getEmail() );
		} else if (null == usuario.getContrasena()) {
			usuario = this.updateUsuario(usuario);
		}
		TokenDto tokenRes = login(usuario);
		return new ResponseEntity(tokenRes, HttpStatus.OK);
	}

	private TokenDto login(Usuario usuario) {
		Authentication autentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getCorreo(), secretPsw)
		);
		SecurityContextHolder.getContext().setAuthentication(autentication);
		String jwt = jwtProvider.generateToken(autentication);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setValue(jwt);
		usuario = usuarioDao.findByCorreo(usuario.getCorreo());
		if (usuario.getCorreo().equals(emailRoot)) {
			tokenDto.addRol(RolConverter.entityToDto(rolDao.findByNombre("Administrador")));
		}
		rolUsuarioDao.findAllByUsuario(usuario).stream().forEach(rolUsu -> {
			long esAdmin = tokenDto.getRoles().stream().filter(rol -> "Administrador".equalsIgnoreCase(rol.getNombre())).count();
			if ("Administrador".equals(rolUsu.getRol().getNombre()) && esAdmin > 0) {
				return;
			} else if (ConstantesEntity.ROL_ESTUDIANTE.equalsIgnoreCase(rolUsu.getRol().getNombre())) {
				tokenDto.setEstudianteInfo(UsuarioConverter.entityToDto(rolUsu.getEstudianteInfo(), rolUsu.getUsuario()));
			}
			tokenDto.addRol(RolConverter.entityToDto(rolUsu.getRol()));
		});
		
		return tokenDto;
	}

	private Usuario saveUsuario (String nombres, String apellidos, String email) {
		Usuario usuario = new Usuario(email, passwordEncode.encode(secretPsw));
		usuario.setNombres(nombres);
		usuario.setApellidos(apellidos);
		usuarioDao.save(usuario);
		
		Rol rol = rolDao.findByNombre("Estudiante");

		RolUsuario rolUsuario = new RolUsuario();
		rolUsuario.setRol(rol);
		rolUsuario.setUsuario(usuario);
		usuario.addRolUsuarios(rolUsuarioDao.save(rolUsuario));

		return usuario;
		
	}

	private Usuario updateUsuario (Usuario usuario) {
		usuario.setContrasena(passwordEncode.encode(secretPsw));
		return usuarioDao.save(usuario);
		
	}
}
