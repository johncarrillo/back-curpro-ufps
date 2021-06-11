package com.co.ufps.curpro.back.app.models.service;

import java.util.List;

import com.co.ufps.curpro.back.app.dto.PresupuestoDto;
import com.co.ufps.curpro.back.app.models.entity.Presupuesto;

public interface IPresupuestoService {

	public List<PresupuestoDto> findAll();

	public PresupuestoDto findOne(Long idPresupuesto);

	public Presupuesto save(PresupuestoDto presupuestoDto);

	public Presupuesto update(PresupuestoDto presupuestoDto);
}
