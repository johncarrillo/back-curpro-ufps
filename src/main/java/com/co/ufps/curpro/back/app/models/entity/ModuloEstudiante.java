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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "modulo_estudiante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuloEstudiante {

	@Id
	@Column(name="moes_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="moes_nota_final")
	private Double notaFinal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esin_id")
	private EstudianteInfo estudianteInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mocu_id")
	private ModuloCurso moduloCurso;

}
