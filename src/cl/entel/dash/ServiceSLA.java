package cl.entel.dash;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import cl.entel.dash.beans.Sla;
import cl.entel.dash.dao.impl.ConsultasSLAImp;


@Path("/sla")
public class ServiceSLA {
	Logger log = Logger.getLogger(ServiceSLA.class.getName());


	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/{grupo}")
	public Sla isSLA(
			@QueryParam("fechaInicio") String fechaInicio,
			@QueryParam("fechaFin") String fechaFin,
			@PathParam("grupo") String grupo) {
		
		log.info("Calcula sla [GRUPO "+grupo+"]|[INI "+fechaInicio+"]|[FIN "+fechaFin+"]");
		ConsultasSLAImp i=new ConsultasSLAImp();
		Sla bean=i.isSLA(fechaInicio, fechaFin, grupo);
		
		return bean;
	}
}
