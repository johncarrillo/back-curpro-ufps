package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.dto.EstudianteInfoDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoSaveDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoUpdateDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.dao.IEstudianteInfoDao;
import com.co.ufps.curpro.back.app.models.dao.IRolUsuarioDao;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.parser.UsuarioConverter;

@Service
public class EstudianteInfoServiceImpl implements IEstudianteInfoService{

	@Autowired
	private IEstudianteInfoDao estudianteInfoDao;

	@Autowired
	private IRolUsuarioDao rolUsuarioDao;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	public List<EstudianteInfoDto> findAll() {
		List<EstudianteInfoDto> estudiantesInfo = new ArrayList<EstudianteInfoDto>();
		estudianteInfoDao.findAll().forEach(estudiante -> {
			estudiantesInfo.add(UsuarioConverter.entityToDto(estudiante, estudiante.getRolUsuario().getUsuario()));
		});
		return estudiantesInfo;
	}

	public UsuarioDto find() throws Exception {
		String correo = SecurityContextHolder.getContext().getAuthentication().getName();
		return usuarioService.findByCorreo(correo);
	}

	public void save(EstudianteInfoSaveDto estudianteInfoSave) {
		RolUsuario rolUsuario = null;
		if (estudianteInfoSave.getIdRolUsuario() != null) {
			rolUsuario = rolUsuarioDao.findById(estudianteInfoSave.getIdRolUsuario()).orElse(null);
		}
		EstudianteInfo estudianteInfo = UsuarioConverter.dtoToEntity(estudianteInfoSave, rolUsuario);
		estudianteInfoDao.save(estudianteInfo);
	}

	public void update (EstudianteInfoUpdateDto estudianteInfoUpdateDto) throws Exception {
		EstudianteInfo estudianteInfo = estudianteInfoDao.findById(estudianteInfoUpdateDto.getId())
				.orElseThrow(() -> new Exception("La informacion del estudiante no existe"));
		estudianteInfo = UsuarioConverter.dtoToEntity(estudianteInfo, estudianteInfoUpdateDto);
		estudianteInfoDao.save(estudianteInfo);
	}

	public void deleteById(Long id) throws Exception {
		estudianteInfoDao.deleteById(id);
	}

	@Override
	public EstudianteInfo findById(Long id) throws Exception {
		return estudianteInfoDao.findById(id).orElseThrow(()->new Exception("La informacion de ete usuario no esta disponible"));
	}
}
