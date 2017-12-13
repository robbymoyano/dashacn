package cl.entel.dash.dao;

import java.util.List;

import cl.entel.dash.beans.GraficoCasuistica;
import cl.entel.dash.beans.GraficoGruposDia;

public interface ConsultasDashboard {
	public List<GraficoGruposDia> getGruposPorDia (int perfil, String fecha);

	public List<GraficoCasuistica> getCasuisticas (String grupo, String fecha);
}
