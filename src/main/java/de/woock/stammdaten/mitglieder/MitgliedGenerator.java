package de.woock.stammdaten.mitglieder;

import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.controlsfx.control.Notifications;

import de.woock.entity.Adresse;
import de.woock.entity.Anrede;
import de.woock.entity.Mitglied;
import de.woock.service.communication.StammdatenAccess;
import de.woock.ui.view.dialog.mitglieder.MitgliederDialog;
import de.woock.ui.view.dialog.mitglieder.MitgliederProStadt;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class MitgliedGenerator {
	
	private final StammdatenAccess     stammdatenService;
    private       Map<String, Integer> mitgliederProStadt = new HashMap<>();

	public void generiereMitglieder() {
		 MitgliederDialog.auswahl().showAndWait()
                                   .ifPresent(result -> {
                                	   result.stream()
                                	         .forEach(auswahl -> kündigeMitglieder(auswahl));
                                	   result.stream()
                                	         .forEach(auswahl -> neueMitgliederAufnehmen(auswahl));
                                   });
    	neueMitgliederAusAuswahlErstellen();
    	
		Notifications.create()
                     .title("Stammdaten")
                     .text("Mitglieder generated!")
                     .showInformation();
	}

	private void kündigeMitglieder(MitgliederProStadt auswahl) {
		if (auswahl.loeschen.isSelected()) 
			 stammdatenService.kuendigeAlleMitgliederIn(auswahl.stadt.getText());
	}

	private Integer neueMitgliederAufnehmen(MitgliederProStadt auswahl) {
		return mitgliederProStadt.put(auswahl.stadt.getText(), 
				                      Integer.valueOf(auswahl.anzahl.getText()));
	}

	private void neueMitgliederAusAuswahlErstellen() {
		log.debug("generate Mitglieder");
		mitgliederProStadt.keySet().stream()
    	                           .forEach(ort -> IntStream.range(0, mitgliederProStadt.get(ort))
    	                        		                    .forEach(i -> {
    	                        	   String   id           = RandomMitgliedData.id();
    	                        	   Anrede   anrede       = RandomMitgliedData.anrede();
    	                        	   String   vorname      = RandomMitgliedData.vorname(anrede);
    	                        	   String   name         = RandomMitgliedData.name();
    	                        	   Adresse  adresse      = RandomMitgliedData.adresse(ort);
    	                        	   Temporal geburtsdatum = RandomMitgliedData.geburtsdatum();
    	                        	   String   telefon      = RandomMitgliedData.getFestnetz(ort); 
    	                        	   String   mobil        = RandomMitgliedData.getMobil();
    	                        	   String   email        = RandomMitgliedData.getEMail(vorname, name);
    	                        	   Mitglied mitglied     = new Mitglied(null, anrede, vorname, name, vorname, 
    	                        			                                      telefon, mobil, email, adresse);
    	                        	   stammdatenService.neuesMitglied(mitglied);
    	                           }));
		log.debug("Mitglieder generated");
	}

	

}
