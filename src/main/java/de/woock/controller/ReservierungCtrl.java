package de.woock.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import de.woock.entity.Reservierung;
import de.woock.service.ReservierungsService;
import de.woock.service.communication.ReservierungAccess;
import de.woock.ui.model.ReservierungUI;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Controller
public class ReservierungCtrl implements ReservierungAccess {
	
	private final ReservierungsService reservierungsService;

	@Override
	public Boolean reserviere(Reservierung reservierung) {
		return reservierungsService.reserviere(reservierung);
	}

	@Override
	public Reservierung reservierungMitNummer(Long nummer) {
		return reservierungsService.reservierungMitNummer(nummer);
	}

	@Override
	public List<Reservierung> reservierungenVonMitglied(String mitgliedID) {
		return reservierungsService.reservierungenVonMitglied(mitgliedID);
	}

	@Override
	public ObservableList<ReservierungUI> reservierungenUIVonMitglied(String mitgliedID) {
		return reservierungsService.reservierungenUIVonMitglied(mitgliedID);
	}

	@Override
	public List<String> reservierungsNummernVonMitglied(String mitgliedID) {
		return reservierungsService.reservierungsNummernVonMitglied(mitgliedID);
	}

	@Override
	public List<Reservierung> alleReservierungen() {
		return reservierungsService.alleReservierungen();
	}

	@Override
	public void update(Reservierung reservierung) {
		reservierungsService.update(reservierung);

	}

}
