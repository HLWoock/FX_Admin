package de.woock.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Kfz;
import de.woock.entity.Mitglied;
import de.woock.entity.Reservierung;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import de.woock.entity.Versicherung;
import de.woock.service.StammdatenService;
import de.woock.service.communication.StammdatenAccess;
import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Controller
public class StammdatenCtrl implements StammdatenAccess {
	
	private final StammdatenService stammdatenService;

	@Override
	public List<Mitglied> alleMitglieder() {
		return stammdatenService.alleMitglieder();
	}

	@Override
	public List<Kfz> alleKfzs() {
		return stammdatenService.alleKfzs();
	}

	@Override
	public List<Reservierung> alleReservierungen() {
		return stammdatenService.alleReservierungen();
	}

	@Override
	public List<Station> alleStationen() {
		return stammdatenService.alleStationen();
	}

	@Override
	public void neuesMitglied(Mitglied mitglied) {
		stammdatenService.neuesMitglied(mitglied);
	}

	@Override
	public void neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
		stammdatenService.neueStationEinrichten(auswahlkriterien, spezifikation);
	}

	@Override
	public Boolean neueReservierung(Reservierung reservierung) {
		return stammdatenService.neueReservierung(reservierung);
	}

	@Override
	public void kfzStationZuordnen(Station station) {
		stammdatenService.kfzStationZuordnen(station);
	}

	@Override
	public void kuendigeAlleMitglieder() {
		stammdatenService.kuendigeAlleMitglieder();
	}

	@Override
	public void printStatistik() {
		stammdatenService.printStatistik();
	}

	@Override
	public void monatsabrechnung(int monat) {
		stammdatenService.monatsabrechnung(monat);
	}

	@Override
	public Versicherung versicherungZu(Reservierung reservierung) {
		return stammdatenService.versicherungZu(reservierung);
	}

	@Override
	public void deleteDB() {
		stammdatenService.deleteDB();
	}

	@Override
	public void kuendigeAlleMitgliederIn(String stadt) {
		// TODO Auto-generated method stub
		
	}

}
