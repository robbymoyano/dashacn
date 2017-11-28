package cl.entel.dash;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cl.entel.dash.beans.GraficoMensual;
import cl.entel.dash.beans.Salida2;
import cl.entel.dash.dao.Conection;
import cl.entel.dash.dao.impl.RoboticImpl;
import cl.entel.dash.rest.impl.RunningRobotic;

@Path("/dash")
public class ServiceRobot {
	@GET
	
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	//@Path("/{idrobot}")
	public Salida2 IniciaOperacion(@QueryParam("action") String action) {
		GraficoMensual m = new GraficoMensual();
		m.setCode("01");
		m.setMsg("algo");
		
		Salida2 s=new Salida2("02","hola mundo");
		return s;
	}

	@POST
	@Produces(MediaType.TEXT_XML)
	@Path("/{idrobot}/{idrun}")
	public GraficoMensual GrabaOperacion(@PathParam("idrobot") String idrobot,
			@PathParam("idrun") String idrun, @QueryParam("step") String step,
			@QueryParam("value") String value,
			@QueryParam("result") String result) {
		GraficoMensual m = new GraficoMensual();
		m.setCode("01");
		m.setMsg("algo");
		return m;

	}

	@PUT
	@Produces(MediaType.TEXT_XML)
	@Path("/{idrobot}/{idrun}")
	public GraficoMensual FinalizarRun(@PathParam("idrobot") String idrobot,
			@PathParam("idrun") String idrun,
			@QueryParam("action") String action) {

		GraficoMensual m = new GraficoMensual();
		m.setCode("01");
		m.setMsg("algo");
		return m;

	}

	public static void main(String args[]) throws ClassNotFoundException,
			SQLException {

	}

}
