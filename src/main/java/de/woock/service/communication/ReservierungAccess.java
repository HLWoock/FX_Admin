package de.woock.service.communication;

import java.util.List;

import de.woock.entity.Reservierung;
import de.woock.ui.model.ReservierungUI;
import javafx.collections.ObservableList;

public interface ReservierungAccess {

	Boolean reserviere(Reservierung reservierung);

	Reservierung reservierungMitNummer(Long nummer);

	List<Reservierung> reservierungenVonMitglied(String mitgliedID);

	ObservableList<ReservierungUI> reservierungenUIVonMitglied(String mitgliedID);

	List<String> reservierungsNummernVonMitglied(String mitgliedID);

	List<Reservierung> alleReservierungen();

	void update(Reservierung reservierung);

}