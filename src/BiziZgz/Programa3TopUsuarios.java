package BiziZgz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Programa3TopUsuarios {
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el path + nombre");
		String path = entrada.next();
		ArrayList<UsuarioBizi> usuarios = crearUsuarios(path);
//		for (UsuarioBizi usuario : usuarios) {
//			System.out.println("Usuario: " + usuario.getID() + " " + usuario.getUsos().size());
//		}
		mostrarTop15(usuarios);
		entrada.close();
	}
	
	public static ArrayList<UsuarioBizi> crearUsuarios(String path) {
		File fichero = new File(path);
		ArrayList<UsuarioBizi> usuarios = new ArrayList<UsuarioBizi>();
		ArrayList<UsoBizi> usos = Programa2ContarUsos.mostrarInformacionResumida(path);
		ArrayList<UsoBizi> usosUsuario = new ArrayList<UsoBizi>();
		UsuarioBizi usuario;
		try {
			Scanner f = new Scanner(fichero);
			while (f.hasNextLine()) {
				String[] lineaSeparada = f.nextLine().split(";");
				if (Programa2ContarUsos.isInteger(lineaSeparada[0])) {
					int ID = Integer.parseInt(lineaSeparada[0]);
					if (!estaRegistrado(usuarios, ID)) {
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
	
	public static ArrayList<UsoBizi> usosPorUnUsuario (ArrayList<UsoBizi> usos, int ID) {
		ArrayList<UsoBizi> usosDeUsuario = new ArrayList<UsoBizi>();
		for (UsoBizi uso : usos) {
			if (uso.getID() == ID) {
				usosDeUsuario.add(uso);
			}
		}
		return usosDeUsuario;
	}
	
	public static boolean estaRegistrado (ArrayList<UsuarioBizi> usuarios, int ID) {
		for (UsuarioBizi usuario : usuarios) {
			if (usuario.getID() == ID) {
				return true;
			}
		}
		return false;
	}
	
	public static void mostrarTop15 (ArrayList<UsuarioBizi> usuarios) {
		System.out.format("+----------+-----------+----------+-------+%n");
		System.out.format("| Usuario  | Traslados | Circular | Total |%n");
		System.out.format("+----------+-----------+----------+-------+%n");
		ArrayList<UsuarioBizi> usuariosAnaliazados = new ArrayList<UsuarioBizi>();
		int mayorUsos = 0;
		UsuarioBizi mayor = new UsuarioBizi(0, usuarios.get(0).getUsos());
		for (int i = 0; i < 15; i++) {
			for(UsuarioBizi usuario : usuarios) {
				if (usuario.getUsos().size() > mayorUsos && !estaRegistrado(usuariosAnaliazados, usuario.getID())) {
					mayor = usuario;
					mayorUsos = usuario.getUsos().size();
				}
			}
			usuariosAnaliazados.add(mayor);
			mayorUsos = 0;
			int[] usosAnalizados = contarCirculares(mayor.getUsos());
			String leftAlignFormat = "| %-8s | %-9d | %-8d | %-5d |%n";
			System.out.format(leftAlignFormat, mayor.getID() , usosAnalizados[1] , usosAnalizados[0] , mayor.getUsos().size());
			System.out.format("+----------+-----------+----------+-------+%n");
		}

	}
	
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
