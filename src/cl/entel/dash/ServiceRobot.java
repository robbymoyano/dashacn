package cl.entel.dash;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import cl.entel.dash.beans.GraficoCasuistica;
import cl.entel.dash.beans.GraficoGruposDia;
import cl.entel.dash.dao.impl.ConsultasDashboardImp;

@Path("/dash")
public class ServiceRobot {

	Logger log = Logger.getLogger(ServiceRobot.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/grupos")
	public List<GraficoGruposDia> GrabaOperacion(
			@QueryParam("fecha") String fecha) {
		log.info("inicio " + fecha);
		List<GraficoGruposDia> lista = new ArrayList<GraficoGruposDia>();
		try {
			ConsultasDashboardImp i = new ConsultasDashboardImp();
			lista = i.getGruposPorDia(1, "12-12-2017");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return lista;
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/grupos/{grupo}")
	public List<GraficoCasuistica> ObtieneCasuisticas(
			@PathParam("grupo") String grupo,
			@QueryParam("fecha") String fecha) {
		log.info("[Grupo " + grupo+"]|[Fecha "+fecha+"]");
		List<GraficoCasuistica> lista = new ArrayList<GraficoCasuistica>();
		

		try {
			ConsultasDashboardImp i = new ConsultasDashboardImp();
			lista = i.getCasuisticas(grupo, "12-12-2017");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return lista;
	}

	
	public static void main(String args[]) throws ClassNotFoundException,
			SQLException {
		
	}

}
