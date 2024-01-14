package de.woock.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class Spezifikation implements Serializable {
	String beschreibung;
	Gps    position;
	String oepnv;
}
