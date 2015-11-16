package instancias;

public class Detalle_Venta {
	
	private int id_detalle_venta;
	private int id_venta;
	private int id_modelo;
	private int id_ropa;
	private int cantidad_articulos;
	private double precio_unitario;
	private String estado;
	
	/**
	 * @return the id_detalle_venta
	 */
	public int getId_detalle_venta() {
		return id_detalle_venta;
	}
	
	/**
	 * @param id_detalle_venta the id_detalle_venta to set
	 */
	public void setId_detalle_venta(int id_detalle_venta) {
		this.id_detalle_venta = id_detalle_venta;
	}
	
	/**
	 * @return the id_venta
	 */
	public int getId_venta() {
		return id_venta;
	}
	
	/**
	 * @param id_venta the id_venta to set
	 */
	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
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
	 * @return the cantidad_articulos
	 */
	public int getCantidad_articulos() {
		return cantidad_articulos;
	}
	
	/**
	 * @param cantidad_articulos the cantidad_articulos to set
	 */
	public void setCantidad_articulos(int cantidad_articulos) {
		this.cantidad_articulos = cantidad_articulos;
	}
	
	/**
	 * @return the precio_unitario
	 */
	public double getPrecio_unitario() {
		return precio_unitario;
	}
	
	/**
	 * @param precio_unitario the precio_unitario to set
	 */
	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Detalle_Venta [id_detalle_venta=" + id_detalle_venta
				+ ", id_venta=" + id_venta + ", id_ropa=" + id_ropa
				+ ", cantidad_articulos=" + cantidad_articulos
				+ ", precio_unitario=" + precio_unitario + "]";
	}
	

}
