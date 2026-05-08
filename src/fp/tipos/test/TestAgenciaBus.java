package fp.tipos.test;

import java.util.stream.Collectors;

import fp.tipos.AgenciaBus;
import fp.tipos.FactoriaViajes;


public class TestAgenciaBus {

	public static void main(String[] args) {
		AgenciaBus ab = new AgenciaBus("Ate", FactoriaViajes.leeViajes("./data/viajes.csv").stream());
		testLectura();
		testGetMaximaDuracion(ab);
		testAñadirTiempoDescanso(ab, "Bilbao", 30);
	}
	
	public static void testLectura() {
		System.out.println(" Test lectura ==========================================================================");
		System.out.println(" " + FactoriaViajes.leeViajes("./data/viajes.csv").stream()
				.limit(3)
				.collect(Collectors.toList()));
	}
	
	public static void testGetMaximaDuracion(AgenciaBus ab) {
		System.out.println(" Test 4.a ==============================================================================");
		System.out.println(" La duración máxima es: " + ab.getMaximaDuracion());
	}
	
	public static void testAñadirTiempoDescanso(AgenciaBus ab, String parada, Integer minutos) {
		try {
			
			System.out.println(" Se van a añadir " + minutos + " minutos a los viajes con la parada " + parada + ", que son:");
			ab.añadirTiempoDescanso(parada, minutos);
			System.out.println(" " + ab.mostrarViajesConParada(parada));
			
		} catch (Exception e) {
			System.out.println(" Capturada excepcion inesperada " + 
							e.getMessage());
		}
	}

}
