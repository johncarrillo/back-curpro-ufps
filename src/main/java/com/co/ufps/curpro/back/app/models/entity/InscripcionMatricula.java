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
@Table(name="inscripcion_matricula")
public class InscripcionMatricula implements Serializable, Comparable<InscripcionMatricula> {

	@Id
	@Column(name="inma_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="inma_tipo_participante")
	private String tipoParticiante;

	@Column(name="inma_cantidad")
	private Integer cantidad;

	@Column(name="inma_valor_unitario")
	private Double valorUnitario;

	@Column(name="inma_valor_total")
	private Double valorTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itin_id")
	private ItemIngreso itemIngreso;

	@Override
	public int compareTo(InscripcionMatricula o) {
		if (id < o.id) {
            return -1;
        }
        if (id > o.id) {
            return 1;
        }
        return 0;
	}
	
}
