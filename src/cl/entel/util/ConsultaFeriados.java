package cl.entel.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ConsultaFeriados {
	List<Calendar> feriados = new ArrayList<Calendar>();
    List<Integer> diasYear = new ArrayList<Integer>();

    private void llenarDatos() {
        feriados.add(new GregorianCalendar(2017, 1 - 1, 1));
        feriados.add(new GregorianCalendar(2017, 3 - 1, 23));
        feriados.add(new GregorianCalendar(2017, 3 - 1, 25));
        feriados.add(new GregorianCalendar(2017, 5 - 1, 01));
        feriados.add(new GregorianCalendar(2017, 5 - 1, 21));
        feriados.add(new GregorianCalendar(2017, 6 - 1, 19));
        feriados.add(new GregorianCalendar(2017, 6 - 1, 25));
        feriados.add(new GregorianCalendar(2017, 7 - 1, 16));
        feriados.add(new GregorianCalendar(2017, 8 - 1, 15));
        feriados.add(new GregorianCalendar(2017, 9 - 1, 18));
        feriados.add(new GregorianCalendar(2017, 9 - 1, 19));
        feriados.add(new GregorianCalendar(2017, 10 - 1, 10));
        feriados.add(new GregorianCalendar(2017, 10 - 1, 23));
        feriados.add(new GregorianCalendar(2017, 10 - 1, 31));
        feriados.add(new GregorianCalendar(2017, 11 - 1, 01));
        feriados.add(new GregorianCalendar(2017, 12 - 1, 8));
        feriados.add(new GregorianCalendar(2017, 12 - 1, 25));

       // System.out.println("====================");

       // System.out.println("feriados dia-año");
        for (int i = 0; i < feriados.size(); i++) {
            diasYear.add(feriados.get(i).get(Calendar.DAY_OF_YEAR));
          //  System.out.println(diasYear.get(i));
        }
       // System.out.println("====================");

    }

    public ConsultaFeriados() {
        this.llenarDatos();
    }

    public int getFeriados(Calendar inicio, Calendar fin) {
        Integer diaInicio = inicio.get(Calendar.DAY_OF_YEAR);
        Integer diaFin = fin.get(Calendar.DAY_OF_YEAR);
        int contador = 0;
      //  System.out.println("====================");

     //   System.out.println("rango consultado");

        System.out.println(diaInicio);
        System.out.println(diaFin);
      //  System.out.println("====================");

        for (int i = 0; i < diasYear.size(); i++) {
            if (diasYear.get(i) > diaInicio && diasYear.get(i) < diaFin) {
                System.out.println(diasYear.get(i) + " es feriado");
                // vemos que no se fin de semana
                if (feriados.get(i).get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && feriados.get(i).get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                 //   System.out.println("feriado cae en la semana");
                    contador++;
                } else {
                 //   System.out.println("feriado cae fin de semana");
                }

            } else {
              //  System.out.println(diasYear.get(i) + " no feriado");
            }
        }
        return contador;
    }
    
    
    public boolean isInhabil(Calendar fecha){
    	for (int i = 0; i < diasYear.size(); i++) {
	            if(fecha.get(Calendar.DAY_OF_YEAR)==diasYear.get(i)){
	            	return true;
	            }
	        }
    	if(fecha.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || fecha.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY )return true;
    	return false;
   	
   }
    
}
