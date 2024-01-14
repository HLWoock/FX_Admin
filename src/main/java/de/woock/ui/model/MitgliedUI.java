package de.woock.ui.model;

import java.util.UUID;

import de.woock.entity.Adresse;
import de.woock.entity.Mitglied;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MitgliedUI {
	
	private Long           id;
	private int            version;
	private Adresse        adresse;
	private StringProperty mitgliederID;
	private StringProperty vorname;
	private StringProperty name;
	private StringProperty ort;
	private StringProperty strasse;
	private StringProperty telefon;
	private StringProperty email;
	
	public MitgliedUI() {
		adresse = new Adresse();
		vorname      = new SimpleStringProperty();
		name         = new SimpleStringProperty("");
		mitgliederID = new SimpleStringProperty(UUID.randomUUID().toString());
		strasse      = new SimpleStringProperty("");
		ort          = new SimpleStringProperty("");
		telefon      = new SimpleStringProperty("");
		email        = new SimpleStringProperty("");
	}
	
	public MitgliedUI(Mitglied mitglied) {
//		mitglied.setAdresse(mitglied.getAdresse());
//		id      = mitglied.getId();
//		version = mitglied.getVersion();
//		adresse = mitglied.getAdresse();
//		vorname = new SimpleStringProperty(mitglied.getVorname());
//		name    = new SimpleStringProperty(mitglied.getName());
//		
//		mitgliederID = new SimpleStringProperty(String.valueOf(mitglied.getMitgliedID()));
//		strasse      = new SimpleStringProperty(mitglied.getAdresse().getStrasse());
//		ort          = new SimpleStringProperty(mitglied.getAdresse().getOrt());
//		telefon      = new SimpleStringProperty(mitglied.getTelefon());
//		email        = new SimpleStringProperty(mitglied.getEmail());
	}

	public void setMitgliederID(String mitgliederID) {this.mitgliederID.set(mitgliederID);}
	public void setVorname     (String vorname)      {this.vorname.set(vorname);}
	public void setName        (String name)         {this.name.set(name);}
	public void setStrasse     (String strasse)      {this.strasse.set(strasse);}
	public void setOrt         (String ort)          {this.ort.set(ort);}
	public void setTelefon     (String telefon)      {this.telefon.set(telefon);}
	public void setEmail       (String email)        {this.email.set(email);}
	
	public Long    getId()           {return id;}
	public int     getVersion()      {return version;}
	public Adresse getAdresse()      {return adresse;}
	public String  getMitgliederID() {return mitgliederID.get();}
	public String  getVorname()      {return vorname.get();}
	public String  getName()         {return name.get();}
	public String  getStrasse()      {return strasse.get();}
	public String  getOrt()          {return ort.get();}
	public String  getTelefon()      {return telefon.get();}
	public String  getEmail()        {return email.get();}

//	public Mitglied getMitglied() {
//		adresse.setOrt(getOrt());
//		adresse.setStrasse(getStrasse());
//		
//		Mitglied mitglied = new Mitglied(getMitgliederID(), getVorname(), getName(), getAdresse(), getTelefon(), getEmail());
//		
//		mitglied.setId(getId());
//		mitglied.setVersion(getVersion());
//		
//		return mitglied;
//	}
}
