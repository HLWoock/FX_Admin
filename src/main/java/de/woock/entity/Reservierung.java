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
import java.util.List;


public class Reservierung implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Zeitraum zeitraum = null;
	
    public Status    status;
	private Fahrbericht fahrbericht = null;
	private Kfz kfz = null;
	private Mitglied mitglied = null;
	private List<Beleg> belege;
	private Versicherung versicherung;
	
	public Reservierung(Status status, Mitglied mitglied, Zeitraum zeitraum, Kfz kfz) {
		super();
		this.zeitraum     = zeitraum;
		this.kfz          = kfz;
		this.mitglied     = mitglied;
        this.status       = Status.angelegt;
        this.versicherung = null;
	}
	
	public Reservierung(Status status, Mitglied mitglied, Zeitraum zeitraum, Kfz kfz, Fahrbericht fahrbericht, List<Beleg> belege) {
		super();
		this.zeitraum     = zeitraum;
		this.kfz          = kfz;
		this.mitglied     = mitglied;
		this.fahrbericht  = fahrbericht;
		this.belege       = belege;
		this.status       = Status.angelegt;
		this.versicherung = null;
	}
	
	public Kfz getKfz() {
		return kfz;
	}
	
	public Fahrbericht getFahrbericht() {
		return fahrbericht;
	}
			
	Reservierung() {
		super();
	}

	public boolean isOpen() {
		return fahrbericht==null;
	}
	
	public Fahrbericht erzeugeFahrbericht(Zeitraum z, Strecke km) {
		fahrbericht=new Fahrbericht(z,km);
		return fahrbericht;
	}
	
	public Zeitraum getZeitraum() {
		return zeitraum;
	}
	
	
	public Mitglied getMitglied() {
		return mitglied;
	}	
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setZeitraum (Zeitraum zeitraum) {
		this.zeitraum = zeitraum;
	}

	public void setKfz(Kfz kfz) {
		this.kfz = kfz;
	}
	
	public List<Beleg> getBelege() {
		return belege;
	}
	
	public void setVersicherung(Versicherung versicherung) {
		this.versicherung = versicherung;
	}
	
	public Versicherung getVersicherung() {
		return versicherung;
	}
}
