package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.constante.ConstantesEntity;
import com.co.ufps.curpro.back.app.dto.InscripcionDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.RolAsignarUsuarioDto;
import com.co.ufps.curpro.back.app.dto.RolDto;
import com.co.ufps.curpro.back.app.dto.RolUsuarioDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.dao.IDependenciaDao;
import com.co.ufps.curpro.back.app.models.dao.IRolDao;
import com.co.ufps.curpro.back.app.models.dao.IRolUsuarioDao;
import com.co.ufps.curpro.back.app.models.dao.IUsuarioDao;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.parser.UsuarioConverter;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IDependenciaDao dependenciaDao;

	@Autowired
	private IRolUsuarioDao rolUsuarioDao;

	@Autowired
	private IRolDao rolDao;

	@Autowired
	private IModuloCursoService moduloCursoService; 

	@Autowired
	private IMatriculaService matriculaService;

	@Autowired
	private IEstudianteInfoService estudianteInfoService;


	@Autowired
	PasswordEncoder passwordEncode;

	@Value("${secretPsw}")
	String secretPsw;

	@Override
	public List<UsuarioDto> findAll() {
		List listUsuarios = new ArrayList<Usuario>();
		usuarioDao.findAll().forEach(listUsuarios::add);
		return UsuarioConverter.listUsuarioToDto(listUsuarios);
	}

	@Override
	public void save(UsuarioDto usuarioDto) {
		Usuario usuario = null;
		if (usuarioDto.getId() != null) {
			// Actualiza el usuario
			usuario = usuarioDao.findById(usuarioDto.getId()).orElse(null);
			usuario = UsuarioConverter.usuarioDtoToEntity(usuarioDto, usuario);
			usuarioDao.save(usuario);
		} else {
			// Crea usuario si no existe
			usuario = new Usuario(usuarioDto.getCorreo(), passwordEncode.encode(secretPsw));
			RolUsuario rolUsuario = new RolUsuario();
			rolUsuario.setRol(rolDao.findByNombre("Estudiante"));
			usuario.addRolUsuarios(rolUsuario);

			usuario.setNombres(usuarioDto.getNombre());
			usuario.setApellidos(usuarioDto.getApellido());
			usuarioDao.save(usuario);
		}
	}

	
	@Override
	public UsuarioDto findOne(Long id) {
		Usuario usuario = usuarioDao.findById(id).orElse(null);
		EstudianteInfo estudianteInfo = null;
		for (RolUsuario rolUsuario : usuario.getRolUsuarios()) {
			if (ConstantesEntity.ROL_ESTUDIANTE.equalsIgnoreCase(rolUsuario.getRol().getNombre())) {
				estudianteInfo = rolUsuario.getEstudianteInfo();
			}
		}
		return UsuarioConverter.usuarioToDto(usuario, estudianteInfo);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	
	@Override
	public UsuarioDto findByCorreo(String correo) {
		Usuario usuario = usuarioDao.findByCorreo(correo);
		EstudianteInfo estudianteInfo = null;
		for (RolUsuario rolUsuario : usuario.getRolUsuarios()) {
			if (ConstantesEntity.ROL_ESTUDIANTE.equalsIgnoreCase(rolUsuario.getRol().getNombre())) {
				estudianteInfo = rolUsuario.getEstudianteInfo();
			}
		}
		return UsuarioConverter.usuarioToDto(usuario, estudianteInfo);
	}

	@Override
	public void addRolToUsuario(RolAsignarUsuarioDto rolAsignarUsuarioDto) throws Exception {
		if (rolAsignarUsuarioDto == null || rolAsignarUsuarioDto.getIdUsuario() == null
				|| rolAsignarUsuarioDto.getIdRol() == null) {
			return;
		}
		Usuario usuario = usuarioDao.findById(rolAsignarUsuarioDto.getIdUsuario()).orElseThrow(()->new Exception("No existe este usuario"));
		RolUsuario rolUsuario = usuario.getRolUsuarios()
				.stream()
				.filter(rolUsu -> rolUsu.getRol().getId() == rolAsignarUsuarioDto.getIdRol())
				.findFirst()
				.orElse(null);
		if (rolUsuario == null && rolAsignarUsuarioDto.getAsignar()) {
			Rol rol = rolDao.findById(rolAsignarUsuarioDto.getIdRol())
					.orElseThrow(() -> new Exception("Este Rol no existe"));
			rolUsuario = new RolUsuario();
			rolUsuario.setRol(rol);
			rolUsuario.setUsuario(usuario);
			rolUsuarioDao.save(rolUsuario);
		} else if (rolUsuario != null && !rolAsignarUsuarioDto.getAsignar()) {
			Dependencia dependencia = rolUsuario.getDependencia();
			if (dependencia != null) {
				dependencia.setRolUsuario(null);
				dependenciaDao.save(dependencia);
			}
			List<ModuloCurso> listaModuloCurso = rolUsuario.getModuloCurso();
			if (listaModuloCurso != null) {
				listaModuloCurso.forEach(moduloCurso -> {
					this.moduloCursoService.deleteDocente(moduloCurso);
				});
			}
			rolUsuario.setDependencia(null);
			rolUsuario.setRol(null);
			rolUsuario.setUsuario(null);
			rolUsuarioDao.deleteById(rolUsuario.getId());
		}
	}

	@Override
	public List<RolUsuarioDto> listRol(String correo) {
		return UsuarioConverter.usuarioRolToDto(usuarioDao.findByCorreo(correo).getRolUsuarios());
	}

	@Override
	public RolUsuario findRolUsuario (Long id) {
		return rolUsuarioDao.findById(id).orElseThrow();
	}

	@Override
	public List<UsuarioDto> findDirectoresDisponibles() {
		Rol rol = rolDao.findByNombre(ConstantesEntity.ROL_DIRECTOR_PROGRAMA);
		List<RolUsuario> rolesUsuarios = rol.getRolUsuarios().stream().filter(rolUsuario -> {
			return rolUsuario.getDependencia() == null;
		}).collect(Collectors.toList());
		return rolesUsuarios
				.stream()
				.map(rolesUsuario -> {
					return UsuarioConverter.entityToDto(rolesUsuario.getUsuario());
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<UsuarioDto> findByRol(Rol rol) {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		this.rolUsuarioDao.findAllByRol(rol).forEach(rolUsuario -> {
			usuariosDto.add(UsuarioConverter.entityToDto(rolUsuario.getUsuario()));
		});
		return usuariosDto;
	}

	@Override
	public Rol findRolByName (String nombreRol) {
		Rol rol = rolDao.findByNombre(nombreRol);
		return rol;
	}

	@Override
	public Dependencia findDependenciaByDirector(Long idRolUsuario) throws Exception {
		RolUsuario rolUsuario = rolUsuarioDao.findById(idRolUsuario).orElseThrow(() -> new Exception("El id del RolUsuario no existe"));
		return rolUsuario.getDependencia();
	}

	@Override
	public List<RolDto> listRol() {
		List<RolDto> listaRol = new ArrayList<RolDto>();
		this.rolDao.findAll().forEach(rol -> {
			listaRol.add(UsuarioConverter.entityToDto(rol));
		});
		return listaRol;
	}

	@Override
	public List<UsuarioDto> listUsuarioByRol(Long id) throws Exception {
		Rol rol = this.rolDao.findById(id).orElseThrow(() -> new Exception("Este rol no Existe"));
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		rol.getRolUsuarios().forEach(rolUsuario -> {
			usuariosDto.add(UsuarioConverter.entityToDto(rolUsuario.getUsuario()));
		});
		return usuariosDto;
	}


	@Override
	public RolUsuario findRolUsuarioDocente(Long idUsuario) throws Exception {
		if (idUsuario == null) {
			new Exception("Este usuario no tiene rol de docente");
		}
		Usuario usuario = this.usuarioDao.findById(idUsuario).orElseThrow(()->new Exception ("Este Usuario no existe"));
		RolUsuario rolUsuario = usuario.getRolUsuarios().stream().filter(rolUsu -> (rolUsu.getRol() != null && rolUsu.getRol().getNombre().equalsIgnoreCase(ConstantesEntity.ROL_DOCENTE)))
				.findFirst()
				.orElseThrow(() -> new Exception("Este usuario no tiene rol de docente"));
		return rolUsuario;
	}

	@Override
	public RolUsuario findRolUsuarioEstudiante(Long idUsuario) throws Exception {
		if (idUsuario == null) {
			new Exception("Este usuario no tiene rol de estudiante");
		}
		Usuario usuario = this.usuarioDao.findById(idUsuario).orElseThrow(()->new Exception ("Este Usuario no existe"));
		RolUsuario rolUsuario = usuario.getRolUsuarios().stream().filter(rolUsu -> (rolUsu.getRol() != null && rolUsu.getRol().getNombre().equalsIgnoreCase(ConstantesEntity.ROL_ESTUDIANTE)))
				.findFirst()
				.orElseThrow(() -> new Exception("Este usuario no tiene rol de estudiante"));
		return rolUsuario;
	}

	public List<MatriculaDto> listaMatriculaPorEstudianteInfo (Long idEstudianteInfo) throws Exception {
		EstudianteInfo estudianteInfo = estudianteInfoService.findById(idEstudianteInfo);
		return matriculaService.findByMatriculaInfo(estudianteInfo);
	}

	public List<InscripcionDto> listaInscripcionPorEstudianteInfo (Long idEstudianteInfo) throws Exception {
		EstudianteInfo estudianteInfo = estudianteInfoService.findById(idEstudianteInfo);
		return matriculaService.findByEstudianteInfo(estudianteInfo);
	}
}
