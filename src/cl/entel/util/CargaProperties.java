package cl.entel.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CargaProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CargaProperties() {
		super();
		Utilidades.inicializador();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Utilidades.inicializador();

	}

}
