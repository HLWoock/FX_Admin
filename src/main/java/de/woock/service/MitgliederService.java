package de.woock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.entity.Mitglied;
import de.woock.service.communication.MitgliederAccess;
import de.woock.ui.model.MitgliedUI;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MitgliederService implements MitgliederAccess {
	
	
	@Override
	public ObservableList<MitgliedUI> mitgliederUI(String name, int page) {
		return null;
	}
	
	@Override
	public ObservableList<String> mitgliederNamenUI() {
		return null;
	}

	@Override
	public void save(MitgliedUI mitgliedUI) {
	}

	@Override
	public void delete(MitgliedUI mitgliedUI) {
		
	}

	@Override
	public List<Mitglied> findByName(String name) {
		return null;
		
	}

	@Override
	public Mitglied findByNameAndVorname(String name, String vorname) {
		return null;
	}

	@Override
	public Mitglied findByMitliedID(String mitgliedID) {
		return null;
		
	}

	@Override
	public Long chunksForFilter(String name) {
		return  null;
	}

}
