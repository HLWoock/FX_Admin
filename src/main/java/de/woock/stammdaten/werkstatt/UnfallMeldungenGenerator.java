package de.woock.stammdaten.werkstatt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import de.woock.entity.Reservierung;
import de.woock.entity.Unfallmeldung;
import de.woock.entity.Versicherung;
import de.woock.entity.Unfallmeldung.Status;
import de.woock.service.communication.ReservierungAccess;
import de.woock.service.communication.StammdatenAccess;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UnfallMeldungenGenerator {
	
	private final ReservierungAccess reservierungsService;
	
	public List<Unfallmeldung> generiereUnfallmeldungen(StammdatenAccess stammdatenService) {
		List<Unfallmeldung> unfallmeldungen = new ArrayList<>();
		
		List<String> leichteUnfallmeldungen = List.of("linker Au�enspiegel abgebrochen", 
				                                      "rechter Au�enspiegel abgebrochen",
				                                      "Schramme in linker Fahrert�r",
				                                      "Schramme in rechter Beifahrert�r",
				                                      "Schramme in T�r hinten links",
				                                      "Schramme in T�r rechts links",
				                                      "Sto�stange vorne rechts besch�digt",
				                                      "Sto�stange vorne links besch�digt",
				                                      "Sto�stange hinten rechts besch�digt",
				                                      "Sto�stange hinten links besch�digt",
				                                      "Scheinwerfer vorne rechts besch�digt",
				                                      "Scheinwerfer vorne links besch�digt",
				                                      "R�cklicht hinten rechts besch�digt",
				                                      "R�cklicht hinten links besch�digt",
				                                      "",
				                                      "",
				                                      "");
		List<String> mittlereUnfallmeldungen = List.of("Antenne abgebrochen", 
				                                       "Frontscheibe besch�digt",
				                                       "Seitenscheibe vorne links besch�digt",
				                                       "Seitenscheibe vorne rechts besch�digt",
				                                       "Seitenscheibe hinten links besch�digt",
				                                       "Seitenscheibe hinten rechts besch�digt",
				                                       "Fahrersitz eingerissen",
				                                       "Beifahrersitz eingerissen",
				                                       "Sitz hinten links eingerissen",
				                                       "Sitz hinten rechts eingerissen",
				                                       "Radkappe vorne links verloren",
				                                       "Radkappe vorne rechts verloren",
				                                       "Radkappe hinten links verloren",
				                                       "Radkappe hinten rechts verloren");
		List<String> schwereUnfallmeldungen = List.of("Motorraum stark gestaucht und Motorhaube verformt", 
				                                      "Kofferaum stark gestaucht und Heck verformt",
				                                      "Achsenbruch vorne",
				                                      "Achsenbruch hinten",
				                                      "Kolbenfresser",
				                                      "Wagen springt nicht mehr an",
				                                      "�l leer",
				                                      "Motor gibt ungew�hnlich laute Ger�usche von sich",
				                                      "",
				                                      "");
		Random       random = new Random();
		List<Reservierung> reservierungen = reservierungsService.alleReservierungen();
		reservierungen.stream()
		              .forEach(reservierung -> {
		            	  Versicherung versicherung = stammdatenService.versicherungZu(reservierung);
		            	  int rnd = random.nextInt(1000);
		            	  String severity = "";
		            	  if      (rnd > 990) severity = "schwer";
		            	  else if (rnd > 950) severity = "mittel";
		            	  else if (rnd > 850) severity = "leicht";
		            	  switch (severity) {
						case "schwer":
							unfallmeldungen.add(new Unfallmeldung(reservierung, versicherung, 
									           "schwerer Schaden"  , "abschleppen", 
									            schwereUnfallmeldungen.get(random.nextInt(schwereUnfallmeldungen.size())), 
									            reservierung.getZeitraum().getStartZeit(), 
									            Status.AUFGENOMMEN));
							break;
						case "mittel":
							unfallmeldungen.add(new Unfallmeldung(reservierung, versicherung, 
									           "mittlerer Schaden", "Mechaniker vorbeischicken"     , 
									            mittlereUnfallmeldungen.get(random.nextInt(schwereUnfallmeldungen.size())), 
									            reservierung.getZeitraum().getStartZeit(), 
									            Status.AUFGENOMMEN));
							break;
						case "leicht":
							unfallmeldungen.add(new Unfallmeldung(reservierung, versicherung, 
									           "leicht behebbar" , "keine sofortige Ma�nahme n�tig"                   , 
									            leichteUnfallmeldungen.get(random.nextInt(schwereUnfallmeldungen.size())), 
									            reservierung.getZeitraum().getStartZeit(), 
									            Status.AUFGENOMMEN));
							break;

						default:
							break;
						}
		               });
		return unfallmeldungen;
	}

}
