package cl.entel.dash.rest.impl;


import java.sql.SQLException;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import cl.entel.dash.ServiceRobot;
import cl.entel.dash.beans.GraficoMensual;
import cl.entel.dash.dao.impl.RoboticImpl;
import cl.entel.util.Utilidades;

public class RunningRobotic {

	Logger log = Logger.getLogger(RunningRobotic.class.getName());

	public GraficoMensual IniciaOperacion(String idrobot, String action) {
		log.info("INI: resource/robot/" + idrobot + " [action=" + action + "]");
		
		
		try {
			RoboticImpl impl=new RoboticImpl();
			String strfecha=impl.getFechaActual();
			impl.IniciarEjecucion(idrobot, strfecha, strfecha);
			GraficoMensual res = new GraficoMensual();
			res.setCode("00");
			res.setMsg(strfecha);
			log.info("FIN: resource/robot " + res.toString());
			return res;
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		

	}

	public GraficoMensual GrabaOperacion(String idrobot, String idrun, String step,
			String value, String result) {

		Logger log = Logger.getLogger(ServiceRobot.class.getName());
		log.info("INI: resource/robot/" + idrobot + "/" + idrun + " [step="
				+ step + ", value=" + value + ", result=" + result + "]");

		try {
			RoboticImpl impl=new RoboticImpl();
			impl.GrabaOperacion(idrobot, idrun, step, value, result);
			GraficoMensual res = new GraficoMensual();
			res.setCode("00");
			res.setMsg("OK");
			log.info("FIN: resource/robot/"+idrun+ " "+ res.toString());
			return res;
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	public GraficoMensual FinalizaOperacion(String idrobot, String idrun,
			String action
	) {

		Logger log = Logger.getLogger(ServiceRobot.class.getName());
		log.info("INI: resource/robot/" + idrobot + "/" + idrun + " [action="
				+ action + "]");
		try {
			RoboticImpl impl=new RoboticImpl();
			impl.FinalizarEjecucion(idrobot, idrun);
			GraficoMensual res = new GraficoMensual();
			res.setCode("00");
			res.setMsg("OK");
			log.info("FIN: resource/robot/"+idrun+ " "+ res.toString());
			return res;
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

	}
}
