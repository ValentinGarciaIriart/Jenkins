package habitaciones;

import excepciones.TipoHabitacionInvalidaException;

/**
 * @author
 * Clase Factory en donde se generar�n las distintas habitaciones : Habitacion Compartida, Habitacion Privada y Sala de Terapia Intensiva<br>
 */
public class HabitacionFactory {
	

	/**Metodo static de tipo IHabitacion que instancia la habitacion a generarse<br>
	 * <b> Pre : </b> el string debe ser distinto de null <br>
	 * <b> Post : </b> devuelve un par�metro respuesta de tipo IHabitacion <br>
	 * @param tipo : parametro de tipo string el cual contiene el tipo de habitacion que se va a instanciar<br>
	 * @return se crea una habitacion del tipo ingresado<br>
	 * @throws TipoHabitacionInvalidaException : lanza una excepci�n informando al usuario que la habitaci�n ingresada es nula o err�nea 
	 */
	public static IHabitacion getHabitacion (String tipo, double costo)throws TipoHabitacionInvalidaException {
		IHabitacion respuesta = null;
		
		if(tipo.equalsIgnoreCase("Habitacion Compartida"))
			respuesta = new HabCompartidaComun(costo);
		else if(tipo.equalsIgnoreCase("Habitacion Privada"))
			respuesta = new HabPrivada(costo);
		else if (tipo.equalsIgnoreCase("Sala Terapia Intensiva"))
			respuesta = new SalaTerapiaIntensiva(costo);
		if(respuesta!=null)
			 return respuesta;
			else
				throw new TipoHabitacionInvalidaException("Este tipo de habitaci�n no existe en esta cl�nica",tipo);
	}
	

}
