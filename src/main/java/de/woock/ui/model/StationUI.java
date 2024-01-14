package de.woock.ui.model;

import java.util.List;

import de.woock.entity.Auswahlkriterien;
import de.woock.entity.Gps;
import de.woock.entity.Kfz;
import de.woock.entity.Spezifikation;
import de.woock.entity.Station;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StationUI {

	private Long           id;
	private int            version;
	private StringProperty kuerzel;
	private StringProperty stadt;
	private StringProperty stadtteil;
	private StringProperty standort;
	private StringProperty beschreibung;
	private StringProperty oepnv;
	private DoubleProperty lat;
	private DoubleProperty lng;
	private List<Kfz>      kfzs;

	public StationUI() {
		this.kuerzel      = new SimpleStringProperty();
		this.stadt        = new SimpleStringProperty();
		this.stadtteil    = new SimpleStringProperty();
		this.standort     = new SimpleStringProperty();
		this.beschreibung = new SimpleStringProperty();
		this.oepnv        = new SimpleStringProperty();
		this.lat          = new SimpleDoubleProperty();
		this.lng          = new SimpleDoubleProperty();
		this.kfzs         = null;
	}
	
	public StationUI(Station station) {
		this.kfzs         = station.getKfzs();
		this.kuerzel      = new SimpleStringProperty(station.auswahlkriterien.getKuerzel());
		this.stadt        = new SimpleStringProperty(station.auswahlkriterien.getStadt());
		this.stadtteil    = new SimpleStringProperty(station.auswahlkriterien.getStadtteil());
		this.standort     = new SimpleStringProperty(station.auswahlkriterien.getStandort());
		this.beschreibung = new SimpleStringProperty(station.spezifikation.getBeschreibung());
		this.oepnv        = new SimpleStringProperty(station.spezifikation.getOepnv());
		this.lat          = new SimpleDoubleProperty(station.spezifikation.getPosition().getLat());
		this.lng          = new SimpleDoubleProperty(station.spezifikation.getPosition().getLng());
	}
	
	public void setKuerzel     (String kuerzel)      {this.kuerzel.set(kuerzel);}
	public void setStadt       (String stadt)        {this.stadt.set(stadt);}
	public void setStadtteil   (String stadtteil)    {this.stadtteil.set(stadtteil);}
	public void setStandort    (String standort)     {this.standort.set(standort);}
	public void setBeschreibung(String beschreibung) {this.beschreibung.set(beschreibung);}
	public void setOepnv       (String oepnv)        {this.oepnv.set(oepnv);}
	public void setLat         (Double lat)          {this.lat.set(lat);} 
	public void setLng         (Double lng)          {this.lng.set(lng);}
	
	public Long   getId()           {return id;}
	public int    getVersion()      {return version;}
	public List<Kfz> getKfzs()      {return kfzs;}
	public String getKuerzel()      {return kuerzel.get();}
	public String getStadt()        {return stadt.get();}
	public String getStadtteil()    {return stadtteil.get();}
	public String getStandort()     {return standort.get();}
	public String getBeschreibung() {return beschreibung.get();}
	public String getOepnv()        {return oepnv.get();}
	public Double getLat()          {return lat.get();}
	public Double getLng()          {return lng.get();}

	public Station convertToStation() {
		Auswahlkriterien auswahlkriterien = new Auswahlkriterien(getStadt(), getKuerzel(), getStadtteil(), getStandort());
		Spezifikation    spezifikation    = new Spezifikation(getBeschreibung(), new Gps(getLat(), getLng()), getOepnv());
		Station station = new Station(auswahlkriterien, spezifikation, kfzs);
		return station;
	}

}
