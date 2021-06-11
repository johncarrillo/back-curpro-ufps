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
@Table(name="alquiler_aula")
public class AlquilerAula implements Serializable, Comparable<AlquilerAula>{

	@Id
	@Column(name="alau_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="alau_numero")
	private Integer numero;

	@Column(name="alau_dependencia_presta_servicio")
	private String dependenciaPrestaServicio;

	@Column(name="alau_cantidad")
	private Integer cantidad;

	@Column(name="alau_valor_unitario")
	private Double valorUnitario;

	@Column(name="alau_valor_total")
	private Double valorTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itga_id")
	private ItemGasto itemGasto;
	
	@Override
	public int compareTo(AlquilerAula o) {
		if (numero < o.numero) {
            return -1;
        }
        if (numero	 > o.numero) {
            return 1;
        }
        return 0;
	}
}
