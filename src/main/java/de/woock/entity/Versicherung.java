package de.woock.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Versicherung implements Serializable {

	private Reservierung reservierung;
	private Typ          typ = Typ.KEINE;
	private Boolean      beifahrerschutz;
	
	public Versicherung(Reservierung reservierung) {
		this.reservierung = reservierung;
		this.reservierung.setVersicherung(this);
	}

	public enum Typ {
		KEINE,
		VOLLKASKO,
		TEILKASKO;
	}
}
