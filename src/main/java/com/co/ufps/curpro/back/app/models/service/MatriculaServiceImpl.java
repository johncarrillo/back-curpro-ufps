package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.constante.ConstantesEntity;
import com.co.ufps.curpro.back.app.dto.BancoDto;
import com.co.ufps.curpro.back.app.dto.EstadoMatriculaDto;
import com.co.ufps.curpro.back.app.dto.InscripcionDto;
import com.co.ufps.curpro.back.app.dto.InscripcionPdfDto;
import com.co.ufps.curpro.back.app.dto.InscripcionSaveDto;
import com.co.ufps.curpro.back.app.dto.InscripcionUpdateDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.MatriculaPdfDto;
import com.co.ufps.curpro.back.app.dto.MatriculaSaveDto;
import com.co.ufps.curpro.back.app.dto.MatriculaUpdateDto;
import com.co.ufps.curpro.back.app.models.dao.IBancoDao;
import com.co.ufps.curpro.back.app.models.dao.IEstadoMatriculaDao;
import com.co.ufps.curpro.back.app.models.dao.IInscripcionDao;
import com.co.ufps.curpro.back.app.models.dao.IMatriculaDao;
import com.co.ufps.curpro.back.app.models.dao.ITipoCursoDao;
import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.EstadoMatricula;
import com.co.ufps.curpro.back.app.models.entity.EstudianteInfo;
import com.co.ufps.curpro.back.app.models.entity.Inscripcion;
import com.co.ufps.curpro.back.app.models.entity.Matricula;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;
import com.co.ufps.curpro.back.app.models.parser.MatriculaConverter;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	private IMatriculaDao matriculaDao;

	@Autowired
	private IInscripcionDao inscripcionDao;

	@Autowired
	private ITipoCursoDao tipoCursoDao;

	@Autowired
	private IEstadoMatriculaDao estadoMatriculaDao;

	@Autowired
	private IBancoDao bancoDao;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ICursoService cursoService;

	@Override
	public InscripcionDto findInscripcionById(Long idEstudiante, Long idCurso) throws Exception {
		RolUsuario rolUsuario = usuarioService.findRolUsuarioEstudiante(idEstudiante);
		if (rolUsuario.getEstudianteInfo() == null) {
			new Exception("El usuario no tiene rol de estudiante");
		}
		Inscripcion inscripcion = inscripcionDao.findByIdEstudianteInfoAndIdCurso(rolUsuario.getEstudianteInfo().getId(), idCurso);
		return MatriculaConverter.entityToDto(inscripcion, inscripcion.getEstudianteInfo().getRolUsuario().getUsuario());
	}

	@Override
	public List<InscripcionDto> listInscripcionByIdCurso(Long idCurso) {
		Curso curso = new Curso();
		curso.setId(idCurso);
		List<InscripcionDto> inscripcionesDto = new ArrayList<InscripcionDto>();
		inscripcionDao.findAllByCurso(curso).forEach(insc -> {
			inscripcionesDto.add(MatriculaConverter.entityToDto(insc, insc.getEstudianteInfo().getRolUsuario().getUsuario()));
		});
		return inscripcionesDto;
	}

	@Override
	public MatriculaDto findMatriculaById(Long idEstudiante, Long idCurso) throws Exception {
		RolUsuario rolUsuario = usuarioService.findRolUsuarioEstudiante(idEstudiante);
		if (rolUsuario.getEstudianteInfo() == null) {
			new Exception("El usuario no tiene rol de estudiante");
		}
		Matricula matricula = matriculaDao.findByIdEstudianteInfoAndIdCurso(rolUsuario.getEstudianteInfo().getId(), idCurso);
		return MatriculaConverter.entityToDto(matricula, matricula.getEstudianteInformacion().getRolUsuario().getUsuario());
	}

	@Override
	public List<MatriculaDto> listMatriculaByIdCurso(Long idCurso) {
		Curso curso = new Curso();
		curso.setId(idCurso);
		List<MatriculaDto> matriculasDto = new ArrayList<MatriculaDto>();
		matriculaDao.findAllByCurso(curso).forEach(matri -> {
			matriculasDto.add(MatriculaConverter.entityToDto(matri, matri.getEstudianteInformacion().getRolUsuario().getUsuario()));
		});
		return matriculasDto;
	}

	@Override
	public void saveInscripcion (InscripcionSaveDto inscripcionSave) throws Exception {
		RolUsuario rolUsuario = usuarioService.findRolUsuarioEstudiante(inscripcionSave.getIdEstudiante());
		if (rolUsuario.getEstudianteInfo() == null) {
			new Exception("Debe registrar la información especifica del estudiante para poder registrar la inscripcion");
		}
		Curso curso = cursoService.findOneEntity(inscripcionSave.getIdCurso());
		Inscripcion inscripcion = MatriculaConverter.dtoToEntity(inscripcionSave,
				rolUsuario.getEstudianteInfo(), curso,
				bancoDao.findById(inscripcionSave.getIdBanco())
						.orElseThrow(()-> new Exception ("Este banco no existe"))
						);
		inscripcionDao.save(inscripcion);
	}

	@Override
	public void saveMatricula (MatriculaSaveDto matriculaSave) throws Exception {
		RolUsuario rolUsuario = usuarioService.findRolUsuarioEstudiante(matriculaSave.getIdEstudiante());
		if (rolUsuario.getEstudianteInfo() == null) {
			new Exception("Debe registrar la información especifica del estudiante para poder registrar la matricula");
		}
		Curso curso = cursoService.findOneEntity(matriculaSave.getIdCurso());
		Matricula matricula = MatriculaConverter.dtoToEntity(matriculaSave,
				rolUsuario.getEstudianteInfo(), curso,
				bancoDao.findById(matriculaSave.getIdBanco())
						.orElseThrow(()-> new Exception ("Este banco no existe")),
				estadoMatriculaDao.findByNombre(ConstantesEntity.ESTADO_MATRICULA_PAGO)
						);
		matriculaDao.save(matricula);
	}

	@Override
	public void updateInscripcion (InscripcionUpdateDto inscripcionUpdate) throws Exception {
		Inscripcion inscripcion = inscripcionDao.findById(inscripcionUpdate.getId()).orElseThrow(()->new Exception ("Esta inscripcion no existe"));
		inscripcion = MatriculaConverter.dtoToEntity(inscripcion, inscripcionUpdate,
				bancoDao.findById(inscripcionUpdate.getIdBanco())
						.orElseThrow(()-> new Exception ("Este banco no existe"))
						);
		inscripcionDao.save(inscripcion);
	}

	@Override
	public void updateMatricula (MatriculaUpdateDto matriculaUpdate) throws Exception {
		Matricula matricula = matriculaDao.findById(matriculaUpdate.getId()).orElseThrow(()->new Exception ("Esta matricula no existe"));
		matricula = MatriculaConverter.dtoToEntity(matricula, matriculaUpdate,
				bancoDao.findById(matriculaUpdate.getIdBanco())
						.orElseThrow(()-> new Exception ("Este banco no existe")),
				estadoMatriculaDao.findById(matriculaUpdate.getIdEstadoMatricula()).orElseThrow(()->new Exception ("Este estado de la matricula no existe"))
						);
		matriculaDao.save(matricula);
	}

	@Override
	public List<EstadoMatriculaDto> listEstadoMatricula() {
		List<EstadoMatriculaDto> estadosMaticula = new ArrayList<EstadoMatriculaDto>();
		estadoMatriculaDao.findAll().forEach(estadoMatricula -> {
			estadosMaticula.add(MatriculaConverter.entityToDto(estadoMatricula));
		});
		return estadosMaticula;
	}

	@Override
	public List<BancoDto> listBanco() {
		List<BancoDto> bancos = new ArrayList<BancoDto>();
		bancoDao.findAll().forEach(banco -> {
			bancos.add(MatriculaConverter.entityToDto(banco));
		});
		return bancos;
	}

	@Override
	public void deleteMatriculaById(Long id) {
		matriculaDao.deleteById(id);
	}

	@Override
	public void deleteInscripcionById(Long id) {
		inscripcionDao.deleteById(id);
	}

	@Override
	public List<EstudianteInfo> listMatriculaByIdCursoActiveEntities(Long idCurso) {
		Curso curso = new Curso();
		curso.setId(idCurso);
		EstadoMatricula estado = estadoMatriculaDao.findByNombre(ConstantesEntity.ESTADO_MATRICULA_PAGO);
		List<Matricula> matriculas = matriculaDao.findAllByCursoAndEstadoMatricula(curso, estado);
		List<EstudianteInfo> estudiantes = new ArrayList<EstudianteInfo>();
		matriculas.forEach(matricula->{
			estudiantes.add(matricula.getEstudianteInformacion());
		});
		return estudiantes;
	}

	public List<MatriculaPdfDto> listMatriculaByIdCursoFromPdf(Long idCurso) {
		Curso curso = new Curso();
		curso.setId(idCurso);
		List<MatriculaPdfDto> matriculasDto = new ArrayList<MatriculaPdfDto>();
		matriculaDao.findAllByCurso(curso).forEach(matri -> {
			Usuario usuario = matri.getEstudianteInformacion().getRolUsuario().getUsuario();
			matriculasDto.add(MatriculaConverter.entityToDtoPdf(matri, usuario));
		});
		return matriculasDto;
	}

	@Override
	public List<InscripcionPdfDto> listInscripcionByIdCursoFromPdf(Long idCurso) {
		Curso curso = new Curso();
		curso.setId(idCurso);
		List<InscripcionPdfDto> inscripcionesDto = new ArrayList<InscripcionPdfDto>();
		inscripcionDao.findAllByCurso(curso).forEach(inscripcion -> {
			Usuario usuario = inscripcion.getEstudianteInfo().getRolUsuario().getUsuario();
			inscripcionesDto.add(MatriculaConverter.entityToDtoPdf(inscripcion, usuario));
		});
		return inscripcionesDto;
	}

	@Override
	public List<InscripcionDto> findByEstudianteInfo (EstudianteInfo estudianteInfo) {
		List<Inscripcion> inscripciones = inscripcionDao.findByEstudianteInfo(estudianteInfo);
		List<InscripcionDto> inscripcionesDto = new ArrayList<InscripcionDto>();
		inscripciones.forEach(inscripcion -> {
			inscripcionesDto.add(MatriculaConverter.entityToDto(inscripcion, null));
		});
		return inscripcionesDto;
	}

	@Override
	public List<MatriculaDto> findByMatriculaInfo (EstudianteInfo estudianteInfo) {
		List<Matricula> matriculas = matriculaDao.findByEstudianteInformacion(estudianteInfo);
		List<MatriculaDto> matriculasDto = new ArrayList<MatriculaDto>();
		matriculas.forEach(matricula -> {
			matriculasDto.add(MatriculaConverter.entityToDto(matricula, null));
		});
		return matriculasDto;
	}
}
