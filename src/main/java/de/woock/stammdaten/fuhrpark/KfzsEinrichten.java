package de.woock.stammdaten.fuhrpark;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.woock.entity.Kfz;
import de.woock.entity.KfzKlasse;
import de.woock.entity.Station;

public class KfzsEinrichten {
	static final String       letters   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final Random       rand      = new Random();
	static       List<String> typeS     = null;
	static       List<String> typeM     = null;
	static       List<String> typeL     = null;
	static {
		typeS     = Arrays.asList("Smart Fortwo", "Opel Corsa", "Fiat Ponto", "Peugeot 106");
		typeM     = Arrays.asList("Golf GTI", "Volvo S60", "Opel Astra Combi");
		typeL     = Arrays.asList("VW Passat Combi", "Mercedes E220", "BMW 5i", "Volvo S80", "Audi RS 7");
	}

	public static Kfz createAuto(Station station) {
		KfzKlasse fahrzeugKlasse = createFahrzeugKlasse();
		return new Kfz(createNummernschild(), 
				       createType(fahrzeugKlasse), 
				       fahrzeugKlasse, 
				       station,
				       false);
	}
	
	private static String createType(KfzKlasse kfzKlasse) {
		switch (kfzKlasse) {
		case S:	return typeS.get(new Random().nextInt(typeS.size()));
		case M:	return typeM.get(new Random().nextInt(typeM.size()));
		case L:	return typeL.get(new Random().nextInt(typeL.size()));
		default: 		break;
		}
		return null;
	}

	private static String createNummernschild() {
		return String.format("HH %s%s %d", letters.charAt(rand.nextInt(25)), 
				                           letters.charAt(rand.nextInt(25)), 
				                           rand.nextInt(10000));
	}
	
	private static KfzKlasse createFahrzeugKlasse() {
		return KfzKlasse.values()[new Random().nextInt(3)];
	}
}
