package com.myapp.web.struts.action;

import com.myapp.dao.AddressDAO;
import com.myapp.dao.AddressDAOImpl;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AddressListAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Recupero de la BD la lista de inmuebles para mostrarlos por pantalla.
		AddressDAO addressDAO = new AddressDAOImpl();
		ArrayList inmuebles = addressDAO.getAddressList();
		
		request.setAttribute("inmuebles", inmuebles);
		
		return mapping.findForward("success");
		
	}

}
