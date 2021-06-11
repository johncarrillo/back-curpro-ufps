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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="matricula")
public class Matricula implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="matr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="matr_numero_recibo_banco")
	private String numeroReciboBanco;

	@Column(name="matr_referencia_banco")
	private String referenciaBanco;

	@Column(name="matr_costo")
	private Double costo;

	@Column(name="matr_soporte_banco")
	private String soporteBanco;

	@Column(name="matr_fecha_retiro")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaRetiro;

	@Column(name="matr_justificacion_retiro")
	private String justificacionRetiro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esin_id")
	private EstudianteInfo estudianteInformacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="curs_id")
	private Curso curso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esma_id")
	private EstadoMatricula estadoMatricula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="banc_id")
	private Banco banco;
}
