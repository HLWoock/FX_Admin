package de.woock.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import de.woock.entity.Reservierung;
import de.woock.entity.Versicherung;
import de.woock.service.VersicherungsService;
import de.woock.service.communication.VersicherungAccess;
import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Controller
public class VersicherungCtrl implements VersicherungAccess {
	
	private final VersicherungsService versicherungsService;

	@Override
	public Versicherung versicherungFuerReservierung(Reservierung reservierung) {
		return versicherungsService.versicherungFuerReservierung(reservierung);
	}

	@Override
	public Versicherung legeVersicherungAn(Versicherung versicherung) {
		return versicherungsService.legeVersicherungAn(versicherung);
	}

}
