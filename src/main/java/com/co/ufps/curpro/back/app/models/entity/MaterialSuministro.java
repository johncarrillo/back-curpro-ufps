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
@Table(name="material_suministro")
public class MaterialSuministro implements Serializable,Comparable<MaterialSuministro>{

	@Id
	@Column(name="masu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="masu_numero")
	private Integer numero;

	@Column(name="masu_bien_servicio")
	private String bienServicio;

	@Column(name="masu_cantidad")
	private Integer cantidad;

	@Column(name="masu_valor_unitario")
	private Double valorUnitario;

	@Column(name="masu_valor_total")
	private Double valorTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itga_id")
	private ItemGasto itemGasto;

	@Override
	public int compareTo(MaterialSuministro o) {
		if (numero < o.numero) {
            return -1;
        }
        if (numero > o.numero) {
            return 1;
        }
        return 0;
	}
}
