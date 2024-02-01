package hilos;

import java.util.Random;

/**
 * @author usuario <br>
 * clase util que implementa una pausa de un numero aleatorio de segundos para los hilos del sistema <br>
 */
public class Util {
	private static Random r= new Random();
	public static void espera() {
		try {
			Thread.sleep(r.nextInt(3000));
		}
		catch(InterruptedException e) {
			
		}
	}
	

}
