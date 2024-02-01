package medicos;

/**
 * @author usuario
 * Clase Magister que se extiende de DecoratorPosgrado (significando, magister, un tipo de contratacion para los médicos) <br>
 */
public class Magister extends DecoratorPosgrado {

	public Magister(IMedico encapsulado) {
		super(encapsulado);
	}

	@Override
	public double calculaHonorario() {
		return (this.encapsulado.calculaHonorario()*1.05);
		
	}


}
