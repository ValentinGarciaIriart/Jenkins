package estados;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa el estado de la ambulancia cuando se encuentra regresando de una atencion a domicilio<br>
 * contiene los metodos para modificar o no su estado actual
 */
public class RegresandoAtencionSinPacienteState implements IState
{

	
	private Ambulancia a;
	
	public RegresandoAtencionSinPacienteState(Ambulancia a) {
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
	public boolean solicitudTrasladoClinica() {
		//System.out.println("No puede porque está regresando de una atención sin paciente");
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
	public boolean solicitudRepararAmbulancia() {
		//System.out.println("Ahora la ambulancia esta en el taller");
		this.a.setEstado(new TallerState(this.a));
		return true;
		//System.out.println("No puede porque esta regresando de una atencion sin paciente");
	}

	@Override
	public String toString() {
		return "está regresando a la clínica sin paciente";
	}


}
