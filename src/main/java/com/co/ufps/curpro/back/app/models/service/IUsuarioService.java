package com.co.ufps.curpro.back.app.models.service;

import java.util.List;
import java.util.Optional;

import com.co.ufps.curpro.back.app.dto.InscripcionDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.RolAsignarUsuarioDto;
import com.co.ufps.curpro.back.app.dto.RolDto;
import com.co.ufps.curpro.back.app.dto.RolUsuarioDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<UsuarioDto> findAll();

	public void save(UsuarioDto usuario);

	public UsuarioDto findOne(Long id);

	public void delete(Long id);

	public UsuarioDto findByCorreo(String correo);

	public void addRolToUsuario(RolAsignarUsuarioDto rolAsignarUsuarioDto) throws Exception;

	public List<RolUsuarioDto> listRol(String correo);

	public RolUsuario findRolUsuario (Long id);

	public List<UsuarioDto> findDirectoresDisponibles();

	public List<UsuarioDto> findByRol(Rol rol);

	public Rol findRolByName (String nombreRol);

	public Dependencia findDependenciaByDirector(Long idRolUsuario) throws Exception;

	public List<RolDto> listRol();

	public List<UsuarioDto> listUsuarioByRol(Long id) throws Exception;

	public RolUsuario findRolUsuarioDocente(Long idUsuario) throws Exception;

	public RolUsuario findRolUsuarioEstudiante(Long idUsuario) throws Exception;

	public List<MatriculaDto> listaMatriculaPorEstudianteInfo (Long idEstudianteInfo) throws Exception;

	public List<InscripcionDto> listaInscripcionPorEstudianteInfo (Long idEstudianteInfo) throws Exception;
}
