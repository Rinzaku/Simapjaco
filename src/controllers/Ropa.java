package controllers;

public class Ropa {

	private int id_ropa;
	private String prenda;
	private String descricion;
	private int existencias;
	
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
	 * @return the prenda
	 */
	public String getPrenda() {
		return prenda;
	}
	
	/**
	 * @param prenda the prenda to set
	 */
	public void setPrenda(String prenda) {
		this.prenda = prenda;
	}
	
	/**
	 * @return the descricion
	 */
	public String getDescricion() {
		return descricion;
	}
	
	/**
	 * @param descricion the descricion to set
	 */
	public void setDescricion(String descricion) {
		this.descricion = descricion;
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
		return "Ropa [id_ropa=" + id_ropa + ", prenda=" + prenda
				+ ", descricion=" + descricion + ", existencias=" + existencias
				+ "]";
	}
	
	
	
	
}
