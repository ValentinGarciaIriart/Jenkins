package pacientes;

/**
 * @author 
 * Clase ni�o que se extiende la superclase Paciente e implementa la interfaz IPaciente
 */
public class Ni�o extends Paciente {

	public Ni�o(String dni, String nomAp, String telefono, String domicilio, String ciudad, int numHistoria) {
		super(dni, nomAp, telefono, domicilio, ciudad, numHistoria);
	}

	@Override
	public boolean beats(IPaciente o) {
		return o.beatsNi�o();
	}

	@Override
	public boolean beatsNi�o() {
		return true;
	}

	@Override
	public boolean beatsJoven() {
		return false;
	}

	@Override
	public boolean beatsMayor() {
		return true;
	}


}