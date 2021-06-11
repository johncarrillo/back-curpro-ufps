package com.co.ufps.curpro.back.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.TipoActividad;
import com.co.ufps.curpro.back.app.models.entity.TipoCurso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="actividad")
public class Actividad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="acti_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="acti_nombre")
	private String nombre;

	@Column(name="acti_descripcion")
	private String descripcion;

	@Column(name="acti_fecha_limite")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaLimite;

	@Column(name="acti_fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-dd-MM")
	private Date fechaCreacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mocu_id")
	private ModuloCurso moduloCurso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tiac_id")
	private TipoActividad tipoActividad;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="acti_id")
	public List<ActividadEstudiante> actividadesEstudiante;

	public Actividad () {
		actividadesEstudiante = new ArrayList<ActividadEstudiante>();
	}
}
