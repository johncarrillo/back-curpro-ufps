package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="presupuesto")
public class Presupuesto implements Serializable {

	@Id
	@Column(name="pres_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="pres_valor_ingreso")
	private Double valorIngreso;

	@Column(name="pres_valor_gasto")
	private Double valorGasto;

	@Column(name="pres_valor_utilidad")
	private Double valorUtilidad;

	@Column(name="pres_fondo_investigacion_universitario")
	private Double fondoInvestigacionUniversitario;

	@Column(name="pres_utilidad_neta")
	private Double utilidadNeta;

	@OneToOne(mappedBy = "presupuesto", cascade = CascadeType.ALL)
	private Curso curso;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itga_id")
	private ItemGasto itemGasto;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itin_id")
	private ItemIngreso itemIngreso;

}
