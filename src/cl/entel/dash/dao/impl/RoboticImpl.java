package cl.entel.dash.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import cl.entel.dash.dao.Conection;
import cl.entel.dash.dao.Robotic;

public class RoboticImpl implements Robotic {

	Logger log = Logger.getLogger(RoboticImpl.class.getName());
	Connection con = null;
	OracleCallableStatement statement = null;

	public RoboticImpl() throws ClassNotFoundException, SQLException {
		con = Conection.getConectionOracle();
	}

	@Override
	public void IniciarEjecucion(String idRobot, String id_ejecucion,
			String fecha) {
		try {
			CallableStatement cStmt = con.prepareCall("{ CALL iniciarExe('"
					+ id_ejecucion + "','" + idRobot + "') } ");
			log.info(idRobot + " " + id_ejecucion + " " + fecha);
			cStmt.execute();
			log.info("SP llamado correctamente");
			con.close();
			con = null;
		} catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
		}
	}

	@Override
	public void FinalizarEjecucion(String idRobot, String id_ejecucion) {
		try {
			CallableStatement cStmt = con.prepareCall("{ CALL StopExe('"
					+ id_ejecucion + "','" + idRobot + "') } ");
			log.info(idRobot + " " + id_ejecucion);
			cStmt.execute();
			log.info("SP llamado correctamente");
			con.close();
			con = null;
		} catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
		}
		
	}

	@Override
	public String getFechaActual() {
		String fecha;
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select get_fecha() from dual");
			
			rs.next();
			fecha = rs.getString(1);
				
			log.info("SP llamado correctamente "+fecha);
			stmt.close();
			
			return fecha;
		} catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
			return null;
		}
	}

	@Override
	public void GrabaOperacion(String idRobot, String id_ejecucion,
			String step, String valor, String operacion) {
		
		try {
			CallableStatement cStmt = con.prepareCall("{ CALL record_step('"
					+ id_ejecucion + "','" + idRobot + "','"+step+"','"+valor+"','"+operacion+"') } ");
			log.info(idRobot + " " + id_ejecucion);
			cStmt.execute();
			log.info("SP llamado correctamente");
			con.close();
			con = null;
		} catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
		}

		
	}

}
