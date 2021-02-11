package BiziZgz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase Java, dado el path de un fichero .csv con la estructura determinada, muestra el numero totales de UsosBizi y la
 * el numero de recorridos si son Usos Circulares (RetiroEstacion y AnclajeEstacion son iguales), o si son traslados.
 * @author alexc y Dana
 *
 */
public class Programa2ContarUsos {
	
	/**
	 * Este metodo unicamente pregunta al usuario por un path, y llama a los metodos necesarios.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		String path = "";
		while (path.equals("")) {
			System.out.println("Intoduce el path + nombre del fichero a analizar");
			path = entrada.next();
		}
		ArrayList<UsoBizi> usos = mostrarInformacionResumida(path);
		analizarRecorridos(usos);
		entrada.close();
	}
	
	/**
	 * Pre: Este metodo dado un path de un fichero .csv, asigna UsosBizi a cada línea del fichero
	 * Post: Para ello se recoore cada linea del fichero creando un UsoBBizi a cada una de ellas con sus respectivos
	 * atributos.
	 * @param path Ruta del fichero
	 * @return
	 */
	public static ArrayList<UsoBizi> mostrarInformacionResumida(String path) {
		UsoBizi uso;
		ArrayList<UsoBizi> usos = new ArrayList<UsoBizi>();
		File fichero = new File(path);
		try {
			Scanner f = new Scanner(fichero);
			while (f.hasNextLine()) {
				String[] lineaSeparada = f.nextLine().split(";");
				if(isInteger(lineaSeparada[0])) {
					uso = new UsoBizi(Integer.parseInt(lineaSeparada[0]), lineaSeparada[1], Integer.parseInt(lineaSeparada[2]),
							lineaSeparada[3], Integer.parseInt(lineaSeparada[4]));
					usos.add(uso);
				} else {
					f.nextLine();
				}
			}
			System.out.println("Fichero " + path + " analizado con éxito: ");
			f.close();
			return usos;
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido abrir el fichero " + path);
			return null;
		}
	}
	
	/**
	 * Pre: Este metodo, dado una lista de UsosBizi [usos], analiza el numero total de recorridos circulares o traslados
	 * Post: Para ello se recorre la lista y si coinciden las estaciones de anclaje y retiro se suma uno al cCirculos, si no
	 * se suma uno al cTraslados
	 * @param usos Lista de usos
	 */
	public static void analizarRecorridos (ArrayList<UsoBizi> usos) {
		int cTraslados = 0;
		int cCirculos = 0;
		for (UsoBizi uso : usos) {
			if (uso.getAnclajeEstacion() == uso.getRetiroEstacion()) {
				cCirculos++;
			} else {
				cTraslados++;
			}
		}
		System.out.println("Número de usos como Traslados: " + cTraslados);
		System.out.println("Número de usos circulares: " + cCirculos);
		System.out.println("Número total de usos: " + usos.size());

	}
	
	/**
	 * Pre: Este metodo devuelve true si un string se puede pasar a int, si no, devuelve false
	 * Post: Para ello se utiliza el try y catch, se intenta pasar el string [respuesta] a int haciendo uso
	 * del metodo java Integer.parseInt(), si este metodo devuelve el error java.lang.NumberFormatException, el catch
	 * devolvera flase, si no salta ningun error, devuelve true.
	 * @param respuesta
	 * @return
	 */
	public static boolean isInteger (String respuesta) {
		try {
			int numero = Integer.parseInt(respuesta);
			numero = numero + 1;
			return true;
		} catch(java.lang.NumberFormatException e) {
			return false;
		}
	}
}
