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
public class Unfallmeldung implements Serializable {
	
	private Reservierung reservierung;
	private Versicherung versicherung;
	private String       schadenmass;
	private String       massnahme;
    private String       bericht;
    private Date         datum;
    private Status       status = Status.AUFGENOMMEN;
    
    public enum Status {
    	AUFGENOMMEN, IN_KLAERUNG, IN_BEARBEITUNG, ERLEDIGT;
    }
	
}
