package com.myapp.web.struts.action;

import com.myapp.dao.AddressDAO;
import com.myapp.dao.AddressDAOImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoveAddressAction extends Action{
	
        @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = new Integer(request.getParameter("id")).intValue();
		
		AddressDAO addressDAO = new AddressDAOImpl();
		addressDAO.removeAddress(id);
		
		return mapping.findForward("success");
	}

}
