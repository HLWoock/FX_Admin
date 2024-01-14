package de.woock.ui.controller;

import static java.util.Locale.GERMANY;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.controlsfx.control.Notifications;
import org.springframework.stereotype.Component;

import de.woock.entity.Beleg;
import de.woock.entity.Fahrbericht;
import de.woock.entity.Geldbetrag;
import de.woock.entity.Kfz;
import de.woock.entity.Mitglied;
import de.woock.entity.Reservierung;
import de.woock.entity.Status;
import de.woock.entity.Strecke;
import de.woock.entity.Unfallmeldung;
import de.woock.entity.Versicherung;
import de.woock.entity.Zeitraum;
import de.woock.service.communication.ReservierungAccess;
import de.woock.service.communication.StammdatenAccess;
import de.woock.service.communication.UnfallmeldungAccess;
import de.woock.service.communication.VersicherungAccess;
import de.woock.stammdaten.fuhrpark.KfzsGenerator;
import de.woock.stammdaten.fuhrpark.StationenGenerator;
import de.woock.stammdaten.mitglieder.MitgliedGenerator;
import de.woock.stammdaten.reservierung.ZeitraumGenerator;
import de.woock.stammdaten.werkstatt.UnfallMeldungenGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class FXStammdatenController {
	
	private final UnfallMeldungenGenerator unfallMeldungenGenerator;
	private final StammdatenAccess         stammdatenService;
	private final VersicherungAccess       versicherungsService;
	private final ReservierungAccess       reservierungsService;
	private final UnfallmeldungAccess      unfallmeldungService;
	
	@FXML	ComboBox<String> cbMonat;
	@FXML	ComboBox<String> cbFuerMonat;
	@FXML   Button           bnDeleteDB;
	
	@FXML
	public void initialize() {
		cbFuerMonat.getItems().clear();
		cbFuerMonat.getItems().addAll(Arrays.asList(new DateFormatSymbols(GERMANY).getMonths()));
		cbMonat.getItems().clear();
		cbMonat.getItems().addAll(Arrays.asList(new DateFormatSymbols(GERMANY).getMonths()));
	}
	
	@FXML 
	public void handleDeleteDB() {
		stammdatenService.deleteDB();
	}
	
	@FXML
	public void handleGeneriereMitglieder() {
		new MitgliedGenerator(stammdatenService).generiereMitglieder();
	}
	
	@FXML
	public void handleGeneriereStationen() {
		 new StationenGenerator(stammdatenService).generiereStationen();
	}
	
	@FXML
	public void handleGeneriereKfzs() {
		new KfzsGenerator(stammdatenService).kfzsAnlegen();
	}
	
	@FXML
	public void handleBnMonatsabrechnung() {
		log.debug(String.format("Monatslauf f�r %s angesto�en", cbMonat.getSelectionModel().getSelectedItem()));
		stammdatenService.monatsabrechnung(cbMonat.getSelectionModel().getSelectedIndex());
	}
	
	@FXML
	public void handleGeneriereReservierungen() {
		List<Mitglied>    mitglieder = stammdatenService.alleMitglieder();
		List<Kfz>         kfzs       = stammdatenService.alleKfzs();
		ZeitraumGenerator zeiten     = new ZeitraumGenerator();
		Random            random     = new Random();
		
		log.debug("generate Reservierungen");
		int monat = 1 + cbFuerMonat.getSelectionModel().getSelectedIndex();
		mitglieder.forEach(mitglied -> zeiten.getZeitraeume(monat, random.nextInt(10))
				                             .forEach(zeitraum -> stammdatenService
				                            		                           .neueReservierung(new Reservierung(Status.angelegt, 
				                                                                                                  mitglied, 
				                                                                                                  zeitraum, 
				                                                                                                  kfzs.get(random.nextInt(kfzs.size())),
				                                                                                                  generiereFahrberichte(zeitraum),
				                                                                                                  generiereBelege()))));
		log.debug("Reservierungen generated");
		Notifications.create()
                     .title("Stammdaten")
                     .text("Reservierungen generiert!")
                     .showInformation();
	}
	
	@FXML
	public void handleGeneriereVersicherungen() {
		Random random = new Random();
		log.debug("generate Versicherungen");
		List<Reservierung> reservierungen = reservierungsService.alleReservierungen();
		reservierungen.forEach(reservierung -> versicherungsService.legeVersicherungAn(new Versicherung(reservierung, Versicherung.Typ.values()[random.nextInt(Versicherung.Typ.values().length)], random.nextBoolean())));
		log.debug("Versicherungen generated");
		Notifications.create()
                     .title("Stammdaten")
                     .text("Versicherungen generiert!")
                     .showInformation();
	}
	
	@FXML
	public void handleGeneriereSchadensmeldungen() {
		log.debug("generate Schadensmeldungen");
		
		List<Unfallmeldung> unfallmeldungen = unfallMeldungenGenerator.generiereUnfallmeldungen(stammdatenService); 
		unfallmeldungen.forEach(unfallmeldungService::save);
		
		log.debug("Schadensmeldungen generated");
		Notifications.create()
		             .title("Stammdaten")
		             .text("Schadensmeldungen generiert!")
		             .showInformation();
	}
	
	@FXML
	public void handleGeneriereStammdaten() throws InterruptedException {
		log.debug("generate Stammdaten");
		handleGeneriereMitglieder();        
		handleGeneriereStationen();         
		handleGeneriereKfzs();              
		handleGeneriereReservierungen();    
		handleGeneriereVersicherungen();    
		handleGeneriereSchadensmeldungen();
		
		
		log.debug("Stammdaten generated");
		Notifications.create()
		             .title("Stammdaten")
                     .darkStyle()
		             .text("Stammdaten generiert!")
		             .showInformation();

		stammdatenService.printStatistik();
	}

	private Fahrbericht generiereFahrberichte(Zeitraum zeitraum) {
		Random rnd = new Random();
		return new Fahrbericht(zeitraum.verlaengernUmMinuten(rnd.nextInt(30)-25), new Strecke(rnd.nextInt(50)+150));
		
	}
	
   private List<Beleg> generiereBelege() {
	   Random rnd = new Random();
	   ArrayList<Beleg> belege = new ArrayList<>();
	   if (rnd.nextInt(100) < 15) belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(rnd.nextInt(150)+30), "Tanken"));
	   if (rnd.nextInt(100) < 5)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(rnd.nextInt(15)+10) , "Wagen gewaschen"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(rnd.nextInt(15)+5)  , "Neue Scheibenwischer gekauft"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(rnd.nextInt(2)+1)   , "Parkscheibe gekauft"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(rnd.nextInt(15)+5)  , "Eiskratzer gekauft"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(25)                 , "Entsch�digung bei nicht verf�gbarem Kfz"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(-25)                , "Ersatzkarte"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(-50)                , "Versp�tete R�ckgabe ohne Benachrichtigung"));
	   if (rnd.nextInt(100) < 1)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(-30)                , "Versp�tete R�ckgabe mit Benachrichtigung"));
	   if (rnd.nextInt(100) < 3)  belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), new Geldbetrag(-5)                 , "Strafzettelbeareitung"));
	   return belege;
   }
}
