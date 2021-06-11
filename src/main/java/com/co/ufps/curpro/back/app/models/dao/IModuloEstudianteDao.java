package com.co.ufps.curpro.back.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.co.ufps.curpro.back.app.models.entity.Curso;
import com.co.ufps.curpro.back.app.models.entity.ModuloCurso;
import com.co.ufps.curpro.back.app.models.entity.ModuloEstudiante;

public interface IModuloEstudianteDao extends CrudRepository<ModuloEstudiante, Long>{

}
