package de.woock.ui.model;

import de.woock.entity.Kfz;
import de.woock.entity.Mitglied;
import de.woock.entity.Reservierung;
import de.woock.entity.Status;
import de.woock.entity.Zeitraum;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
public class ReservierungUI {

	private Long      id;
	private int       version;
	private Mitglied  mitglied;
	private Kfz       kfz;
	private String    stadt;
	private String    stadtteil;
	private String    standort;
	private Zeitraum  zeitraum;
	private StringProperty zeitraumStr;
	private StringProperty status;
	private StringProperty kfzKlasse;
	private StringProperty kfzTyp;
	private StringProperty kennzeichen;
	private StringProperty station;
	
	public ReservierungUI() {
		this.zeitraumStr = new SimpleStringProperty();
		this.status      = new SimpleStringProperty();
		this.kfzKlasse   = new SimpleStringProperty();
		this.kfzTyp      = new SimpleStringProperty();
		this.kennzeichen = new SimpleStringProperty();
		this.station     = new SimpleStringProperty();
	}
	
	public ReservierungUI(Reservierung reservierung) {
		log.debug("convertiere Reservierung  Kfz: {}", reservierung.getKfz().getKennzeichen());
		this.mitglied    = reservierung.getMitglied();
		this.stadt       = reservierung.getKfz().getStation().getAuswahlkriterien().getStadt();
		this.stadtteil   = reservierung.getKfz().getStation().getAuswahlkriterien().getStadtteil();
		this.standort    = reservierung.getKfz().getStation().getAuswahlkriterien().getStandort();
		this.kfz         = reservierung.getKfz();
		this.zeitraum    = reservierung.getZeitraum();
		this.zeitraumStr = new SimpleStringProperty(reservierung.getZeitraum().toString());
		this.status      = new SimpleStringProperty(reservierung.getStatus().name());
		this.kfzKlasse   = new SimpleStringProperty(reservierung.getKfz().getKlasse().name());
		this.kfzTyp      = new SimpleStringProperty(reservierung.getKfz().getTyp());
		this.kennzeichen = new SimpleStringProperty(reservierung.getKfz().getKennzeichen());
		this.station     = new SimpleStringProperty(reservierung.getKfz().getStation().auswahlkriterien.getStandort());
	}

	public void setZeitraum    (StringProperty zeitraumStr) {this.zeitraumStr = zeitraumStr;}
	public void setStatus      (StringProperty status)      {this.status      = status;}
	public void setKfzKlasse   (StringProperty kfzKlasse)   {this.kfzKlasse   = kfzKlasse;}
	public void setKfzTyp      (StringProperty kfzTyp)      {this.kfzTyp      = kfzTyp;}
	public void setKennzeichen (StringProperty kennzeichen) {this.kennzeichen = kennzeichen;}
	public void setStation     (StringProperty station)     {this.station     = station;}
	
	public Long     getId()          {return id;}
	public int      getVersion()     {return version;}
	public Mitglied getMitglied()    {return mitglied;}
	public Kfz      getKfz()         {return kfz;}
	public String   getZeitraumStr() {return zeitraumStr.get();}
	public String   getStatus()      {return status.get();}
	public String   getKfzKlasse()   {return kfzKlasse.get();}
	public String   getKfzTyp()      {return kfzTyp.get();}
	public String   getKennzeichen() {return kennzeichen.get();}
	public String   getStation()     {return station.get();}
	public String   getStadt()       {return stadt;}
	public String   getStadtteil()   {return stadtteil;}
	public String   getStandort()    {return standort;}
	
	public Reservierung convertToReservierung() {
		Reservierung reservierung = new Reservierung(Status.valueOf(status.get()), mitglied, zeitraum, kfz);
		log.debug("convertiere Reservierung Kfz: {}", reservierung.getKfz().getKennzeichen());
		return reservierung;
	}
}
