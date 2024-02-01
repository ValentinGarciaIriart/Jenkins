package habitaciones;

import java.util.ArrayList;

import pacientes.IPaciente;

/**
 * @author Clase abstracta habitación que representa parte de las habitaciones de la clínica<br>
 * <br>
 */
public abstract class Habitacion implements IHabitacion {

	protected double costoAsignacion;
	

	public Habitacion(double costoAsignacion) {
		this.costoAsignacion = costoAsignacion;
	}



	@Override
	public double getCostoAsignacion() {
		return this.costoAsignacion;
	}


	
	
}