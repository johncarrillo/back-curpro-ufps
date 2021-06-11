package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.CursoAndPresupuestoDto;
import com.co.ufps.curpro.back.app.dto.CursoDto;
import com.co.ufps.curpro.back.app.dto.CursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.DependenciaDto;
import com.co.ufps.curpro.back.app.dto.DependenciaSaveDto;
import com.co.ufps.curpro.back.app.dto.DependenciaUpdateDto;

public interface IDependenciaService {

	public List<DependenciaDto> findAll();

	public DependenciaDto findOne(Long id) throws Exception ;

	public void save(DependenciaSaveDto dependenciaSaveDto);

	public void update(DependenciaUpdateDto dependenciaUpdateDto) throws Exception;

	public void delete(Long id);
}
