package cl.entel.dash.beans;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sla")
public class Sla {

	Calendar inicio;
	Calendar fin;
	double horasSla;
	double horasConsumidas;
	boolean isDentroSla;
	
	
	@Override
	public String toString() {
		return "Sla [inicio=" + inicio.getTime() + ", fin=" + fin.getTime() + ", horasSla="
				+ horasSla + ", horasConsumidas=" + horasConsumidas
				+ ", isDentroSla=" + isDentroSla + "]";
	}

	public Calendar getInicio() {
		return inicio;
	}
		
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}
	public Calendar getFin() {
		return fin;
	}
	
	@XmlAttribute
	public void setFin(Calendar fin) {
		this.fin = fin;
	}
	public double getHorasSla() {
		return horasSla;
	}
	
	@XmlAttribute
	public void setHorasSla(double horasSla) {
		this.horasSla = horasSla;
	}
	public double getHorasConsumidas() {
		return horasConsumidas;
	}
	
	@XmlAttribute
	public void setHorasConsumidas(double horasConsumidas) {
		this.horasConsumidas = horasConsumidas;
	}

	
	public void setDentroSla(boolean isDentroSla) {
		this.isDentroSla = isDentroSla;
	}
	
	
	
}
