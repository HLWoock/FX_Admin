package de.woock.service;

import org.springframework.stereotype.Service;

import de.woock.entity.Unfallmeldung;
import de.woock.service.communication.UnfallmeldungAccess;
import de.woock.ui.model.AbrechnungUI;
import de.woock.ui.model.UnfallmeldungUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UnfallmeldungService implements UnfallmeldungAccess {
	
	
	private ObservableList<UnfallmeldungUI> unfallmeldungenUI = FXCollections.observableArrayList();
	private ObservableList<AbrechnungUI>    abrechnungenUI    = FXCollections.observableArrayList();

	@Override
	public Unfallmeldung unfallMelden(Unfallmeldung unfallmeldung) {
		return null;
	}
	
	@Override
	public ObservableList<UnfallmeldungUI> offeneUnfallmeldungenUI() {
		return unfallmeldungenUI;
	}

	@Override
	public ObservableList<UnfallmeldungUI> unfallmeldungenUI() {
		return unfallmeldungenUI;
	}

	@Override
	public ObservableList<AbrechnungUI> abrchnungenUI() {
		return abrechnungenUI;
	}

	@Override
	public Unfallmeldung unfallmeldungMitId(String id) {
		return null;
	}

	@Override
	public void save(Unfallmeldung unfallmeldung) {
	}

}
