package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="estudiante_info")
public class EstudianteInfo implements Serializable{

	@Id
	@Column(name="esin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="esin_codigo")
	private String codigo;

	@Column(name="esin_cedula")
	private String cedula;

	@Column(name="esin_direccion")
	private String direccion;

	@Column(name="esin_telefono_personal")
	private String telefonoPersonal;

	@Column(name="esin_telefono_familiar")
	private String telefonoFamiliar;

	@OneToOne
    @JoinColumn(name = "rous_id")
	private RolUsuario rolUsuario;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="esin_id")
	public List<Matricula> matriculas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="esin_id")
	public List<Inscripcion> inscripciones;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="esin_id")
	public List<ActividadEstudiante> actividadesEstudiante;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="esin_id")
	public List<ModuloEstudiante> modulosEstudiante;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="esin_id")
	public List<Sustentacion> sustentacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="esin_id")
	public List<CierreCurso> cierreCurso;

	public EstudianteInfo () {
		matriculas = new ArrayList<Matricula>();
		inscripciones = new ArrayList<Inscripcion>();
		actividadesEstudiante = new ArrayList<ActividadEstudiante>();
		sustentacion = new ArrayList<Sustentacion>();
		cierreCurso = new ArrayList<CierreCurso>();
	}
}
