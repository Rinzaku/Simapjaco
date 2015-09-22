package instancias;

public class Ventas {

	private int id_venta;
	private String fecha;
	private int no_articulos;
	private double total_venta;
	
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return the no_articulos
	 */
	public int getNo_articulos() {
		return no_articulos;
	}
	
	/**
	 * @param no_articulos the no_articulos to set
	 */
	public void setNo_articulos(int no_articulos) {
		this.no_articulos = no_articulos;
	}
	
	/**
	 * @return the total_venta
	 */
	public double getTotal_venta() {
		return total_venta;
	}
	
	/**
	 * @param total_venta the total_venta to set
	 */
	public void setTotal_venta(double total_venta) {
		this.total_venta = total_venta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ventas [id_venta=" + id_venta + ", fecha=" + fecha
				+ ", no_articulos=" + no_articulos + ", total_venta="
				+ total_venta + "]";
	}
	
	
}
