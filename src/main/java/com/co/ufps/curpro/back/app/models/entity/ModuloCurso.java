package com.co.ufps.curpro.back.app.models.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "modulo_curso")
@Getter
@Setter
@AllArgsConstructor
public class ModuloCurso {

	@Id
	@Column(name="mocu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="mocu_nombre")
	private String nombre;

	@Column(name="mocu_horas_clase")
	private Integer horasClase;

	@Column(name="mocu_horas_independientes")
	private Integer horasIndependientes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="docente_id")
	private RolUsuario rolUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="curs_id")
	private Curso curso;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="mocu_id")
	public List<Actividad> actividades;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="mocu_id")
	public List<ModuloEstudiante> modulosEstudiante;

	@Column(name="curs_evaluacion")
	private String evaluacion;

	public ModuloCurso() {
		actividades = new ArrayList<Actividad>();
	}
}
