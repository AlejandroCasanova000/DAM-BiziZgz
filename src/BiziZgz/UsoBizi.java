package BiziZgz;

/**
 * Esta clase Java define el objeto UsoBizi, que contiene el ID, la fecha de retiro de la bici, el ID de la estacion de retiro,
 * el dateTime de la llegada y la estacion de la misma
 * @author alexc y Dana
 *
 */
public class UsoBizi {
	private int ID;
	private String retiroDT;
	private int retiroEstacion;
	private String anclajeDT;
	private int anclajeEstacion;
	
	/**
	 * El constructor del objeto
	 * @param ID
	 * @param retiroDT
	 * @param retiroEstacion
	 * @param anclajeDT
	 * @param anclajeEstacion
	 */
	public UsoBizi (int ID, String retiroDT, int retiroEstacion, String anclajeDT, int anclajeEstacion) {
		this.ID = ID;
		this.retiroDT = retiroDT;
		this.retiroEstacion = retiroEstacion;
		this.anclajeDT = anclajeDT;
		this.anclajeEstacion = anclajeEstacion;
	}
	
	/**
	 * Pre:Este metodo devuelve el ID del UsoBizi
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Pre: Este metodo cambia el Id por [ID]
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Pre: Este metodo devuelve el DateTime del retiro de la Bizi
	 * @return
	 */
	public String getRetiroDT() {
		return retiroDT;
	}

	/**
	 * Pre: Este metodo cambia el Datetime a [retiroDT]
	 * @param retiroDT
	 */
	public void setRetiroDT(String retiroDT) {
		this.retiroDT = retiroDT;
	}

	/**
	 * Pre: Este metodo devuelve la estacion de retiro
	 * @return
	 */
	public int getRetiroEstacion() {
		return retiroEstacion;
	}

	/**
	 * Pre: Este metodo cambai la estacion a [retiroEstacion]
	 * @param retiroEstacion
	 */
	public void setRetiroEstacion(int retiroEstacion) {
		this.retiroEstacion = retiroEstacion;
	}

	/**
	 * Pre: Este metodo devuelve el DateTime del ancalje de la Bizi
	 * @return
	 */
	public String getAnclajeDT() {
		return anclajeDT;
	}

	/**
	 * Pre: Este metodo cambia el anclajeDT a [anclajeDT]
	 * @param anclajeDT
	 */
	public void setAnclajeDT(String anclajeDT) {
		this.anclajeDT = anclajeDT;
	}

	/**
	 * Pre: Este metodo devuelve la estacion de anclaje
	 * @return
	 */
	public int getAnclajeEstacion() {
		return anclajeEstacion;
	}

	/**
	 * Pre: Este metodo cambia el anclajeEstacion a [anclajeestacion]
	 * @param anclajeEstacion
	 */
	public void setAnclajeEstacion(int anclajeEstacion) {
		this.anclajeEstacion = anclajeEstacion;
	}

	@Override
	/**
	 * Pre: Este metodo devuelve un String de toda la informacion del UsoBizi
	 */
	public String toString() {
		return "El usuario: " + ID + " ha cogido una bizi el " + retiroDT + " en la estacion " + retiroEstacion + " y la ha dejado el" + 
				anclajeDT + " en la estacion " + anclajeEstacion;
	}
}
