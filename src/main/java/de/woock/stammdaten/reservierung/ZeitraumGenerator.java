package de.woock.stammdaten.reservierung;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.woock.entity.Zeitraum;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class ZeitraumGenerator {
	private static LocalDate now  = LocalDate.now();
	private static Random    rand = new Random();
	
	public List<Zeitraum> getZeitraeume(int monat, int count) {
		int actualMonth = now.getMonth().ordinal();
		int actulDay    = now.getDayOfMonth();
		if (actualMonth == monat) {
			count = count * 31 / actulDay;
		}
		List<Zeitraum> zeiten = new ArrayList<>();
		for(int i=0; i<count; i++) {
			int   year  = now.getYear();
			Month month = Month.of(monat); //now.getMonth();
			int   day   = rand.nextInt(month.maxLength()) + 1;
			LocalDateTime from;
			LocalDateTime to;
			try {
				from = LocalDateTime.of(year, month, day, rand.nextInt(22), 0, 0, 0);
				to = from.plusHours(rand.nextInt(7*24)+1);
				String von = String.format("%s.%s.%s %d:%d", from.getDayOfMonth(), from.getMonthValue(), from.getYear(), from.getHour(), from.getMinute());
				String bis = String.format("%s.%s.%s %d:%d", to.getDayOfMonth()  , to.getMonthValue()  , to.getYear()  , to.getHour()  , to.getMinute());
				Zeitraum zeitraum = new Zeitraum(von, bis);
				zeiten.add(zeitraum);
			} catch (DateTimeException e) {}
		}
		
		return zeiten;
	}

}
