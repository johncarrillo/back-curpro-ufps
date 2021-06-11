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

import com.co.ufps.curpro.back.app.dto.CursoAndPresupuestoDto;
import com.co.ufps.curpro.back.app.dto.CursoDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.CursoWithModulosDto;
import com.co.ufps.curpro.back.app.dto.EstadoCursoDto;
import com.co.ufps.curpro.back.app.dto.EvaluacionModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.PresupuestoDto;
import com.co.ufps.curpro.back.app.dto.TipoCursoDto;
import com.co.ufps.curpro.back.app.models.service.ICursoService;
import com.co.ufps.curpro.back.app.models.service.IModuloCursoService;
import com.co.ufps.curpro.back.app.models.service.IPresupuestoService;



@RestController
@RequestMapping("/curso")
@CrossOrigin
public class CursoController {

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IPresupuestoService presupuestoService;

	@Autowired
	private IModuloCursoService moduloCursoService;

	@GetMapping
	public List<CursoDto> lista(@RequestParam(name = "idEstadoCurso", required = false) Long idEstadoCurso,
			@RequestParam(name = "idTipoCurso",  required = false) Long idTipoCurso, @RequestParam(name = "idDependencia", required = false) Long idDependencia) {
		return cursoService.findAll(idEstadoCurso, idTipoCurso, idDependencia);
	}

	@GetMapping("/tipos")
	public List<TipoCursoDto> listaTipoCuro() {
		return cursoService.listTipoCurso();
	}

	@GetMapping("/estados")
	public List<EstadoCursoDto> listaEstadoCurso() {
		return cursoService.listEstadoCurso();
	}

	@GetMapping("/{id}")
	public CursoDto buscar(@PathVariable(value="id") Long id) {
		return cursoService.findOne(id);
	}

	@PostMapping
	public void guardar(@RequestBody CursoAndPresupuestoDto cursoAndPresupuestoDto) throws Exception {
		cursoService.save(cursoAndPresupuestoDto);
	}

	@PutMapping
	public void modificar(@RequestBody CursoUpdateDto cursoSaveDto) {
		cursoService.update(cursoSaveDto);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable(value="id") Long idCurso) {
		cursoService.delete(idCurso);
	}

	@GetMapping("/presupuesto")
	public List<PresupuestoDto> listarPresupuesto() {
		return presupuestoService.findAll();
	}

	@GetMapping("/presupuesto/{id}")
	public PresupuestoDto buscarPresupuesto(@PathVariable(value="id") Long id) {
		return presupuestoService.findOne(id);
	}

	@PutMapping("/presupuesto")
	public void actualizarPresupuesto(@RequestBody PresupuestoDto presupuestoDto) {
		presupuestoService.update(presupuestoDto);
	}

	@GetMapping("/listaModulo/{id}")
	public CursoWithModulosDto buscarCursoConModulos(@PathVariable(value="id") Long id) {
		return moduloCursoService.getCursoWithModulo(id);
	}

	@PostMapping("/evaluacion")
	public void guardarEvaluacion(@RequestBody EvaluacionModuloCursoDto evaluacionCurso) throws Exception {
		cursoService.publicarEvaluacionCurso(evaluacionCurso);
	}
}
