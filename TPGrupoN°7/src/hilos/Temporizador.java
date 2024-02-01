package hilos;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa un temporizador que cada cierto intervalo de tiempo intenta cambiar el estado de la ambulancia para que regrese a la clinica
 *
 */
public class Temporizador extends Thread
{
	
	/**
	 * Constructor vacio del temporizador 
	 */
	public Temporizador()
	{
		
	}
	
	
	/**
	 *metodo run del temporizador, se ejecuta mientras la ventana permanezca abierta y accede al recurso compartido para intentar cambiar su estado
	 */
	@Override
	public void run() 
	{
		while(true) //cambiar
		{	
			try 
			{
				Ambulancia.getInstance().temporizador(this);
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	
}