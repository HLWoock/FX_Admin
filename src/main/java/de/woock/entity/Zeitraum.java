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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Zeitraum implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final long MS_PRO_S = 1000;
	public static final long S_PRO_MIN = 60;
	public static final long MIN_PRO_H = 60;
	public static final long MS_PRO_H = MS_PRO_S*S_PRO_MIN*MIN_PRO_H;
	public static final long H_PRO_D = 24;
	public static final long D_PRO_W = 7;
	public static final long S_PRO_W = D_PRO_W * H_PRO_D;
	
	
	private Date startZeit=null;
	private Date endZeit=null;

	Zeitraum() {
		
	}
	
	public Zeitraum(Date startZeit, Date endZeit) {
		this.startZeit = startZeit;
		this.endZeit = endZeit;
	}
	
	public Zeitraum(String startZeit, String endZeit) {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			this.startZeit= parser.parse(startZeit);
			this.endZeit= parser.parse(endZeit);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Ung�ltiges Datum");
		}
	}
	
	public Date getEndZeit() {
		return endZeit;
	}
	
	public Date getStartZeit() {
		return startZeit;
	}
	
	
	public boolean schneidet(Zeitraum z) {
		return (!(startZeit.after(z.endZeit) || (endZeit.before(z.startZeit))));
	}
	
	public double getStunden() {
		return ((double)(endZeit.getTime()-startZeit.getTime())) / MS_PRO_H; 
	}
	
	public double getTage() {
		return getStunden() / H_PRO_D; 
	}
	
	public double getWochen() {
		return getTage() / D_PRO_W; 
	}
	
	@SuppressWarnings("deprecation")
	public Zeitraum verlaengernUmMinuten(int dauer) {
		endZeit.setMinutes(endZeit.getMinutes() + dauer);
		return this;
	}
	
	public Zeitraum verlaegernBis(String ende) {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			endZeit= parser.parse(ende);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Ung�ltiges Datum");
		}
		return this;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return String.format("%02d.%02d.%s %02d:%02d - %02d.%02d.%s %02d:%02d", startZeit.getDate()+1, startZeit.getMonth()+1, startZeit.getYear()+1900, startZeit.getHours(), startZeit.getMinutes(),
				                                                                endZeit.getDate()+1  , endZeit.getMonth()+1  , endZeit.getYear()+1900  , endZeit.getHours()  , endZeit.getMinutes());
	}
}
