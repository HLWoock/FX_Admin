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
		
		List<String> leichteUnfallmeldungen = List.of("linker Außenspiegel abgebrochen", 
				                                      "rechter Außenspiegel abgebrochen",
				                                      "Schramme in linker Fahrertür",
				                                      "Schramme in rechter Beifahrertür",
				                                      "Schramme in Tür hinten links",
				                                      "Schramme in Tür rechts links",
				                                      "Stoßstange vorne rechts beschädigt",
				                                      "Stoßstange vorne links beschädigt",
				                                      "Stoßstange hinten rechts beschädigt",
				                                      "Stoßstange hinten links beschädigt",
				                                      "Scheinwerfer vorne rechts beschädigt",
				                                      "Scheinwerfer vorne links beschädigt",
				                                      "Rücklicht hinten rechts beschädigt",
				                                      "Rücklicht hinten links beschädigt",
				                                      "",
				                                      "",
				                                      "");
		List<String> mittlereUnfallmeldungen = List.of("Antenne abgebrochen", 
				                                       "Frontscheibe beschädigt",
				                                       "Seitenscheibe vorne links beschädigt",
				                                       "Seitenscheibe vorne rechts beschädigt",
				                                       "Seitenscheibe hinten links beschädigt",
				                                       "Seitenscheibe hinten rechts beschädigt",
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
				                                      "Öl leer",
				                                      "Motor gibt ungewöhnlich laute Geräusche von sich",
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
									           "leicht behebbar" , "keine sofortige Maßnahme nötig"                   , 
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
