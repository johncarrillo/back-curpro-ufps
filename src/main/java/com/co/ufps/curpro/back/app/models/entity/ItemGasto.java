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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="item_gasto")
public class ItemGasto implements Serializable, Comparable<ItemGasto> {

	public ItemGasto () {
		this.itemGastos = new ArrayList<ItemGasto>();
		this.serviciosEducativos = new ArrayList<ServicioEducativo>();
		this.coordinacionOfertasAcademicas = new ArrayList<CoordinacionOfertaAcademica>();
		this.apoyosLogisticos = new ArrayList<ApoyoLogistico>();
		this.materialesSuministro = new ArrayList<MaterialSuministro>();
		this.impresosPublicaciones = new ArrayList<ImpresoPublicacion>();
		this.alquileresAulas = new ArrayList<AlquilerAula>();
		this.otrosAnexos = new ArrayList<OtroAnexo>();
	}

	@Id
	@Column(name="itga_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="itga_nombre")
	private String nombre;

	@Column(name="itga_consecutivo")
	private Integer consecutivo;

	@Column(name="itga_valor")
	private Double valor;

	@OneToOne(mappedBy = "itemGasto", cascade = CascadeType.ALL)
	private Presupuesto presupuesto;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<ServicioEducativo> serviciosEducativos;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<CoordinacionOfertaAcademica> coordinacionOfertasAcademicas;


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<ApoyoLogistico> apoyosLogisticos;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<MaterialSuministro> materialesSuministro;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<ImpresoPublicacion> impresosPublicaciones;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<AlquilerAula> alquileresAulas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_id")
	private List<OtroAnexo> otrosAnexos;


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="itga_padre")
	private List<ItemGasto> itemGastos;

	public void addItemGasto(ItemGasto itemGasto) {
		this.itemGastos.add(itemGasto);
	}

	public void addServicioEducativo(ServicioEducativo servicioEducativo) {
		this.serviciosEducativos.add(servicioEducativo);
	}

	public void addCoordinacionOfertaAcademica(CoordinacionOfertaAcademica coordinacionOfertaAcademica) {
		this.coordinacionOfertasAcademicas.add(coordinacionOfertaAcademica);
	}

	public void addApoyoLogistico(ApoyoLogistico apoyoLogistico) {
		this.apoyosLogisticos.add(apoyoLogistico);
	}

	public void addMaterialSuministro(MaterialSuministro materialSuministro) {
		this.materialesSuministro.add(materialSuministro);
	}

	public void addImpresoPublicacion(ImpresoPublicacion impresoPublicacion) {
		this.impresosPublicaciones.add(impresoPublicacion);
	}

	public void addAlquilerAula(AlquilerAula alquilerAula) {
		this.alquileresAulas.add(alquilerAula);
	}

	public void addOtroAnexo(OtroAnexo otroAnexo) {
		this.otrosAnexos.add(otroAnexo);
	}

	@Override
	public int compareTo(ItemGasto o) {
		if (consecutivo < o.consecutivo) {
            return -1;
        }
        if (consecutivo > o.consecutivo) {
            return 1;
        }
        return 0;
	}
}
