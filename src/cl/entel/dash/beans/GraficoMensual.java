package cl.entel.dash.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "grafico")
public class GraficoMensual {
	String code;
	String msg;
	int i=50;

	public GraficoMensual() {
	}

	public GraficoMensual(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	@XmlAttribute
	public void setCode(String code) {
		this.code = code;
	}
	

	public String getMsg() {
		return msg;
	}

	@XmlAttribute
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + "]";
	}

	public int getI() {
		return i;
	}
	
	@XmlAttribute
	public void setI(int i) {
		this.i = i;
	}

	
	
}