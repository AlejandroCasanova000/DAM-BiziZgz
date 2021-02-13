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
			f.nextLine();
			while (f.hasNextLine()) {
				String[] lineaSeparada = f.nextLine().split(";");
				uso = new UsoBizi(Integer.parseInt(lineaSeparada[0]), lineaSeparada[1], Integer.parseInt(lineaSeparada[2]),
						lineaSeparada[3], Integer.parseInt(lineaSeparada[4]));
				usos.add(uso);
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
}
