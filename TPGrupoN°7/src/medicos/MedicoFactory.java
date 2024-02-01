package medicos;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;

/**
 * @author usuario
 *Clase Factory en donde se generarán los distintos médicos <br>
 */
public class MedicoFactory {
	

	/**	/**Metodo static de tipo IMedico que instancia el médico a generarse<br>
	 * <b> Pre: </b> los strings de especialidad, contratacion y posgrado deben ser distinto de null o erróneos <br>
	 * <b> Post: </b> devuelve un parámetro respuesta de tipo IHabitacion <br>
	 * @param nomAp : nombre y apellido del médico<br>
	 * @param dni<br>
	 * @param domicilio<br>
	 * @param ciudad<br>
	 * @param tel<br>
	 * @param numMat : número de matrícula del médico<br>
	 * @param especialidad<br>
	 * @param contratacion<br>
	 * @param posgrado<br>
	 * @return retorna un médico con una determinada especialidad, contratación y posgrado<br>
	 * @throws PosgradoInvalidoException : lanza una excepción en el caso de que el posgrado sea erróneo o nulo
	 * @throws EspecialidadInvalidaException : lanza una excepcion en el caso de que la especialidad sea erronea o nula
	 * @throws ContratacionInvalidaException : lanza una excepcion en el caso de que la contratacion sea erronea o nula
	 */
	public static IMedico getMedico(String nomAp,String dni,String domicilio,String ciudad,String tel,int numMat, String especialidad, String contratacion, String posgrado, double honorario)throws PosgradoInvalidoException,ContratacionInvalidaException,EspecialidadInvalidaException {
		
		IMedico encapsulado=null;
		IMedico encapsuladobis=null;
		IMedico respuesta = null;
		  
		  if(especialidad.equalsIgnoreCase("Clinico")) 
			  encapsulado = new Clínico(nomAp,dni,domicilio,ciudad,tel,numMat,honorario);
		  else if (especialidad.equalsIgnoreCase("Cirujano"))
			  encapsulado = new Cirujano(nomAp,dni,domicilio,ciudad,tel,numMat,honorario);
		  else if (especialidad.equalsIgnoreCase("Pediatra"))
			  encapsulado = new Pediatra(nomAp,dni,domicilio,ciudad,tel,numMat,honorario);
		  else
			  throw new EspecialidadInvalidaException("No existe esta especialidad en la clínica: ", especialidad);
		  
		  if(encapsulado != null) {
			  if(posgrado.equalsIgnoreCase("Doctorado"))
				  encapsuladobis = new Doctorado(encapsulado);
			  else if (posgrado.equalsIgnoreCase("Magister"))
				  encapsuladobis = new Magister(encapsulado);
			  else
				 throw new PosgradoInvalidoException("No contamos con médicos con este tipo de posgrado: ", posgrado);
		  }
		  if(encapsuladobis != null) {
			  if(contratacion.equalsIgnoreCase("Permanente"))
				  respuesta = new PlantelPermanente(encapsuladobis);
			  else if (contratacion.equalsIgnoreCase("Residente"))
				  respuesta = new Residente(encapsuladobis);
			  else 
				  throw new ContratacionInvalidaException("No existe un médico con este tipo de contratación: ",contratacion);
		  }
			  return respuesta;
			
	}

}