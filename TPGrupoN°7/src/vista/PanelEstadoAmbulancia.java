package vista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modulo.Ambulancia;
import java.awt.BorderLayout;

/**
 * @author usuario <br>
 * clase que contiene los metodos para poder mostrar correctamente el estado de la ambulancia durante la ejecucion en la interfaz de usuario<br>
 * implementa la interfaz observer y obtiene cada novedad del estado de la ambulancia
 */
public class PanelEstadoAmbulancia extends JPanel implements Observer //aca se actualizara el estado de la ambulancia
{
	
	private JScrollPane scrollPaneEstadoAmbulancia;
	private JTextArea textAreaEstadoAmbulancia;
	
	//deberia hacer singleton? siempre sera un solo panel.

	public PanelEstadoAmbulancia()
	{
		setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneEstadoAmbulancia = new JScrollPane();
		add(this.scrollPaneEstadoAmbulancia, BorderLayout.CENTER);
		
		this.textAreaEstadoAmbulancia = new JTextArea();
		this.scrollPaneEstadoAmbulancia.setViewportView(this.textAreaEstadoAmbulancia);
		

		Ambulancia.getInstance().addObserver(this);  //por ser singleton siempre tengo referencia a al ambulancia
		
	}
	
	/**
	 * metodo update de la interfaz observer, en donde el estado que pasó el observable por parámetro es el cambio de estado presente en el observable
	 * y es notificado a sus observadores, que en este caso es este panel, y el observador se encargar de reflejar ese cambio<br>
	 */
	@Override
	public void update(Observable o, Object arg) {
		Ambulancia a=(Ambulancia)o;
		if (a==Ambulancia.getInstance())
			this.textAreaEstadoAmbulancia.append("La ambulancia " + arg + "\n"); 
		else throw new IllegalArgumentException();
	}

}
