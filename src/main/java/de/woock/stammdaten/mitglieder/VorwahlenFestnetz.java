package de.woock.stammdaten.mitglieder;

import java.util.HashMap;
import java.util.Map;

public class VorwahlenFestnetz {

	public static Map<String, String> vorwahlen = new HashMap<>();
	
	static {
		vorwahlen.put("Bremen"   , "0421");
		vorwahlen.put("Hamburg"  , "040");
		vorwahlen.put("Kiel"     , "0431");
		vorwahlen.put("Lübeck"   , "04502");
		vorwahlen.put("Lüneburg" , "04131");
		vorwahlen.put("Pinneberg", "04101");
		vorwahlen.put("Reinbek"  , "04104");
		vorwahlen.put("Stade"    , "04101");
		vorwahlen.put("Buchholz" , "04825");
	}
}
