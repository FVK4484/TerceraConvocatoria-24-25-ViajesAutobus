package fp.tipos.test;

import java.time.Duration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fp.tipos.Parada;
import fp.tipos.TipoViaje;
import fp.tipos.Viaje;

public class TestViaje {
	
	public static void testViaje() {
		Parada parada1 = new Parada("Sevilla", LocalTime.of(9, 0));
		Parada parada2 = new Parada("Huelva", LocalTime.of(10, 10));
		Parada parada3 = new Parada("Faro", LocalTime.of(11, 20));
		Parada parada4 = new Parada("Faro", LocalTime.of(11, 30));
		Parada parada5 = new Parada("Aeropuerto Faro", LocalTime.of(12, 30));
		Parada parada6 = new Parada("Albufeira", LocalTime.of(13, 40));
		Parada parada7 = new Parada("Albufeira", LocalTime.of(14, 0));
		Parada parada8 = new Parada("Lisboa", null);
		
		List<Parada> trayecto = new ArrayList<>();
		
		trayecto.add(parada1);
		trayecto.add(parada2);
		trayecto.add(parada3);
		trayecto.add(parada4);
		trayecto.add(parada5);
		trayecto.add(parada6);
		trayecto.add(parada7);
		trayecto.add(parada8);
		
		Viaje viaje = new Viaje(14.99, 507, Duration.ofHours(7).plusMinutes(20), TipoViaje.TRANSBORDO, trayecto);
		
		System.out.println(viaje);
		System.out.println(viaje.getVelocidadMedia());
		System.out.println(viaje.getNumeroParadas());
		System.out.println(viaje.getIntermedias());
		System.out.println(viaje.getNumeroTransbordos());
	}

	public static void main(String[] args) {
		try {
			testViaje();
		} catch(Exception e) {
			System.out.println("Excepción capturada: ");
			System.out.println(e.getMessage());
		}

	}

}
