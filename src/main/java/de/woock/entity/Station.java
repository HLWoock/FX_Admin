package de.woock.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Station implements Serializable {

	public Station(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation, List<Kfz> kfzs) {
		this.auswahlkriterien = auswahlkriterien;
		this.spezifikation    = spezifikation;
		this.kfzs             = kfzs;
	}

	public Spezifikation    spezifikation;
    public Auswahlkriterien auswahlkriterien;
	public List<Kfz>        kfzs  = new ArrayList<>();
	
	public void autoEinrichten(Kfz kfz) {
		
		kfzs.add(kfz);
	}
}
