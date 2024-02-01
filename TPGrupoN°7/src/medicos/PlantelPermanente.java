package medicos;

/**
 * @author usuario
 * Clase plantelPermanente que se extiende del decorator de contratación. Plantel permanente es una forma de contratación que afecta al honorario del médico<br> 
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
