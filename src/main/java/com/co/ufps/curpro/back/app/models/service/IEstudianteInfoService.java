package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.EstudianteInfoDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoSaveDto;
import com.co.ufps.curpro.back.app.dto.EstudianteInfoUpdateDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;

public interface IEstudianteInfoService {

	public List<EstudianteInfoDto> findAll();

	public UsuarioDto find() throws Exception;

	public void save(EstudianteInfoSaveDto estudianteInfoSave);

	public void update (EstudianteInfoUpdateDto estudianteInfoUpdateDto) throws Exception;

	public void deleteById(Long id) throws Exception;

	public EstudianteInfo findById(Long id) throws Exception;
}
