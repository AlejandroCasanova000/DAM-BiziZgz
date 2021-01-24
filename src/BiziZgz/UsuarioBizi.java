package BiziZgz;

import java.util.ArrayList;

public class UsuarioBizi {
	private int ID;
	private ArrayList<UsoBizi> usos;
	
	public UsuarioBizi(int ID, ArrayList<UsoBizi> usos) {
		this.ID = ID;
		this.usos = usos;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<UsoBizi> getUsos() {
		return usos;
	}

	public void setUsos(ArrayList<UsoBizi> usos) {
		this.usos = usos;
	}

}
