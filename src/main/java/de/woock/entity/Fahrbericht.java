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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Fahrbericht implements Serializable {
	
	private Zeitraum zeitraum = null;
	private Strecke  km       = null;
	
}
