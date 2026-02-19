package fp.tipos;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Viaje {
	private Double precio;
	private Integer distancia;
	private Duration duracion;
	private TipoViaje tipoViaje;
	private List<Parada> trayecto;
	
	public Viaje(Double precio, Integer distancia, Duration duracion, TipoViaje tipoViaje, Parada origen, Parada destino) {
		setPrecio(precio);
		setDistancia(distancia);
		setDuracion(duracion);
		this.trayecto = new ArrayList<>();
		trayecto.add(origen);
		trayecto.add(destino);
		setTipoViaje(tipoViaje);
	}
	public Viaje(Double precio, Integer distancia, Duration duracion, TipoViaje tipoViaje, List<Parada> trayecto) {
		setPrecio(precio);
		setDistancia(distancia);
		setDuracion(duracion);
		this.trayecto = trayecto;
		setTipoViaje(tipoViaje);
	}
	private void checkTipoViaje(TipoViaje tipoViaje) {
		if (this.trayecto != null && this.trayecto.size() == 2) {
	        Checkers.check("El tipo de viaje de un viaje con solo origen y destino no puede ser TRANSBORDO.",
	            tipoViaje != TipoViaje.TRANSBORDO);
	    }
	}
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		Checkers.check("El precio debe ser mayor que 0."
				, precio > 0);
		this.precio = precio;
	}
	public Integer getDistancia() {
		return distancia;
	}
	public void setDistancia(Integer distancia) {
		Checkers.check("La distancia debe ser mayor que 0."
				, distancia > 0);
		this.distancia = distancia;
	}
	public Duration getDuracion() {
		return duracion;
	}
	public void setDuracion(Duration duracion) {
		Checkers.check("La duración debe ser mayor que 0.", 
		        duracion != null && !duracion.isZero() && !duracion.isNegative());
		this.duracion = duracion;
	}
	public TipoViaje getTipoViaje() {
		return tipoViaje;
	}
	public void setTipoViaje(TipoViaje tipoViaje) {
		checkTipoViaje(tipoViaje);
		this.tipoViaje = tipoViaje;
	}
	public List<Parada> getTrayecto() {
		return trayecto;
	}
	public void setTrayecto(List<Parada> trayecto) {
		this.trayecto = trayecto;
	}
	
	public Double getVelocidadMedia() {
		return (double) distancia / (duracion.getSeconds() / 3600.0);
	}
	
	public Integer getNumeroParadas() {
		List<String> lista = new ArrayList<>();
		for(int i = 1; i < trayecto.size() - 1; i++) {
			if(!lista.contains(trayecto.get(i).nombre())) {
				lista.add(trayecto.get(i).nombre());
			}
		}
		return lista.size();
	}
	
	public List<String> getIntermedias() {
		List<String> lista = new ArrayList<>();
		for(int i = 1; i < trayecto.size() - 1; i++) {
			lista.add(trayecto.get(i).nombre());
		}
		return lista;
	}
	
	public String getOrigen() {
		return trayecto.getFirst().nombre();
	}
	
	public String getDestino() {
		return trayecto.getLast().nombre();
	}
	
	public Integer getNumeroTransbordos() {
		Integer cont = 0;
		for(int i = 0; i < trayecto.size() - 1; i++) {
			if(trayecto.get(i).nombre().equals(trayecto.get(i + 1).nombre())) {
				cont ++;
			}
		}
		return cont;
	}

	public int hashCode() {
		return Objects.hash(trayecto);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(trayecto, other.trayecto);
	}

	public String toString() {
		return "Viaje [precio=" + precio + ", distancia=" + distancia + ", duracion=" + duracion + ", tipoViaje="
				+ tipoViaje + ", trayecto=" + trayecto + "]";
	}
	
}
