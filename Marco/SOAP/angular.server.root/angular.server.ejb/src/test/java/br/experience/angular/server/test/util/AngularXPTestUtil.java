package br.experience.angular.server.test.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AngularXPTestUtil {
	
	private static Integer codigoOperadora = 0;
	
	public static Integer getCodigoOperadora(){
		codigoOperadora += 1;
		return codigoOperadora;
	}

	/**
	 * Método usado para adicionar dias em uma data especificada. Se passado um
	 * número negativo a data será retrocedida na mesma quantidade de dias
	 * passados
	 * 
	 * @param data
	 *            data a ser adicionada os dias
	 * @param dias
	 *            número para acrescentar a data
	 * @return
	 */
	public static Date adicionaDiasData(Date data, int dias) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, dias);

		return calendar.getTime();
	}

	/**
	 * Método para pegar Data sem horas.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDataSemHoras(Date date) {
		Calendar cal = converterDateToCalendar(date);
		return converterCalendarToDate(cal);
	}

	/**
	 * Método para Converter Date para Calendar.
	 * 
	 * @param data
	 * @return
	 */
	private static Calendar converterDateToCalendar(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal;
	}

	/**
	 * Método para converter Calendar para Date.
	 * 
	 * @param cal
	 * @return
	 */
	private static Date converterCalendarToDate(Calendar cal) {
		return new GregorianCalendar(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
				.getTime();
	}

}
