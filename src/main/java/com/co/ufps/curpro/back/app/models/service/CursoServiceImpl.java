package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.constante.ConstantesEntity;
import com.co.ufps.curpro.back.app.dto.CursoAndPresupuestoDto;
import com.co.ufps.curpro.back.app.dto.CursoDto;
import com.co.ufps.curpro.back.app.dto.CursoSaveDto;
import com.co.ufps.curpro.back.app.dto.CursoUpdateDto;
import com.co.ufps.curpro.back.app.dto.EstadoCursoDto;
import com.co.ufps.curpro.back.app.dto.EvaluacionModuloCursoDto;
import com.co.ufps.curpro.back.app.dto.RolDto;
import com.co.ufps.curpro.back.app.dto.RolUsuarioDto;
import com.co.ufps.curpro.back.app.dto.TipoCursoDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.co.ufps.curpro.back.app.models.dao.ICursoDao;
import com.co.ufps.curpro.back.app.models.dao.IEstadoCursoDao;
import com.co.ufps.curpro.back.app.models.dao.IPresupuestoDao;
import com.co.ufps.curpro.back.app.models.dao.ITipoCursoDao;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.Dependencia;
import com.co.ufps.curpro.back.app.models.entity.EstadoCurso;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.Presupuesto;
import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.TipoCurso;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.parser.CursoConverter;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoDao cursoDao;

	@Autowired
	private IModuloCursoService moduloCursoService;

	@Autowired
	private IEstadoCursoDao estadoCursoDao;

	@Autowired
	private ITipoCursoDao tipoCursoDao;

	@Autowired
	private IPresupuestoService presupuestoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IMailService mailService;

	@Autowired
	private IMatriculaService matriculaService;

	@Override
	public List<CursoDto> findAll(Long idEstadoCurso, Long idTipoCurso, Long idDependencia) {
		List<CursoDto> listaCursosDto = new ArrayList<CursoDto>();
		Iterable<Curso> iterableCurso = null;
		if (idEstadoCurso != null && idTipoCurso != null && idDependencia != null) {
			 iterableCurso = this.cursoDao.findByIdEstadoAndTipoAndDependencia(idEstadoCurso, idTipoCurso, idDependencia);
		} else if (idEstadoCurso != null && idTipoCurso != null) {
			iterableCurso = this.cursoDao.findByIdEstadoAndTipo(idEstadoCurso, idTipoCurso);
		} else if (idEstadoCurso != null && idDependencia != null) {
			iterableCurso = this.cursoDao.findByIdEstadoAndDependencia(idEstadoCurso, idDependencia);
		} else if (idTipoCurso != null && idDependencia != null) {
			iterableCurso = this.cursoDao.findByIdTipoAndDependencia(idTipoCurso, idDependencia);
		} else if (idEstadoCurso != null) {
			EstadoCurso estadoCurso = new EstadoCurso();
			estadoCurso.setId(idEstadoCurso);
			iterableCurso = this.cursoDao.findByEstadoCurso(estadoCurso);
		} else if (idTipoCurso != null) {
			TipoCurso tipoCurso = new TipoCurso ();
			tipoCurso.setId(idTipoCurso);
			iterableCurso = this.cursoDao.findByTipoCurso(tipoCurso);
		} else if (idDependencia != null) {
			Dependencia dependencia = new Dependencia();
			dependencia.setId(idDependencia);
			iterableCurso = this.cursoDao.findByDependencia(dependencia);
		} else {
			iterableCurso = this.cursoDao.findAll(); 
		}
		iterableCurso.forEach(curso -> {
			listaCursosDto.add(CursoConverter.entityToDto(curso));
		});
		return listaCursosDto;
	}

	@Override
	public CursoDto findOne(Long id) {
		return CursoConverter.entityToDto(this.cursoDao.findById(id).orElse(null));
	}

	@Override
	public void save(CursoAndPresupuestoDto cursoAndPresupuestoDto) throws Exception {
		String correo = SecurityContextHolder.getContext().getAuthentication().getName();
		List<RolUsuarioDto> roles = usuarioService.listRol(correo);
		RolUsuarioDto rolUsuarioDto = roles.stream()
				.filter(rol -> ConstantesEntity.ROL_DIRECTOR_PROGRAMA.equalsIgnoreCase(rol.getNombreRol()))
				.findFirst()
				.orElse(null);
		if (rolUsuarioDto == null) {
			throw new Exception("El usuario no es director de programa");
		}
		Dependencia dependencia = usuarioService.findDependenciaByDirector(rolUsuarioDto.getId());
		if (dependencia == null) {
			throw new Exception("El director no esta asociado a ninguna dependencia");
		}
		Presupuesto presupuesto = presupuestoService.save(cursoAndPresupuestoDto.getPresupuesto());
		Curso curso = this.cursoDao.save(
				CursoConverter.dtoToEntity(
					cursoAndPresupuestoDto.getCurso(),
					estadoCursoDao.findByNombre(ConstantesEntity.ESTADO_CURSO_ENVIADO_SIN_REVISION),
					tipoCursoDao.findById(cursoAndPresupuestoDto.getCurso().getIdTipoCurso()).orElse(null),
					presupuesto,
					dependencia
				)
		);
		Rol rol = this.usuarioService.findRolByName(ConstantesEntity.ROL_JUNTA_FIRE);

		String tipoCurso = curso.getTipoCurso().equals(ConstantesEntity.TIPO_CURSO_OTROS) ? curso.getTipoCurso().getNombre() + " - " + curso.getOtroTipoCurso() : curso.getOtroTipoCurso(); 
		List<UsuarioDto> usuariosDto = this.usuarioService.findByRol(rol);
		usuariosDto.forEach(usuario -> {
			this.mailService.sendMail(correo, usuario.getCorreo(), "Solicitud de aprobación de nuevo curso",
					"Hola, tienes pendiente una nueva solicitud de creación de curso de tipo " + tipoCurso + " con el nombre de \" " + curso.getNombre() +"\""
							+ "\n"
							+ "\nDependencia: " + dependencia.getNombre()
							+ "\nNombre Director: " + rolUsuarioDto.getNombreUsuario());
		});
	}

	@Override
	public void update(CursoUpdateDto cursoUpdateDto) {
		String correo = SecurityContextHolder.getContext().getAuthentication().getName();
		Curso curso = this.cursoDao.findById(cursoUpdateDto.getId()).orElseThrow();
		if (cursoUpdateDto.getJustificacionRechazo() != null 
				&& !cursoUpdateDto.getJustificacionRechazo().equalsIgnoreCase(curso.getJustificacionRechazo())
						&& curso.getDependencia().getRolUsuario() != null) {
			
			this.mailService.sendMail(correo, curso.getDependencia().getRolUsuario().getUsuario().getCorreo(),
					"Motivo de rechazo de la solicitud del curso",
					"El curso a sido rechazado por la siguiente razón \""+ cursoUpdateDto.getJustificacionRechazo() +"\"");
		}
		curso = CursoConverter.dtoToEntity(curso, cursoUpdateDto,
				this.estadoCursoDao.findById(cursoUpdateDto.getIdEstadoCurso()).orElseThrow(),
				this.tipoCursoDao.findById(cursoUpdateDto.getIdTipoCurso()).orElseThrow());
		this.cursoDao.save(curso);
	}

	@Override
	public void delete(Long id) {
		this.cursoDao.deleteById(id);
	}

	@Override
	public List<TipoCursoDto> listTipoCurso() {
		List<TipoCursoDto> listaTipoCurso = new ArrayList<TipoCursoDto>();
		this.tipoCursoDao.findAll().forEach(tipo -> {
			listaTipoCurso.add(CursoConverter.entityToDto(tipo));
		});
		return listaTipoCurso;
	}

	@Override
	public List<EstadoCursoDto> listEstadoCurso() {
		List<EstadoCursoDto> listEstadoCurso = new ArrayList<EstadoCursoDto>();
		estadoCursoDao.findAll().forEach(estado -> {
			listEstadoCurso.add(CursoConverter.entityToDto(estado));
		});
		return listEstadoCurso;
	}


	@Override
	public Curso findOneEntity(Long id) throws Exception {
		return cursoDao.findById(id).orElseThrow(() -> new Exception("Este Curso no existe"));
	}

	@Override
	public void publicarEvaluacionCurso (EvaluacionModuloCursoDto evaluacionCursoDto) throws Exception {
		String correo = SecurityContextHolder.getContext().getAuthentication().getName();
		ModuloCurso moduloCurso = this.moduloCursoService.findOneEntity(evaluacionCursoDto.getIdModuloCurso());
		Curso curso = moduloCurso.getCurso();
		if (null != evaluacionCursoDto.getLinkEvaluacion()) {
			matriculaService.listMatriculaByIdCursoActiveEntities(moduloCurso.getCurso().getId()).forEach(estudianteInfo->{
				Usuario usuario = estudianteInfo.getRolUsuario().getUsuario();
				this.mailService.sendMail(correo, usuario.getCorreo(),
						"Evaluación del curso " + curso.getNombre(),
						"Se le remite el link de evaluación del curso " +curso.getNombre() + ", para evaluar el desempeño del docente " 
						+ moduloCurso.getRolUsuario().getUsuario().getNombres()
						+ " " + moduloCurso.getRolUsuario().getUsuario().getNombres() + ". \nLink de evaluacion: " + evaluacionCursoDto.getLinkEvaluacion());
			});
		}
		moduloCurso.setEvaluacion(evaluacionCursoDto.getLinkEvaluacion());
		moduloCursoService.updateModuloCurso(moduloCurso);
	}
}
