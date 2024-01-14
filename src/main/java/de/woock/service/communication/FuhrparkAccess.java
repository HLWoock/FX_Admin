package de.woock.service.communication;

import java.util.List;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Kfz;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import de.woock.ui.model.StationUI;
import javafx.collections.ObservableList;

public interface FuhrparkAccess {

	ObservableList<StationUI> stationenUI();

	ObservableList<String> staedteUI();

	ObservableList<String> staedtteileUI(String stadt);

	void neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation);

	void delete(StationUI stationUI);

	List<String> stadtteile(String stadt);

	List<String> standorte(String stadt, String stadtteil);

	List<Kfz> kfzs(String stadt, String stadtteil, String standort);

	Kfz findKfzByKennzeichen(String Kennzeichen);

	Station findStation(String stadt, String stadtteil, String standort);

	void save(Kfz kfz);

}