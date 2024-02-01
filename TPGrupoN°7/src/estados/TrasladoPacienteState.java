package estados;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa el estado de la ambulancia cuando se encuentra trasladando un paciente a la clinica <br>
 * contiene los metodos para modificar o no el estado actual de la ambulancia
 */
public class TrasladoPacienteState implements IState
{
	
	private Ambulancia a;
	
	public TrasladoPacienteState(Ambulancia a)
	{
		this.a=a;
	}

	@Override
	public boolean solicitudAtencionDomicilio() {
		//System.out.println("No se puede atender a domicilio porque se está trasladando un paciente a la clínica");
		return false;
	}

	@Override
	public boolean solicitudTrasladoClinica() {
		//System.out.println("No se puede trasladar un paciente a la clínica porque se está trasladando otro paciente");
		return false;
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
		//System.out.println("No se puede reparar la ambulancia porque se está trasladando un paciente a la clínica");
		return false;
	}

	@Override
	public String toString() {
		return "está trasladando un paciente a la clínica";
	}
	
	


	
	
	
	
	
}