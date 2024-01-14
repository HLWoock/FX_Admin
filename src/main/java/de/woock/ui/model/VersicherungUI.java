package de.woock.ui.model;

import de.woock.entity.Reservierung;
import de.woock.entity.Versicherung;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VersicherungUI {

	private Long                  id;
	private int                   version;
	private StringProperty        typ;
	private SimpleBooleanProperty beifahrerSchutz;

	private Reservierung   reservierung;
	
	public VersicherungUI() {
		this.typ             = new SimpleStringProperty();
		this.beifahrerSchutz = new SimpleBooleanProperty();
		this.reservierung    = null;
	}
	
	public VersicherungUI(Versicherung versicherung) {
		this.typ          = new SimpleStringProperty(versicherung.getTyp().name());
		this.beifahrerSchutz = new SimpleBooleanProperty(versicherung.getBeifahrerschutz());
		this.reservierung = versicherung.getReservierung();
	}

	public void setTyp(StringProperty typ) {this.typ = typ;}
	
	public Long         getId()              {return id;}
	public int          getVersion()         {return version;}
	public String       getTyp()             {return typ.get();}
	public Boolean      getBeifahrerSchutz() {return beifahrerSchutz.get();}
	public Reservierung getReservierung()    {return reservierung;}
	
	public Versicherung convertToVersicherung() {
		return new Versicherung(reservierung, Versicherung.Typ.valueOf(getTyp()), getBeifahrerSchutz());
	}
}
