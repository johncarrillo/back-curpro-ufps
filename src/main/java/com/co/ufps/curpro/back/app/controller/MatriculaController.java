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

import com.co.ufps.curpro.back.app.dto.BancoDto;
import com.co.ufps.curpro.back.app.dto.EstadoMatriculaDto;
import com.co.ufps.curpro.back.app.dto.InscripcionDto;
import com.co.ufps.curpro.back.app.dto.InscripcionSaveDto;
import com.co.ufps.curpro.back.app.dto.InscripcionUpdateDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.models.service.IMatriculaService;
import com.co.ufps.curpro.back.app.dto.MatriculaSaveDto;
import com.co.ufps.curpro.back.app.dto.MatriculaUpdateDto;

@RestController
@RequestMapping("/matricula")
@CrossOrigin
public class MatriculaController {

	@Autowired
	private IMatriculaService matriculaSevice;

	@GetMapping
	public MatriculaDto buscarMatricula(@RequestParam(name = "idEstudiante", required = false) Long idEstudiante,
			@RequestParam(name = "idCurso",  required = false) Long idCurso) throws Exception {
		return matriculaSevice.findMatriculaById(idEstudiante, idCurso);
	}

	@GetMapping("/inscripcion")
	public InscripcionDto buscarInscripcion(@RequestParam(name = "idEstudiante", required = false) Long idEstudiante,
			@RequestParam(name = "idCurso",  required = false) Long idCurso) throws Exception {
		return matriculaSevice.findInscripcionById(idEstudiante, idCurso);
	}

	@PostMapping
	public void guardarMatricula(@RequestBody MatriculaSaveDto matriculaSaveDto) throws Exception {
		matriculaSevice.saveMatricula(matriculaSaveDto);
	}

	@PostMapping("/inscripcion")
	public void guardarMatricula(@RequestBody InscripcionSaveDto inscripcionSaveDto) throws Exception {
		matriculaSevice.saveInscripcion(inscripcionSaveDto);
	}

	@PutMapping
	public void modificarMatricula(@RequestBody MatriculaUpdateDto matriculaUpdateDto) throws Exception {
		matriculaSevice.updateMatricula(matriculaUpdateDto);
	}

	@PutMapping("/inscripcion")
	public void modificarInscripcion(@RequestBody InscripcionUpdateDto inscripcionUpdateDto) throws Exception {
		matriculaSevice.updateInscripcion(inscripcionUpdateDto);
	}

	@DeleteMapping("/{id}")
	public void eliminarMatricula(@PathVariable(value="id") Long idMatricula) {
		matriculaSevice.deleteMatriculaById(idMatricula);
	}

	@DeleteMapping("/inscripcion/{id}")
	public void eliminarInscripcion(@PathVariable(value="id") Long idInscripcion) {
		matriculaSevice.deleteInscripcionById(idInscripcion);
	}

	@GetMapping("/{id}")
	public List<MatriculaDto> buscarMatriculaByIdCurso(@PathVariable(value="id") Long idCurso) throws Exception {
		return matriculaSevice.listMatriculaByIdCurso(idCurso);
	}

	@GetMapping("/inscripcion/{id}")
	public List<InscripcionDto> buscarInscripcionByIdCurso(@PathVariable(value="id") Long idCurso) throws Exception {
		return matriculaSevice.listInscripcionByIdCurso(idCurso);
	}

	@GetMapping("/estados")
	public List<EstadoMatriculaDto> buscarEstadosMatricula(){
		return matriculaSevice.listEstadoMatricula();
	}

	@GetMapping("/bancos")
	public List<BancoDto> buscarBancos(){
		return matriculaSevice.listBanco();
	}
}
