package de.woock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Kfz;
import de.woock.entity.Mitglied;
import de.woock.entity.Reservierung;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import de.woock.entity.Versicherung;
import de.woock.remote.VereinClient;
import de.woock.service.communication.ReservierungAccess;
import de.woock.service.communication.StammdatenAccess;
import de.woock.service.communication.VersicherungAccess;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StammdatenService implements StammdatenAccess {
	
	private final ReservierungAccess   reservierungsService; 
	private final StatistikService     statistikService;
	private final VersicherungAccess   versicherungsService;
	private final AbrechnungsService   abrechnungsService;
	
	private final VereinClient vereinClient;

	@Override
	public List<Mitglied> alleMitglieder() {
		return null;
	}

	@Override
	public List<Kfz> alleKfzs() {
		return null;
	}

	@Override
	public List<Reservierung> alleReservierungen() {
		return reservierungsService.alleReservierungen();
	}

	@Override
	public List<Station> alleStationen() {
		return null;
	}

	@Override
	public void neuesMitglied(Mitglied mitglied) {
		vereinClient.mitgliedErstellen(mitglied);
	}

	@Override
	public void neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {

	}

	@Override
	public Boolean neueReservierung(Reservierung reservierung) {
		return reservierungsService.reserviere(reservierung);
	}

	@Override
	public void kfzStationZuordnen(Station station) {
		
	}

	@Override
	public void kuendigeAlleMitglieder() {
		
	}
	
	@Override
	public void kuendigeAlleMitgliederIn(String stadt) {
	}

	@Override
	public void printStatistik() {
		statistikService.printStatistic();
	}

	@Override
	public void monatsabrechnung(int monat) {
		abrechnungsService.monatsabrechnung(monat);
	}

	@Override
	public Versicherung versicherungZu(Reservierung reservierung) {
		return versicherungsService.versicherungFuerReservierung(reservierung);
	}

	@Override
	public void deleteDB() {
	}
}
