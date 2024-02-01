package estados;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa el estado de la ambulancia cuando se encuentra en el taller para reparacion <br>
 * contiene los metodos para modificar o no el estado actual
 */
public class TallerState implements IState
{

	
	private Ambulancia a;
	
	public TallerState(Ambulancia a) {
		this.a=a;
	}
	
	@Override
	public boolean solicitudAtencionDomicilio() {
		//System.out.println("No se puede solicitar atención a domicilio porque la ambulancia está en taller");
		return false;
	}

	@Override
	public boolean solicitudTrasladoClinica() 
	{
		//System.out.println("No se puede solicitar traslado a la clínica porque la ambulancia está en taller");
		return false;
	}

	@Override
	public boolean solicitudEsTiempoVolverClinica() 
	{
		//System.out.println("Ahora la ambulancia esta regresando del taller");
		this.a.setEstado(new RegresandoTallerState(this.a));
		return true;
		
	}

	@Override
	public boolean solicitudRepararAmbulancia() 
	{
		//System.out.println("La ambulancia ya se encuentra en el taller");
		return true;
	}


	@Override
	public String toString() {
		return "está en el taller";
	}


}
