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

import com.co.ufps.curpro.back.app.dto.DependenciaDto;
import com.co.ufps.curpro.back.app.dto.DependenciaSaveDto;
import com.co.ufps.curpro.back.app.dto.DependenciaUpdateDto;
import com.co.ufps.curpro.back.app.models.service.IDependenciaService;

@RestController
@RequestMapping("/dependencia")
@CrossOrigin
public class DependenciaController {

	@Autowired
	private IDependenciaService dependenciaService;

	@GetMapping
	public List<DependenciaDto> lista() {
		return dependenciaService.findAll();
	}

	@GetMapping("/{id}")
	public DependenciaDto buscar(@PathVariable(value="id") Long id) throws Exception {
		return dependenciaService.findOne(id);
	}

	@PostMapping
	public void guardar(@RequestBody DependenciaSaveDto dependenciaSaveDto) throws Exception {
		dependenciaService.save(dependenciaSaveDto);
	}

	@PutMapping
	public void modificar(@RequestBody DependenciaUpdateDto dependenciaUpdateDto) throws Exception {
		dependenciaService.update(dependenciaUpdateDto);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable(value="id") Long idDependencia) {
		dependenciaService.delete(idDependencia);
	}
}
