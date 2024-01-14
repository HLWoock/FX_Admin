package de.woock.service.communication;

import de.woock.entity.Unfallmeldung;
import de.woock.ui.model.AbrechnungUI;
import de.woock.ui.model.UnfallmeldungUI;
import javafx.collections.ObservableList;

public interface UnfallmeldungAccess {

	Unfallmeldung unfallMelden(Unfallmeldung unfallmeldung);

	ObservableList<UnfallmeldungUI> offeneUnfallmeldungenUI();

	ObservableList<UnfallmeldungUI> unfallmeldungenUI();

	ObservableList<AbrechnungUI> abrchnungenUI();

	Unfallmeldung unfallmeldungMitId(String id);

	void save(Unfallmeldung unfallmeldung);

}