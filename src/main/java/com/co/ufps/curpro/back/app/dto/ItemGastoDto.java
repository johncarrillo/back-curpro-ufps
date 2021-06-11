package com.co.ufps.curpro.back.app.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemGastoDto {

	private Long id;

	private String nombre;

	private Integer consecutivo;

	private Double valor;

	private List<ItemGastoDto> itemsGasto;

	private List<ServicioEducativoDto> serviciosEducativos;

	private List<CoordinacionOfertaAcademicaDto> coordinacionOfertasAcademicas;

	private List<ApoyoLogisticoDto> apoyosLogisticos;

	private List<MaterialSuministroDto> materialesSuministro;

	private List<ImpresoPublicacionDto> impresosPublicaciones;

	private List<AlquilerAulaDto> alquilerAulas;

	private List<OtroAnexoDto> otrosAnexos;
}
