package de.woock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.entity.Kfz;
import de.woock.entity.Reservierung;
import de.woock.entity.Zeitraum;
import de.woock.service.communication.ReservierungAccess;
import de.woock.ui.model.ReservierungUI;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReservierungsService implements ReservierungAccess {
	
	@Override
	public Boolean reserviere(Reservierung reservierung) {
		return null;
	}
	
	@Override
	public Reservierung reservierungMitNummer(Long nummer) {
		return null;
	}
	
	private Boolean istKfzFrei(Kfz kfz, Zeitraum zeitraum) {
        return null;
	}

	private void addKollisionToStation(Kfz kfz, Zeitraum zeitraum) {
	}

	@Override
	public List<Reservierung> reservierungenVonMitglied(String mitgliedID) {
		return null;
	}
	
	@Override
	public ObservableList<ReservierungUI> reservierungenUIVonMitglied(String mitgliedID) {
		 return null;
}

	@Override
	public List<String> reservierungsNummernVonMitglied(String mitgliedID) {
		return null;
	}

	@Override
	public List<Reservierung> alleReservierungen() {
		return null;
	}

	@Override
	public void update(Reservierung reservierung) {
	}
}
