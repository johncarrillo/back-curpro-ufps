package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Rol;
import com.co.ufps.curpro.back.app.models.entity.RolUsuario;
import com.co.ufps.curpro.back.app.models.entity.Usuario;

public interface IRolUsuarioDao extends CrudRepository<RolUsuario, Long>{

	public List<RolUsuario> findAllByUsuario(Usuario usuario);

	public List<RolUsuario> findAllByRol(Rol rol);
}
