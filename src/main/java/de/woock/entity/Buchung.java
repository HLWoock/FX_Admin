/*
 * Copyright oose innovative Informatik GmbH All Rights Reserved.
 *
 * This software is the proprietary information of oose.de GmbH
 * Use is subject to license terms.
 * 
 * http://www.oose.de
 */
package de.woock.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Buchung implements Serializable {

	private final Date date = new Date();
	
	private Integer    anzahl;
	private Geldbetrag betrag;
	private String     text;
	private Long       reservierungsId;
	
	public Geldbetrag getGesamtbetrag() {
		return betrag.multiply(anzahl);
	}
}
