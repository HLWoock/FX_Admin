package de.woock.ui.model;

import de.woock.entity.Beleg;
import de.woock.entity.Geldbetrag;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BelegUI {
	
	private Long           id;
	private int            version;
	private StringProperty nummer;
	private StringProperty betrag;
	private StringProperty beschreibung;

	public BelegUI() {
		nummer       = new SimpleStringProperty();
		betrag       = new SimpleStringProperty();
		beschreibung = new SimpleStringProperty();
	}
	
	public BelegUI(Beleg beleg) {
		nummer       = new SimpleStringProperty(beleg.getNummer());
		betrag       = new SimpleStringProperty(beleg.getBetrag().getBetrag().toPlainString());
		beschreibung = new SimpleStringProperty(beleg.getBeschreibung());
	}
	
	public void setNummer(String nummer) {this.nummer.set(nummer);}
	public void setBetrag(String betrag) {this.betrag.set(betrag);}
	public void setBeschreibung(String beschreibung) {this.beschreibung.set(beschreibung);}
	
	public Long   getId()           {return id;}
	public int    getVersion()      {return version;}
	public String getNummer()       {return nummer.get();}
	public String getBetrag()       {return betrag.get();}
	public String getBeschreibung() {return beschreibung.get();}
	
	public Beleg getBeleg() {
		Beleg beleg = new Beleg(getNummer(), new Geldbetrag(getBetrag()), getBeschreibung());
		
		return beleg;
	}
}
