package fp.tipos;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaViajes {
	
	public static Viaje parseaViaje(String fichero) {
		Checkers.checkNoNull(fichero);
		String [] trozos = fichero.split(";");
		Checkers.check("Formato no válido->" + trozos.length, trozos.length == 5);
		Double precio = Double.valueOf(trozos[0].trim());
		Integer distancia = Integer.valueOf(trozos[1].trim());
		Duration duracion = parseaDuracion(trozos[2].trim());
		TipoViaje tipoViaje = TipoViaje.valueOf(trozos[3].trim().toUpperCase());
		List<Parada> trayecto = parseaTrayecto(trozos[4].trim());
		return new Viaje(precio, distancia, duracion, tipoViaje, trayecto);
	}

	private static Duration parseaDuracion(String strDuracion) { 
		Checkers.checkNoNull(strDuracion); 
		String [] trozos = strDuracion.split(":"); 
		Checkers.check("Formato no válido", trozos.length == 2); 
		Integer horas = Integer.parseInt(trozos[0].trim()); 
		Integer minutos = Integer.parseInt(trozos[1].trim()); 
		return Duration.ofHours(horas).plusMinutes(minutos); 
	}
	
	private static List<Parada> parseaTrayecto(String strTrayecto) {
		List<Parada> lista = new ArrayList<>();
		Checkers.checkNoNull(strTrayecto);
		String nuevoTrayecto = strTrayecto.replace("[", "").replace("]", "");
		String [] trozos = nuevoTrayecto.split(",");
		for(String trozo : trozos) {
			String [] lineas = trozo.split("-");
			String nombre = lineas[0].trim();
			LocalTime hora = null;
			if (!lineas[1].toUpperCase().equals("FIN")) { 
				hora = LocalTime.parse(lineas[1], DateTimeFormatter.ofPattern("HH:mm"));; 
			}
			Parada parada = new Parada(nombre, hora);
			lista.add(parada);
		}
		return lista;
	}
	
	public static List<Viaje> leeViajes(String fichero) {
		Checkers.checkNoNull(fichero);
		String errMsg = String.format("Error leyendo fichero <%s>", fichero);
		List<String> lineas = Ficheros.leeFichero(errMsg, fichero, StandardCharsets.UTF_8);

		List<Viaje> res = lineas.stream()
				                  .skip(1)
				                  .map(linea -> parseaViaje(linea))
				                  .collect(Collectors.toList());
		return res;
	}
	
}
