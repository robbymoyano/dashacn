package cl.entel.dash.beans;

public class Salida2 {
	String code;
	String msg;
	int i[]= new int[3];
	String[] nombre = {"María", "Gerson"};   //Array de 2 elementos
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Salida2(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
		i[0]=10;
		i[1]=20;
		i[2]=30;
	}
	
	public Salida2() {
		super();
		
	}
	public int[] getI() {
		return i;
	}
	public void setI(int[] i) {
		this.i = i;
	}
	public String[] getNombre() {
		return nombre;
	}
	public void setNombre(String[] nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
}
