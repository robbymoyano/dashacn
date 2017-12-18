package cl.entel.dash.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import cl.entel.util.Utilidades;

@XmlRootElement(name = "grafico")
public class GraficoCasuistica {
	String casuistica;
	int cantidad;

	public String getCasuistica() {
		return casuistica;
	}

	@XmlAttribute
	public void setCasuistica(String casuistica) {
		int largo = Integer.parseInt(Utilidades
				.getProperties("casuistica.largo"));
		if (casuistica.length() > largo) {
			casuistica=casuistica.substring(0, largo);
			casuistica = casuistica + "...";
			System.out.println(casuistica);
		}
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
