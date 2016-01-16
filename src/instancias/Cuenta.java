package instancias;
/**
 * 
 * @author Cecilia Hernandez Vasquez
 *
 */
public class Cuenta {

	private int idCuenta;
	private String fecha;
	private double cuenta;
	
	
	public int getIdCuenta() {
		return idCuenta;
	}
	
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public double getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(double cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", fecha=" + fecha
				+ ", cuenta=" + cuenta + "]";
	}
	
}
