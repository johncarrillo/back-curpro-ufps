package com.co.ufps.curpro.back.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.ufps.curpro.back.app.models.service.IActividadService;
import com.co.ufps.curpro.back.app.models.service.IMatriculaService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/file")
public class FilePdfController {

	@Autowired
	private IMatriculaService matriculaService;

	@Autowired
	private IActividadService actividadService;

	@GetMapping("/matricula/{idCurso}")
	public String verMatricula(@PathVariable(value = "idCurso") Long idCurso, Model model) {
		model.addAttribute("matriculas", matriculaService.listMatriculaByIdCursoFromPdf(idCurso));
		return "matricula/pdf";
	}

	@GetMapping("/inscripcion/{idCurso}")
	public String verInscripcion(@PathVariable(value = "idCurso") Long idCurso, Model model) {
		model.addAttribute("inscripcion", matriculaService.listInscripcionByIdCursoFromPdf(idCurso));
		return "inscripcion/pdf";
	}

	@GetMapping("/modulo/calificaciones/{idModuloCurso}")
	public String verCalificacionesPorModulo(@PathVariable(value = "idModuloCurso") Long idModuloCurso, Model model) throws Exception {
		model.addAttribute("calificaciones", actividadService.listarNotasByModulo(idModuloCurso));
		return "modulo/calificacion/pdf";
	}
}
