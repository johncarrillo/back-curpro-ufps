package com.co.ufps.curpro.back.app.view.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.co.ufps.curpro.back.app.dto.InscripcionPdfDto;
import com.co.ufps.curpro.back.app.dto.MatriculaDto;
import com.co.ufps.curpro.back.app.dto.MatriculaPdfDto;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("inscripcion/pdf")
public class InscripcionPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<InscripcionPdfDto> inscripciones = (ArrayList<InscripcionPdfDto>) model.get("inscripcion");
		
		PdfPTable titulo = new PdfPTable(1);
		titulo.addCell("Listado de Estudiantes Inscriptos");
		titulo.setSpacingAfter(20);
		
		PdfPTable informacion = new PdfPTable(4);
		informacion.addCell("Codigo");
		informacion.addCell("Nombre Completo");
		informacion.addCell("Telefono");
		
		inscripciones.forEach(inscripto -> {
			informacion.addCell(inscripto.getEstudianteInfo().getCodigo());
			informacion.addCell(inscripto.getNombreCompleto());
			informacion.addCell(inscripto.getEstudianteInfo().getTelefonoPersonal());
		});
		
		document.add(titulo);
		document.add(informacion);
	}

}
