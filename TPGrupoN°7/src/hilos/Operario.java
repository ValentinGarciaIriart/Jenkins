package hilos;


import java.util.Observable;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa al operario que intenta solicitar la ambulancia para repararla en el taller<br>
 * se extiende de observable e implementa runnable para modificar su estado en tiempo de ejecucion <br>
 * al ser de única instancia se aplica el patrón singleton 
 */
public class Operario extends Observable implements Runnable
{

	private static Operario instance=null;
	private String nombre;
	
	/**
	 * constructor privado para aplicar singleton
	 */
	private Operario()
	{
		this.nombre ="Pela de la Facultad";	
	}
	
	
	/**
	 * metodo que devuelve la unica instancia de la clase
	 * @return devuelve un operario, unico de la clase
	 */
	public static Operario getInstance()
	{
		if (instance==null)
			instance=new Operario();
		return instance;	
	}


	public String getNombre()
	{
		return this.nombre;
	}

	/**
	 *metodo run que notifica su estado, intenta cambiar el estado del recurso compartido y notifica luego la accion realizada y finaliza
	 */
	@Override
	public void run()
	{
		this.setChanged();
		this.notifyObservers("está llamando a la ambulancia");
		Ambulancia.getInstance().irAlTaller();
		this.setChanged();
		this.notifyObservers("logró llamar a la ambulancia, ahora está descansando");
	}


}
