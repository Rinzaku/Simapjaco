package instancias;

public class Modelo {
	
	private int id_modelo;
	private int id_ropa;
	private int id_color;
	private int id_talla;
	private String modelo;
	private int existencias;
	
	/**
	 * @return the id_modelo
	 */
	public int getId_modelo() {
		return id_modelo;
	}
	
	/**
	 * @param id_modelo the id_modelo to set
	 */
	public void setId_modelo(int id_modelo) {
		this.id_modelo = id_modelo;
	}
	
	/**
	 * @return the id_ropa
	 */
	public int getId_ropa() {
		return id_ropa;
	}
	
	/**
	 * @param id_ropa the id_ropa to set
	 */
	public void setId_ropa(int id_ropa) {
		this.id_ropa = id_ropa;
	}
	
	/**
	 * @return the id_color
	 */
	public int getId_color() {
		return id_color;
	}
	
	/**
	 * @param id_color the id_color to set
	 */
	public void setId_color(int id_color) {
		this.id_color = id_color;
	}
	
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
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * @return the existencias
	 */
	public int getExistencias() {
		return existencias;
	}
	
	/**
	 * @param existencias the existencias to set
	 */
	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Modelo [id_modelo=" + id_modelo + ", id_ropa=" + id_ropa
				+ ", id_color=" + id_color + ", id_talla=" + id_talla
				+ ", modelo=" + modelo + ", existencias=" + existencias + "]";
	}
	
	
	
}
