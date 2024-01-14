package de.woock.service.monitoring;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.woock.entity.KfzKlasse;
import de.woock.service.communication.MonitoringAccess;
import de.woock.service.communication.ReservierungAccess;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MonitoringService implements MonitoringAccess {
	
	private final ReservierungAccess reservierungsService;

	@Override
	public Map<String, Long> reservierungenPerTyp() {
		return reservierungsService.alleReservierungen().stream()
				                                        .collect(Collectors.groupingBy(r -> r.getKfz().getTyp(), 
				                                                                            Collectors.counting()));		
	}
	
	@Override
	public Map<String, Long> reservierungenPerTypForKlasse(KfzKlasse klasse) {
		return reservierungsService.alleReservierungen().stream()
				                                        .filter(entry -> entry.getKfz().getKlasse() == klasse)
				                                        .collect(Collectors.groupingBy(r -> r.getKfz().getTyp(), 
				                                                                            Collectors.counting()));		
	}
	
	@Override
	public Map<String, Long> reservierungenPerKlasse() {
		return reservierungsService.alleReservierungen().stream()
				                                        .collect(Collectors.groupingBy(r -> r.getKfz().getKlasse().name(), 
						                                                                    Collectors.counting()));		
	}

}
