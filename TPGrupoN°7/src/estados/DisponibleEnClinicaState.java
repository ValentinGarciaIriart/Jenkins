package estados;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa el estado de la ambulancia cuando se encuentra disponible en la clinica, con sus metodos para modificar el estado o no
 */
public class DisponibleEnClinicaState implements IState
{

	private Ambulancia a;
	
	public DisponibleEnClinicaState(Ambulancia a) 
	{
		this.a=a;
	}
	
	@Override
	public boolean solicitudAtencionDomicilio() 
	{
		//System.out.println("Ahora la ambulancia esta atendiendo un paciente a domicilio");
		this.a.setEstado(new AtencionPacienteDomicilioState(this.a));
		return true;
	}

	@Override
	public boolean solicitudTrasladoClinica() 
	{	
		//System.out.println("Ahora la ambulancia esta regresando a la clinica, sin pacientes");
		//this.a.setEstado(new RegresandoAtencionSinPacienteState(this.a));
		this.a.setEstado(new TrasladoPacienteState(this.a));
		return true;
	}

	@Override
	public boolean solicitudEsTiempoVolverClinica() 
	{
		//System.out.println("La ambulancia ya estaba en la clinica");
		return true;
	}

	@Override
	public boolean solicitudRepararAmbulancia()
	{
		//System.out.println("Ahora la ambulancia esta en el taller");
		this.a.setEstado(new TallerState(this.a));
		return true;
	}

	@Override
	public String toString() {
		return "está disponible en la clínica";
	}


}
