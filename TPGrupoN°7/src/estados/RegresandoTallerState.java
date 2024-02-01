package estados;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa el estado de la ambulancia cuando se encuentra regresando del taller <br>
 * contiene los metodos para la modificacion o no del estado actual
 */
public class RegresandoTallerState implements IState
{

	private Ambulancia a;
	
	public RegresandoTallerState(Ambulancia a) {
		this.a=a;
	}
	
	@Override
	public boolean solicitudAtencionDomicilio() {
		//System.out.println("Ahora la ambulancia esta atendiendo un paciente a domicilio");
		this.a.setEstado(new AtencionPacienteDomicilioState(this.a));
		return true;
		
	}

	@Override
	public boolean solicitudTrasladoClinica() {
		//System.out.println("Ahora la ambulancia esta trasladando a un paciente");
		this.a.setEstado(new TrasladoPacienteState(this.a));
		return true;
		
	}

	@Override
	public boolean solicitudEsTiempoVolverClinica() 
	{
		//System.out.println("Ahora la ambulancia esta disponible en la clinica");
		this.a.setEstado(new DisponibleEnClinicaState(this.a));
		return true;
	}

	@Override
	public boolean solicitudRepararAmbulancia() 
	{
		//System.out.println("La ambulancia no debería ir al taller, ya que está regresando de él");
		return false;
		
	}


	@Override
	public String toString() {
		return "está regresando del taller";
	}


	
	
	
	
}