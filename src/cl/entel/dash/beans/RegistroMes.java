package cl.entel.dash.beans;

public class RegistroMes {
	String dia;
	String fecha;
	int cantidad;
	public RegistroMes(String dia, String fecha, int cantidad) {
		super();
		this.dia = dia;
		this.fecha = fecha;
		this.cantidad = cantidad;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "RegistroMes [dia=" + dia + ", fecha=" + fecha + ", cantidad="
				+ cantidad + "]";
	}
	
	
}
