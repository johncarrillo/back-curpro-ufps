package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="inscripcion")
public class Inscripcion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="insc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="insc_numero_recibo_banco")
	private String numeroReciboBanco;

	@Column(name="insc_referencia_banco")
	private String referenciaBanco;

	@Column(name="insc_costo")
	private Double costo;

	@Column(name="insc_soporte_banco")
	private String soporteBanco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esin_id")
	private EstudianteInfo estudianteInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="curs_id")
	private Curso curso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="banc_id")
	private Banco banco;
}
