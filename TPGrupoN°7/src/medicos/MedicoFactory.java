package medicos;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;

/**
 * @author usuario
 *Clase Factory en donde se generar�n los distintos m�dicos <br>
 */
public class MedicoFactory {
	

	/**	/**Metodo static de tipo IMedico que instancia el m�dico a generarse<br>
	 * <b> Pre: </b> los strings de especialidad, contratacion y posgrado deben ser distinto de null o err�neos <br>
	 * <b> Post: </b> devuelve un par�metro respuesta de tipo IHabitacion <br>
	 * @param nomAp : nombre y apellido del m�dico<br>
	 * @param dni<br>
	 * @param domicilio<br>
	 * @param ciudad<br>
	 * @param tel<br>
	 * @param numMat : n�mero de matr�cula del m�dico<br>
	 * @param especialidad<br>
	 * @param contratacion<br>
	 * @param posgrado<br>
	 * @return retorna un m�dico con una determinada especialidad, contrataci�n y posgrado<br>
	 * @throws PosgradoInvalidoException : lanza una excepci�n en el caso de que el posgrado sea err�neo o nulo
	 * @throws EspecialidadInvalidaException : lanza una excepcion en el caso de que la especialidad sea erronea o nula
	 * @throws ContratacionInvalidaException : lanza una excepcion en el caso de que la contratacion sea erronea o nula
	 */
	public static IMedico getMedico(String nomAp,String dni,String domicilio,String ciudad,String tel,int numMat, String especialidad, String contratacion, String posgrado, double honorario)throws PosgradoInvalidoException,ContratacionInvalidaException,EspecialidadInvalidaException {
		
		IMedico encapsulado=null;
		IMedico encapsuladobis=null;
		IMedico respuesta = null;
		  
		  if(especialidad.equalsIgnoreCase("Clinico")) 
			  encapsulado = new Cl�nico(nomAp,dni,domicilio,ciudad,tel,numMat,honorario);
		  else if (especialidad.equalsIgnoreCase("Cirujano"))
			  encapsulado = new Cirujano(nomAp,dni,domicilio,ciudad,tel,numMat,honorario);
		  else if (especialidad.equalsIgnoreCase("Pediatra"))
			  encapsulado = new Pediatra(nomAp,dni,domicilio,ciudad,tel,numMat,honorario);
		  else
			  throw new EspecialidadInvalidaException("No existe esta especialidad en la cl�nica: ", especialidad);
		  
		  if(encapsulado != null) {
			  if(posgrado.equalsIgnoreCase("Doctorado"))
				  encapsuladobis = new Doctorado(encapsulado);
			  else if (posgrado.equalsIgnoreCase("Magister"))
				  encapsuladobis = new Magister(encapsulado);
			  else
				 throw new PosgradoInvalidoException("No contamos con m�dicos con este tipo de posgrado: ", posgrado);
		  }
		  if(encapsuladobis != null) {
			  if(contratacion.equalsIgnoreCase("Permanente"))
				  respuesta = new PlantelPermanente(encapsuladobis);
			  else if (contratacion.equalsIgnoreCase("Residente"))
				  respuesta = new Residente(encapsuladobis);
			  else 
				  throw new ContratacionInvalidaException("No existe un m�dico con este tipo de contrataci�n: ",contratacion);
		  }
			  return respuesta;
			
	}

}