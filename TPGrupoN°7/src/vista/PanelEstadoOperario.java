package vista;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import hilos.Operario;
import modulo.Ambulancia;

/**
 * @author usuario <br>
 * clase que contiene a los metodos para poder mostrar correctamente el estado del operario en la interfaz de usuario <br>
 * implementa observer y obtiene el estado del operario con cada cambio
 */
public class PanelEstadoOperario extends JPanel implements Observer //aca se actualizara el estado de la ambulancia
{
	
	private JScrollPane scrollPaneEstadoOperario;
	private JTextArea txtAreaEstadoOperario;

	public PanelEstadoOperario()
	{

		setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneEstadoOperario = new JScrollPane();
		add(this.scrollPaneEstadoOperario, BorderLayout.CENTER);
		
		this.txtAreaEstadoOperario = new JTextArea();
		this.scrollPaneEstadoOperario.setViewportView(this.txtAreaEstadoOperario);
		Operario.getInstance().addObserver(this);  //por ser sing siempre tengo ref a operario
	}
	
	/**
	 * metodo update de la interfaz observer, en donde el estado que pasó el observable por parámetro es el cambio de estado presente en el observable
	 * y es notificado a sus observadores, que en este caso es este panel, y el observador se encargar de reflejar ese cambio<br>
	 */
	@Override
	public void update(Observable o, Object arg) {
		Operario op=(Operario)o;
		if (op==Operario.getInstance())
			this.txtAreaEstadoOperario.append("El operario " + op.getNombre() +" " + arg + "\n"); 
		
	}

}
