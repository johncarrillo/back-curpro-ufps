package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="coordinacion_oferta_academica")
public class CoordinacionOfertaAcademica implements Serializable, Comparable<CoordinacionOfertaAcademica>{

	@Id
	@Column(name="cofa_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="cofa_nombre")
	private String nombre;

	@Column(name="cofa_perfil")
	private String perfil;

	@Column(name="cofa_idoneidad_competencia")
	private String idoneidadCompetencia;

	@Column(name="cofa_valor_total")
	private Double valorTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itga_id")
	private ItemGasto itemGasto;

	@Override
	public int compareTo(CoordinacionOfertaAcademica o) {
		if (id < o.id) {
            return -1;
        }
        if (id > o.id) {
            return 1;
        }
        return 0;
	}
}
