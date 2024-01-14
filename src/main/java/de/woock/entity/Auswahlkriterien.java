package de.woock.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class Auswahlkriterien implements Serializable {

	private String stadt;
	private String kuerzel;
	private String stadtteil;
	private String standort;
}
