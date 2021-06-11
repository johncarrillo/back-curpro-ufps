package com.co.ufps.curpro.back.app.models.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.co.ufps.curpro.back.app.dto.AlquilerAulaDto;
import com.co.ufps.curpro.back.app.dto.ApoyoLogisticoDto;
import com.co.ufps.curpro.back.app.dto.CoordinacionOfertaAcademicaDto;
import com.co.ufps.curpro.back.app.dto.ImpresoPublicacionDto;
import com.co.ufps.curpro.back.app.dto.InscripcionMatriculaDto;
import com.co.ufps.curpro.back.app.dto.ItemGastoDto;
import com.co.ufps.curpro.back.app.dto.ItemIngresoDto;
import com.co.ufps.curpro.back.app.dto.MaterialSuministroDto;
import com.co.ufps.curpro.back.app.dto.OtroAnexoDto;
import com.co.ufps.curpro.back.app.dto.PresupuestoDto;
import com.co.ufps.curpro.back.app.dto.ServicioEducativoDto;
import com.co.ufps.curpro.back.app.models.entity.AlquilerAula;
import com.co.ufps.curpro.back.app.models.entity.ApoyoLogistico;
import com.co.ufps.curpro.back.app.models.entity.CoordinacionOfertaAcademica;
import com.co.ufps.curpro.back.app.models.entity.ImpresoPublicacion;
import com.co.ufps.curpro.back.app.models.entity.InscripcionMatricula;
import com.co.ufps.curpro.back.app.models.entity.ItemGasto;
import com.co.ufps.curpro.back.app.models.entity.ItemIngreso;
import com.co.ufps.curpro.back.app.models.entity.MaterialSuministro;
import com.co.ufps.curpro.back.app.models.entity.OtroAnexo;
import com.co.ufps.curpro.back.app.models.entity.Presupuesto;
import com.co.ufps.curpro.back.app.models.entity.ServicioEducativo;

public class PresupuestoConverter {

	public static Presupuesto dtoToEntity(PresupuestoDto presupuestoDto) {
		if (presupuestoDto == null) {
			return null;
		}
		Presupuesto presupuesto = new Presupuesto();
		presupuesto.setId(presupuestoDto.getId());
		presupuesto.setValorIngreso(presupuestoDto.getValorIngreso());
		presupuesto.setValorGasto(presupuestoDto.getValorGasto());
		presupuesto.setValorUtilidad(presupuestoDto.getUtilidad());
		presupuesto.setFondoInvestigacionUniversitario(presupuestoDto.getFondoInvestigacionUniversitario());
		presupuesto.setUtilidadNeta(presupuestoDto.getUtilidadNeta());
		presupuesto.setItemIngreso(dtoToEntity(presupuestoDto.getItemIngreso()));
		presupuesto.setItemGasto(dtoToEntity(presupuestoDto.getItemGasto()));

		return presupuesto;
	}

	public static ItemIngreso dtoToEntity(ItemIngresoDto itemIngresoDto) {
		if (itemIngresoDto == null) {
			return null;
		}
		if (itemIngresoDto.getItemIngreso() != null && itemIngresoDto.getItemIngreso().size() > 0) {
			ItemIngreso itemIngreso = new ItemIngreso();
			itemIngreso.setId(itemIngresoDto.getId());
			itemIngreso.setNombre(itemIngresoDto.getNombre());
			itemIngreso.setConsecutivo(itemIngresoDto.getConsecutivo());
			itemIngreso.setValor(itemIngresoDto.getValor());
			itemIngreso.setItemIngreso(dtosToentitiesItemIngreso(itemIngresoDto.getItemIngreso()));
			itemIngreso.setInscripcionMatriculas(dtosToEntitiesInscripcionMatricula(itemIngresoDto.getInscripcionMatricula(), itemIngreso));
			return itemIngreso;
		}
		ItemIngreso itemIngreso = new ItemIngreso();
		itemIngreso.setId(itemIngresoDto.getId());
		itemIngreso.setNombre(itemIngresoDto.getNombre());
		itemIngreso.setConsecutivo(itemIngresoDto.getConsecutivo());
		itemIngreso.setValor(itemIngresoDto.getValor());
		itemIngreso.setInscripcionMatriculas(dtosToEntitiesInscripcionMatricula(itemIngresoDto.getInscripcionMatricula(), itemIngreso));
		return itemIngreso;

	}

	public static List<ItemIngreso> dtosToentitiesItemIngreso (List<ItemIngresoDto> itemIngresosDtos) {
		if (itemIngresosDtos == null) {
			return null;
		}
		//Collections.sort(itemIngresos);
		return itemIngresosDtos.stream().map(item -> {
			if (item.getItemIngreso() == null || item.getItemIngreso().size() == 0) {
				return dtoToEntity(item);
			}
			ItemIngreso itemIngreso = new ItemIngreso();
			itemIngreso.setId(item.getId());
			itemIngreso.setNombre(item.getNombre());
			itemIngreso.setConsecutivo(item.getConsecutivo());
			itemIngreso.setValor(item.getValor());
			itemIngreso.setItemIngreso(dtosToentitiesItemIngreso(item.getItemIngreso()));
			itemIngreso.setInscripcionMatriculas(dtosToEntitiesInscripcionMatricula(item.getInscripcionMatricula(), itemIngreso));
			return itemIngreso;

		}).collect(Collectors.toList());
	}

	public static ItemGasto dtoToEntity(ItemGastoDto itemGastoDto) {
		if (itemGastoDto == null) {
			return null;
		}
		if (itemGastoDto.getItemsGasto() != null && itemGastoDto.getItemsGasto().size() > 0) {
			ItemGasto itemGasto = new ItemGasto();
			itemGasto.setId(itemGastoDto.getId());
			itemGasto.setNombre(itemGastoDto.getNombre());
			itemGasto.setConsecutivo(itemGastoDto.getConsecutivo());
			itemGasto.setValor(itemGastoDto.getValor());
			itemGasto.setItemGastos(dtosToEntitiesItemGasto(itemGastoDto.getItemsGasto()));
			itemGasto.setServiciosEducativos(dtosToEntitiesServicioEducativo(itemGastoDto.getServiciosEducativos(), itemGasto));
			itemGasto.setCoordinacionOfertasAcademicas(dtosToEntitiesCoordinacionOfertaAcademica(itemGastoDto.getCoordinacionOfertasAcademicas(), itemGasto));
			itemGasto.setApoyosLogisticos(dtosToEntitiesApoyoLogistico(itemGastoDto.getApoyosLogisticos(), itemGasto));
			itemGasto.setMaterialesSuministro(dtosToEntitiesMaterialSuministro(itemGastoDto.getMaterialesSuministro(), itemGasto));
			itemGasto.setImpresosPublicaciones(dtosToEntitiesImpresoPublicacion(itemGastoDto.getImpresosPublicaciones(), itemGasto));
			itemGasto.setAlquileresAulas(dtosToEntitiesAlquilerAula(itemGastoDto.getAlquilerAulas(), itemGasto));
			itemGasto.setOtrosAnexos(dtosToEntitiesOtroAnexo(itemGastoDto.getOtrosAnexos(), itemGasto));
			return itemGasto;
		}
		ItemGasto itemGasto = new ItemGasto();
		itemGasto.setId(itemGastoDto.getId());
		itemGasto.setNombre(itemGastoDto.getNombre());
		itemGasto.setConsecutivo(itemGastoDto.getConsecutivo());
		itemGasto.setValor(itemGastoDto.getValor());
		itemGasto.setServiciosEducativos(dtosToEntitiesServicioEducativo(itemGastoDto.getServiciosEducativos(), itemGasto));
		itemGasto.setCoordinacionOfertasAcademicas(dtosToEntitiesCoordinacionOfertaAcademica(itemGastoDto.getCoordinacionOfertasAcademicas(), itemGasto));
		itemGasto.setApoyosLogisticos(dtosToEntitiesApoyoLogistico(itemGastoDto.getApoyosLogisticos(), itemGasto));
		itemGasto.setMaterialesSuministro(dtosToEntitiesMaterialSuministro(itemGastoDto.getMaterialesSuministro(), itemGasto));
		itemGasto.setImpresosPublicaciones(dtosToEntitiesImpresoPublicacion(itemGastoDto.getImpresosPublicaciones(), itemGasto));
		itemGasto.setAlquileresAulas(dtosToEntitiesAlquilerAula(itemGastoDto.getAlquilerAulas(), itemGasto));
		itemGasto.setOtrosAnexos(dtosToEntitiesOtroAnexo(itemGastoDto.getOtrosAnexos(), itemGasto));
		return itemGasto;
	}


	public static List<ItemGasto> dtosToEntitiesItemGasto (List<ItemGastoDto> itemGastosDto) {
		if (itemGastosDto == null) {
			return null;
		}
		//Collections.sort(itemGastos);
		return itemGastosDto.stream().map(item -> {
			if (item.getItemsGasto() == null || item.getItemsGasto().size() == 0) {
				return dtoToEntity(item);
			}
			ItemGasto itemGasto = new ItemGasto();
			itemGasto.setId(item.getId());
			itemGasto.setNombre(item.getNombre());
			itemGasto.setConsecutivo(item.getConsecutivo());
			itemGasto.setValor(item.getValor());
			itemGasto.setItemGastos(dtosToEntitiesItemGasto(item.getItemsGasto()));
			itemGasto.setServiciosEducativos(dtosToEntitiesServicioEducativo(item.getServiciosEducativos(), itemGasto));
			itemGasto.setCoordinacionOfertasAcademicas(dtosToEntitiesCoordinacionOfertaAcademica(item.getCoordinacionOfertasAcademicas(), itemGasto));
			itemGasto.setApoyosLogisticos(dtosToEntitiesApoyoLogistico(item.getApoyosLogisticos(), itemGasto));
			itemGasto.setMaterialesSuministro(dtosToEntitiesMaterialSuministro(item.getMaterialesSuministro(), itemGasto));
			itemGasto.setImpresosPublicaciones(dtosToEntitiesImpresoPublicacion(item.getImpresosPublicaciones(), itemGasto));
			itemGasto.setAlquileresAulas(dtosToEntitiesAlquilerAula(item.getAlquilerAulas(), itemGasto));
			itemGasto.setOtrosAnexos(dtosToEntitiesOtroAnexo(item.getOtrosAnexos(), itemGasto));
			return itemGasto;
			/*return null;ItemGastoDto.builder()
					.id(item.getId())
					.nombre(item.getNombre())
					.consecutivo(item.getConsecutivo())
					.valor(item.getValor())
					.itemsGasto(entitiesToDtosItemGasto(item.getItemGastos()))
					.serviciosEducativos(entitiesToDtosServicioEducativo(item.getServiciosEducativos()))
					.coordinacionOfertasAcademicas(entitiesToDtosCoordinacionOfertaAcademica(item.getCoordinacionOfertasAcademicas()))
					.apoyosLogisticos(entitiesToDtosApoyoLogistico(item.getApoyosLogisticos()))
					.materialesSuministro(entitiesToDtosMaterialSuministro(item.getMaterialesSuministro()))
					.impresosPublicaciones(entitiesToDtosImpresoPublicacion(item.getImpresosPublicaciones()))
					.alquilerAulas(entitiesToDtosAlquilerAula(item.getAlquileresAulas()))
					.otrosAnexos(entitiesToDtosOtroAnexo(item.getOtrosAnexos()))
					.build();*/
		}).collect(Collectors.toList());
	}

	public static PresupuestoDto entityToDto(Presupuesto presupuesto) {
		if (presupuesto == null) {
			return null;
		}
		return PresupuestoDto.builder()
				.id(presupuesto.getId())
				.valorIngreso(presupuesto.getValorIngreso())
				.valorGasto(presupuesto.getValorGasto())
				.utilidad(presupuesto.getValorUtilidad())
				.fondoInvestigacionUniversitario(presupuesto.getFondoInvestigacionUniversitario())
				.utilidadNeta(presupuesto.getUtilidadNeta())
				.itemIngreso(entityToDto(presupuesto.getItemIngreso()))
				.itemGasto(entityToDto(presupuesto.getItemGasto()))
				.build();
	}

	public static ItemIngresoDto entityToDto(ItemIngreso itemIngreso) {
		if (itemIngreso == null) {
			return null;
		}
		if (itemIngreso.getItemIngreso() != null && itemIngreso.getItemIngreso().size() > 0) {
			return ItemIngresoDto.builder()
					.id(itemIngreso.getId())
					.nombre(itemIngreso.getNombre())
					.consecutivo(itemIngreso.getConsecutivo())
					.valor(itemIngreso.getValor())
					.itemIngreso(entitiesToDtosItemIngreso(itemIngreso.getItemIngreso()))
					.inscripcionMatricula(entitiesToDtosInscripcionMatricula(itemIngreso.getInscripcionMatriculas()))
					.build();
		}
		return ItemIngresoDto.builder()
				.id(itemIngreso.getId())
				.nombre(itemIngreso.getNombre())
				.consecutivo(itemIngreso.getConsecutivo())
				.itemIngreso(new ArrayList<ItemIngresoDto>())
				.valor(itemIngreso.getValor())
				.inscripcionMatricula(entitiesToDtosInscripcionMatricula(itemIngreso.getInscripcionMatriculas()))
				.build();
	}

	public static List<ItemIngresoDto> entitiesToDtosItemIngreso (List<ItemIngreso> itemIngresos) {
		if (itemIngresos == null) {
			return null;
		}
		Collections.sort(itemIngresos);
		return itemIngresos.stream().map(item -> {
			if (item.getItemIngreso() == null || item.getItemIngreso().size() == 0) {
				return entityToDto(item);
			}
			return ItemIngresoDto.builder()
					.id(item.getId())
					.nombre(item.getNombre())
					.consecutivo(item.getConsecutivo())
					.valor(item.getValor())
					.itemIngreso(entitiesToDtosItemIngreso(item.getItemIngreso()))
					.inscripcionMatricula(entitiesToDtosInscripcionMatricula(item.getInscripcionMatriculas()))
					.build();
		}).collect(Collectors.toList());
	}

	public static ItemGastoDto entityToDto(ItemGasto itemGasto) {
		if (itemGasto == null) {
			return null;
		}
		if (itemGasto.getItemGastos() != null && itemGasto.getItemGastos().size() > 0) {
			return ItemGastoDto.builder()
					.id(itemGasto.getId())
					.nombre(itemGasto.getNombre())
					.consecutivo(itemGasto.getConsecutivo())
					.valor(itemGasto.getValor())
					.itemsGasto(entitiesToDtosItemGasto(itemGasto.getItemGastos()))
					.serviciosEducativos(entitiesToDtosServicioEducativo(itemGasto.getServiciosEducativos()))
					.coordinacionOfertasAcademicas(entitiesToDtosCoordinacionOfertaAcademica(itemGasto.getCoordinacionOfertasAcademicas()))
					.apoyosLogisticos(entitiesToDtosApoyoLogistico(itemGasto.getApoyosLogisticos()))
					.materialesSuministro(entitiesToDtosMaterialSuministro(itemGasto.getMaterialesSuministro()))
					.impresosPublicaciones(entitiesToDtosImpresoPublicacion(itemGasto.getImpresosPublicaciones()))
					.alquilerAulas(entitiesToDtosAlquilerAula(itemGasto.getAlquileresAulas()))
					.otrosAnexos(entitiesToDtosOtroAnexo(itemGasto.getOtrosAnexos()))
					.build();
		}
		return ItemGastoDto.builder()
				.id(itemGasto.getId())
				.nombre(itemGasto.getNombre())
				.consecutivo(itemGasto.getConsecutivo())
				.valor(itemGasto.getValor())
				.itemsGasto(new ArrayList<ItemGastoDto>())
				.serviciosEducativos(entitiesToDtosServicioEducativo(itemGasto.getServiciosEducativos()))
				.coordinacionOfertasAcademicas(entitiesToDtosCoordinacionOfertaAcademica(itemGasto.getCoordinacionOfertasAcademicas()))
				.apoyosLogisticos(entitiesToDtosApoyoLogistico(itemGasto.getApoyosLogisticos()))
				.materialesSuministro(entitiesToDtosMaterialSuministro(itemGasto.getMaterialesSuministro()))
				.impresosPublicaciones(entitiesToDtosImpresoPublicacion(itemGasto.getImpresosPublicaciones()))
				.alquilerAulas(entitiesToDtosAlquilerAula(itemGasto.getAlquileresAulas()))
				.otrosAnexos(entitiesToDtosOtroAnexo(itemGasto.getOtrosAnexos()))
				.build();
	}


	public static List<ItemGastoDto> entitiesToDtosItemGasto (List<ItemGasto> itemGastos) {
		if (itemGastos == null) {
			return null;
		}
		Collections.sort(itemGastos);
		return itemGastos.stream().map(item -> {
			if (item.getItemGastos() == null || item.getItemGastos().size() == 0) {
				return entityToDto(item);
			}
			return ItemGastoDto.builder()
					.id(item.getId())
					.nombre(item.getNombre())
					.consecutivo(item.getConsecutivo())
					.valor(item.getValor())
					.itemsGasto(entitiesToDtosItemGasto(item.getItemGastos()))
					.serviciosEducativos(entitiesToDtosServicioEducativo(item.getServiciosEducativos()))
					.coordinacionOfertasAcademicas(entitiesToDtosCoordinacionOfertaAcademica(item.getCoordinacionOfertasAcademicas()))
					.apoyosLogisticos(entitiesToDtosApoyoLogistico(item.getApoyosLogisticos()))
					.materialesSuministro(entitiesToDtosMaterialSuministro(item.getMaterialesSuministro()))
					.impresosPublicaciones(entitiesToDtosImpresoPublicacion(item.getImpresosPublicaciones()))
					.alquilerAulas(entitiesToDtosAlquilerAula(item.getAlquileresAulas()))
					.otrosAnexos(entitiesToDtosOtroAnexo(item.getOtrosAnexos()))
					.build();
		}).collect(Collectors.toList());
	}

	public static List<InscripcionMatriculaDto> entitiesToDtosInscripcionMatricula (List<InscripcionMatricula> inscripcionMatricula) {
		if (inscripcionMatricula == null) {
			return null;
		}
		Collections.sort(inscripcionMatricula);
		return inscripcionMatricula.stream().map(inscripcion -> entityToDto(inscripcion)).collect(Collectors.toList());
	}

	public static List<InscripcionMatricula> dtosToEntitiesInscripcionMatricula (List<InscripcionMatriculaDto> inscripcionMatricula, ItemIngreso itemIngreso) {
		if (inscripcionMatricula == null) {
			return null;
		}
		//Collections.sort(inscripcionMatricula);
		return inscripcionMatricula.stream().map(inscripcion -> dtoToEntity(inscripcion, itemIngreso)).collect(Collectors.toList());
	}


	public static List<ServicioEducativoDto> entitiesToDtosServicioEducativo (List<ServicioEducativo> serviciosEducativos) {
		if (serviciosEducativos == null) {
			return null;
		}
		Collections.sort(serviciosEducativos);
		return serviciosEducativos.stream().map(servicio -> entityToDto(servicio)).collect(Collectors.toList());
	}

	public static List<ServicioEducativo> dtosToEntitiesServicioEducativo (List<ServicioEducativoDto> serviciosEducativos, ItemGasto itemGasto) {
		if (serviciosEducativos == null) {
			return null;
		}
		//Collections.sort(serviciosEducativos);
		return serviciosEducativos.stream().map(servicio -> dtoToEntity(servicio, itemGasto)).collect(Collectors.toList());
	}

	public static List<CoordinacionOfertaAcademicaDto> entitiesToDtosCoordinacionOfertaAcademica (List<CoordinacionOfertaAcademica> coordinacionOfertasAcademicas) {
		if (coordinacionOfertasAcademicas == null) {
			return null;
		}
		Collections.sort(coordinacionOfertasAcademicas);
		return coordinacionOfertasAcademicas.stream().map(coordinacion -> entityToDto(coordinacion)).collect(Collectors.toList());
	}
	
	public static List<CoordinacionOfertaAcademica> dtosToEntitiesCoordinacionOfertaAcademica (List<CoordinacionOfertaAcademicaDto> coordinacionOfertasAcademicas, ItemGasto itemGasto) {
		if (coordinacionOfertasAcademicas == null) {
			return null;
		}
		// Collections.sort(coordinacionOfertasAcademicas);
		return coordinacionOfertasAcademicas.stream().map(coordinacion -> dtoToEntity(coordinacion, itemGasto)).collect(Collectors.toList());
	}

	public static List<ApoyoLogisticoDto> entitiesToDtosApoyoLogistico (List<ApoyoLogistico> apoyoLogistico) {
		if (apoyoLogistico == null) {
			return null;
		}
		Collections.sort(apoyoLogistico);
		return apoyoLogistico.stream().map(apoyo -> entityToDto(apoyo)).collect(Collectors.toList());
	}

	public static List<ApoyoLogistico> dtosToEntitiesApoyoLogistico (List<ApoyoLogisticoDto> apoyoLogistico, ItemGasto itemGasto) {
		if (apoyoLogistico == null) {
			return null;
		}
		// Collections.sort(apoyoLogistico);
		return apoyoLogistico.stream().map(apoyo -> dtoToEntity(apoyo, itemGasto)).collect(Collectors.toList());
	}

	public static List<MaterialSuministroDto> entitiesToDtosMaterialSuministro (List<MaterialSuministro> materialesSuministro) {
		if (materialesSuministro == null) {
			return null;
		}
		Collections.sort(materialesSuministro);
		return materialesSuministro.stream().map(material -> entityToDto(material)).collect(Collectors.toList());
	}

	public static List<MaterialSuministro> dtosToEntitiesMaterialSuministro (List<MaterialSuministroDto> materialesSuministro, ItemGasto itemGasto) {
		if (materialesSuministro == null) {
			return null;
		}
		// Collections.sort(materialesSuministro);
		return materialesSuministro.stream().map(material -> dtoToEntity(material, itemGasto)).collect(Collectors.toList());
	}
	
	public static List<ImpresoPublicacionDto> entitiesToDtosImpresoPublicacion (List<ImpresoPublicacion> impresosPublicaciones) {
		if (impresosPublicaciones == null) {
			return null;
		}
		Collections.sort(impresosPublicaciones);
		return impresosPublicaciones.stream().map(impresoPublicacion -> entityToDto(impresoPublicacion)).collect(Collectors.toList());
	}

	public static List<ImpresoPublicacion> dtosToEntitiesImpresoPublicacion (List<ImpresoPublicacionDto> impresosPublicaciones, ItemGasto itemGasto) {
		if (impresosPublicaciones == null) {
			return null;
		}
		// Collections.sort(impresosPublicaciones);
		return impresosPublicaciones.stream().map(impresoPublicacion -> dtoToEntity(impresoPublicacion, itemGasto)).collect(Collectors.toList());
	}
	
	public static List<AlquilerAulaDto> entitiesToDtosAlquilerAula (List<AlquilerAula> alquileresAulas) {
		if (alquileresAulas == null) {
			return null;
		}
		Collections.sort(alquileresAulas);
		return alquileresAulas.stream().map(alquiler -> entityToDto(alquiler)).collect(Collectors.toList());
	}

	public static List<AlquilerAula> dtosToEntitiesAlquilerAula (List<AlquilerAulaDto> alquileresAulas, ItemGasto itemGasto) {
		if (alquileresAulas == null) {
			return null;
		}
		// Collections.sort(alquileresAulas);
		return alquileresAulas.stream().map(alquiler -> dtoToEntity(alquiler,itemGasto)).collect(Collectors.toList());
	}

	public static List<OtroAnexoDto> entitiesToDtosOtroAnexo (List<OtroAnexo> otrosAnexos) {
		if (otrosAnexos == null) {
			return null;
		}
		Collections.sort(otrosAnexos);
		return otrosAnexos.stream().map(anexo -> entityToDto(anexo)).collect(Collectors.toList());
	}

	public static List<OtroAnexo> dtosToEntitiesOtroAnexo (List<OtroAnexoDto> otrosAnexos, ItemGasto itemGasto) {
		if (otrosAnexos == null) {
			return null;
		}
		//Collections.sort(otrosAnexos);
		return otrosAnexos.stream()
				.map(anexo -> dtoToEntity(anexo, itemGasto))
				.collect(Collectors.toList());
	}

	public static InscripcionMatriculaDto entityToDto(InscripcionMatricula inscripcionMatricula) {
		if (inscripcionMatricula == null) {
			return null;
		}
		return InscripcionMatriculaDto.builder()
				.id(inscripcionMatricula.getId())
				.tipoParticipante(inscripcionMatricula.getTipoParticiante())
				.cantidad(inscripcionMatricula.getCantidad())
				.valorUnitario(inscripcionMatricula.getValorUnitario())
				.valorTotal(inscripcionMatricula.getValorTotal())
				.build();
	}

	public static InscripcionMatricula dtoToEntity(InscripcionMatriculaDto inscripcionMatriculaDto, ItemIngreso itemIngreso) {
		if (inscripcionMatriculaDto == null) {
			return null;
		}
		InscripcionMatricula inscripcionMatricula = new InscripcionMatricula();
		inscripcionMatricula.setId(inscripcionMatriculaDto.getId());
		inscripcionMatricula.setTipoParticiante(inscripcionMatriculaDto.getTipoParticipante());
		inscripcionMatricula.setCantidad(inscripcionMatriculaDto.getCantidad());
		inscripcionMatricula.setValorUnitario(inscripcionMatriculaDto.getValorUnitario());
		inscripcionMatricula.setValorTotal(inscripcionMatriculaDto.getValorTotal());
		inscripcionMatricula.setItemIngreso(itemIngreso);

		return inscripcionMatricula;
	}

	public static ServicioEducativoDto entityToDto(ServicioEducativo servicioEducativo) {
		if (servicioEducativo == null) {
			return null;
		}
		return ServicioEducativoDto.builder()
				.id(servicioEducativo.getId())
				.nombreDocente(servicioEducativo.getNombreDocente())
				.escolaridad(servicioEducativo.getEscolaridad())
				.escalafon(servicioEducativo.getEscalafon())
				.puntaje(servicioEducativo.getPuntaje())
				.valorPunto(servicioEducativo.getValorPunto())
				.valorHora(servicioEducativo.getValorHora())
				.cantidadHora(servicioEducativo.getCantidadHora())
				.valorTotal(servicioEducativo.getValorTotal())
				.build();
	}

	public static ServicioEducativo dtoToEntity(ServicioEducativoDto servicioEducativoDto, ItemGasto itemGasto) {
		if (servicioEducativoDto == null) {
			return null;
		}
		ServicioEducativo servicioEducativo = new ServicioEducativo();
		servicioEducativo.setId(servicioEducativoDto.getId());
		servicioEducativo.setNombreDocente(servicioEducativoDto.getNombreDocente());
		servicioEducativo.setEscolaridad(servicioEducativoDto.getEscolaridad());
		servicioEducativo.setPuntaje(servicioEducativoDto.getPuntaje());
		servicioEducativo.setEscalafon(servicioEducativoDto.getEscalafon());
		servicioEducativo.setValorPunto(servicioEducativoDto.getValorPunto());
		servicioEducativo.setValorHora(servicioEducativoDto.getValorHora());
		servicioEducativo.setCantidadHora(servicioEducativoDto.getCantidadHora());
		servicioEducativo.setValorTotal(servicioEducativoDto.getValorTotal());
		servicioEducativo.setItemGasto(itemGasto);
		return servicioEducativo;
	}

	public static CoordinacionOfertaAcademicaDto entityToDto(CoordinacionOfertaAcademica coordinacionOfertaAcademica) {
		if (coordinacionOfertaAcademica == null) {
			return null;
		}
		return CoordinacionOfertaAcademicaDto.builder()
				.id(coordinacionOfertaAcademica.getId())
				.nombre(coordinacionOfertaAcademica.getNombre())
				.perfil(coordinacionOfertaAcademica.getPerfil())
				.idoneidadCompetencia(coordinacionOfertaAcademica.getIdoneidadCompetencia())
				.valorTotal(coordinacionOfertaAcademica.getValorTotal())
				.build();
	}

	public static CoordinacionOfertaAcademica dtoToEntity(CoordinacionOfertaAcademicaDto coordinacionOfertaAcademicaDto, ItemGasto itemGasto) {
		if (coordinacionOfertaAcademicaDto == null) {
			return null;
		}
		CoordinacionOfertaAcademica coordinacionOfertaAcademica = new CoordinacionOfertaAcademica();
		coordinacionOfertaAcademica.setId(coordinacionOfertaAcademicaDto.getId());
		coordinacionOfertaAcademica.setNombre(coordinacionOfertaAcademicaDto.getNombre());
		coordinacionOfertaAcademica.setPerfil(coordinacionOfertaAcademicaDto.getPerfil());
		coordinacionOfertaAcademica.setIdoneidadCompetencia(coordinacionOfertaAcademicaDto.getIdoneidadCompetencia());
		coordinacionOfertaAcademica.setValorTotal(coordinacionOfertaAcademicaDto.getValorTotal());
		coordinacionOfertaAcademica.setItemGasto(itemGasto);
		return coordinacionOfertaAcademica;
	}

	public static ApoyoLogisticoDto entityToDto(ApoyoLogistico apoyoLogistico) {
		if (apoyoLogistico == null) {
			return null;
		}
		return ApoyoLogisticoDto.builder()
				.id(apoyoLogistico.getId())
				.numero(apoyoLogistico.getNumero())
				.actividad(apoyoLogistico.getActividad())
				.valorTotal(apoyoLogistico.getValorTotal())
				.build();
	}

	public static ApoyoLogistico dtoToEntity(ApoyoLogisticoDto apoyoLogisticoDto, ItemGasto itemGasto) {
		if (apoyoLogisticoDto == null) {
			return null;
		}
		ApoyoLogistico apoyoLogistico = new ApoyoLogistico();
		apoyoLogistico.setId(apoyoLogisticoDto.getId());
		apoyoLogistico.setNumero(apoyoLogisticoDto.getNumero());
		apoyoLogistico.setActividad(apoyoLogisticoDto.getActividad());
		apoyoLogistico.setValorTotal(apoyoLogisticoDto.getValorTotal());
		apoyoLogistico.setItemGasto(itemGasto);
		return apoyoLogistico;
	}

	public static MaterialSuministroDto entityToDto(MaterialSuministro materialSuministro) {
		if (materialSuministro == null) {
			return null;
		}
		return MaterialSuministroDto.builder()
				.id(materialSuministro.getId())
				.numero(materialSuministro.getNumero())
				.bienServicio(materialSuministro.getBienServicio())
				.cantidad(materialSuministro.getCantidad())
				.valorUnitario(materialSuministro.getValorUnitario())
				.valorTotal(materialSuministro.getValorTotal())
				.build();
	}

	public static MaterialSuministro dtoToEntity(MaterialSuministroDto materialSuministroDto, ItemGasto itemGasto) {
		if (materialSuministroDto == null) {
			return null;
		}
		MaterialSuministro materialSuministro = new MaterialSuministro();
		materialSuministro.setId(materialSuministroDto.getId());
		materialSuministro.setNumero(materialSuministroDto.getNumero());
		materialSuministro.setBienServicio(materialSuministroDto.getBienServicio());
		materialSuministro.setCantidad(materialSuministroDto.getCantidad());
		materialSuministro.setValorUnitario(materialSuministroDto.getValorUnitario());
		materialSuministro.setValorTotal(materialSuministroDto.getValorTotal());
		materialSuministro.setItemGasto(itemGasto);
		return materialSuministro;
	}
	
	public static ImpresoPublicacionDto entityToDto(ImpresoPublicacion impresoPublicacion) {
		if (impresoPublicacion == null) {
			return null;
		}
		return ImpresoPublicacionDto.builder()
				.id(impresoPublicacion.getId())
				.numero(impresoPublicacion.getNumero())
				.publicacion(impresoPublicacion.getPublicacion())
				.cantidad(impresoPublicacion.getCantidad())
				.valorUnitario(impresoPublicacion.getValorUnitario())
				.valorTotal(impresoPublicacion.getValorTotal())
				.build();
	}

	public static ImpresoPublicacion dtoToEntity(ImpresoPublicacionDto impresoPublicacionDto, ItemGasto itemGasto) {
		if (impresoPublicacionDto == null) {
			return null;
		}
		ImpresoPublicacion impresoPublicacion = new ImpresoPublicacion();
		impresoPublicacion.setId(impresoPublicacionDto.getId());
		impresoPublicacion.setNumero(impresoPublicacionDto.getNumero());
		impresoPublicacion.setPublicacion(impresoPublicacionDto.getPublicacion());
		impresoPublicacion.setCantidad(impresoPublicacionDto.getCantidad());
		impresoPublicacion.setValorUnitario(impresoPublicacionDto.getValorUnitario());
		impresoPublicacion.setValorTotal(impresoPublicacionDto.getValorTotal());
		impresoPublicacion.setItemGasto(itemGasto);
		return impresoPublicacion;
	}

	public static AlquilerAulaDto entityToDto(AlquilerAula alquilerAula) {
		if (alquilerAula == null) {
			return null;
		}
		return AlquilerAulaDto.builder()
				.id(alquilerAula.getId())
				.numero(alquilerAula.getNumero())
				.dependenciaPrestaServicio(alquilerAula.getDependenciaPrestaServicio())
				.cantidad(alquilerAula.getCantidad())
				.valorUnitario(alquilerAula.getValorUnitario())
				.valorTotal(alquilerAula.getValorTotal())
				.build();
	}

	public static AlquilerAula dtoToEntity(AlquilerAulaDto alquilerAulaDto, ItemGasto itemGasto) {
		if (alquilerAulaDto == null) {
			return null;
		}
		AlquilerAula alquilerAula = new AlquilerAula();
		alquilerAula.setId(alquilerAulaDto.getId());
		alquilerAula.setNumero(alquilerAulaDto.getNumero());
		alquilerAula.setDependenciaPrestaServicio(alquilerAulaDto.getDependenciaPrestaServicio());
		alquilerAula.setCantidad(alquilerAulaDto.getCantidad());
		alquilerAula.setValorUnitario(alquilerAulaDto.getValorUnitario());
		alquilerAula.setValorTotal(alquilerAulaDto.getValorTotal());
		alquilerAula.setItemGasto(itemGasto);
		return alquilerAula;
	}

	public static OtroAnexoDto entityToDto(OtroAnexo otroAnexo) {
		if (otroAnexo == null) {
			return null;
		}
		return OtroAnexoDto.builder()
				.id(otroAnexo.getId())
				.numero(otroAnexo.getNumero())
				.descripcion(otroAnexo.getDescripcion())
				.valorTotal(otroAnexo.getValorTotal())
				.build();
	}

	public static OtroAnexo dtoToEntity(OtroAnexoDto otroAnexoDto, ItemGasto itemGasto) {
		if (otroAnexoDto == null) {
			return null;
		}
		OtroAnexo otroAnexo = new OtroAnexo();
		otroAnexo.setId(otroAnexoDto.getId());
		otroAnexo.setNumero(otroAnexoDto.getNumero());
		otroAnexo.setDescripcion(otroAnexoDto.getDescripcion());
		otroAnexo.setValorTotal(otroAnexoDto.getValorTotal());
		otroAnexo.setItemGasto(itemGasto);
		return otroAnexo;
	}
}
