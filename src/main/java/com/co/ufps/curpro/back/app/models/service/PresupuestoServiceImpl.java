package com.co.ufps.curpro.back.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ufps.curpro.back.app.dto.PresupuestoDto;
import com.co.ufps.curpro.back.app.models.dao.IPresupuestoDao;
import com.co.ufps.curpro.back.app.models.entity.Presupuesto;
import com.co.ufps.curpro.back.app.models.parser.PresupuestoConverter;

@Service
public class PresupuestoServiceImpl implements IPresupuestoService {

	@Autowired
	private IPresupuestoDao presupuestoDao;

	@Override
	public List<PresupuestoDto> findAll() {
		List<PresupuestoDto> listaPresupuestoDto = new ArrayList<PresupuestoDto>();
		presupuestoDao.findAll().forEach(presupuesto->{
			listaPresupuestoDto.add(PresupuestoConverter.entityToDto(presupuesto));
		});
		return listaPresupuestoDto;
	}

	@Override
	public PresupuestoDto findOne(Long idPresupuesto) {
		return PresupuestoConverter.entityToDto(this.presupuestoDao.findById(idPresupuesto).orElse(null));
	}

	@Override
	public Presupuesto save(PresupuestoDto presupuestoDto) {
		return this.presupuestoDao.save(PresupuestoConverter.dtoToEntity(presupuestoDto));
	}

	@Override
	public Presupuesto update(PresupuestoDto presupuestoDto) {
		
		return this.presupuestoDao.save(PresupuestoConverter.dtoToEntity(presupuestoDto));
	}

}
