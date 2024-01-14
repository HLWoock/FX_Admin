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
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Geldbetrag implements Comparable<Geldbetrag>, Serializable{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal betrag;
	private Currency waehrung=null;
	
	public Currency getWaehrung() {
		return waehrung;
	}
			
	public Geldbetrag() {
		super();
		betrag=new BigDecimal(0);
		this.waehrung=Currency.getInstance(Locale.getDefault());

	}

	public BigDecimal getBetrag() {
		return betrag;
	}

	public Geldbetrag(BigDecimal d) {		
		betrag=d;
		this.waehrung=Currency.getInstance(Locale.getDefault());
	}

	public Geldbetrag(String d) {		
		betrag=new BigDecimal(d);
		this.waehrung=Currency.getInstance(Locale.getDefault());
	}
	
	public Geldbetrag(BigDecimal betrag, Currency currency) {
		super();
		this.betrag = betrag;
		this.waehrung = currency;
	}

	public Geldbetrag(int i) {
		this.betrag=new BigDecimal(i);
		this.waehrung=Currency.getInstance(Locale.getDefault());
	}
	
	
	public Geldbetrag add(Geldbetrag other) {
		if (!other.getWaehrung().equals(waehrung))
			throw new InvalidCurrencyException();
		return new Geldbetrag(betrag.add(other.getBetrag()));
	}

	public boolean equals(Object obj) {
		if (this==obj)
			return true;
		if (obj==null || obj.getClass()!=getClass())
			return false;
		Geldbetrag other = (Geldbetrag) obj;
		return this.betrag.equals(other.betrag) && this.waehrung.equals(other.waehrung);
	}
		
	public int hashCode() {
		return 2*betrag.hashCode()+3*waehrung.hashCode();
	}
	
	
	public String toString() {
		return waehrung.toString() + " " + betrag.toString();
	}

	public Geldbetrag multiply(long d) {		
		return new Geldbetrag(betrag.multiply(new BigDecimal(d)));
	}

	public int compareTo(Geldbetrag other) {
		if (!other.getWaehrung().equals(waehrung))
			throw new InvalidCurrencyException();
		return this.betrag.compareTo(other.getBetrag());
	}
}
