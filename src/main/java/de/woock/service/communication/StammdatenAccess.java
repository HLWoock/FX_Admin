package de.woock.service.communication;

import java.util.List;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Kfz;
import de.woock.entity.Mitglied;
import de.woock.entity.Reservierung;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import de.woock.entity.Versicherung;

public interface StammdatenAccess {

	List<Mitglied> alleMitglieder();

	List<Kfz> alleKfzs();

	List<Reservierung> alleReservierungen();

	List<Station> alleStationen();

	void neuesMitglied(Mitglied mitglied);

	void neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation);

	Boolean neueReservierung(Reservierung reservierung);

	void kfzStationZuordnen(Station station);

	void kuendigeAlleMitglieder();
	
	void deleteDB();

	void printStatistik();

	void monatsabrechnung(int monat);

	Versicherung versicherungZu(Reservierung reservierung);

	void kuendigeAlleMitgliederIn(String stadt);

}