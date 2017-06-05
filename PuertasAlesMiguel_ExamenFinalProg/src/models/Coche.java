package models;

public class Coche {

	private String matricula;
	private String marca;
	private String modelo;
	private int tiempoIni;
	private int tiempoFin;
	private float precio;
	
	public Coche(){
		tiempoIni = 0;
		tiempoFin = 0;
	}
	
	public int getTiempoIni() {
		return tiempoIni;
	}

	public void setTiempoIni(int tiempoIni) {
		this.tiempoIni = tiempoIni;
	}

	public int getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(int tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
