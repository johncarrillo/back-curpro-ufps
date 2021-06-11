package com.co.ufps.curpro.back.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CierreCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.SustentacionDto;
import com.co.ufps.curpro.back.app.dto.SustentacionSaveDto;
import com.co.ufps.curpro.back.app.dto.SustentacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadUpdateDto;
import com.co.ufps.curpro.back.app.models.service.ICierreCursoService;

@RestController
@RequestMapping("/cierre-curso")
@CrossOrigin
public class CierreCursoController {

	@Autowired
	private ICierreCursoService cierreCurso;

	@GetMapping("/sustentacion/{id}")
	public SustentacionDto buscarSustentacion(@PathVariable(value="id") Long id) throws Exception {
		return cierreCurso.findByIdSustentacion(id);
	}

	@GetMapping("/{id}")
	public CierreCursoDto buscarCierreCurso(@PathVariable(value="id") Long id) throws Exception {
		return cierreCurso.findByIdCierreCurso(id);
	}

	@GetMapping
	public List<CierreCursoDto> listarCierreCurso(@RequestParam(name = "idCurso", required = false) Long idCurso) throws Exception {
		return cierreCurso.listCierreCursoByIdCurso(idCurso);
	}

	@GetMapping("/sustentacion")
	public List<SustentacionDto> listarSustentacion(@RequestParam(name = "idCurso", required = false) Long idCurso) throws Exception {
		return cierreCurso.listSustentacionByIdCurso(idCurso);
	}

	@PostMapping("/sustentacion")
	public void guardarSustentacion(@RequestBody SustentacionSaveDto sustentacionSaveDto) throws Exception {
		cierreCurso.saveSustentacion(sustentacionSaveDto);
	}

	@PostMapping
	public void guardarCierreCurso(@RequestBody CierreCursoSaveDto cierreCursoSaveDto) throws Exception {
		cierreCurso.saveCierreCurso(cierreCursoSaveDto);
	}

	@PutMapping("/sustentacion")
	public void modificarSustentacion(@RequestBody SustentacionUpdateDto sustentacionUpdateDto) throws Exception {
		cierreCurso.updateSustentacion(sustentacionUpdateDto);
	}

	@PutMapping
	public void modificarCierreCurso(@RequestBody CierreCursoUpdateDto cierreCursoUpdateDto) throws Exception {
		cierreCurso.updateCierreCurso(cierreCursoUpdateDto);
	}

	@DeleteMapping("/{idCierreCurso}")
	public void eliminarCierreCurso(@PathVariable(value="idCierreCurso") Long idCierreCurso) throws Exception {
		cierreCurso.findByIdCierreCurso(idCierreCurso);
	}

	@DeleteMapping("/sustentacion/{idSustentacion}")
	public void eliminarSustentacion(@PathVariable(value="idSustentacion") Long idSustentacion) throws Exception {
		cierreCurso.findByIdSustentacion(idSustentacion);
	}
}
