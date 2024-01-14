/*
 * Copyright oose innovative Informatik GmbH All Rights Reserved.
 *
 * This software is the proprietary information of oose.de GmbH
 * Use is subject to license terms.
 * 
 * http://www.oose.de
 */
package de.woock.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Rechnung implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Mitglied mitglied;
	private List<Buchung> buchungen = new ArrayList<>();
	
	public Rechnung(Mitglied mitglied) {
		this.mitglied = mitglied;
	}

	public List<Buchung> getPositionen() {
		return buchungen;
	}

	public void add(Buchung b) {
		buchungen.add(b);
	}
	
	public Geldbetrag getSaldo() {
		Geldbetrag saldo=new Geldbetrag();
		Iterator<Buchung> it=buchungen.iterator();
		while(it.hasNext()) {
			Buchung b=it.next();
			saldo=saldo.add(b.getBetrag());
		}
		return saldo;
	}
	
	public Mitglied getMitglied() {
		return mitglied;
	}
	
	public List<Buchung> getBuchungen() {
		return buchungen;
	}
}