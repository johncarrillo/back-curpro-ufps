package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="curso")
public class Curso implements Serializable {

	@Id
	@Column(name="curs_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="curs_nombre")
	private String nombre;

	@Column(name="curs_descripcion")
	private String descripcion;

	@Column(name="curs_imagen")
	private String imagen;

	@Column(name="curs_fecha_inicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaInicio;

	@Column(name="curs_fecha_fin")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaFin;

	@Column(name="curs_fecha_limite_pago")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaLimitePago;

	@Column(name="curs_fecha_limite_retiro")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaLimiteRetiro;

	@Column(name="curs_cantidad_horas")
	private Integer cantidadHoras;

	@Column(name="curs_justificacion_rechazo")
	private String justificacionRechazo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ticu_id")
	private TipoCurso tipoCurso;
	
	@Column(name="curs_ticu_otro")
	private String otroTipoCurso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="escu_id")
	private EstadoCurso estadoCurso;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pres_id")
    private Presupuesto presupuesto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="depe_id")
	private Dependencia dependencia;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="curs_id")
	private List<ModuloCurso> moduloCurso;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="curs_id")
	public List<Matricula> matriculas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="curs_id")
	public List<Inscripcion> inscripciones;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="curs_id")
	public List<Sustentacion> sustentaciones;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="curs_id")
	public List<CierreCurso> cierreCurso;

	public Curso() {
		moduloCurso = new ArrayList<ModuloCurso>();
		inscripciones = new ArrayList<Inscripcion>();
		matriculas = new ArrayList<Matricula>();
		sustentaciones = new ArrayList<Sustentacion>();
		cierreCurso = new ArrayList<CierreCurso>();
	}
}
