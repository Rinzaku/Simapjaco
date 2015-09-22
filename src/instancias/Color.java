package instancias;

public class Color {

	private int id_color;
	private String color;
	
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [id_color=" + id_color + ", color=" + color + "]";
	}
	
	
	
}
