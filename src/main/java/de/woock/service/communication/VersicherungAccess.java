package de.woock.service.communication;

import de.woock.entity.Reservierung;
import de.woock.entity.Versicherung;

public interface VersicherungAccess {

	Versicherung versicherungFuerReservierung(Reservierung reservierung);

	Versicherung legeVersicherungAn(Versicherung versicherung);

}