package instancias;

public class Talla {

	private int id_talla;
	private String talla;
	
	/**
	 * @return the id_talla
	 */
	public int getId_talla() {
		return id_talla;
	}
	
	/**
	 * @param id_talla the id_talla to set
	 */
	public void setId_talla(int id_talla) {
		this.id_talla = id_talla;
	}
	
	/**
	 * @return the talla
	 */
	public String getTalla() {
		return talla;
	}
	
	/**
	 * @param talla the talla to set
	 */
	public void setTalla(String talla) {
		this.talla = talla;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Talla [id_talla=" + id_talla + ", talla=" + talla + "]";
	}
	
}
