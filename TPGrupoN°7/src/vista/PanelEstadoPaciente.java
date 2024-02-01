package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pacientes.IPaciente;
import pacientes.Paciente;

/**
 * @author usuario <br>
 * clase que contiene los metodos para poder mostrar correctamente el estado de cada paciente seleccionado en la interfaz de usuario<br>
 * implementa la interfaz observer y recibe las novedades sobre el estado de cada paciente 
 */
public class PanelEstadoPaciente extends JPanel implements Observer //aca se actualizara el estado de la ambulancia
{
	
	private static PanelEstadoPaciente instance = null;
	private JScrollPane scrollPanePanelEstadoPacientes;
	private JTextArea txtAreaEstadoPacientes;
	private ArrayList<IPaciente> pacientesObservables= new ArrayList<IPaciente>();
	

	public ArrayList<IPaciente> getPacientesObservables() {
		return pacientesObservables;
	}


	public void setPacientesObservables(ArrayList<IPaciente> hPacientes) 
	{
		this.agregarObservables(hPacientes.iterator());
	}


	private PanelEstadoPaciente()
	{
		setLayout(new BorderLayout(0, 0));
		
		this.scrollPanePanelEstadoPacientes = new JScrollPane();
		add(this.scrollPanePanelEstadoPacientes, BorderLayout.CENTER);
		
		this.txtAreaEstadoPacientes = new JTextArea();
		this.scrollPanePanelEstadoPacientes.setViewportView(this.txtAreaEstadoPacientes);
	}
	
	
	public static PanelEstadoPaciente getInstance() {
		if (instance == null)
			instance = new PanelEstadoPaciente();
		return instance;
	}


	
	public void agregarObservables(Iterator<IPaciente> iterator)
	{
		
		boolean rta=false;
		while (iterator.hasNext())
		{
		    Paciente paciente = (Paciente) iterator.next();
		    if (!this.pacientesObservables.contains(paciente))
		    {	
		    	this.pacientesObservables.add(paciente);
		    	paciente.addObserver(PanelEstadoPaciente.getInstance());
		    }
		}

	}
	
	public void eliminarObservables(IPaciente p)
	{
		Paciente o = (Paciente) p;
		o.deleteObserver(PanelEstadoPaciente.getInstance());
		this.pacientesObservables.remove(o);
	}
	
	/**
	 * metodo update de la interfaz observer, en donde el estado que pasó el observable por parámetro es el cambio de estado presente en el observable
	 * y es notificado a sus observadores, que en este caso es este panel, y el observador se encargar de reflejar ese cambio<br>
	 */
	@Override
	public void update(Observable o, Object arg) {
		Paciente a=(Paciente)o;
		if (this.pacientesObservables.contains(a))
			this.txtAreaEstadoPacientes.append("El paciente " + a.getNombre() +" "+ arg + "\n");
		else System.out.println("SIN REGISTRO DE ESTE TIPO");
		
	}

}