package com.myapp.web.struts.action;

import com.myapp.dao.AddressDAO;
import com.myapp.dao.AddressDAOImpl;
import com.myapp.domain.Inmueble;
import com.myapp.web.struts.form.InmuebleActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class EditAddressAction extends Action{
	
    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Inmueble inmueble = null;
		InmuebleActionForm inmuebleActionForm = new InmuebleActionForm();
		
		// Recuperamos el par�metro para saber si se trata de un inmueble nuevo o de uno existente.
		int id = 0;
		if (request.getParameter("id") != null &&
				new Integer(request.getParameter("id")).intValue() != 0) {
			id = new Integer(request.getParameter("id")).intValue();
		} else if(request.getSession().getAttribute("id") != null){
			id = new Integer((String)request.getSession().getAttribute("id")).intValue();
		}
		
		if (id != 0){
			// Si es uno existente, lo traemos de la base de datos.
			AddressDAO addressDAO = new AddressDAOImpl();
			inmueble = addressDAO.getAddress(id);
			BeanUtils.copyProperties(inmuebleActionForm, inmueble);
		}
		
		// Cargamos el formulario como corresponda.
		request.setAttribute("inmuebleActionForm", inmuebleActionForm);
		
		// Borramos de sesi�n la variable "id".
		request.getSession().removeAttribute("id");
		
		return mapping.findForward("success");
	}

}
