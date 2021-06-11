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
import org.springframework.web.bind.annotation.RestController;

import com.co.ufps.curpro.back.app.dto.AsignarDocenteAModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.CursoAndPresupuestoDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ModuloEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.dto.TipoCursoDto;
import com.co.ufps.curpro.back.app.models.service.IModuloCursoService;

@RestController
@RequestMapping("/modulo_curso")
@CrossOrigin
public class ModuloCursoController {

	@Autowired
	private IModuloCursoService moduloCursoService; 

	@GetMapping("/{idModuloCurso}")
	public ModuloCursoDto findModuloCurso(@PathVariable(value="idModuloCurso") Long idModuloCurso) throws Exception {
		return moduloCursoService.findOne(idModuloCurso);
	}

	@GetMapping("/curso/{idCurso}")
	public List<ModuloCursoDto> listar(@PathVariable(value="idCurso") Long idCurso) {
		return moduloCursoService.findAll(idCurso);
	}

	@PostMapping
	public void guardar(@RequestBody ModuloCursoSaveDto moduloCursoSaveDto) throws Exception {
		moduloCursoService.saveModuloCurso(moduloCursoSaveDto);
	}

	@PutMapping
	public void modificar(@RequestBody ModuloCursoUpdateDto moduloCursoUpdate) throws Exception {
		moduloCursoService.updateModuloCurso(moduloCursoUpdate);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable(value="id") Long idModulo) {
		moduloCursoService.deleteById(idModulo);
	}

	@PostMapping("/asignarDocente")
	public void asignarDocente(@RequestBody AsignarDocenteAModuloCursoDto asignarDocenteAModuloCursoDto) throws Exception {
		moduloCursoService.asignarDocenteAModuloCurso(asignarDocenteAModuloCursoDto);
	}

	@GetMapping("/moduloEstudiante/{idModulo}")
	public List<ModuloEstudianteDto> listarModuloEstudiante(@PathVariable(value="idModulo") Long idModulo) throws Exception {
		return moduloCursoService.listarModuloEstudiante(idModulo);
	}

	@PostMapping("/moduloEstudiante")
	public void guardarModuloEstudiante(@RequestBody ModuloEstudianteSaveDto moduloEstudianteSaveDto) throws Exception {
		moduloCursoService.saveModuloEstudiante(moduloEstudianteSaveDto);
	}

	@PutMapping("/moduloEstudiante")
	public void actualizarModuloEstudiante(@RequestBody ModuloEstudianteUpdateDto moduloEstudianteUpdateDto) throws Exception {
		moduloCursoService.updateModuloEstudiante(moduloEstudianteUpdateDto);
	}
}
