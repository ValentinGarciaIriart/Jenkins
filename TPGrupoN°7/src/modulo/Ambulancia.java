package modulo;

import java.io.Serializable;
import java.util.Observable;


import estados.DisponibleEnClinicaState;
import estados.IState;
import hilos.Operario;
import hilos.Temporizador;
import pacientes.IPaciente;

/**
 * @author usuario <br>
 * clase que representa la ambulancia del sistema, recurso compartido que operarios,pacientes y temporizador buscan cambiar su estado <br>
 * se extiende de observable y notifica su estado <br>
 * aplica el patron Singleton al ser la unica de su clase en el sistema
 */
public class Ambulancia	 extends Observable
{
	private static Ambulancia instance=null;
	private IState estado;
	
	private Ambulancia()
	{
		this.estado=new DisponibleEnClinicaState(this);
	}
	
	public void setEstado(IState e)
	{
		this.estado=e;
	}
	
	public IState getEstado()
	{
		return this.estado;
	}
	
	
	public static Ambulancia getInstance()
	{
		if (instance==null)
			instance=new Ambulancia();
		return instance;
		
	}
	

	/**
	 * metodo que informa que se busca cambiar su estado a atencion a domicilio , se invoca al metodo que cambia estado e informa si se pudro lograr o no <br> 
	 * @return rta de tipo boolean indica si se logro cambiar el estado actual o no
	 */
	public boolean solicitudAtencionDomicilio() 
	{
		this.setChanged();
		this.notifyObservers("es llamada para atencion domicilio");
		
		boolean rta=this.estado.solicitudAtencionDomicilio();
		
		this.setChanged();
		if (rta)
			this.notifyObservers(this.estado.toString());
		else
			this.notifyObservers(this.estado.toString() + ", no puede atender a domicilio");
		return rta;
		
	}

	/**
	 * metodo que informa que se busca cambiar su estado a traslado a clinica, se invoca el metodo que cambia estado e informa si se pudo lograr o no <br>
	 * @return rta de tipo boolean indica si se logro cambiar el estado actual o no
	 */
	public boolean solicitudTrasladoClinica() 
	{
		this.setChanged();
		this.notifyObservers("es llamada para traslado a clínica");
		
		boolean rta=this.estado.solicitudTrasladoClinica();
		
		this.setChanged();
		if (rta)
			this.notifyObservers(this.estado.toString());
		else
			this.notifyObservers(this.estado.toString() + ", no puede trasladar a la clínica");
		return rta;
	}

	/**
	 * metodo que informa que se busca cambiar su estado a volver a la clinica, invoca al metodo que cambiar a ese estado e informa si se pudo o no<br>
	 * @return rta de tipo boolean indica si se cambio o no el estado actual
	 */
	public boolean solicitudEsTiempoVolverClinica() 
	{
		this.setChanged();
		this.notifyObservers("es llamada para volver a clínica");
		
		boolean rta=this.estado.solicitudEsTiempoVolverClinica();
		
		this.setChanged();
		if (rta)
			this.notifyObservers(this.estado.toString());
		else
			this.notifyObservers(this.estado.toString() + ", no puede volver a la clínica");
		return rta;
	}


	/**
	 * metodo que informa que se busca cambiar el estado para reparar la ambulancia, invoca al metodo que cambia a ese estado, e informa si lo logra o no<br> 
	 * @return rta de tipo boolean indica si el estado actual fue modificado o no
	 */
	public boolean solicitudRepararAmbulancia() //cambiar boolean por int
	{
		this.setChanged();
		this.notifyObservers("es llamada para ser reparada");
		
		boolean rta=this.estado.solicitudRepararAmbulancia();
		
		this.setChanged();
		if (rta)
			this.notifyObservers(this.estado.toString());
		else
			this.notifyObservers(this.estado.toString() + ", no puede volver al taller");
		return rta;
	}

	
	public synchronized void temporizador(Temporizador t)
	{
		boolean rta=this.solicitudEsTiempoVolverClinica();
		while(!rta)
		{
			try 
			{
				wait();
				rta=this.solicitudEsTiempoVolverClinica();
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	
	public synchronized void pidoTraslado(IPaciente p)
	{
		boolean rta=this.solicitudTrasladoClinica();
		while(!rta)  //si es alguno de los dos, es que el hilo lo pudo cambiar, o no lo puede cambiar direct.
		{
			try 
			{
				//System.out.println("SE ROMPE TODO");
				wait();
				rta=this.solicitudTrasladoClinica();
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		notifyAll();
		}
	}
	
	
	public synchronized void pidoAtencion(IPaciente p)
	{
		boolean rta=this.solicitudAtencionDomicilio();
		while(!rta)  //si es alguno de los dos, es que el hilo lo pudo cambiar, o no lo puede cambiar direct.
		{
			try 
			{
				wait();
				rta=this.solicitudAtencionDomicilio();
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	
	
	public synchronized void irAlTaller()
	{
		boolean rta=this.solicitudRepararAmbulancia();
		while(!rta)
		{
			try 
			{
				wait();
				rta=this.solicitudRepararAmbulancia();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();	
	}

}