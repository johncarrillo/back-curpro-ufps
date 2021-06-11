package com.co.ufps.curpro.back.app.models.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sustentacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sustentacion {

	@Id
	@Column(name="sust_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esin_id")
	private EstudianteInfo estudianteInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="curs_id")
	private Curso curso;

	@Column(name="sust_calificacion")
	private Double calificacion;
}
