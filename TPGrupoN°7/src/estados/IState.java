package estados;

/**
 * @author usuario <br>
 * Interfaz con los metodos para modelar el comportamiento de los distintos estados de la ambulancia
 */
public interface IState 
{
	
	boolean solicitudAtencionDomicilio();
	
	boolean solicitudTrasladoClinica();
	
	boolean solicitudEsTiempoVolverClinica();
	
	boolean solicitudRepararAmbulancia();	
	

	
}
