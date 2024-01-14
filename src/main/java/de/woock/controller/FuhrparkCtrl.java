package de.woock.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Kfz;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import de.woock.service.FuhrparkService;
import de.woock.service.communication.FuhrparkAccess;
import de.woock.ui.model.StationUI;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Controller
public class FuhrparkCtrl implements FuhrparkAccess {
	
	private final FuhrparkService fuhrparkService;

	@Override
	public ObservableList<StationUI> stationenUI() {
		return fuhrparkService.stationenUI();
	}

	@Override
	public ObservableList<String> staedteUI() {
		return fuhrparkService.staedteUI();
	}

	@Override
	public ObservableList<String> staedtteileUI(String stadt) {
		return fuhrparkService.staedtteileUI(stadt);
	}

	@Override
	public void neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
		fuhrparkService.neueStationEinrichten(auswahlkriterien, spezifikation);
	}

	@Override
	public void delete(StationUI stationUI) {
		fuhrparkService.delete(stationUI);

	}

	@Override
	public List<String> stadtteile(String stadt) {
		return fuhrparkService.stadtteile(stadt);
	}

	@Override
	public List<String> standorte(String stadt, String stadtteil) {
		return fuhrparkService.standorte(stadt, stadtteil);
	}

	@Override
	public List<Kfz> kfzs(String stadt, String stadtteil, String standort) {
		return fuhrparkService.kfzs(stadt, stadtteil, standort);
	}

	@Override
	public Kfz findKfzByKennzeichen(String Kennzeichen) {
		return fuhrparkService.findKfzByKennzeichen(Kennzeichen);
	}

	@Override
	public Station findStation(String stadt, String stadtteil, String standort) {
		return fuhrparkService.findStation(stadt, stadtteil, standort);
	}

	@Override
	public void save(Kfz kfz) {
		fuhrparkService.save(kfz);
	}

}
