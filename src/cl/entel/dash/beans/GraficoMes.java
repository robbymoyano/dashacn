package cl.entel.dash.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "grafico")
public class GraficoMes {

	String subTitle;
	String categorias[];
	String fechas[];
	int dataManual[];
	int dataRobot[];
	int dataAcumulado[];

	
	
	public String[] getFechas() {
		return fechas;
	}

	@XmlAttribute
	public void setFechas(String[] fechas) {
		this.fechas = fechas;
	}

	public GraficoMes() {
		super();
	}

	public String getSubTitle() {
		return subTitle;
	}

	@XmlAttribute
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String[] getCategorias() {
		return categorias;
	}

	@XmlAttribute
	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public int[] getDataManual() {
		return dataManual;
	}

	@XmlAttribute
	public void setDataManual(int[] dataManual) {
		this.dataManual = dataManual;
	}

	public int[] getDataRobot() {
		return dataRobot;
	}

	@XmlAttribute
	public void setDataRobot(int[] dataRobot) {
		this.dataRobot = dataRobot;
	}

	public int[] getDataAcumulado() {
		return dataAcumulado;
	}

	@XmlAttribute
	public void setDataAcumulado(int[] dataAcumulado) {
		this.dataAcumulado = dataAcumulado;
	}

}
