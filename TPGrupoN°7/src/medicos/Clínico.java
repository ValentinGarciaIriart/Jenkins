package medicos;

/**
 * @author usuario
 * Subclase cl�nico que se extiende de la clase m�dico<br>
 */
public class Cl�nico extends Medico {


	public Cl�nico(String nomAp, String dni, String domicilio, String ciudad, String telefono, int numMatricula,
			double honorario) {
		super(nomAp, dni, domicilio, ciudad, telefono, numMatricula, honorario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculaHonorario() {
		
		return (honorarioBase*1.05);
	}



	
}