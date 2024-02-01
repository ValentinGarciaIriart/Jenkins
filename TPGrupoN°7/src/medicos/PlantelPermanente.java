package medicos;

/**
 * @author usuario
 * Clase plantelPermanente que se extiende del decorator de contrataci�n. Plantel permanente es una forma de contrataci�n que afecta al honorario del m�dico<br> 
 */
public class PlantelPermanente extends DecoratorContratacion {

	public PlantelPermanente(IMedico encapsuladobis) {
		super(encapsuladobis);
	}

	@Override
	public double calculaHonorario() {
		return (this.encapsuladobis.calculaHonorario()*1.1);
		
	}
	


	
	


}
