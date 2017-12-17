package cl.entel.dash;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import cl.entel.util.Utilidades;

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
			@PathParam("grupo") String grupo, @QueryParam("fecha") String fecha) {
		log.info("[Grupo " + grupo + "]|[Fecha " + fecha + "]");
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
	public GraficoMes obtieneMes(@QueryParam("fecha") String fecha) {

		log.info("Incio grafico mes " + fecha);
		Calendar hoy = Utilidades.StringToCalendar(fecha);
		hoy.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH) - 1);
		log.info("hoy: " + hoy.getTime());

		Calendar inicio = Calendar.getInstance();
		inicio.setTime(hoy.getTime());
		inicio.set(Calendar.MONTH, hoy.get(Calendar.MONTH) - 1);
		// dado el inicio, creo las fechas de inicio y fin

		// ahora ya tengo los 2 intervalos de fechas
		log.info("[inicio " + inicio.getTime() + "]|[final " + hoy.getTime()
				+ "]");
		List<RegistroMes> lista = new ArrayList<RegistroMes>();

		List<RegistroMes> lista2 = new ArrayList<RegistroMes>();
		ConsultasDashboardImp imp=null;
		try {
			imp = new ConsultasDashboardImp();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista2=imp.getMes(1, Utilidades.CalendarToString(inicio), Utilidades.CalendarToString(hoy));
	//	lista2.add(new RegistroMes("28", "28-11-2017", 24));
		//lista2.add(new RegistroMes("03", "03-12-2017", 60));
		//lista2.add(new RegistroMes("12", "12-12-2017", 58));
		
		
		while (!inicio.equals(hoy)) {

			String aux = Utilidades.CalendarToString(inicio);
			lista.add(new RegistroMes(aux.substring(0, 2), aux, 0));
			inicio.set(Calendar.DAY_OF_MONTH,
					inicio.get(Calendar.DAY_OF_MONTH) + 1);

		}
		String aux = Utilidades.CalendarToString(hoy);
		lista.add(new RegistroMes(aux.substring(0, 2), aux, 0));

		
		

		int j = 0;
		for (int i = 0; i < lista.size(); i++) { //se asume q lista 2 trae al menos 1 registro
			if (lista.get(i).getFecha().equals(lista2.get(j).getFecha())) {

				lista.get(i).setCantidad(lista2.get(j).getCantidad());
				j++;
				if(j==lista2.size())break;
			}
		}

		for (int i = 0; i < lista.size(); i++) {
			log.info(lista.get(i).toString());
		}

		// hasta aqui tenemos la lista con 1 mes completo, debemos compararla
		// con la del sp

		GraficoMes mes = new GraficoMes();
		mes.setSubTitle("Del "+Utilidades.CalendarToString(inicio)+" al "+Utilidades.CalendarToString(hoy));

		String categorias[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			categorias[i]=lista.get(i).getDia();
		}
		
		int dataManual[] = new int[lista.size()];
		int AcumuladoManual[] = new int[lista.size()];
		int dataRobot[]= new int[lista.size()];
		int dataRobotAux[]= new int[lista.size()];
		int acu=0;
		int acuBot=0;
		for (int i = 0; i < lista.size(); i++) {
			dataManual[i]=lista.get(i).getCantidad();
			dataRobotAux[i]= (int) (dataManual[i]/Utilidades.getRandomBot(categorias[i]));
			acu=acu+dataManual[i];
			AcumuladoManual[i]=acu;
			acuBot=acuBot+dataRobotAux[i];
			dataRobot[i]=acuBot;
		}
		

		String fechas[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			fechas[i]=lista.get(i).getFecha();
			log.info("data aux robot: "+dataRobotAux[i]);
		}
		
		
		mes.setFechas(fechas);
		mes.setCategorias(categorias);
		mes.setDataManual(dataManual);
		mes.setDataAcumulado(AcumuladoManual);
		mes.setDataRobot(dataRobot);
		
		return mes;
	}

	public static void main(String args[]) throws ClassNotFoundException,
			SQLException {
System.out.println(Utilidades.getRandomBot("01"));
	}

}
