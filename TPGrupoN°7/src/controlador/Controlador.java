package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import excepciones.CantDiasInvalidosException;
import excepciones.ContratacionInvalidaException;
import excepciones.EgresoInvalidoException;
import excepciones.EspecialidadInvalidaException;
import excepciones.FechaInvalidaException;
import excepciones.HistoriaInvalidaException;
import excepciones.HonorarioInvalidoException;
import excepciones.JugoRobinhoException;
import excepciones.MatriculaInvalidaException;
import excepciones.MedicoRepetidoException;
import excepciones.NoFueLlamadoException;
import excepciones.NoHayEspacioException;
import excepciones.NoHayPacienteEsperandoException;
import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.PosgradoInvalidoException;
import excepciones.ReporteInvalidoException;
import excepciones.SeleccionIncorrectaException;
import excepciones.TipoPacienteInvalidoException;
import habitaciones.IHabitacion;
import hilos.Operario;
import hilos.Temporizador;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import persistencia.ClinicaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilClinica;
import vista.IVista;
import vista.PanelEstadoPaciente;
/**
 * @author usuario <br>
 *Clase que representa al controlador, se encarga de independizar la vista del modelo
 */
public class Controlador implements ActionListener
{
	private IVista vista=null;
	IPersistencia per = new PersistenciaBIN();
	
	//Ambulancia y clinica son singleton, por lo que no necesita un atrib 

	
	
	/**
	 * constructor de la clase, parametro IVista, se realiza la lectura de del archivo que contiene los objetos del sistema
	 * @param vista : permite llamar al metodo que sea necesario por el evento recibido
	 */
	public Controlador(IVista vista)
	{
		this.vista= vista;
		this.vista.setActionListener(this);
		Temporizador t = new Temporizador();
		t.start();
		this.vista.pacientemedicoSeleccionado();
		try
		{
		     per.abrirInput("Clinica.bin");
             ClinicaDTO cDTO=(ClinicaDTO)per.leer();
            // System.out.println("lectura con exito");
             UtilClinica.ClinicafromClinicaDTO(cDTO);
             per.cerrarInput();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			catch(IOException e) 
			{
				System.out.println(e.getMessage());
	        }
		
			//REFRESCA LISTAS, PARA QUE CARGUE NUEVAMENTE LOS MEDICOS DE LA PERSISTENCIA
		this.vista.actualizaListaHabitaciones(Clinica.getInstance().getIteratorHabitaciones());
		this.vista.actualizaAgregaListaMedicos(Clinica.getInstance().getIteratorMedicos());
		this.vista.actualizaAgregaListaPacientes(Clinica.getInstance().getIteratorHPacientes());
		PanelEstadoPaciente.getInstance().setPacientesObservables(Clinica.getInstance().gethPacientes());
		
	}

	

	/**
	 *metodo que permite llamar al metodo de la interfaz IVista para la accion correspondiente segun el tipo de evento recibido de la interfaz de usuario<br>
	 *al final de cada accion persiste el estado actual del sistema
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		this.vista.pacientemedicoSeleccionado();
		if (comando.equalsIgnoreCase("Agregar"))  
			this.Agregar(comando);
		else if (comando.equalsIgnoreCase("Eliminar"))  
			this.Eliminar();
		else if (comando.equalsIgnoreCase("ReparaAmbulancia"))
			this.repararAmbulancia();
		else if (comando.equalsIgnoreCase("Traslado") || comando.equalsIgnoreCase("Atencion")) 
			this.trasladoAtencion(comando);				
		else if (comando.equalsIgnoreCase("PRESTACION"))
			this.prestacion();		
		else if (comando.equalsIgnoreCase("FACTURAR")) 	//hace el egreso
			this.Facturar();			
		else if (comando.equalsIgnoreCase("REPORTE")) 
			this.Reporte();
		else if (comando.equalsIgnoreCase("LlamaPaciente")) 
			this.LlamarPaciente();
		else if(comando.equalsIgnoreCase("INGRESO"))
			this.Ingreso();
		else if(comando.equalsIgnoreCase("DESELECCIONAR"))
			this.Deseleccionar();
			
		
		this.vista.soltarSeleccion();
		this.guardaPersistencia();
	}


	
	/**
	 * metodo que acciona la invocacion del metodo agrega pacientes y medicos, invoca a otro metodo para actualizar las listas y tambien se encarga de validar los datos <br>
	 * @param comando indica el tipo de objeto a crear y agregar
	 */
	public void Agregar(String comando)
	{
		if (this.vista.getTipoAgregarSeleccionado()=="Paciente") //recibe action comand
		{	
			try 
			{
				String rango = this.vista.getRangoEtario();
				IPaciente paciente = PacienteFactory.getPaciente(this.vista.getDNINuevo(),this.vista.getNomNuevo(),this.vista.getTelNuevo(), this.vista.getDomNuevo(), this.vista.getCiudadNuevo(), this.vista.getMatriculaNuevo(), this.vista.getRangoEtario());
				Clinica.getInstance().ingresoPaciente(paciente);
				PanelEstadoPaciente.getInstance().agregarObservables(Clinica.getInstance().getIteratorHPacientes());
				this.vista.actualizaAgregaListaPacientes(Clinica.getInstance().getIteratorHPacientes());
			}
			catch (PacienteRepetidoException pre) 
			{
				JOptionPane.showMessageDialog(null, "PACIENTE REPETIDO");
			}	
			catch(PacienteYaIngresadoException pi)
			{
				JOptionPane.showMessageDialog(null, "PACIENTE YA INGRESADO, ERROR");	
			}						
			catch (TipoPacienteInvalidoException tpie) 
			{ 
				JOptionPane.showMessageDialog(null, "TIPO DE PACIENTE INVALIDO");
			} 
			catch (SeleccionIncorrectaException e) 
			{
				JOptionPane.showMessageDialog(null, "SELECCIONE RANGO ETARIO");
			}
			catch(HistoriaInvalidaException hie)
			{
				JOptionPane.showMessageDialog(null, "INGRESE UN NÚMERO DE HISTORIA CLÍNICA VÁLIDO");	
		    }
			catch (MatriculaInvalidaException mie)
			{
				JOptionPane.showMessageDialog(null, "INGRESE UN NÚMERO DE MATRÍCULA VÁLIDO");
			}

		}
		else   //hacer ifs para comprobar si esta presionado: permanente, residente etc...
			if(this.vista.getTipoAgregarSeleccionado()=="Medico")
			{	
				try 
				{
					IMedico medico = MedicoFactory.getMedico(this.vista.getNomNuevo(),this.vista.getDNINuevo(), this.vista.getDomNuevo(), this.vista.getCiudadNuevo(), this.vista.getTelNuevo(), this.vista.getMatriculaNuevo(), this.vista.getEspecialidad(), this.vista.getContratacion(), this.vista.getPosgrado(), this.vista.getHonorario());
					Clinica.getInstance().agregaMedico(medico);
					this.vista.actualizaAgregaListaMedicos(Clinica.getInstance().getIteratorMedicos());
				} 
				catch (PosgradoInvalidoException pos) 
				{
					JOptionPane.showMessageDialog(null, "Seleccione posgrado");
				} 
				catch (ContratacionInvalidaException con) 
				{
					JOptionPane.showMessageDialog(null, "Seleccione contratacion");
				} 
				catch (MedicoRepetidoException med) 
				{
					JOptionPane.showMessageDialog(null, "Medico repetido");
				}
				catch (HonorarioInvalidoException hon)
				{
					JOptionPane.showMessageDialog(null, "Ingrese honorario correcto");	
				} 
				catch (EspecialidadInvalidaException e) 
				{
					JOptionPane.showMessageDialog(null, "Seleccione especialidad");
				} 	
				catch(HistoriaInvalidaException hie)
				{
					JOptionPane.showMessageDialog(null, "INGRESE UN NÚMERO DE HISTORIA CLÍNICA VÁLIDO");	
			    }
				catch (MatriculaInvalidaException mie)
				{
					JOptionPane.showMessageDialog(null, "INGRESE UN NÚMERO DE MATRÍCULA VÁLIDO");
				}
		}
		else JOptionPane.showMessageDialog(null, "Seleccione Medico o Paciente");	
	}
	
	/**
	 * metodo que acciona el metodo que elimina medicos y pacientes de las listas del sistema de la Interfaz IVista, se invoca al metodo actualiza listas y se valida los datos <br> 
	 */
	public void Eliminar()
	{
		IPaciente paciente= this.vista.getPacienteSeleccionado();
		IMedico medico= this.vista.getMedicoSeleccionado();
		if  (paciente==null && medico==null)
		 	JOptionPane.showMessageDialog(null, "Seleccione un paciente o medico de la lista");
		if (paciente!=null && medico!=null)
			JOptionPane.showMessageDialog(null, "Seleccione solo un paciente o solo un medico");
		else
		{	
			if (paciente!=null) //algun socio selecciono
			{	
				try
				{
					Clinica.getInstance().eliminaSinEgreso(paciente);
					this.vista.actualizaEliminaListaPacientes(Clinica.getInstance().getIteratorHPacientes(),paciente);
				}
				catch(JugoRobinhoException r)
				{
					JOptionPane.showMessageDialog(null, "No puede eliminarse el paciente si no pagó las prestaciones");	
				}
			}
			if (medico!=null)  
			{
				this.vista.actualizaEliminaListaMedicos(Clinica.getInstance().getIteratorMedicos(),medico);
				Clinica.getInstance().eliminaMedico(medico);
			}
		}	
	}
	
	/**
	 * metodo que crea thread y comienza la ejecucion del operario al seleccionarse el boton en la interfaz para reparar la ambulancia
	 */
	public void repararAmbulancia()
	{
		Operario op=Operario.getInstance();
		if(op!=null) //redundante
		{
			Thread h = new Thread(op);
			h.start();
		} 
	}
	
	/**
	 * metodo que inicia la ejecucion del paciente para que intente pedir un traslado o atencion a domicilio de la ambulancia <br>
	 * @param comando indica si es pedido de traslado o atencion a domicilio
	 */
	public void trasladoAtencion(String comando)
	{
		IPaciente paciente = this.vista.getPacienteSeleccionado();
		if (paciente!=null)
		{
			paciente.setPedido(comando);
			Thread h = new Thread(paciente);
			h.start();
		}
		else
			JOptionPane.showMessageDialog(null, "Seleccione un paciente de la lista");		
	}
	
	/**
	 * metodo que acciona la realizacion de una prestacion en el modelo y valida los datos antes
	 */
	public void prestacion()
	{
		IPaciente paciente = this.vista.getPacienteSeleccionado();
		if (paciente!=null)   //hay alguien
		{
			IMedico medico = this.vista.getMedicoSeleccionado();
			if(medico!=null) //tiene medico
			{
				IHabitacion habitacion=this.vista.getHabitacionSeleccionada();
				if(habitacion!=null)
				{
					try 
					{
						int cantDias=this.vista.getCantidadDias();
						Clinica.getInstance().Prestaciones(paciente, medico,cantDias,habitacion);
						this.vista.actualizaListaHabitaciones(Clinica.getInstance().getIteratorHabitaciones());
					}
					catch(NoHayEspacioException e2)
					{
						JOptionPane.showMessageDialog(null, "No hay espacio disponible en esa habitacion, selecciona otra");
					}
					catch(NoFueLlamadoException e3)
					{
						JOptionPane.showMessageDialog(null, "Para realizar las prestaciones, primero debe ser llamado");
					}
					catch(CantDiasInvalidosException cd)
					{
						JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida de dias");
					}	
					catch(NumberFormatException f1)
					{
						JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida de dias");
					}
				}
				else JOptionPane.showMessageDialog(null, "Seleccione una habitacion");
			}
			else JOptionPane.showMessageDialog(null, "Seleccione un medico de la lista");
		}
		else JOptionPane.showMessageDialog(null, "Seleccione un paciente de la lista");
	}
	
	
	public void Facturar()
	{
		IPaciente pac = this.vista.getPacienteSeleccionado();  //vamos a unificar seguramente
		if(pac!=null)
		{
			try 
			{
				String factura = Clinica.getInstance().egreso(pac, new GregorianCalendar());  //cambiar el gregorian calendar
				this.vista.actualizaFactura(factura);  //aca mandaria para mostrar por pantalla
			}
			catch(EgresoInvalidoException egreso)
			{
				JOptionPane.showMessageDialog(null, "El paciente nunca recibio prestaciones o ya las recibio");
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Seleccione un paciente de la lista");
	}
	
	
	public void Reporte()
	{
		IMedico medico = this.vista.getMedicoSeleccionado();
		if (medico!=null)
		{
			try 
			{
				GregorianCalendar f1=this.vista.getFecha1();
				GregorianCalendar f2=this.vista.getFecha2();
				String reporte=Clinica.getInstance().ReporteFechas(f1, f2,medico);
				this.vista.actualizaFactura(reporte);
			} catch (FechaInvalidaException e1) 
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Ingrese fecha valida");
			} 
			catch(ReporteInvalidoException rep)
			{
				JOptionPane.showMessageDialog(null, "El medico no realizo consultas");
			}
		}
		else JOptionPane.showMessageDialog(null, "Seleccione un medico de la lista");
	}

	
	/**
	 * metodo que acciona al metodo llamar paciente del modelo, valida que haya pacientes en espera 
	 */
	public void LlamarPaciente()
	{
		try 
		{
			IPaciente pac=Clinica.getInstance().llamarPaciente();
			JOptionPane.showMessageDialog(null, "El paciente "+pac.getNombre()+" fue llamado");
		}
		catch (NoHayPacienteEsperandoException e1) 
		{
			JOptionPane.showMessageDialog(null, "No hay pacientes esperando");	
		}
	}
	
	/**
	 * metodo que acciona al metodo ingreso de la clinica, valida que el paciente no este ingresado 
	 */
	public void Ingreso()
	{
		IPaciente p= this.vista.getPacienteSeleccionado();
		if (p!=null)
		{
			try 
			{
				Clinica.getInstance().ingresoNuevamente(p);
			}
			catch(PacienteYaIngresadoException pi)
			{
				JOptionPane.showMessageDialog(null, "PACIENTE YA INGRESADO, ERROR");	
			}
		}
		else JOptionPane.showMessageDialog(null, "Seleccione un paciente de la lista");	
	}
	
		
	public void Deseleccionar()
	{
		this.vista.soltarSeleccion();
	}
	
/**
 * metodo que guarda el estado actual del sistema luego de que alguna accion se produjo
 */
public void guardaPersistencia()
{
	try
    {
        per.abrirOutput("Clinica.bin");
        per.escribir(UtilClinica.ClinicaDTOfromClinica());
        per.cerrarOutput();
    } catch (Exception ex)
    {
        // TODO Auto-generated catch block
        System.out.println("Exception " + ex.getMessage());
    }

}





	
}
