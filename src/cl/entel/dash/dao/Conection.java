package cl.entel.dash.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Conection {
	public static Connection getConectionOracle() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Logger log = Logger.getLogger(Conection.class.getName());

		String DB_DRIVER = "oracle.jdbc.OracleDriver";
		String DB_CONNECTION = "jdbc:oracle:thin:@10.43.24.100:1521:xe";
		String DB_USER = "DASHACN";
		String DB_PASSWORD = "ENTEL123";
		Class.forName(DB_DRIVER);
		log.info("Creando Conexion");
		con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		log.info("Conexion creada: " + con.toString());
		return con;
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
	
		Conection.getConectionOracle();
	}

}