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

public class Kfz implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String kennzeichen = null;
	private String typ         = null;
	
	private KfzKlasse klasse   = null;
	private Station   station  = null;
	private Boolean   gesperrt = false;

	public Station getStation() {
		return station;
	}
	
	public void setStation(Station station) {
		this.station = station;
	}
	
	public KfzKlasse getKlasse() {
		return klasse;
	}
	
	public void setKlasse(KfzKlasse klasse) {
		this.klasse = klasse;
	}
	
	public String getTyp() {
		return typ;
	}
	
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public Boolean getGesperrt() {
		return gesperrt;
	}
	
	public void setGesperrt(Boolean gesperrt) {
		this.gesperrt = gesperrt;
	}
	
	public Kfz() {
		super();
	}
	public Kfz(String kuerzel, String typ, KfzKlasse klasse, Station station, Boolean gesperrt) {
		super();
		this.kennzeichen = kuerzel;
		this.typ         = typ;
		this.klasse      = klasse;
		this.station     = station;
		this.gesperrt    = gesperrt;
	}
	
	public String getKennzeichen() {
		return kennzeichen;
	}
	
	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}
}
