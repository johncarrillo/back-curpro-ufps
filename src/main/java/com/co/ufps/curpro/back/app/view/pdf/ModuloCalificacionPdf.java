package com.co.ufps.curpro.back.app.view.pdf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.co.ufps.curpro.back.app.dto.ActividadDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.MatriculaPdfDto;
import com.co.ufps.curpro.back.app.dto.NotasActividadesPorModuloDto;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("modulo/calificacion/pdf")
public class ModuloCalificacionPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		NotasActividadesPorModuloDto calificaciones = (NotasActividadesPorModuloDto) model.get("calificaciones");
		DecimalFormat df = new DecimalFormat("###.##");

		PdfPTable titulo = new PdfPTable(1);
		titulo.addCell("Listado de Calificaciones Modulo " + calificaciones.getModuloCursoDto().getNombre());
		titulo.setSpacingAfter(20);
		
		PdfPTable informacion = new PdfPTable(2 + calificaciones.getActividadesInfo().size() + 1);
		informacion.addCell("Codigo");
		informacion.addCell("Nombre Completo");
		calificaciones.getActividadesInfo().forEach(actividad->{
			informacion.addCell(actividad.getNombre());
		});
		informacion.addCell("Ponderado");
		
		calificaciones.getListaNotasActividades().forEach(notas -> {
			informacion.addCell(notas.getEstudianteInfo().getCodigo());
			informacion.addCell(notas.getEstudianteInfo().getNombreCompleto());
			Double ponderado = 0.0;
			Integer contador = 0;
			for (ActividadDto actividadDto: calificaciones.getActividadesInfo()) {
				Double nota = notas.getCalificacion(actividadDto.getId());
				nota = nota == null ? 0 : nota; 
				contador++;
				ponderado += nota;
				informacion.addCell(nota == 0 ? "N/A" : nota + "");
			}
			if (contador == 0) {
				informacion.addCell("0");
			} else {
				informacion.addCell(df.format((ponderado/contador)*0.70) + "");
			}
		});
		
		document.add(titulo);
		document.add(informacion);
	}

}
