package BiziZgz;

import java.util.ArrayList;

/**
 * Esta clase Java define el objeto UsuarioBizi, que contiene el ID del usuario y una lista de 
 * UsosBizi, que tiene  por ID el mismo que el id del UsuarioBizi
 * @author alexc
 *
 */
public class UsuarioBizi {
	private int ID;
	private ArrayList<UsoBizi> usos;
	
	/**
	 * El constructor del UsuarioBizi
	 * @param ID
	 * @param usos
	 */
	public UsuarioBizi(int ID, ArrayList<UsoBizi> usos) {
		this.ID = ID;
		this.usos = usos;
	}
	
	/**
	 * Pre: Este metodo devuelve el ID del UsuarioBizi
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Pre: Este metodo cambia el id a [id]
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Pre: Este metodo devuelve una lista de usos del UsuarioBizi
	 * @return
	 */
	public ArrayList<UsoBizi> getUsos() {
		return usos;
	}

	/**
	 * Pre: Este metodo cambia la lsista de usos a [usos]
	 * @param usos
	 */
	public void setUsos(ArrayList<UsoBizi> usos) {
		this.usos = usos;
	}

}
