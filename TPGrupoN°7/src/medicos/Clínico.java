package medicos;

/**
 * @author usuario
 * Subclase clínico que se extiende de la clase médico<br>
 */
public class Clínico extends Medico {


	public Clínico(String nomAp, String dni, String domicilio, String ciudad, String telefono, int numMatricula,
			double honorario) {
		super(nomAp, dni, domicilio, ciudad, telefono, numMatricula, honorario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculaHonorario() {
		
		return (honorarioBase*1.05);
	}



	
}