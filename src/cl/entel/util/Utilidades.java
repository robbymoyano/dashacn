package cl.entel.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.StringTokenizer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

public class Utilidades {
	public static String directorio_properties;
	public static PropertiesConfiguration properties = null;

	public static Calendar StringToCalendar(String fecha) {
		Calendar c = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			c.setTime(formatter.parse(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static String CalendarToString(Calendar fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String salida;
		salida = formatter.format(fecha.getTime());
		return salida;
	}

	public static XMLGregorianCalendar fechacero() {

		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar date2 = null;
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			date2.setHour(0);
			date2.setMinute(0);
			date2.setSecond(0);
			date2.setMillisecond(0);

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return date2;
	}

	public static XMLGregorianCalendar SumarMeses(XMLGregorianCalendar fecha,
			int mes) {
		XMLGregorianCalendar date = null;
		try {
			GregorianCalendar gc = fecha.toGregorianCalendar();
			gc.add(Calendar.MONTH, mes);
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getRandomParam(int largo) {
		String result = RandomStringUtils.random(largo, false, true);
		return result;
	}
	
	public static double getRandomBot(String dia) {
		int i=Integer.parseInt(dia);
		int factor=i%3;
		switch(factor){
		case 0:return 3.0;
		case 1:return 3.2;
		case 2: return 3.1;
		default: return 3.3;
		}
		
	}

	public static String formatFecha(XMLGregorianCalendar fecha, String formato) {
		Logger LOGGER = Logger.getLogger(Utilidades.class.getName());
		String currentDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formato);
			currentDate = formatter.format(fecha.toGregorianCalendar()
					.getTime());
		} catch (Exception e) {
			LOGGER.warn("Error al parsear fecha: " + e.getMessage());
		}
		return currentDate;
	}

	public static XMLGregorianCalendar StringToDate(String dateInString) {
		Logger LOGGER = Logger.getLogger(Utilidades.class.getName());
		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss.SSS");
		// dateInString = "12-12-2012 18:45:03.080";

		if (!dateInString.equals("")) {
			try {
				Date date = formatter.parse(dateInString);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				XMLGregorianCalendar fechaGregoriana = DatatypeFactory
						.newInstance().newXMLGregorianCalendar(calendar);
				return fechaGregoriana;

			} catch (ParseException e) {
				LOGGER.warn("Error al transformar la fecha: " + e);
				throw new NumberFormatException("Error al transformar fecha: "
						+ dateInString);

			} catch (DatatypeConfigurationException ex) {
				LOGGER.warn("Error al transformar la fecha: " + ex);
				throw new NumberFormatException("Error al transformar fecha: "
						+ dateInString);
			}
		} else {
			LOGGER.warn("La fecha fue ingresada como nula");
			throw new NumberFormatException("La fecha fue ingresada como nula");
		}
	}

	public static Long StartTimer() {
		Long init = Long.valueOf("0");
		init = System.currentTimeMillis();
		return init;
	}

	public static String floatToString(float f) {
		String s = String.valueOf(f);
		StringTokenizer token = new StringTokenizer(s, ".");
		return token.nextToken();
	}

	public static Long StopTimer(Long start) {
		Long tiempoEjecucion = Long.valueOf("0");
		tiempoEjecucion = System.currentTimeMillis() - start;
		return tiempoEjecucion;
	}

	public static String getRandom() {
		int i = Math.abs(new Random().nextInt());
		return String.valueOf(i);
	}

	public static void inicializador() {

		String RUTA_PROPERTIES = "./aplsEPCS/DashAccenture/config/";
		String NOMBRE_PROPERTIES = "config.properties";
		if (Utilidades.properties == null) {
			if (RUTA_PROPERTIES != null && existeDirectorio(RUTA_PROPERTIES)) {
				String ficheroProperties = RUTA_PROPERTIES + NOMBRE_PROPERTIES;
				if (existeFichero(ficheroProperties)) {

					try {
						properties = new PropertiesConfiguration();
						properties.setFileName(ficheroProperties);
						properties
								.setReloadingStrategy(new FileChangedReloadingStrategy());
						properties.setDelimiterParsingDisabled(true);
						properties.load();
						System.out.println("Property cargada");
					} catch (org.apache.commons.configuration.ConfigurationException e) {
						e.printStackTrace();
					}

				} else {
					System.err.println("El fichero de properties no existe");
				}
			} else {
				System.err.println("El directorio no existe");
			}
		}

	}

	private static boolean existeFichero(String ruta) {
		File f = new File(ruta);
		return f.exists() && f.canRead();
	}

	private static boolean existeDirectorio(String ruta) {
		File f = new File(ruta);
		return f.exists() && f.isDirectory();
	}

	public static Logger log = Logger.getLogger("DEBUG");

	public static String getProperties(String key) {
		inicializador();
		String value = properties.getString(key);
		// log.info("Property [" + key + "][" + value + "]");
		return value;
	}

	public static String getStringDia(int dia) {
		if (dia > 10) {
			String aux = String.valueOf(dia);
			return String.valueOf("0" + aux);
		} else {
			return String.valueOf(dia);
		}
	}

}
