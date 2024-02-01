package estados;

import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que representa el estado de la ambulancia cuando esta atendiendo un paciente a domicilio con sus metodos para modificar o no el estado actual
 */
public class AtencionPacienteDomicilioState implements IState
{
	
	private Ambulancia a;
	
	public AtencionPacienteDomicilioState(Ambulancia a) 
	{
		this.a=a;
	}
	
	@Override
	public boolean solicitudAtencionDomicilio() 
	{
		//System.out.println("Es imposible porque ya se esta atendiendo un paciente a domicilio");
		return false;
	}

	@Override
	public boolean solicitudTrasladoClinica()
	{
		//System.out.println("Es imposible porque ya se esta atendiendo un paciente a domicilio");
		return false;
	}

	@Override
	public boolean solicitudEsTiempoVolverClinica()
	{
		//System.out.println("Ahora la ambulancia esta trasladando un paciente a la clinica");
		this.a.setEstado(new RegresandoAtencionSinPacienteState(this.a));
		return true;
	}

	@Override
	public boolean solicitudRepararAmbulancia() 
	{
		//System.out.println("Es imposible porque ya se esta atendiendo un paciente a domicilio");
		return false;
		
	}

	@Override
	public String toString() {
		return "está atendiendo a domicilio";
	}
	

	
	
}