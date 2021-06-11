package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.CierreCursoDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.SustentacionDto;
import com.co.ufps.curpro.back.app.dto.SustentacionSaveDto;
import com.co.ufps.curpro.back.app.dto.SustentacionUpdateDto;

public interface ICierreCursoService {

	public List<SustentacionDto> listSustentacionByIdCurso (Long idCurso) throws Exception;

	public List<CierreCursoDto> listCierreCursoByIdCurso (Long idCurso) throws Exception;

	public CierreCursoDto findByIdCierreCurso (Long id) throws Exception;

	public SustentacionDto findByIdSustentacion (Long id) throws Exception;

	public void saveSustentacion (SustentacionSaveDto sustentacionSave) throws Exception;

	public void saveCierreCurso (CierreCursoSaveDto cierreCursoSave) throws Exception;

	public void updateSustentacion (SustentacionUpdateDto sustentacionUpdate) throws Exception;

	public void updateCierreCurso (CierreCursoUpdateDto cierreCursoUpdate) throws Exception;

	public void deleteCierreCurso (Long id);

	public void deleteSustentacion (Long id);
}
