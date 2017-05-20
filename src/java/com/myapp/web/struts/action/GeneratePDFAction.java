package com.myapp.web.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.myapp.dao.AddressDAO;
import com.myapp.dao.AddressDAOImpl;
import com.myapp.domain.Inmueble;


public class GeneratePDFAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Traigo la lista de inmuebles.
		AddressDAO addressDAO;
		addressDAO = new AddressDAOImpl();
		ArrayList inmuebles = addressDAO.getAddressList();
		
		// step 1: creation of a document-object
		Document document = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, baos);
			document.setPageSize(PageSize.A4);
			document.setMargins(72, 36, 84, 84);
			
			// step 3: we open the document
			document.open();
			
			// step 4: we add a paragraph to the document
			Paragraph paragraph = new Paragraph("LISTADO DE INMUEBLES");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(new Paragraph(" "));
			Iterator it = inmuebles.iterator();
			int i = 1;
			while(it.hasNext()){
				Inmueble inmueble = (Inmueble) it.next();
				String cadena = new String(new Integer(i).toString() + ". ");
				cadena += inmueble.getStreet() + " " + inmueble.getNumber() + ", ";
				cadena += "piso " + inmueble.getFloor() + ", ";
				if(inmueble.getPrice() > 0){
					cadena += "precio U$S " + inmueble.getPrice() + ", ";
				}
				if(inmueble.getArea() > 0){
					cadena += inmueble.getArea() + " m2, ";
				}
				if(inmueble.isCredit_readyness()){
					cadena += "apto crédito, ";
				}
				if(inmueble.getBedrooms() == 1){
					cadena += inmueble.getBedrooms() + " dormitorio, ";
				}else if(inmueble.getBedrooms() > 1){
					cadena += inmueble.getBedrooms() + " dormitorios, ";
				}
				cadena += "teléfonos: " + inmueble.getTelephone1() + ", ";
				if(inmueble.getTelephone2() != null && !inmueble.getTelephone2().equals("")){
					cadena += inmueble.getTelephone2() + ", ";
				}
				if(inmueble.getTelephone3() != null && !inmueble.getTelephone3().equals("")){
					cadena += inmueble.getTelephone3() + ", ";
				}
				cadena += inmueble.getComments() + " VALORACION: " + inmueble.getStars() + " estrellas.";
				Paragraph p = new Paragraph(cadena);
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				document.add(new Paragraph(" "));
				i++;
			}
			
			// step 5: we close the document
			document.close();
			
			// C�mo lo muestro dentro del browser?
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			baos.writeTo(response.getOutputStream());
			response.getOutputStream().flush();
			
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
		return mapping.findForward("success");
	}

}
