package de.woock.service.communication;

import java.util.Map;

import de.woock.entity.KfzKlasse;

public interface MonitoringAccess {

	Map<String, Long> reservierungenPerTyp();
	Map<String, Long> reservierungenPerTypForKlasse(KfzKlasse klasse);
	
	Map<String, Long> reservierungenPerKlasse();
}
