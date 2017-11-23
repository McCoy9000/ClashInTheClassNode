package es.eroski.miDemoProject._model;

public class Carta {

	private String idCarta;
	private String nombre;
	private String descripcion;
	private int	nivel;
	private int	precio;
	
	public Carta() {
		
	}
	@Override
	public String toString() {
		return "Carta [idCarta=" + idCarta + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel=" + nivel
				+ ", precio=" + precio + "]";
	}
	public String getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(String idCarta) {
		this.idCarta = idCarta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
}
