package cl.entel.dash.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "grafico")

public class GraficoGruposDia {

	String grupo;
	int cantidad;
	
	public GraficoGruposDia(){}
	public GraficoGruposDia(String grupo, int cantidad) {
		super();
		this.grupo = grupo;
		this.cantidad = cantidad;
	}
	public String getGrupo() {
		return grupo;
	}
	
	@XmlAttribute
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
		return "GraficoGruposDia [grupo=" + grupo + ", cantidad=" + cantidad
				+ "]";
	}
	
	
	
}
