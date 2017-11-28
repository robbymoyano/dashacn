package cl.entel.dash.dao;

public interface Robotic {

	public String getFechaActual();
	
	public void IniciarEjecucion (String idRobot, String id_ejecucion, String fecha);
	
	public void FinalizarEjecucion (String idRobot, String id_ejecucion);
	
	public void GrabaOperacion (String idRobot, String id_ejecucion,String step, String valor, String operacion);

}
