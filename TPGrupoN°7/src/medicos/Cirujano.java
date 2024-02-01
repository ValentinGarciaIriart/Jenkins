package medicos;

/**
 * @author usuario
 * Subclase cirujano que se extiende de la clase medico <br>
 */
public class Cirujano extends Medico {



	public Cirujano(String nomAp, String dni, String domicilio, String ciudad, String telefono, int numMatricula,
			double honorario) {
		super(nomAp, dni, domicilio, ciudad, telefono, numMatricula, honorario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculaHonorario() {
		
		return (honorarioBase*1.1);
	}






}
