package com.co.ufps.curpro.back.app.view.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.MatriculaPdfDto;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("matricula/pdf")
public class MatriculaPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<MatriculaPdfDto> matriculas = (ArrayList<MatriculaPdfDto>) model.get("matriculas");
		
		PdfPTable titulo = new PdfPTable(1);
		titulo.addCell("Listado de Estudiantes Matriculados");
		titulo.setSpacingAfter(20);
		
		PdfPTable informacion = new PdfPTable(4);
		informacion.addCell("Codigo");
		informacion.addCell("Nombre Completo");
		informacion.addCell("Telefono");
		informacion.addCell("Estado");
		
		matriculas.forEach(matricula -> {
			informacion.addCell(matricula.getEstudianteInfo().getCodigo());
			informacion.addCell(matricula.getNombreCompleto());
			informacion.addCell(matricula.getEstudianteInfo().getTelefonoPersonal());
			informacion.addCell(matricula.getEstadoMatricula().getNombre());
		});
		
		document.add(titulo);
		document.add(informacion);
	}

}
