package de.woock.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mitglied implements Serializable {
	Long        id;
	Anrede      anrede;
	String      vorname;
	String      name;
    String      username;
    String      telefon;
    String      mobil;
    String      email;
    Adresse     adresse;
}
