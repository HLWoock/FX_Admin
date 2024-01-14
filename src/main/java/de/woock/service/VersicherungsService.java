package de.woock.service;

import org.springframework.stereotype.Service;

import de.woock.entity.Reservierung;
import de.woock.entity.Versicherung;
import de.woock.service.communication.VersicherungAccess;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VersicherungsService implements VersicherungAccess {
	

	@Override
	public Versicherung versicherungFuerReservierung(Reservierung reservierung) {
		return null;
	}
	
	@Override
	public Versicherung legeVersicherungAn(Versicherung versicherung) {
		versicherung.getReservierung().setVersicherung(versicherung);
		return null;
	}

}
