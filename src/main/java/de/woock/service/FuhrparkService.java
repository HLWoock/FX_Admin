package de.woock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Kfz;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import de.woock.service.communication.FuhrparkAccess;
import de.woock.ui.model.StationUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FuhrparkService implements FuhrparkAccess {

	private ObservableList<StationUI> stationen   = FXCollections.observableArrayList();
	private ObservableList<String>    staedte     = FXCollections.observableArrayList();
	private ObservableList<String>    staedtteile = FXCollections.observableArrayList();
	
	
	@Override
	public ObservableList<StationUI> stationenUI() {
		 return null;
	}

	@Override
	public ObservableList<String> staedteUI() {
		return null;
	}
	
	@Override
	public ObservableList<String> staedtteileUI(String stadt) {
		
		return null;
	}

	@Override
	public void neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
	}
	
	@Override
	public void delete(StationUI stationUI) {
	}

	@Override
	public List<String> stadtteile(String stadt) {
		return null;
	}

	@Override
	public List<String> standorte(String stadt, String stadtteil) {
		return null;
	}

	@Override
	public List<Kfz> kfzs(String stadt, String stadtteil, String standort) {
		return null;
	}
	
	@Override
	public Kfz findKfzByKennzeichen(String Kennzeichen) {
		return null;
	}

	@Override
	public Station findStation(String stadt, String stadtteil, String standort) {
		return null;
	}

	@Override
	public void save(Kfz kfz) {
	}

}
