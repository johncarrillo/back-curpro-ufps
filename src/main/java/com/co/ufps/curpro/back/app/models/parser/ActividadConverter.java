package com.co.ufps.curpro.back.app.models.parser;

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteCalificacionSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteCalificacionUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadEstudianteUpdateDto;
import com.co.ufps.curpro.back.app.dto.ActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.ActividadUpdateDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadSaveDto;
import com.co.ufps.curpro.back.app.dto.TipoActividadUpdateDto;
import com.co.ufps.curpro.back.app.models.entity.Actividad;
import com.co.ufps.curpro.back.app.models.entity.ActividadEstudiante;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.TipoActividad;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.util.Common;

public class ActividadConverter {

	public static TipoActividadDto entityToDto (TipoActividad tipoActidad) {
		if (tipoActidad == null) {
			return null;
		}
		return TipoActividadDto.builder()
				.id(tipoActidad.getId())
				.nombre(tipoActidad.getNombre())
				.descripcion(tipoActidad.getDescripcion())
				.build();
	}

	public static TipoActividad dtoToEntity (TipoActividadSaveDto tipoActividadSave) {
		TipoActividad tipoActividad = new TipoActividad();
		tipoActividad.setNombre(tipoActividadSave.getNombre());
		tipoActividad.setDescripcion(tipoActividadSave.getDescripcion());
		return tipoActividad;
	}

	public static TipoActividad dtoToEntity (TipoActividad tipoActividad, TipoActividadUpdateDto tipoActividadUpdate) {
		tipoActividad.setNombre(tipoActividadUpdate.getNombre());
		tipoActividad.setDescripcion(tipoActividadUpdate.getDescripcion());
		return tipoActividad;
	}

	public static ActividadDto entityToDto (Actividad actividad) {
		if (actividad == null) {
			return null;
		}
		return ActividadDto.builder()
				.id(actividad.getId())
				.nombre(actividad.getNombre())
				.descripcion(actividad.getDescripcion())
				.fechaLimite(Common.dateToString(actividad.getFechaLimite()))
				.fechaCreacion(Common.dateToString(actividad.getFechaCreacion()))
				.tipoActividad(entityToDto(actividad.getTipoActividad()))
				.build();
	}

	public static Actividad dtoToEntity (ActividadSaveDto actividadSaveDto, ModuloCurso moduloCurso, TipoActividad tipoActividad) {
		if (actividadSaveDto == null) {
			return null;
		}
		Actividad actividad = new Actividad();
		actividad.setNombre(actividadSaveDto.getNombre());
		actividad.setDescripcion(actividadSaveDto.getDescripcion());
		actividad.setFechaLimite(Common.stringToFecha(actividadSaveDto.getFechaLimite()));
		actividad.setFechaCreacion(Common.stringToFecha(actividadSaveDto.getFechaCreacion()));
		actividad.setModuloCurso(moduloCurso);
		actividad.setTipoActividad(tipoActividad);
		return actividad;
	}

	public static Actividad dtoToEntity (Actividad actividad, ActividadUpdateDto actividadSaveDto, TipoActividad tipoActividad) {
		if (actividadSaveDto == null) {
			return null;
		}
		actividad.setNombre(actividadSaveDto.getNombre());
		actividad.setDescripcion(actividadSaveDto.getDescripcion());
		actividad.setFechaLimite(Common.stringToFecha(actividadSaveDto.getFechaLimite()));
		actividad.setFechaCreacion(Common.stringToFecha(actividadSaveDto.getFechaCreacion()));
		actividad.setTipoActividad(tipoActividad);
		return actividad;
	}

	public static ActividadEstudianteDto entityToDto (ActividadEstudiante actividadEstudiante, Usuario usuario) {
		if (actividadEstudiante == null) {
			return null;
		}
		return ActividadEstudianteDto.builder()
				.id(actividadEstudiante.getId())
				.estudianteInfo(UsuarioConverter.entityToDto(actividadEstudiante.getEstudianteInfo(), usuario))
				.calificacion(actividadEstudiante.getCalificacion())
				.build();
	}

	public static ActividadEstudiante dtoToEntity(ActividadEstudianteSaveDto actividadEstudianteDto, EstudianteInfo estudianteInfo, Actividad actividad) {
		ActividadEstudiante actividadEstudiante = new ActividadEstudiante();
		actividadEstudiante.setActividad(actividad);
		actividadEstudiante.setCalificacion(actividadEstudianteDto.getCalificacion());
		actividadEstudiante.setEstudianteInfo(estudianteInfo);
		return actividadEstudiante;
	}

	public static ActividadEstudiante dtoToEntity(ActividadEstudiante actividadEstudiante, ActividadEstudianteUpdateDto actividadEstudianteDto) {
		actividadEstudiante.setCalificacion(actividadEstudianteDto.getCalificacion());
		return actividadEstudiante;
	}

	public static ActividadEstudiante dtoToEntity (ActividadEstudianteCalificacionSaveDto actividadEstudianteCalificacionSaveDto,Actividad actividad, EstudianteInfo estudianteInfo) {
		ActividadEstudiante actividadEstudiante = new ActividadEstudiante();
		actividadEstudiante.setActividad(actividad);
		actividadEstudiante.setCalificacion(actividadEstudianteCalificacionSaveDto.getCalificacion());
		actividadEstudiante.setEstudianteInfo(estudianteInfo);
		return actividadEstudiante;
	}

	public static ActividadEstudiante dtoToEntity (ActividadEstudiante actividadEstudiante, ActividadEstudianteCalificacionUpdateDto actividadEstudianteCalificacionUpdateDto) {
		actividadEstudiante.setCalificacion(actividadEstudianteCalificacionUpdateDto.getCalificacion());
		return actividadEstudiante;
	}
}
