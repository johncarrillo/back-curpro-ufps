package com.co.ufps.curpro.back.app.dto;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Data
public class EvaluacionModuloCursoDto implements Serializable{
	private Long idModuloCurso;
	private String linkEvaluacion;
}
