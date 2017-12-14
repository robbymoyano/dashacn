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
import cl.entel.dash.beans.GraficoMes;
import cl.entel.dash.beans.RegistroMes;
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
			lista = i.getGruposPorDia(1, fecha);
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
			lista = i.getCasuisticas(grupo, fecha);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return lista;
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/")
	public GraficoMes obtieneMes(@QueryParam("inicio")String inicio,@QueryParam("fin") String fin) {
		
		//dado el dia, calculamos el mes hacia atras
		//debemos mostrar entre el 12 de nov al 12 de dic
		
		/*
		 * datos de salida
		 * 28	28-11-2017	1
		 * 03	03-12-2017	1
		 * 12	12-12-2017	58
		 * */
		List<RegistroMes> lista=new ArrayList<RegistroMes>();
		lista.add(new RegistroMes("28","28-11-2017",1));
		lista.add(new RegistroMes("03","03-12-2017",1));
		lista.add(new RegistroMes("12","12-12-2017",58));
		

		//for(int i=0;i<lista.size();i++){
	//		if(lista.indexOf(i)));
//		}
		//asumimos que comienza el 12
		/*int i=0;
		int j=12;
		while(true){
			if(lista.get)
			int a=3;
			break;
		}*/
		
		log.info("Incio grafico mes "+inicio+", "+fin);
		GraficoMes mes=new GraficoMes();
		mes.setSubTitle("Hola Mundo");

		String categorias[]=new String[2];
		categorias[0]="hola";
		categorias[1]="mundo";

		mes.setCategorias(categorias);
		return mes;
	}

	
	public static void main(String args[]) throws ClassNotFoundException,
			SQLException {
		
	}

}
