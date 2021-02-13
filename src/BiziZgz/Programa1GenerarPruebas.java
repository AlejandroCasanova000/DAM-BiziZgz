package BiziZgz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Esta clase Java se encarga de coger los ficheros usos-16.csv y usos-17.csv y generar dos .csv que 
 * tengan las respectivas 10 y 2000 primeras líneas, para poder trabajar mejor.
 * @author alexc y Dana
 *
 */

public class Programa1GenerarPruebas {
	
	/**
	 * Pre: Este método solo define los paths y llama a los metodos para generar los ficheros
	 * @param args
	 */
	public static void main(String[] args) {
		String pathOrigen10 = "Files/usos-16.csv";
		String pathDestino10 = "Files/pruebas10.csv";
		String pathOrigen2000 = "Files/usos-17.csv";
		String pathDestino2000 = "Files/pruebas2000.csv";
		generarPruebas10(pathOrigen10, pathDestino10);
		generarPruebas2000(pathOrigen2000, pathDestino2000);
	}
	
	/**
	 * Pre: Este metodo genera un fichero pruebas10.csv que tiene las 10 primeras lineas del fichero
	 * usos-16.csv
	 * Post: Para ello se crean las variables de los tipos necesarios, y se hace un bucle 10 veces en el cual
	 * en cada iteracion se lee una línea del fichero origen y se escribe en el destino.
	 * @param pathOrigen Ruta del Archivo Original
	 * @param pathDestino	Ruta del fichero donde se copian las lineas
	 */
	public static void generarPruebas10 (String pathOrigen, String pathDestino) {
		File ficheroOrigen = new File(pathOrigen);
		try {
			Scanner fOrigen = new Scanner(ficheroOrigen);//Variable Scanner para leer fichero
			Formatter salida = new Formatter(pathDestino);//Variable formatter para esribir en fichero
			for (int i = 0; i < 10; i++) {
				salida.format(fOrigen.nextLine() + "\n");
			}
			System.out.println("Fichero pruebas10 generado correctamente");
			fOrigen.close();
			salida.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Pre: Este metodo genera un fichero pruebas2000.csv que tiene las 2000 primeras lineas del fichero
	 * usos-17.csv
	 * Post: Para ello se crean las variables de los tipos necesarios, y se hace un bucle 2000 veces en el cual
	 * en cada iteracion se lee una línea del fichero origen y se escribe en el destino.
	 * @param pathOrigen Ruta del Archivo Original
	 * @param pathDestino	Ruta del fichero donde se copian las lineas
	 */
	public static void generarPruebas2000 (String pathOrigen, String pathDestino) {
		File ficheroOrigen = new File(pathOrigen);
		try {
			Scanner fOrigen = new Scanner(ficheroOrigen);//Variable Scanner para leer fichero
			Formatter salida = new Formatter(pathDestino);//Variable formatter para esribir en fichero
			for (int i = 0; i < 2000; i++) {
				salida.format(fOrigen.nextLine() + "\n");
			}
			System.out.println("Fichero pruebas2000 generado correctamente");
			fOrigen.close();
			salida.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
