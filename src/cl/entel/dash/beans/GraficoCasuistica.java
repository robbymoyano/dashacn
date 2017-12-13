package cl.entel.dash.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "grafico")
public class GraficoCasuistica {
	String casuistica;
	int cantidad;
	public String getCasuistica() {
		return casuistica;
	}
	@XmlAttribute
	public void setCasuistica(String casuistica) {
		this.casuistica = casuistica;
	}
	public int getCantidad() {
		return cantidad;
	}
	@XmlAttribute
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "GraficoCasuistica [casuistica=" + casuistica + ", cantidad="
				+ cantidad + "]";
	}
	public GraficoCasuistica(String casuistica, int cantidad) {
		super();
		this.casuistica = casuistica;
		this.cantidad = cantidad;
	}
	public GraficoCasuistica() {
		super();
	}
	
	
}
