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
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tipo_curso")
public class TipoCurso implements Serializable {

	public TipoCurso() {
		this.cursos = new ArrayList<Curso>();
	}

	@Id
	@Column(name="ticu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="ticu_nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="ticu_id")
	private List<Curso> cursos;

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}
}
