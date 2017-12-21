package cl.entel.dash.dao;

import cl.entel.dash.beans.Sla;

public interface ConsultasSla {
	public Sla isSLA(String fechaInicio, String fechaFin, String grupoResolutor);

}
