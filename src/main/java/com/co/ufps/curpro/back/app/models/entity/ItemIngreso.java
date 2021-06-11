package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="item_ingreso")
public class ItemIngreso implements Serializable, Comparable<ItemIngreso> {

	public ItemIngreso () {
		this.itemIngreso = new ArrayList<ItemIngreso>();
	}

	@Id
	@Column(name="itin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="itin_nombre")
	private String nombre;

	@Column(name="itin_consecutivo")
	private Integer consecutivo;

	@Column(name="itin_valor")
	private Double valor;

	@OneToOne(mappedBy = "itemIngreso", cascade = CascadeType.ALL)
	private Presupuesto presupuesto;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itin_id")
	private List<InscripcionMatricula> inscripcionMatriculas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itin_padre")
	private List<ItemIngreso> itemIngreso;

	public void addItemIngreso(ItemIngreso itemIngreso) {
		this.itemIngreso.add(itemIngreso);
	}

	public void addInscripcionMatricula(InscripcionMatricula inscripcionMatricula) {
		this.inscripcionMatriculas.add(inscripcionMatricula);
	}

	@Override
	public int compareTo(ItemIngreso o) {
		if (consecutivo < o.consecutivo) {
            return -1;
        }
        if (consecutivo > o.consecutivo) {
            return 1;
        }
        return 0;
	}

}
