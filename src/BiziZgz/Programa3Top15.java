package BiziZgz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Esta clase Java se encarga de mostrar el top15 de usuarios con mas UsosBizi y cuantos son circulares o traslados de un
 * fichero .csv
 * @author alexc
 *
 */
public class Programa3Top15 {
	
	/**
	 * Pre:Este metodo va llamando a los otros para desempeñar la tarea
	 * @param args
	 */
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el path + nombre");
		String path = entrada.next();
		HashMap<Integer, ArrayList<UsoBizi>> ids = crearMapaUsuarios(path);
		mostrarTop15(ids);
		entrada.close();
	}

	/**
	 * Pre: Este metodo crea un HashMap<K: ID, V: Lista de usos con el mismo ID> del fichero con ruta [path]
	 * Post: Para ello se lee el fichero y en cada línea de guarda el ID en una variable, si el id no se encuentra en el
	 * HashMap se crea una nueva lista en su value y se añade el uso que define la linea del fichero, así al acabar el método,
	 * devuelve un HashMap que contiene todas las ID del fichero como keys asociadas a su value que es una lista de UsoBizi.
	 * @param path La ruta del fichero
	 * @return
	 */
	public static HashMap<Integer, ArrayList<UsoBizi>> crearMapaUsuarios(String path) {
		File fichero = new File(path);
		UsoBizi uso;
		HashMap<Integer, ArrayList<UsoBizi>> ids = new HashMap<Integer, ArrayList<UsoBizi>>();
		try {
			Scanner f = new Scanner(fichero);
			f.nextLine();
			while(f.hasNextLine()) {
				String[] lineaSeparada = f.nextLine().split(";");
				int id = Integer.parseInt(lineaSeparada[0]);
				uso = new UsoBizi(id , lineaSeparada[1], Integer.parseInt(lineaSeparada[2]),
						lineaSeparada[3], Integer.parseInt(lineaSeparada[4]));
				if(ids.containsKey(id)) {
					ids.get(id).add(uso);
				} else {
					ids.put(Integer.parseInt(lineaSeparada[0]), new ArrayList<UsoBizi>());
					ids.get(id).add(uso);
				}
			}
			f.close();
			return ids;
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + path + " no se ha podido abrir");
			return null;
		}
	}

	/**
	 * Pre: Este método muestra el top15 de usuarios con mas UsoBizi
	 * Post: Para ello se repite 15 veces el proceso de buscar el mayor, que se realiza recorriendo el HasMap y comparando
	 * la longitud de la lista de UsosBizi de cada Key buscando la mayor, una vez sacada la mayor se manda al método
	 * contarCirculares() que analiza si son usos circulares o traslados, y se formatea por pantalla. Se agrega el ID
	 * analizado a la lista idsAnalizados para que no se repita el mismo id a lo largo del top 15 gracias al método
	 * estaRegistrado()
	 * @param ids HasMap con los usuarios
	 */
	public static void mostrarTop15 (HashMap<Integer, ArrayList<UsoBizi>> ids) {
		System.out.format("+----------+-----------+----------+-------+%n");
		System.out.format("| Usuario  | Traslados | Circular | Total |%n");//Formats para la tabla
		System.out.format("+----------+-----------+----------+-------+%n");
		String leftAlignFormat = "| %-8s | %-9d | %-8d | %-5d |%n";//Cosas pa la tabla que no se ni de q van Alvaro
		ArrayList<Integer> idsAnalizados = new ArrayList<Integer>();
		int mayor = 0;
		int idMayor = 0;
		for(int i = 0; i < 15; i++) {
			for (Map.Entry<Integer, ArrayList<UsoBizi>> entrada : ids.entrySet()) {
				int ID = entrada.getKey();
				if(entrada.getValue().size() > mayor && !estaRegistrado(idsAnalizados, ID)) {
					mayor = entrada.getValue().size();
					idMayor = ID;
				}
			}
			idsAnalizados.add(idMayor);
			mayor = 0;
			int[] usosAnalizados = contarCirculares(ids.get(idMayor));
			System.out.format(leftAlignFormat, idMayor, usosAnalizados[1], usosAnalizados[0], ids.get(idMayor).size());
		}
		System.out.format("+----------+-----------+----------+-------+%n");
	}
	
	/**
	 * Pre: Este metodo devuelve true si el [ID] esta dentro de la lista [ids]
	 * @param ids Lista de ids
	 * @param ID El ID a comparar
	 * @return
	 */
	public static boolean estaRegistrado (ArrayList<Integer> ids, int ID) {
		for (Integer idA : ids) {
			if (idA == ID) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Pre: Este metodo devuelve el numero de usos circulares y traslados que tiene la lista de UsosBizi [usos]
	 * Post: Para ello se recorre la losta comparando sus estaciones de retirada y las de llegada, si son iguales
	 * se añade 1 al cCirculos y si son distintas al de Traslados, se devuelven ambos datos en una tabla de int,
	 * en la posicion 0 los UsoBizi cirvulares y en la 1 los traslados.
	 * @param usos
	 * @return
	 */
	public static int[] contarCirculares(ArrayList<UsoBizi> usos) {
		int[] usosAnalizados = new int[2];
		int cCirculos = 0;
		int cTraslados = 0;
		for (UsoBizi uso : usos) {
			if (uso.getAnclajeEstacion() == uso.getRetiroEstacion()) {
				cCirculos++;
			} else {
				cTraslados++;
			}
		}
		usosAnalizados[0] = cCirculos;
		usosAnalizados[1] = cTraslados;
		return usosAnalizados;

	}
	
}
