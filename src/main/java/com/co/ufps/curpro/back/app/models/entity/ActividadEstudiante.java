package com.co.ufps.curpro.back.app.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="actividad_estudiante")
public class ActividadEstudiante {

	@Id
	@Column(name="aces_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="aces_calificacion")
	private Double calificacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esin_id")
	private EstudianteInfo estudianteInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="acti_id")
	private Actividad actividad;
}
