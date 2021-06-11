package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteCalificacionSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadUpdateDto;
import com.co.ufps.curpro.back.app.dto.ListActividadEstudianteCalificacionSaveDto;
import com.co.ufps.curpro.back.app.dto.ListActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.ModuloCursoCalificacionPdfDto;
import com.co.ufps.curpro.back.app.dto.NotasActividadDto;
import com.co.ufps.curpro.back.app.dto.NotasActividadesPorModuloDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadUpdateDto;
import com.co.ufps.curpro.back.app.models.dao.IActividadDao;
import com.co.ufps.curpro.back.app.models.dao.IActividadEstudianteDao;
import com.co.ufps.curpro.back.app.models.dao.IEstudianteInfoDao;
import com.co.ufps.curpro.back.app.models.dao.ITipoActividadDao;
import com.co.ufps.curpro.back.app.models.entity.Actividad;
import com.co.ufps.curpro.back.app.models.entity.ActividadEstudiante;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.TipoActividad;
import com.co.ufps.curpro.back.app.models.parser.ActividadConverter;
import com.co.ufps.curpro.back.app.models.parser.ModuloCursoConverter;
import com.co.ufps.curpro.back.app.models.parser.UsuarioConverter;

@Service
public class ActividadServiceImpl implements IActividadService{

	@Autowired
	private IActividadDao actividadDao;

	@Autowired
	private ITipoActividadDao tipoActividadDao;

	@Autowired
	private IModuloCursoService cursoModuloService;

	@Autowired
	private IActividadEstudianteDao actividadEstudianteDao; 

	@Autowired
	private IUsuarioService usuarioServiceImpl; 

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IMatriculaService matriculaServiceImpl;

	@Autowired
	private IEstudianteInfoService estudianteInfoServiceImpl;

	@Override
	public ActividadDto findActividadById (Long id) throws Exception {
		return ActividadConverter.entityToDto(actividadDao.findById(id).orElseThrow(()->new Exception("Esta actividad no existe")));
	}

	@Override
	public TipoActividadDto findTipoActividadById (Long id) throws Exception {
		return ActividadConverter.entityToDto(tipoActividadDao.findById(id).orElseThrow(()->new Exception("Este tipo de actividad no existe")));
	}

	@Override
	public List<ActividadDto> listActividadByIdModuloCurso (Long idModuloCurso) throws Exception {
		List<ActividadDto> actividades = new ArrayList<ActividadDto>();
		ModuloCurso moduloCurso = cursoModuloService.findOneEntity(idModuloCurso);
		actividadDao.findByModuloCursoOrderByIdAsc(moduloCurso).forEach(actividad -> {
			actividades.add(ActividadConverter.entityToDto(actividad));
		});
		return actividades;
	}

	@Override
	public List<TipoActividadDto> listTipoActividad() {
		List<TipoActividadDto> tiposActividad = new ArrayList<TipoActividadDto>();
		tipoActividadDao.findAll().forEach(tipo -> {
			tiposActividad.add(ActividadConverter.entityToDto(tipo));
		});
		return tiposActividad;
	}

	@Override
	public void saveActividad (ActividadSaveDto actividadSave)  throws Exception {
		TipoActividad tipo = tipoActividadDao.findById(actividadSave.getIdTipoActividad()).orElseThrow(() -> new Exception("Este tipo de actividad no existe"));
		ModuloCurso moduloCurso = cursoModuloService.findOneEntity(actividadSave.getIdModuloCurso());
		Actividad actividad = ActividadConverter.dtoToEntity(actividadSave, moduloCurso, tipo);
		actividadDao.save(actividad);
	}

	@Override
	public void saveTipoActividad (TipoActividadSaveDto tipoActividadSave)  throws Exception {
		tipoActividadDao.save(ActividadConverter.dtoToEntity(tipoActividadSave));
	}

	@Override
	public void updateActividad (ActividadUpdateDto actividadUpdate) throws Exception {
		if (actividadUpdate.getId() == null) {
			return;
		}
		TipoActividad tipo = tipoActividadDao.findById(actividadUpdate.getIdTipoActividad()).orElseThrow(() -> new Exception("Este tipo de actividad no existe"));
		Actividad actividad = actividadDao.findById(actividadUpdate.getId()).orElseThrow(()-> new Exception ("Esta actividad no existe"));
		actividad = ActividadConverter.dtoToEntity(actividad, actividadUpdate, tipo);
		actividadDao.save(actividad);
	}

	@Override
	public void updateTipoActividad (TipoActividadUpdateDto tipoActividadUpdate) throws Exception {
		if (tipoActividadUpdate.getId() == null) {
			return;
		}
		TipoActividad tipo = tipoActividadDao.findById(tipoActividadUpdate.getId()).orElseThrow(() -> new Exception("Este tipo de actividad no existe"));
		tipoActividadDao.save(ActividadConverter.dtoToEntity(tipo, tipoActividadUpdate));
	}

	@Override
	public void deleteTipoActividad(Long id) {
		tipoActividadDao.deleteById(id);
	}

	@Override
	public void deleteActividad(Long id) {
		actividadDao.deleteById(id);
	}

	@Override
	public List<ActividadEstudianteDto> listarNotasEstudiantesByActividad(Long id) throws Exception {
		List<ActividadEstudianteDto> actividadesEstudiantesDto = new ArrayList<ActividadEstudianteDto>();
		Actividad actividad = actividadDao.findById(id).orElseThrow(() -> new Exception("Esta Actividad no existe"));
		actividad.getActividadesEstudiante().stream().forEach(actividadEst->{
			actividadesEstudiantesDto.add(ActividadConverter.entityToDto(actividadEst, actividadEst.getEstudianteInfo().getRolUsuario().getUsuario()));
		});
		return actividadesEstudiantesDto;
	}

	private NotasActividadDto asignarNotasPorActividad (Actividad actividad, EstudianteInfo estudianteInfo, NotasActividadDto notasActividadDto) {
		ActividadEstudiante actividadEstudiante = actividadEstudianteDao.findOneByEstudianteInfoAndActividad(estudianteInfo, actividad);
		notasActividadDto.setEstudianteInfo(UsuarioConverter.entityToDto(estudianteInfo, estudianteInfo.getRolUsuario().getUsuario()));
		notasActividadDto.addCalificacion(actividad.getId(), actividadEstudiante.getCalificacion());
		return notasActividadDto;
	}


	@Override
	public NotasActividadesPorModuloDto listarNotasByModulo(Long idModuloCurso) throws Exception {
		NotasActividadesPorModuloDto notasActividadesPorModuloDto = new NotasActividadesPorModuloDto();
		List<NotasActividadDto> listaNotasActividadDto = new ArrayList<NotasActividadDto>();

		//se valida si existe el curso
		ModuloCurso moduloCurso = cursoModuloService.findOneEntity(idModuloCurso);
	
		//lista de actividades por curso
		List<Actividad> listaActividades = actividadDao.findByModuloCursoOrderByIdAsc(moduloCurso);
		List<ActividadDto> listaActividadesDto = new ArrayList<ActividadDto>();

		//lista de estudiantes matriculados en el curso
		List<EstudianteInfo> estudiantes = matriculaServiceImpl.listMatriculaByIdCursoActiveEntities(idModuloCurso);

		//se crea un item NotasActividadDto por cada estudiante matriculado
		for (EstudianteInfo estudiante: estudiantes) {
			NotasActividadDto notasActividadDto = new NotasActividadDto();
			notasActividadDto.setEstudianteInfo(UsuarioConverter.entityToDto(estudiante, estudiante.getRolUsuario().getUsuario()));
			listaNotasActividadDto.add(notasActividadDto);
		}
		
		//se asigna las notas por cada actividad
		for (Actividad actividad: listaActividades) {
			listaNotasActividadDto = buscarEstudianteInfo(estudiantes, actividad.getActividadesEstudiante(), actividad, listaNotasActividadDto);
			listaActividadesDto.add(ActividadConverter.entityToDto(actividad));
		}

		//Creacion del objeto dto final
		notasActividadesPorModuloDto.setListaNotasActividades(listaNotasActividadDto);
		notasActividadesPorModuloDto.setActividadesInfo(listaActividadesDto);
		notasActividadesPorModuloDto.setModuloCursoDto(ModuloCursoConverter.entityToDto(moduloCurso, moduloCurso.getCurso()));
		
		return notasActividadesPorModuloDto;
	}

	private List<NotasActividadDto> buscarEstudianteInfo(List<EstudianteInfo> estudiantes, List<ActividadEstudiante> actividadEstudianteInfo, Actividad actividad,
			List<NotasActividadDto> notasActividades) {
		actividadEstudianteInfo.stream().forEach(actividadEstu -> {
			EstudianteInfo estudianteInfo = estudiantes.stream().filter(estudiante-> {
				return estudiante.getActividadesEstudiante().stream().filter(acti-> acti.getId() == actividadEstu.getId()).findFirst().isPresent();
			}).findFirst().orElse(null);
			if (estudianteInfo != null) {
				NotasActividadDto notasActividadDto  = notasActividades.stream().filter(notaActEst -> notaActEst.getEstudianteInfo().getId() == actividadEstu.getEstudianteInfo().getId()).findFirst().get();
				notasActividadDto = asignarNotasPorActividad(actividad, estudianteInfo, notasActividadDto);
			}
		});
		return notasActividades;
	}

	@Override
	public void saveActividadEstudiante (ActividadEstudianteSaveDto actividadEstudiante) throws Exception {
		EstudianteInfo estudianteInfo = estudianteInfoServiceImpl.findById(actividadEstudiante.getIdEstudianteInfo());
		Actividad actividad = actividadDao.findById(actividadEstudiante.getIdActividad()).orElseThrow(()-> new Exception("Esta actividad no existe"));
		actividadEstudianteDao.save(ActividadConverter.dtoToEntity(actividadEstudiante, estudianteInfo, actividad));
	}

	@Override
	public void updateActividadEstudiante (ActividadEstudianteUpdateDto actividadEstudianteUpdate) throws Exception {
		ActividadEstudiante actividadEstudiante = actividadEstudianteDao.findById(actividadEstudianteUpdate.getId()).orElseThrow(()->new Exception("Esta nota no existe en el sistema"));
		actividadEstudianteDao.save(ActividadConverter.dtoToEntity(actividadEstudiante, actividadEstudianteUpdate));
	}

	@Override
	public void saveActividadEstudianteMasivo (ListActividadEstudianteCalificacionSaveDto listaActividadEstudianteCalificacionSaveDto) throws Exception {
		Actividad actividad = actividadDao.findById(listaActividadEstudianteCalificacionSaveDto.getIdActividad()).orElseThrow(()-> new Exception("Esta actividad no existe"));
		for (ActividadEstudianteCalificacionSaveDto actividadEstudianteCalificacionSaveDto: listaActividadEstudianteCalificacionSaveDto.getCalificacionEstudiante()) {
			EstudianteInfo estudianteInfo = estudianteInfoServiceImpl.findById(actividadEstudianteCalificacionSaveDto.getIdEstudianteInfo());
			ActividadEstudiante actividadEstudiante = ActividadConverter.dtoToEntity(actividadEstudianteCalificacionSaveDto, actividad, estudianteInfo);
			actividadEstudianteDao.save(actividadEstudiante);
		}
	}

	@Override
	public void updateActividadEstudianteMasivo (List<ActividadEstudianteCalificacionUpdateDto> listaActividadEstudianteCalificacionUpdateDto) throws Exception {
		for (ActividadEstudianteCalificacionUpdateDto actividadEstudianteCalificacionUpdateDto: listaActividadEstudianteCalificacionUpdateDto) {
			ActividadEstudiante actividadEstudiante = actividadEstudianteDao.findById(actividadEstudianteCalificacionUpdateDto.getIdActividadEstudiante()).orElseThrow(()-> new Exception ("Esta Actividad del estudiante no esta registrada"));
			actividadEstudiante = ActividadConverter.dtoToEntity(actividadEstudiante, actividadEstudianteCalificacionUpdateDto);
			actividadEstudianteDao.save(actividadEstudiante);
		}
	}

}
