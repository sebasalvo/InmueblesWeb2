package com.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	
	/**
	 * Constantes de conexi�n a la base.
	 */
	private final String DRIVER   = "com.mysql.jdbc.Driver";
	private final String URL      = "jdbc:mysql://localhost:3306/test";
	//private final String DBNAME   = "test";
	private final String USER     = "root";
	private final String PASSWORD = "salamin";
	
	/**
	 * Objeto que representa la conexi�n a la base.
	 */
	private Connection connection;
	
	/**
	 * M�todo para obtener la conexi�n a la base de datos.
	 * @return Connection
	 */
	public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            // Obtengo la conexi�n.
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch ( ClassNotFoundException cnfe ) {
            System.out.println( "ERROR: " + cnfe.getMessage() );
        } catch ( SQLException sqle ) {
            System.out.println( "ERROR: " + sqle.getMessage() );
        } catch ( Exception e ) {
            System.out.println( "ERROR: " + e.getMessage() );
        }
        return null;
    }

}
