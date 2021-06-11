package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.constante.ConstantesEntity;
import com.co.ufps.curpro.back.app.dto.DependenciaDto;
import com.co.ufps.curpro.back.app.dto.DependenciaSaveDto;
import com.co.ufps.curpro.back.app.dto.DependenciaUpdateDto;
import com.co.ufps.curpro.back.app.models.dao.IDependenciaDao;
import com.co.ufps.curpro.back.app.models.dao.IUsuarioDao;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.parser.DependenciaConverter;
import com.co.ufps.curpro.back.app.models.parser.UsuarioConverter;

@Service
public class DependenciaServiceImpl implements IDependenciaService{

	@Autowired
	private IDependenciaDao dependeciaDao; 

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public List<DependenciaDto> findAll() {
		List<DependenciaDto> listDependenciaDto = new ArrayList<DependenciaDto>();
		dependeciaDao.findAll().forEach(dependencia->{
			listDependenciaDto.add(DependenciaConverter.entityToDto(dependencia));
		});
		return listDependenciaDto;
	}

	@Override
	public DependenciaDto findOne(Long id) throws Exception {
		Dependencia dependencia = dependeciaDao.findById(id).orElseThrow(()->new Exception("La dependencia no existe"));
		return DependenciaConverter.entityToDto(dependencia);
	}

	@Override
	public void save(DependenciaSaveDto dependenciaSaveDto) {
		dependeciaDao.save(DependenciaConverter.dtoToEntity(dependenciaSaveDto));
	}

	@Override
	public void update(DependenciaUpdateDto dependenciaUpdateDto) throws Exception {
		Dependencia dependencia = dependeciaDao.findById(dependenciaUpdateDto.getId()).orElse(null);
		if (dependencia == null) {
			throw new Exception("La dependencia no existe");
		}
		RolUsuario rolUsuario = null;
		if (dependenciaUpdateDto.getIdUsuario() != null) {
			Usuario usuario = usuarioDao.findById(dependenciaUpdateDto.getIdUsuario()).orElseThrow(()->new Exception("El usuario no existe"));
			List<RolUsuario> rolesUsuario = usuario.getRolUsuarios();
			rolUsuario = rolesUsuario.stream()
					.filter(rolesUsu -> ConstantesEntity.ROL_DIRECTOR_PROGRAMA.equalsIgnoreCase(rolesUsu.getRol().getNombre()))
					.findFirst()
					.orElseThrow(()->new Exception("El usuario no tiene rol de " + ConstantesEntity.ROL_DIRECTOR_PROGRAMA));
		}
		
		
		dependencia = DependenciaConverter.dtoToEntity(dependencia, dependenciaUpdateDto, rolUsuario);
		dependeciaDao.save(dependencia);
	}

	@Override
	public void delete(Long id) {
		dependeciaDao.deleteById(id);
	}

}
