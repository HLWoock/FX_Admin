package de.woock.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import de.woock.entity.Mitglied;
import de.woock.service.MitgliederService;
import de.woock.service.communication.MitgliederAccess;
import de.woock.ui.model.MitgliedUI;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Controller
public class MitgliederCtrl implements MitgliederAccess {
	
	private final MitgliederService mitgliederService;

	@Override
	public ObservableList<MitgliedUI> mitgliederUI(String name, int page) {
		return mitgliederService.mitgliederUI(name, page);
	}

	@Override
	public ObservableList<String> mitgliederNamenUI() {
		return mitgliederService.mitgliederNamenUI();
	}

	@Override
	public void save(MitgliedUI mitgliedUI) {
		mitgliederService.save(mitgliedUI);
	}

	@Override
	public void delete(MitgliedUI mitgliedUI) {
		mitgliederService.delete(mitgliedUI);
	}

	@Override
	public List<Mitglied> findByName(String name) {
		return mitgliederService.findByName(name);
	}

	@Override
	public Mitglied findByNameAndVorname(String name, String vorname) {
		return mitgliederService.findByNameAndVorname(name, vorname);
	}

	@Override
	public Mitglied findByMitliedID(String mitgliedID) {
		return mitgliederService.findByMitliedID(mitgliedID);
	}

	@Override
	public Long chunksForFilter(String name) {
		return mitgliederService.chunksForFilter(name);
	}

}
