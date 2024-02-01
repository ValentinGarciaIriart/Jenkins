package medicos;

/**
 * @author usuario
 * Clase residente que se extiende del decorator de contrataci�n. Residente es una forma de contrataci�n que afecta al honorario del m�dico <br>
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