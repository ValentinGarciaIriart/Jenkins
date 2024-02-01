package pacientes;

import java.io.Serializable;
import java.util.GregorianCalendar;

import habitaciones.IHabitacion;

/**
 * @author 
 * Interfaz IPaciente en donde se enumerarán los métodos sin implementarse que serán desarrollados en la clase que implemente
 * esta interfaz<br>
 */
public interface IPaciente extends Comparable<IPaciente>, Runnable, Serializable
{
	
	
	/**
	 * Método boolean que toma como parametro un objeto de tipo IPaciente para determinar que objeto segun su rangoEtario se queda con el lugar de la sala de espera privado y quien iría al patio <br>
	 * <b> Pre : </b> Objeto paciente distinto de null <br>
	 * <b> Post : </b> invoca a otro metodo beat(Rango) para devolver el boolean que se busca
	 */
	boolean beats(IPaciente o);
	
	
	/**
	 * Método boolean que toma como parametro un objeto de tipo IPaciente para determinar<br>
	 * si el IPaciente como parametro "vence" al rango niño para quedarse con el lugar de la sala de espera privada <br>
	  * @return retorna un boolean que determina si el IPaciente pasado como parametro en el metodo beats (IPaciente o) vence o no al niño
	 */
	boolean beatsNiño();
	
	/**
	 * Método boolean que toma como parametro un objeto de tipo IPaciente para determinar<br>
	 * si el IPaciente como parametro "vence" al rango joven para quedarse con el lugar de la sala de espera privada<br>
	 * @return retorna un boolean que determina si el IPaciente pasado como parametro en el metodo beats (IPaciente o) vence o no al joven
	 */
	
	boolean beatsJoven();
	
	/**
	 * Método boolean que toma como parametro un objeto de tipo IPaciente para determinar<br>
	 * si el IPaciente como parametro "vence" al rango mayor para quedarse con el lugar de la sala de espera privada <br>
	 * @return retorna un boolean que determina si el IPaciente pasado como parametro en el metodo beats (IPaciente o) vence o no al mayor
	 */
	boolean beatsMayor();
	

	void setNroTurno(int nroTurno);

	int getNroTurno();
	
	String getNombre();

	String getDNI();
	
	IHabitacion getHabitacion();
	
	void setHabitacion(IHabitacion h);
	
	void setPedido(String pedido);
	
	/**Método int que retorna el número de historia clínica del paciente<br>
	 * @return valor int con el número de historia
	 */

	int getNumHistoria();
	
	void setFacturo(boolean facturo);
	
	boolean isFacturo();
	

	 void setFechaEgreso(GregorianCalendar fechaEgreso);
	
	
	 GregorianCalendar getFechaEgreso();
	



}