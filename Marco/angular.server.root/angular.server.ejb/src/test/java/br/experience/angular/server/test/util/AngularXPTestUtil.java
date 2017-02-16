package br.experience.angular.server.test.util;

import java.util.Calendar;
import java.util.Date;

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

}
