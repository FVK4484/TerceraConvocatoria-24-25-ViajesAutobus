package fp.tipos.test;

import java.util.List;

import fp.tipos.FactoriaViajes;
import fp.tipos.Viaje;

public class TestFactoriaViajes {

	public static void main(String[] args) {
		try {
			List<Viaje> viajes = FactoriaViajes.leeViajes("./data/viajes.csv");
		
			System.out.println("Se han leído " + viajes.size() + " viajes.");
			for (Viaje viaje : viajes) {
				System.out.println(viaje);
			}
		} catch(Exception e) {
			System.out.println("Excepción capturada: ");
			System.out.println(e.getMessage());
		}
	}

}
