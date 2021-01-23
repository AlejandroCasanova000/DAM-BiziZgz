package BiziZgz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Programa2ContarUsos {
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
