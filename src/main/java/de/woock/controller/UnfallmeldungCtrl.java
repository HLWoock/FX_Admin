package de.woock.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import de.woock.entity.Unfallmeldung;
import de.woock.service.UnfallmeldungService;
import de.woock.service.communication.UnfallmeldungAccess;
import de.woock.ui.model.AbrechnungUI;
import de.woock.ui.model.UnfallmeldungUI;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Controller
public class UnfallmeldungCtrl implements UnfallmeldungAccess {

	private final UnfallmeldungService unfallmeldungService;
	
	@Override
	public Unfallmeldung unfallMelden(Unfallmeldung unfallmeldung) {
		return unfallmeldungService.unfallMelden(unfallmeldung);
	}

	@Override
	public ObservableList<UnfallmeldungUI> offeneUnfallmeldungenUI() {
		return unfallmeldungService.offeneUnfallmeldungenUI();
	}

	@Override
	public ObservableList<UnfallmeldungUI> unfallmeldungenUI() {
		return unfallmeldungService.unfallmeldungenUI();
	}

	@Override
	public ObservableList<AbrechnungUI> abrchnungenUI() {
		return unfallmeldungService.abrchnungenUI();
	}

	@Override
	public Unfallmeldung unfallmeldungMitId(String id) {
		return unfallmeldungService.unfallmeldungMitId(id);
	}

	@Override
	public void save(Unfallmeldung unfallmeldung) {
		unfallmeldungService.save(unfallmeldung);
	}

}
