package com.myapp.dao;

import com.myapp.domain.Inmueble;
import java.util.ArrayList;


public interface AddressDAO {
	
	ArrayList getAddressList() throws Exception;
	Inmueble getAddress(int p_id) throws Exception;
	int addAddress(Inmueble p_inmueble) throws Exception;
	void editAddress(Inmueble p_inmueble) throws Exception;
	void removeAddress(int p_id) throws Exception;

}
