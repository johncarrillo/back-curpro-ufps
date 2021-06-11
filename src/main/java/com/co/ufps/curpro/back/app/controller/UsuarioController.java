package com.co.ufps.curpro.back.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ufps.curpro.back.app.dto.EstudianteInfoDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoSaveDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoUpdateDto;
import com.co.ufps.curpro.back.app.dto.InscripcionDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.RolAsignarUsuarioDto;
import com.co.ufps.curpro.back.app.dto.RolDto;
import com.co.ufps.curpro.back.app.dto.RolUsuarioDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.service.IEstudianteInfoService;
import com.co.ufps.curpro.back.app.models.service.IMailService;
import com.co.ufps.curpro.back.app.models.service.IModuloCursoService;
import com.co.ufps.curpro.back.app.models.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;

	@Autowired
	IMailService mailService;

	@Autowired
	IEstudianteInfoService estudianteInfoServiceImpl;

	@Autowired
	IModuloCursoService moduloCursoServiceImpl;

	@GetMapping
	public List<UsuarioDto> lista() {
		return usuarioService.findAll();
	}

	@GetMapping("/roles")
	public List<RolDto> listarRol() {
		return usuarioService.listRol();
	}

	@GetMapping("/{id}")
	public UsuarioDto buscar(@PathVariable(value="id") Long id) {
		return usuarioService.findOne(id);
	}

	@PutMapping
	public void actualizar(@RequestBody UsuarioDto usuarioDto) {
		usuarioService.save(usuarioDto);
	}

	@PostMapping
	public void guardar(@RequestBody UsuarioDto usuarioDto) {
		usuarioService.save(usuarioDto);
	}

	@PostMapping("/asignarRol")
	public void asignarRol(@RequestBody RolAsignarUsuarioDto rolAsignarUsuarioDto) throws Exception {
		usuarioService.addRolToUsuario(rolAsignarUsuarioDto);
	}


	public UsuarioDto buscarPorCorreo(String correo) {
		return usuarioService.findByCorreo(correo);
	}

	@GetMapping("/directores")
	public List<UsuarioDto> listarDirectores() {
		return usuarioService.findDirectoresDisponibles();
	}


	@GetMapping("/usuario/rol/{id}")
	public List<UsuarioDto> listaUsuarioPorRol(@PathVariable(value="id") Long id) throws Exception {
		return this.usuarioService.listUsuarioByRol(id);
	}

	@GetMapping("/info")
	public UsuarioDto find() throws Exception {
		return estudianteInfoServiceImpl.find();
	}

	@GetMapping("/estudiante-info/all")
	public List<EstudianteInfoDto> buscarInfoEstudiante() throws Exception {
		return estudianteInfoServiceImpl.findAll();
	}

	@PostMapping("/estudiante-info")
	public void saveInfoEstudiante(@RequestBody EstudianteInfoSaveDto estudianteInfoSaveDto) throws Exception {
		estudianteInfoServiceImpl.save(estudianteInfoSaveDto);
	}

	@PutMapping("/estudiante-info")
	public void updateInfoEstudiante(@RequestBody EstudianteInfoUpdateDto estudianteInfoUpdateDto) throws Exception {
		estudianteInfoServiceImpl.update(estudianteInfoUpdateDto);
	}

	@GetMapping("/docente/modulo-cursos/{id}")
	public List<ModuloCursoDto> listarModuloCursoPorIdUsuarioDocente(@PathVariable(value="id") Long id) throws Exception {
		return moduloCursoServiceImpl.listarModuloPorDocente(id);
	}

	@GetMapping("/matricula/estudianteInfo/{idEstudianteInfo}")
	public List<MatriculaDto> buscarMatriculaPorEstudianteInfo(@PathVariable(value="idEstudianteInfo") Long id) throws Exception {
		return usuarioService.listaMatriculaPorEstudianteInfo(id);
	}

	@GetMapping("/inscripcion/estudianteInfo/{idEstudianteInfo}")
	public List<InscripcionDto> buscarInscripcionPorEstudianteInfo(@PathVariable(value="idEstudianteInfo") Long id) throws Exception {
		return usuarioService.listaInscripcionPorEstudianteInfo(id);
	}
}
