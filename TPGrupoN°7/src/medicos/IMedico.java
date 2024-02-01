package medicos;

import java.io.Serializable;
	
/**
 * @author usuario
 * Interfaz IHabitacion en donde se declararan los metodos a implementar para la clase médico y sus extensiones <br>
 */
public interface IMedico extends Serializable {


	/**Método double destinado a calcular el honorario de un médico<br>
	 * @return retorna un valor de tipo double con el honorario calculado
	 */
	double calculaHonorario ();
	
	
	String getNombre();
	
	boolean equals(Object obj);
	int hashCode();
	
	String toString();
	
	
	


}
