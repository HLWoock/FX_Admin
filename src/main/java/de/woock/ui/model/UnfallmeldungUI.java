package de.woock.ui.model;

import java.util.Date;

import de.woock.entity.Reservierung;
import de.woock.entity.Station;
import de.woock.entity.Unfallmeldung;
import de.woock.entity.Versicherung;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UnfallmeldungUI {
	
	private Long           id;
	private int            version;
	private Reservierung   reservierung;
	private Versicherung   versicherter;
	private Station        station;
	
	private StringProperty schadenmass;
	private StringProperty massnahme;
	private StringProperty kfzTyp;
	private StringProperty kennzeichen;
	private StringProperty bericht;
	private StringProperty datum;
	private StringProperty status;

	public UnfallmeldungUI() {
		schadenmass = new SimpleStringProperty();
		massnahme   = new SimpleStringProperty();
		kfzTyp      = new SimpleStringProperty();
		kennzeichen = new SimpleStringProperty();
		bericht     = new SimpleStringProperty();
		datum       = new SimpleStringProperty();
		status      = new SimpleStringProperty();
	}
	
	public UnfallmeldungUI(Unfallmeldung unfallmeldung) {
		reservierung = unfallmeldung.getReservierung();
		versicherter = unfallmeldung.getVersicherung();
		station      = unfallmeldung.getReservierung().getKfz().getStation();
		schadenmass  = new SimpleStringProperty(unfallmeldung.getSchadenmass());
		kfzTyp       = new SimpleStringProperty(unfallmeldung.getReservierung().getKfz().getTyp());
		kennzeichen  = new SimpleStringProperty(unfallmeldung.getReservierung().getKfz().getKennzeichen());
		massnahme    = new SimpleStringProperty(unfallmeldung.getMassnahme());
		bericht      = new SimpleStringProperty(unfallmeldung.getBericht());
		datum        = new SimpleStringProperty(unfallmeldung.getDatum().toString());
		status       = new SimpleStringProperty(unfallmeldung.getStatus().name());
	}
	
	public void setSchadenmass(String schadenmass) {this.schadenmass.set(schadenmass);}
	public void setMassnahme(String massnahme)     {this.massnahme.set(massnahme);}
	public void setKfzTyp(String kfzTyp)           {this.kfzTyp.set(kfzTyp);}
	public void setKennzeichen(String kennzeichen) {this.kennzeichen.set(kennzeichen);}
	public void setBericht(String bericht)         {this.bericht.set(bericht);}
	public void setDatum(String datum)             {this.bericht.set(datum);}
	public void setStatus(String status)           {this.bericht.set(status);}
	
	public Long         getId()           {return id;}
	public int          getVersion()      {return version;}
	public Reservierung getReservierung() {return reservierung;}
	public Versicherung getVersicherter() {return versicherter;}
	public String       getStation()      {return station.auswahlkriterien.getStadt() + ", " + station.auswahlkriterien.getStandort();}
	public String       getSchadenmass()  {return schadenmass.get();}
	public String       getKfzTyp()       {return kfzTyp.get();}
	public String       getKennzeichen()  {return kennzeichen.get();}
	public String       getMassnahme()    {return massnahme.get();}
	public String       getBericht()      {return bericht.get();}
	public String       getDatum()        {return datum.get();}
	public String       getStatus()       {return status.get();}
	
	public Unfallmeldung getUnfallmeldung() {
		Unfallmeldung unfallmeldung = new Unfallmeldung(reservierung, versicherter, getSchadenmass(), getMassnahme(), getBericht(), new Date(getDatum()), Unfallmeldung.Status.valueOf(getStatus()));
		
		return unfallmeldung;
	}
}
