package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadUpdateDto;
import com.co.ufps.curpro.back.app.dto.ListActividadEstudianteCalificacionSaveDto;
import com.co.ufps.curpro.back.app.dto.ListActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.NotasActividadesPorModuloDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadUpdateDto;

public interface IActividadService {

	public ActividadDto findActividadById (Long id) throws Exception;

	public TipoActividadDto findTipoActividadById (Long id) throws Exception;

	public List<ActividadDto> listActividadByIdModuloCurso (Long idModuloCurso) throws Exception;

	public List<TipoActividadDto> listTipoActividad();

	public void saveActividad (ActividadSaveDto actividadSave)  throws Exception;

	public void saveTipoActividad (TipoActividadSaveDto tipoActividadSave)  throws Exception;

	public void updateActividad (ActividadUpdateDto actividadUpdate) throws Exception;

	public void updateTipoActividad (TipoActividadUpdateDto tipoActividadUpdate) throws Exception;

	public void deleteTipoActividad(Long id);

	public void deleteActividad(Long id);

	public List<ActividadEstudianteDto> listarNotasEstudiantesByActividad(Long id) throws Exception;

	public NotasActividadesPorModuloDto listarNotasByModulo(Long idModuloCurso) throws Exception;

	public void saveActividadEstudiante (ActividadEstudianteSaveDto actividadEstudiante) throws Exception;

	public void updateActividadEstudiante (ActividadEstudianteUpdateDto actividadEstudianteUpdate) throws Exception;

	public void saveActividadEstudianteMasivo (ListActividadEstudianteCalificacionSaveDto listaActividadEstudianteCalificacionSaveDto) throws Exception;

	public void updateActividadEstudianteMasivo (List<ActividadEstudianteCalificacionUpdateDto> listaActividadEstudianteCalificacionUpdateDto) throws Exception;
}
