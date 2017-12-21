package cl.entel.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import cl.entel.dash.dao.Conection;

public class CargaGrupos extends HttpServlet {

	public static Logger log = Logger.getLogger(CargaGrupos.class);
	private static final long serialVersionUID = 1L;
	public static HashMap<String, String> hashGrupos = new HashMap<String, String>();

	public CargaGrupos() {
		super();
	}

	@Override
	public void init() {
		Connection con = null;
		try {
			con = Conection.getConectionOracle();
			Statement sta = con.createStatement();

			ResultSet rs = sta
					.executeQuery("select id_grupo, sla from SLA_POR_GRUPO");
			while (rs.next()) {
				hashGrupos.put(rs.getString(1),rs.getString(2));
			}
			log.info(hashGrupos.get("EU SOPORTE HOGAR"));
			Utilidades.hashGrupos=hashGrupos;
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			log.error("Imposible cargar el hashmap de grupos "+e.getMessage());
		} catch (SQLException e) {
			log.error("Imposible cargar el hashmap de grupos "+e.getMessage());

		}
	}

	
}