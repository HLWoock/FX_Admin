package de.woock.service;

import org.springframework.stereotype.Service;

import de.woock.config.TarifeConfig;
import de.woock.entity.Geldbetrag;
import de.woock.entity.KfzKlasse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TarifeService {
	
	private TarifeConfig tarifeConfig;

	public Geldbetrag getKilometerTarif(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.KILOMETER.name());
	}

	public Geldbetrag getStundenTarif(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.STUNDEN.name());
	}

	public Geldbetrag getTagesTarif(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.TAGE.name());
	}

	public Geldbetrag getWochenTarif(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.WOCHEN.name());
	}
	
	public Geldbetrag getVollkasko(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.VOLLKASKO.name());
	}
	
	public Geldbetrag getTeilkasko(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.TEILKASKO.name());
	}
	
	public Geldbetrag getBeifahrerschutz(KfzKlasse k) {
		return tarifeConfig.getTarife().get(k).get(TarifTyp.BEIFAHRERSCHUTZ.name());
	}
	
	public enum TarifTyp {KILOMETER, STUNDEN, TAGE, WOCHEN, VOLLKASKO, TEILKASKO, BEIFAHRERSCHUTZ;}
}
