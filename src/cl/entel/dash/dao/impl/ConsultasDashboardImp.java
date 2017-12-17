package cl.entel.dash.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import cl.entel.dash.beans.GraficoCasuistica;
import cl.entel.dash.beans.GraficoGruposDia;
import cl.entel.dash.beans.RegistroMes;
import cl.entel.dash.dao.Conection;
import cl.entel.dash.dao.ConsultasDashboard;

public class ConsultasDashboardImp implements ConsultasDashboard {
	Logger log = Logger.getLogger(ConsultasDashboardImp.class.getName());
	Connection con = null;
	OracleCallableStatement statement = null;

	public ConsultasDashboardImp() throws ClassNotFoundException, SQLException {
		con = Conection.getConectionOracle();
	}

	@Override
	public List<GraficoGruposDia> getGruposPorDia(int perfil, String fecha) {
		List<GraficoGruposDia> lista = new ArrayList<GraficoGruposDia>();

		try {
			CallableStatement cStmt = con.prepareCall("{ CALL GET_TOTALXGRUPO("
					+ perfil + ",'" + fecha + "',?) } ");
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.execute();
			ResultSet rs = (ResultSet) cStmt.getObject(1);

			while (rs.next()) {
				String grupoResolutor = rs.getString(1);
				int cantidadTickets = rs.getInt(2);
				GraficoGruposDia t = new GraficoGruposDia();
				t.setGrupo(grupoResolutor);
				t.setCantidad(cantidadTickets);
				lista.add(t);
			}
			log.info("SP llamado correctamente");
			con.close();
			con = null;

		} catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
		}
		return lista;
	}

	@Override
	public List<GraficoCasuistica> getCasuisticas(String grupo, String fecha) {
		List<GraficoCasuistica> lista = new ArrayList<GraficoCasuistica>();

		try {
			CallableStatement cStmt = con.prepareCall("{ CALL GET_CASUISTICA('"
					+ grupo + "','" + fecha + "',?) } ");
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.execute();
			ResultSet rs = (ResultSet) cStmt.getObject(1);

			while (rs.next()) {
				String casuistica = rs.getString(1);
				int cantidadTickets = rs.getInt(2);
				GraficoCasuistica t = new GraficoCasuistica();
				t.setCasuistica(casuistica);
				t.setCantidad(cantidadTickets);
				lista.add(t);
			}
			log.info("SP llamado correctamente");
			con.close();
			con = null;

		} catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
		}
		return lista;
	}

	@Override
	public List<RegistroMes> getMes(int perfil, String fechaInicio,
			String fechaFin) {
		List<RegistroMes> lista=new ArrayList<RegistroMes>();
		log.info(fechaInicio);
		log.info(fechaFin);
		try {
			CallableStatement cStmt = con.prepareCall("{ CALL GET_MES("
					+ perfil + ",'" + fechaInicio+"','"+fechaFin+"',?) } ");
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			log.info("apunto de ejecutar");
			cStmt.execute();
			ResultSet rs = (ResultSet) cStmt.getObject(1);

			log.info("ya ejecutado");
			while (rs.next()) {
				
				
				RegistroMes r=new RegistroMes(rs.getString(1),rs.getString(2),rs.getInt(3));
				lista.add(r);
				log.info(r.toString());
			}
		}
		catch (SQLException e) {
			log.fatal("SQLException: " + e.getMessage());
		}
		
		return lista;
		
	}

}
