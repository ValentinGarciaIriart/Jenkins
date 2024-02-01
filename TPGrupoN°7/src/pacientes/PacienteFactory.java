package pacientes;

import excepciones.TipoPacienteInvalidoException;

/**
 * @author
 * Clase Factory en donde se generar�n los distintos pacientes seg�n su rangoEtario : ni�o, joven y mayor 
 */
public class PacienteFactory {

	/**Metodo static en donde se instancian los pacientes. Consta de los siguientes parametros:
	 * @param dni : string, documento del paciente<br>
	 * @param nomAp : string, nombre y apellido del paciente<br>
	 * @param telefono : string, telefono del paciente<br>
	 * @param domicilio : string, domicilio donde reside el paciente<br>
	 * @param ciudad : string, ciudad donde vive el paciente<br>
	 * @param numHistoria : int, numero de historia clinica del paciente<br>
	 * @param rangoEtario : string, rangoEtario del paciente (acorde a su edad)<br>
	 * <b> Pre : </b> Paciente tiene que ser distinto de null, adem�s de sus parametros<br>
	 * <b> Post : </b> Obtener un paciente de tipo ni�o,joven o mayor<br>
	 * @return retorna un paciente con su subclase determinada por el rangoEtario<br>
	 * @throws TipoPacienteInvalidoException : lanza una excepci�n si el rangoEtario ingresado es nulo o err�neo
	 */
	public static IPaciente getPaciente(String dni, String nomAp, String telefono, String domicilio, String ciudad, int numHistoria,String rangoEtario) throws TipoPacienteInvalidoException {
		
		IPaciente respuesta = null;
		 	
		if(rangoEtario == "Ni�o")
			respuesta = new Ni�o(dni,nomAp,telefono,domicilio,ciudad,numHistoria);
		else if (rangoEtario == "Joven")
			respuesta = new Joven(dni,nomAp,telefono,domicilio,ciudad,numHistoria);
		else if (rangoEtario == "Mayor")
			respuesta = new Mayor(dni,nomAp,telefono,domicilio,ciudad,numHistoria);
		if(respuesta!=null)	
			 return respuesta;
			else 
				throw new TipoPacienteInvalidoException("No corresponde este rango etario",rangoEtario);
	}
}
