package com.myapp.dao;

import com.myapp.domain.Inmueble;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AddressDAOImpl implements AddressDAO {
	
	private static MySQLConnection mySQLConnection = new MySQLConnection();
	private Connection connection;

	public int addAddress(Inmueble p_inmueble) throws Exception {
		
		connection = mySQLConnection.getConnection();
		//Preparo la llamada al procedimiento.
		CallableStatement callableStatement = null;
		// Invoco al procedimiento almacenado.
		callableStatement = connection.prepareCall(	"{ call insert_address  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }" );
		// Seteo los par�metros del procedimiento.
		callableStatement.setString(1, p_inmueble.getStreet());
		callableStatement.setInt(2, p_inmueble.getNumber());
		callableStatement.setInt(3, p_inmueble.getFloor());
		callableStatement.setInt(4, p_inmueble.getPrice());
		callableStatement.setInt(5, p_inmueble.getArea());
		callableStatement.setBoolean(6, p_inmueble.isCredit_readyness());
		callableStatement.setString(7, p_inmueble.getTelephone1());
		callableStatement.setString(8, p_inmueble.getTelephone2());
		callableStatement.setString(9, p_inmueble.getTelephone3());
		callableStatement.setString(10, p_inmueble.getComments());
		callableStatement.setInt(11, p_inmueble.getStars());
		callableStatement.setInt(12, p_inmueble.getBedrooms());
		
		callableStatement.setDate(13,  new java.sql.Date(p_inmueble.getCreateDate().getTime()));
		callableStatement.setDate(14, new java.sql.Date(p_inmueble.getLastUpdateDate().getTime()));
		// Se ejecuta el procedimiento almacenado
		callableStatement.execute();
		callableStatement.close();
		
		// Como todav�a no manejamos bien el tema de los store procedures, traigo el ID en otra consulta.
		Statement statement = connection.createStatement();
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String query = "SELECT MAX(id) FROM ADDRESS";
		ResultSet resultSet = statement.executeQuery(query);
		int id = 0;
		while(resultSet.next()){
			id = resultSet.getInt(1);
		}

		// Cierro la conexi�n.
		connection.close();
		
		return id;
		
	}

	public void editAddress(Inmueble p_inmueble) throws Exception {
			
		connection = mySQLConnection.getConnection();
		//Preparo la llamada al procedimiento.
		CallableStatement callableStatement = null;
		// Invoco al procedimiento almacenado.
		callableStatement = connection.prepareCall(	"{ call update_address  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }" );
		// Seteo los par�metros del procedimiento.
		callableStatement.setInt(1, p_inmueble.getId());
		callableStatement.setString(2, p_inmueble.getStreet());
		callableStatement.setInt(3, p_inmueble.getNumber());
		callableStatement.setInt(4, p_inmueble.getFloor());
		callableStatement.setInt(5, p_inmueble.getPrice());
		callableStatement.setInt(6, p_inmueble.getArea());
		callableStatement.setBoolean(7, p_inmueble.isCredit_readyness());
		callableStatement.setString(8, p_inmueble.getTelephone1());
		callableStatement.setString(9, p_inmueble.getTelephone2());
		callableStatement.setString(10, p_inmueble.getTelephone3());
		callableStatement.setString(11, p_inmueble.getComments());
		callableStatement.setInt(12, p_inmueble.getStars());
		callableStatement.setInt(13, p_inmueble.getBedrooms());
		callableStatement.setDate(14, new java.sql.Date(p_inmueble.getLastUpdateDate().getTime()));
		// Se ejecuta el procedimiento almacenado
		callableStatement.execute();
		callableStatement.close();
		
		// Cierro la conexi�n.
		connection.close();
		
	}

	public Inmueble getAddress(int p_id) throws Exception {
		
		connection = mySQLConnection.getConnection();
		Statement statement = connection.createStatement();
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String query = "SELECT * FROM ADDRESS WHERE ID = " + p_id;
		ResultSet resultSet = statement.executeQuery(query);
		Inmueble inmueble = null;
		while(resultSet.next()){
			inmueble = new Inmueble();
			inmueble.setId(resultSet.getInt("id"));
			inmueble.setStreet(resultSet.getString("street"));
			inmueble.setNumber(resultSet.getInt("number"));
			inmueble.setFloor(resultSet.getInt("floor"));
			inmueble.setPrice(resultSet.getInt("price"));
			inmueble.setArea(resultSet.getInt("area"));
			inmueble.setCredit_readyness(resultSet.getBoolean("credit_readyness"));
			inmueble.setTelephone1(resultSet.getString("telephone1"));
			inmueble.setTelephone2(resultSet.getString("telephone2"));
			inmueble.setTelephone3(resultSet.getString("telephone3"));
			inmueble.setComments(resultSet.getString("comments"));
			inmueble.setStars(resultSet.getInt("stars"));
			inmueble.setCreateDate(resultSet.getDate("create_date"));
			inmueble.setLastUpdateDate(resultSet.getDate("last_update_date"));
			inmueble.setBedrooms(resultSet.getInt("bedrooms"));
		}
		connection.close();
		return inmueble;
		
	}

	public ArrayList getAddressList() throws Exception {
		
		ArrayList<Inmueble> inmuebles = null;
		connection = mySQLConnection.getConnection();
		Statement statement = connection.createStatement();
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String query = "SELECT * FROM ADDRESS ORDER BY STARS DESC, STREET";
		ResultSet resultSet = statement.executeQuery(query);
		inmuebles = new ArrayList<Inmueble>();
		while(resultSet.next()){
			Inmueble inmueble = new Inmueble();
			inmueble.setId(resultSet.getInt("id"));
			inmueble.setStreet(resultSet.getString("street"));
			inmueble.setNumber(resultSet.getInt("number"));
			inmueble.setFloor(resultSet.getInt("floor"));
			inmueble.setPrice(resultSet.getInt("price"));
			inmueble.setArea(resultSet.getInt("area"));
			inmueble.setCredit_readyness(resultSet.getBoolean("credit_readyness"));
			inmueble.setTelephone1(resultSet.getString("telephone1"));
			inmueble.setTelephone2(resultSet.getString("telephone2"));
			inmueble.setTelephone3(resultSet.getString("telephone3"));
			inmueble.setComments(resultSet.getString("comments"));
			inmueble.setStars(resultSet.getInt("stars"));
			inmueble.setCreateDate(resultSet.getDate("create_date"));
			inmueble.setLastUpdateDate(resultSet.getDate("last_update_date"));
			inmueble.setBedrooms(resultSet.getInt("bedrooms"));
			inmuebles.add(inmueble);
		}
		connection.close();
		return inmuebles;
	}

	public void removeAddress(int p_id) throws Exception {

		connection = mySQLConnection.getConnection();
		//Preparo la llamada al procedimiento.
		CallableStatement callableStatement = null;
		// Invoco al procedimiento almacenado.
		callableStatement = connection.prepareCall(	"{ call remove_address  (?) }" );
		// Seteo los par�metros del procedimiento.
		callableStatement.setInt(1, p_id);
		// Se ejecuta el procedimiento almacenado
		callableStatement.execute();
		callableStatement.close();
		
		// Cierro la conexi�n.
		connection.close();
		
	}

}
