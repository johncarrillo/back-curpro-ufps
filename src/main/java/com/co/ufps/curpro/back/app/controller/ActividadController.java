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

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadUpdateDto;
import com.co.ufps.curpro.back.app.dto.CursoAndPresupuestoDto;
import com.co.ufps.curpro.back.app.dto.CursoDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.ListActividadEstudianteCalificacionSaveDto;
import com.co.ufps.curpro.back.app.dto.ListActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.NotasActividadesPorModuloDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadUpdateDto;
import com.co.ufps.curpro.back.app.models.service.IActividadService;

@RestController
@RequestMapping("/actividad")
@CrossOrigin
public class ActividadController {

	@Autowired
	private IActividadService actividadService;


	@GetMapping("/{id}")
	public ActividadDto buscar(@PathVariable(value="id") Long id) throws Exception {
		return actividadService.findActividadById(id);
	}

	@GetMapping("/moduloCurso/{id}")
	public List<ActividadDto> listarActividadesByModuloCurso(@PathVariable(value="id") Long id) throws Exception {
		return actividadService.listActividadByIdModuloCurso(id);
	}

	@GetMapping("/tipoActividad")
	public List<TipoActividadDto> listarTipoCurso() throws Exception {
		return actividadService.listTipoActividad();
	}

	@PostMapping
	public void guardarActividad(@RequestBody ActividadSaveDto actividadSaveDto) throws Exception {
		actividadService.saveActividad(actividadSaveDto);
	}

	@PostMapping("/tipoActividad")
	public void guardarTipoActividad(@RequestBody TipoActividadSaveDto tipoActividadSaveDto) throws Exception {
		actividadService.saveTipoActividad(tipoActividadSaveDto);
	}

	@PutMapping
	public void modificarActividad(@RequestBody ActividadUpdateDto actividadUpdateDto) throws Exception {
		actividadService.updateActividad(actividadUpdateDto);
	}

	@PutMapping("/tipoActividad")
	public void modificarTipoActividad(@RequestBody TipoActividadUpdateDto tipoActividadUpdateDto) throws Exception {
		actividadService.updateTipoActividad(tipoActividadUpdateDto);
	}

	@DeleteMapping("/{id}")
	public void eliminarActividad(@PathVariable(value="id") Long idActividad) {
		actividadService.deleteActividad(idActividad);
	}

	@DeleteMapping("/tipoActividad/{id}")
	public void eliminarTipoActividad(@PathVariable(value="id") Long idTipoActividad) {
		actividadService.deleteTipoActividad(idTipoActividad);
	}

	@GetMapping("/notas/actividad/{id}")
	public List<ActividadEstudianteDto> listarNotasEstudiantesByActividad(@PathVariable(value="id") Long idActividad) throws Exception {
		return actividadService.listarNotasEstudiantesByActividad(idActividad);
	}

	@GetMapping("/notas/moduloCurso/{id}")
	public NotasActividadesPorModuloDto listarNotasByModuloCurso(@PathVariable(value="id") Long id) throws Exception {
		return actividadService.listarNotasByModulo(id);
	}

	@PostMapping("/notas/actividad")
	public void guardarActividadEstudiante(@RequestBody ActividadEstudianteSaveDto actividadEstudianteSaveDto) throws Exception {
		actividadService.saveActividadEstudiante(actividadEstudianteSaveDto);
	}

	@PutMapping("/notas/actividad")
	public void actualizarActividadEstudiante(@RequestBody ActividadEstudianteUpdateDto actividadEstudianteUpdateDto) throws Exception {
		actividadService.updateActividadEstudiante(actividadEstudianteUpdateDto);
	}

	@PostMapping("/notas/actividad/masivo")
	public void guardarActividadEstudianteMasivo(@RequestBody ListActividadEstudianteCalificacionSaveDto listActividadEstudianteCalificacionSaveDto) throws Exception {
		actividadService.saveActividadEstudianteMasivo(listActividadEstudianteCalificacionSaveDto);
	}

	@PutMapping("/notas/actividad/masivo")
	public void actualizarActividadEstudianteMasivo(@RequestBody List<ActividadEstudianteCalificacionUpdateDto> listaActividadEstudianteCalificacionUpdateDto) throws Exception {
		actividadService.updateActividadEstudianteMasivo(listaActividadEstudianteCalificacionUpdateDto);
	}
}
