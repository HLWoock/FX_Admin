package de.woock.service.communication;

import java.util.List;

import de.woock.entity.Mitglied;
import de.woock.ui.model.MitgliedUI;
import javafx.collections.ObservableList;

public interface MitgliederAccess {

	ObservableList<MitgliedUI> mitgliederUI(String name, int page);

	ObservableList<String> mitgliederNamenUI();

	void save(MitgliedUI mitgliedUI);

	void delete(MitgliedUI mitgliedUI);

	List<Mitglied> findByName(String name);

	// m�sste eigentlich eine Liste zur�ckgeben
	Mitglied findByNameAndVorname(String name, String vorname);

	Mitglied findByMitliedID(String mitgliedID);

	Long chunksForFilter(String name);

}