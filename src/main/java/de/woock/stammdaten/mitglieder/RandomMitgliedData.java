package de.woock.stammdaten.mitglieder;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import de.woock.entity.Adresse;
import de.woock.entity.Anrede;

public class RandomMitgliedData {
	
	private static Random       rand          = new Random();
	private static StreetReader streatReader  = new StreetReader();

	
	public static String id() {
		return UUID.randomUUID().toString();
	}
	
	public static Adresse adresse(String ort) {
		int                 noOfStreats = streatReader.streets.get(ort).keySet().size();
		Map<String, String> strassenMap = streatReader.streets.get(ort);
		String              strasse     = (String) strassenMap.keySet().toArray()[rand.nextInt(noOfStreats)];
		ort = strassenMap.get(strasse);
		return new Adresse(ort, String.format("%s %s", strasse, rand.nextInt(400)));
	}
	
	public static  Anrede anrede() {
        return rand.nextBoolean() ? Anrede.Herr : Anrede.Frau;
    }
	public static String name() {
		int anzahlSure     = Namen.lastName.length;
        return Namen.lastName[rand.nextInt(anzahlSure-1)];
    }
	
	public static String vorname(Anrede anrede) {
		String name = "";
        int geschlecht = anrede.equals(Anrede.Herr)? 0: 1;
        int size = Namen.name[geschlecht].length;
        name = Namen.name[geschlecht][rand.nextInt(size)];
        return name;
    }
	
	public static Temporal geburtsdatum() {
    	return LocalDate.ofYearDay(rand.nextInt(80) + 1936, rand.nextInt(365) + 1);
    }
    
	public static String getMobil() {
    	int anzahlVorwahlen = VorwahlenMobil.vorwahlen.size();
    	return VorwahlenMobil.vorwahlen.values().toArray()[rand.nextInt(anzahlVorwahlen)] + " / " + (rand.nextInt(100000000)+10000000);
    }
    
	public static String getFestnetz(String stadt) {
    	return VorwahlenFestnetz.vorwahlen.get(stadt) + " / " + (rand.nextInt(10000000)+1000000);
    }
	
	public static String getProvide() {
		Provider[] providers = Provider.values();
		int        length    = providers.length;
		return providers[rand.nextInt(length)].name();
	}
	
	public static String getEMail(String vorname, String name) {
		return String.format("%s.%s@%s.de", vorname, name, getProvide());
	}
	
	public  enum Provider {
		gmail, web, aol, gmx, freemail, yahoo, hotmail, ionos, lavabit, mail
	}

}
