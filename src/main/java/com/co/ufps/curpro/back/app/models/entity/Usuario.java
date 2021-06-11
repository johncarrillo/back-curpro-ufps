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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.co.ufps.curpro.back.app.dto.RolUsuarioDto;
import com.co.ufps.curpro.back.app.dto.UsuarioDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{

	@Id
	@Column(name="usua_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="usua_correo")
	private String correo;

	@Column(name="usua_contrasena")
	private String contrasena;

	@Column(name="usua_nombre")
	private String nombres;

	@Column(name="usua_apellido")
	private String apellidos;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="usua_id")
	private List<RolUsuario> rolUsuarios;

	public Usuario(String correo, String contrasena) {
		this.rolUsuarios = new ArrayList<RolUsuario>();
		this.correo = correo;
		this.contrasena = contrasena;
	}

	public Usuario() {
		this.rolUsuarios = new ArrayList<RolUsuario>();
	}

	public void addRolUsuarios(RolUsuario rolUsuario) {
		this.rolUsuarios.add(rolUsuario);
	}


}
