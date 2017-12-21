package cl.entel.dash.dao.impl;

import java.util.Calendar;

import org.apache.log4j.Logger;

import cl.entel.dash.beans.Sla;
import cl.entel.dash.dao.ConsultasSla;
import cl.entel.util.ConsultaFeriados;
import cl.entel.util.Utilidades;

public class ConsultasSLAImp implements ConsultasSla {
	Logger log = Logger.getLogger(ConsultasSLAImp.class.getName());
	Sla bean=new Sla();
	@Override
	public Sla isSLA(String fechaInicio, String fechaFin, String grupoResolutor) {
		// TODO Auto-generated method stub
		
		bean.setInicio(Utilidades.StringToCalendarLongFormatt(fechaInicio));
		bean.setFin(Utilidades.StringToCalendarLongFormatt(fechaFin));
		bean.setHorasSla(16.5);
		log.info(bean.toString());
		
		int i=this.getDiasHabiles();
		double horas=this.calcularHoras(i);
		log.info(horas);
		
		bean.setHorasConsumidas(horas);
		if(bean.getHorasSla()>=bean.getHorasConsumidas())
			bean.setDentroSla(true);
		else bean.setDentroSla(false);
		return bean;
	}

	
	private int getDiasHabiles() {
		int diffDays = 0;
		// Calendar fechaInicial=inicio;
		Calendar fechaInicial = (Calendar) bean.getInicio().clone();
		fechaInicial.set(Calendar.HOUR_OF_DAY, 0);
		fechaInicial.set(Calendar.MINUTE, 0);
		fechaInicial.set(Calendar.SECOND, 0);

		Calendar fechaFinal = (Calendar) bean.getFin().clone();
		fechaFinal.set(Calendar.HOUR_OF_DAY, 0);
		fechaFinal.set(Calendar.MINUTE, 0);
		fechaFinal.set(Calendar.SECOND, 0);

		log.info("fecha inicio metodo " + fechaInicial.getTime());
		log.info("fecha fin metodo " + fechaFinal.getTime());

		log.info("entramos al metodo");
		while (fechaInicial.before(bean.getFin()) || fechaInicial.equals(bean.getFin())) {
			log.info("entramos al while");
			if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					&& fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				diffDays++;
			}
			fechaInicial.add(Calendar.DATE, 1);

		}

		diffDays--;

		ConsultaFeriados f = new ConsultaFeriados();
		int b = f.getFeriados(bean.getInicio(), bean.getFin());
		log.info("dias habiles: " + diffDays);

		log.info("dias feriados: " + b);
		if (diffDays - b - 1 < 0) {
			return 0;
		}
		log.info("dias completos: " + (diffDays - b - 2));
		return diffDays - b - 1;// no incluye las fechas ingresadas

	}
	
	
	private double calcularHoras(int dias) {
		double Totalhoras = dias * 9.25;

		// casos
		int caso = 1;
		if (bean.getInicio().get(Calendar.DAY_OF_YEAR) == bean.getFin().get(Calendar.DAY_OF_YEAR))
			caso = 1; // abre y cierra el mismo dia
		else
			caso = 2;

		System.out.println("el caso es: " + caso);

		if (caso == 1) {
			/*
			 * Inicio: El ticket se cierra el mismo dia
			 */
			// System.out.println("mismo dia");
			ConsultaFeriados f = new ConsultaFeriados();

			if(f.isInhabil(bean.getInicio()))return 0;

			
			double horaLlegada = bean.getInicio().get(Calendar.HOUR_OF_DAY)
					+ (bean.getInicio().get(Calendar.MINUTE) / 60f);
			if (horaLlegada < 8.75f) {
				horaLlegada = 8.75f;
				System.out.println("modificamos la hora de llegada");

			}
			// si llega despues de las 18:00 y es resuelto el mismo dia,
			// entonces se resuelve en 0 horas.
			if (horaLlegada > 18.0f) {
				return 0;
			}

			System.out.println("hora llegada " + horaLlegada);

			double horaCierre = bean.getFin().get(Calendar.HOUR_OF_DAY)
					+ ( bean.getFin().get(Calendar.MINUTE) / 60f);
			if (horaCierre > 18.0f) {
				horaCierre = 18.0f;
			}
			System.out.println("hora cierre " + horaCierre);

			Totalhoras = horaCierre - horaLlegada;
			System.out.println("total horas: " + Totalhoras);

		}

		if (caso == 2) {

			ConsultaFeriados f = new ConsultaFeriados();

			boolean inhabilInicio = f.isInhabil(bean.getInicio());
			double horaLlegada = bean.getInicio().get(Calendar.HOUR_OF_DAY)
					+ (bean.getInicio().get(Calendar.MINUTE) / 60f);
			boolean inhabilFinal = f.isInhabil(bean.getFin());
			double horaCierre = bean.getFin().get(Calendar.HOUR_OF_DAY)
					+ (bean.getFin().get(Calendar.MINUTE) / 60f);

			double primeraMitad = 0;

			if (inhabilInicio)
				primeraMitad = 0;
			else {
				System.out.println("no ingresa en feriado");

				if (horaLlegada < 8.75f) {
					horaLlegada = 8.75f;
				}
				primeraMitad = 18.00f - horaLlegada;

				if (horaLlegada > 18.00f)
					primeraMitad = 0;

			}

			System.out.println("primera mitad:" + primeraMitad);

			double segundaMitad = 0;

			if (inhabilFinal)
				segundaMitad = 0;
			else {
				if (horaCierre > 18.00f) {
					horaCierre = 18.00f;
				}
				segundaMitad = horaCierre - 8.75f;

				if (horaCierre < 8.75f)
					segundaMitad = 0;

			}

			Totalhoras = Totalhoras + primeraMitad + segundaMitad;

		}

		return Totalhoras;

	}
}
