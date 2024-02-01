package medicos;

/**
 * @author usuario
 * Clase residente que se extiende del decorator de contratación. Residente es una forma de contratación que afecta al honorario del médico <br>
 */
public class Residente extends DecoratorContratacion {

	public Residente(IMedico encapsuladobis) {
		super(encapsuladobis);
	}

	@Override
	public double calculaHonorario() {
		return (this.encapsuladobis.calculaHonorario()*1.05);			
	}



}