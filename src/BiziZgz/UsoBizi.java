package BiziZgz;

public class UsoBizi {
	private int ID;
	private String retiroDT;
	private int retiroEstacion;
	private String anclajeDT;
	private int anclajeEstacion;
	
	public UsoBizi (int ID, String retiroDT, int retiroEstacion, String anclajeDT, int anclajeEstacion) {
		this.ID = ID;
		this.retiroDT = retiroDT;
		this.retiroEstacion = retiroEstacion;
		this.anclajeDT = anclajeDT;
		this.anclajeEstacion = anclajeEstacion;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getRetiroDT() {
		return retiroDT;
	}

	public void setRetiroDT(String retiroDT) {
		this.retiroDT = retiroDT;
	}

	public int getRetiroEstacion() {
		return retiroEstacion;
	}

	public void setRetiroEstacion(int retiroEstacion) {
		this.retiroEstacion = retiroEstacion;
	}

	public String getAnclajeDT() {
		return anclajeDT;
	}

	public void setAnclajeDT(String anclajeDT) {
		this.anclajeDT = anclajeDT;
	}

	public int getAnclajeEstacion() {
		return anclajeEstacion;
	}

	public void setAnclajeEstacion(int anclajeEstacion) {
		this.anclajeEstacion = anclajeEstacion;
	}

}
