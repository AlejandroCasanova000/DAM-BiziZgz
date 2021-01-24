package BiziZgz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase Java se encarga de mostrar el top 15 de UsuariosBizi con mayor cantidad de usos dado un fichero .csv con la estructura dada
 * @author alexc
 *
 */
public class Programa3TopUsuarios {
	
	/**
	 * Pre:Este metodo va llamando a los otros para desempeñar la tarea
	 * @param args
	 */
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el path + nombre");
		String path = entrada.next();
		ArrayList<UsuarioBizi> usuarios = crearUsuarios(path);//Albergamos aqui todos los UsuariosBizi
		mostrarTop15(usuarios);
		entrada.close();
	}
	
	/**
	 * Pre:Este metodo, dado el [path] de un .csv, se encarga de definir todos los UsuariosBizi
	 * Post: Para ello se recorre el fichero, y en cada linea se llama al método de usosPorUnUsuario, que devuelve una lista
	 * de usos de un usuario especifico (el usuario de la linea), y se crea un UsuarioBizi y se almacena en la lista de usuarios
	 * @param path La ruta del fichero
	 * @return
	 */
	public static ArrayList<UsuarioBizi> crearUsuarios(String path) {
		File fichero = new File(path);
		ArrayList<UsuarioBizi> usuarios = new ArrayList<UsuarioBizi>();//Donde van a ir todos los usuariosBizi del fichero
		ArrayList<UsoBizi> usos = Programa2ContarUsos.mostrarInformacionResumida(path);//Todos los usos del fichero
		ArrayList<UsoBizi> usosUsuario = new ArrayList<UsoBizi>();//Lista donde van a ir temporalmente los usos de un usuario
		UsuarioBizi usuario;
		try {
			Scanner f = new Scanner(fichero);
			/**
			 * Se recorre el fichero ignorando la cabecera y en cada linea se guardan los usos de ese usuario, y ya ahi se crea
			 * el UsuarioBizi y se añade a la lista
			 */
			while (f.hasNextLine()) {
				String[] lineaSeparada = f.nextLine().split(";");
				if (Programa2ContarUsos.isInteger(lineaSeparada[0])) {
					int ID = Integer.parseInt(lineaSeparada[0]);
					if (!estaRegistrado(usuarios, ID)) {//Condicion para no repetir usuarios
						usosUsuario = usosPorUnUsuario(usos, Integer.parseInt(lineaSeparada[0]));
						usuario = new UsuarioBizi(ID, usosUsuario);
						usuarios.add(usuario);
					}
				} else {
					f.nextLine();
				}
			}
			f.close();
			return usuarios;
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + path + " no se ha podido abrir");
			return null;
		}
	}
	
	/**
	 * Pre: Este metodo devuelve una lista de UsosBizi que tienen todos el mismo usuario
	 * post: para ello se recorre la lista de [usos] con parando cada UsoBizi con el ID dado, si coinciden
	 * se añade el uso a usosDeUsuario
	 * @param usos La lista de usos
	 * @param ID El ID a comparar
	 * @return
	 */
	public static ArrayList<UsoBizi> usosPorUnUsuario (ArrayList<UsoBizi> usos, int ID) {
		ArrayList<UsoBizi> usosDeUsuario = new ArrayList<UsoBizi>();
		for (UsoBizi uso : usos) {
			if (uso.getID() == ID) {
				usosDeUsuario.add(uso);
			}
		}
		return usosDeUsuario;
	}
	
	/**
	 * Pre: Este metodo devuelve true si el [ID] esta en la lista [usuarios]
	 * Post: Se recorre la lista, si los IDs coinciden, devuelve true, si no false
	 * @param usuarios la lista de usuarios
	 * @param ID El ID a comparar
	 * @return
	 */
	public static boolean estaRegistrado (ArrayList<UsuarioBizi> usuarios, int ID) {
		for (UsuarioBizi usuario : usuarios) {
			if (usuario.getID() == ID) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Pre: Este metodo devuelve el Top 15 de UsuariosBizi con mas usos dado una lista de UsuarioBizi
	 * Post: Para ellos se repite veces el bucle donde se busca el usuarioBizi de la lista [usuarios] que tiene el mayor
	 * numero de usos, para que no se repitan, se hace uso del metodo estaRegistrado() para que pueda aparecer en la lista 
	 * @param usuarios
	 */
	public static void mostrarTop15 (ArrayList<UsuarioBizi> usuarios) {
		System.out.format("+----------+-----------+----------+-------+%n");
		System.out.format("| Usuario  | Traslados | Circular | Total |%n");//Formats para la tabla
		System.out.format("+----------+-----------+----------+-------+%n");
		ArrayList<UsuarioBizi> usuariosAnaliazados = new ArrayList<UsuarioBizi>();//Donde van a ir los UsuarioBizi que ya han salido
		int mayorUsos = 0;//Donde va a ir el numero de mayor usos temporalmente
		UsuarioBizi mayor = new UsuarioBizi(0, usuarios.get(0).getUsos());//Se almacena el UsuarioBizi temporalmente
		for (int i = 0; i < 15; i++) {//Bucle para sacar el mayor veces 
			for(UsuarioBizi usuario : usuarios) {//Se recorre la lista de Usuarios
				/**
				 * En esta condicion es donde, al acabar el segundo bucle for, mayor acabara siendo el usuario con mayor
				 * numero de usos de la lista
				 */
				if (usuario.getUsos().size() > mayorUsos && !estaRegistrado(usuariosAnaliazados, usuario.getID())) {
					mayor = usuario;
					mayorUsos = usuario.getUsos().size();
				}
			}
			usuariosAnaliazados.add(mayor);
			mayorUsos = 0;
			int[] usosAnalizados = contarCirculares(mayor.getUsos());//Aqui contamos el numero de traslados y circulos del UsuarioBizi
			String leftAlignFormat = "| %-8s | %-9d | %-8d | %-5d |%n";//Cosas pa la tabla que no se ni de q van Alvaro
			System.out.format(leftAlignFormat, mayor.getID() , usosAnalizados[1] , usosAnalizados[0] , mayor.getUsos().size());
			System.out.format("+----------+-----------+----------+-------+%n");
		}

	}
	
	/**
	 * Pre: Este metodo devuelve una tabla de enteros, donde el primero es el numero de recorridos circulares y el segundo 
	 * el de traslados
	 * Post: Para ello se recoore la lista [usos] de UsoBizi, donde se compara la estacion de llegada con la de salida, si son
	 * las mismas se añade 1 al cCirculos, si no al cTraslados, se devuelve la tabla usosAnalizados[cCirculos, cTraslados]
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
