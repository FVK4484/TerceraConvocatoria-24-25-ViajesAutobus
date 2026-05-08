package fp.tipos;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AgenciaBus {
	
	private String nombre;
	private List<Viaje> viajes;
	
	public AgenciaBus(String nombre, Stream<Viaje> viajes) {
		this.nombre = nombre;
		this.viajes = viajes.toList();
	}
	public String toString() {
		return "AgenciaBus [viajes=" + viajes + "/n]";
	}
	public String getNombre() {
		return nombre;
	}
	public List<Viaje> getViajes() {
		return new ArrayList<>(viajes);
	}
	public int hashCode() {
		return Objects.hash(viajes);
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenciaBus other = (AgenciaBus) obj;
		return Objects.equals(viajes, other.viajes);
	}
	
//	a) Duration getMaximaDuracion(): Devuelve la duración de tipo Duration del viaje más largo, siendo el
//	viaje más largo aquél que consta de un mayor número de paradas intermedias, sin contar los
//	transbordos. En caso de que no exista el viaje más largo, el método debe devolver una duración de cero.
//	(1 punto)
	public Duration getMaximaDuracion() {
		return viajes.stream()
				.max(Comparator.comparingInt(v -> v.getIntermedias().size()))
				.map(Viaje::getDuracion)
				.orElse(Duration.ZERO);
	}
	
//	b) void añadirTiempoDescanso(String parada, Integer minutos): Dados el nombre de una parada intermedia, 
//	de tipo String, y un tiempo extra en minutos, de tipo Integer, añade ese tiempo extra a los viajes 
//	cuyo trayecto incluya dicha parada. (1 punto)
	public void añadirTiempoDescanso(String parada, Integer minutos) {
		viajes.stream()
			.filter(v -> v.getIntermedias().contains(parada))
			.forEach(v -> v.setDuracion(v.getDuracion().plusMinutes(minutos)));	
	}
	
	public List<Viaje> mostrarViajesConParada(String parada) {
		return viajes.stream()
				.filter(v -> v.getIntermedias().contains(parada))
				.collect(Collectors.toList());
	}
	
}
