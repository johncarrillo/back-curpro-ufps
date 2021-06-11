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
@Table(name="servicio_educativo")
public class ServicioEducativo implements Serializable, Comparable<ServicioEducativo>{

	@Id
	@Column(name="seed_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="seed_nombre_docente")
	private String nombreDocente;

	@Column(name="seed_escolaridad")
	private String escolaridad;

	@Column(name="seed_escalafon")
	private String escalafon;

	@Column(name="seed_puntaje")
	private Double puntaje;

	@Column(name="seed_valor_punto")
	private Double valorPunto;

	@Column(name="seed_valor_hora")
	private Double valorHora;

	@Column(name="seed_cantidad_hora")
	private Integer cantidadHora;

	@Column(name="seed_valor_total")
	private Double valorTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itga_id")
	private ItemGasto itemGasto;

	@Override
	public int compareTo(ServicioEducativo o) {
		if (id < o.id) {
            return -1;
        }
        if (id > o.id) {
            return 1;
        }
        return 0;
	}
}
