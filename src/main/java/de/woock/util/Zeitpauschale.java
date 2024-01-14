package de.woock.util;

import de.woock.entity.Geldbetrag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Zeitpauschale {

	public long       zeit;
	public Geldbetrag pauschale;
	public String     tarif;
}
