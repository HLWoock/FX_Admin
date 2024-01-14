package de.woock.stammdaten.fuhrpark;

import java.util.List;
import java.util.Random;

import org.controlsfx.control.Notifications;

import de.woock.entity.Kfz;
import de.woock.entity.Station;
import de.woock.service.communication.StammdatenAccess;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class KfzsGenerator {
	
	StammdatenAccess stammdatenService;
	
	public KfzsGenerator (StammdatenAccess stammdatenService) {
		this.stammdatenService = stammdatenService;
	}
	
	Random   rand    = new Random();
	
	public void kfzsAnlegen()  {
		log.debug("generate Kfzs");
		List<Station> stationen = stammdatenService.alleStationen();
		
		stationen.forEach(station -> {
				for (int i = 1; i <= 3 + rand.nextInt(4); i++) {
					Kfz kfz= KfzsEinrichten.createAuto(station);
					station.autoEinrichten(kfz);
				}
				stammdatenService.kfzStationZuordnen(station);
			});
		log.debug("Kfzs generated");
		Notifications.create()
        .title("Stammdaten")
        .text("Kfzs generated!")
        .darkStyle()
        .showInformation();
	}
}