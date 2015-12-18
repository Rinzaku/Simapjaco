package instancias;

public class Ventas {

	private int id_venta;
	private String fecha;
	private int no_articulos;
	private double sub_total;
	private double descuento;
	private double total_venta;
	private String estado;
	private double abono;
	private int no_empleado;
	
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
	 * @return the sub_total
	 */
	public double getSub_total() {
		return sub_total;
	}

	/**
	 * @param sub_total the sub_total to set
	 */
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}

	/**
	 * @return the descuento
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
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

	/**
	 * @return the abono
	 */
	public double getAbono() {
		return abono;
	}

	/**
	 * @param abono the abono to set
	 */
	public void setAbono(double abono) {
		this.abono = abono;
	}

	/**
	 * @return the no_empleado
	 */
	public int getNo_empleado() {
		return no_empleado;
	}

	/**
	 * @param no_empleado the no_empleado to set
	 */
	public void setNo_empleado(int no_empleado) {
		this.no_empleado = no_empleado;
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
