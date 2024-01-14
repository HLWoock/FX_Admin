package de.woock.ui.model;

import de.woock.entity.Kfz;
import de.woock.entity.KfzKlasse;
import de.woock.entity.Station;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KfzUI {

	private Long            id;
	private int             version;
	private StringProperty  kennzeichen;
	private StringProperty  typ;
	private StringProperty  klasse;
	private BooleanProperty gesperrt;
	private Station         station;
	
	public KfzUI() {
		this.kennzeichen = new SimpleStringProperty();
		this.typ         = new SimpleStringProperty();
		this.klasse      = new SimpleStringProperty();
		this.gesperrt    = new SimpleBooleanProperty();
		this.station     = null;
	}
	
	public KfzUI(Kfz kfz) {
		this.kennzeichen = new SimpleStringProperty(kfz.getKennzeichen());
		this.typ         = new SimpleStringProperty(kfz.getTyp());
		this.klasse      = new SimpleStringProperty(kfz.getKlasse().name());
		this.gesperrt    = new SimpleBooleanProperty(kfz.getGesperrt());
		this.station     = kfz.getStation();
	}

	public void setKennzeichen(StringProperty kennzeichen) {this.kennzeichen = kennzeichen;}
	public void setTyp(StringProperty typ)                 {this.typ = typ;}
	public void setKlasse(StringProperty klasse)           {this.klasse = klasse;}
	public void setGesperrt(BooleanProperty gesperrt)      {this.gesperrt = gesperrt;}
	
	public Long    getId()          {return id;}
	public int     getVersion()     {return version;}
	public String  getKennzeichen() {return kennzeichen.get();}
	public String  getTyp()         {return typ.get();}
	public String  getKlasse()      {return klasse.get();}
	public Boolean getGesperrt()    {return gesperrt.get();}
	public Station getStation()     {return station;}
	
	public Kfz convertToKfz() {
		return new Kfz(getKennzeichen(), getTyp(), KfzKlasse.valueOf(getKlasse()), station, getGesperrt());
	}
}
