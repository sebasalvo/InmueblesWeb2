package com.myapp.web.struts.action;

import com.myapp.dao.AddressDAO;
import com.myapp.dao.AddressDAOImpl;
import com.myapp.domain.Inmueble;
import com.myapp.web.struts.form.InmuebleActionForm;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;



public class SaveAddressAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		InmuebleActionForm inmuebleActionForm = (InmuebleActionForm) form;
		Inmueble inmueble = new Inmueble();
		
		BeanUtils.copyProperties(inmueble, inmuebleActionForm);
		
		AddressDAO addressDAO = new AddressDAOImpl();
		
		int id;
		Date now = new Date();
		
		// Me fijo si se trata de uno nuevo o uno existente.
		if (inmuebleActionForm.getId() == 0) {
			// Se trata de uno nuevo.
			inmueble.setCreateDate(now);
			inmueble.setLastUpdateDate(now);
			id = addressDAO.addAddress(inmueble);
		} else {
			// Se trata de uno existente.
			inmueble.setLastUpdateDate(now);
			addressDAO.editAddress(inmueble);
			id = inmueble.getId();
		}
		
		// Lo guard� en sesi�n por ahora porque no se me ocurre otra forma mejor de hacerlo.
		request.getSession().setAttribute("id", new String(new Integer(id).toString()));
		
		// Mensaje a mostrar indicando que los datos fueron guardados.
		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage("mensaje.datos.guardados");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request,messages);
		
		return mapping.findForward("success");
	}
	
}
