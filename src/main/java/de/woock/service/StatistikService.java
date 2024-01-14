package de.woock.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatistikService {
	
	public void printStatistic() {
		log.debug(String.format("Mitglieder     : %d", 0));
		log.debug(String.format("Stationen      : %d", 0));
		log.debug(String.format("Kfzs           : %d", 0));
		log.debug(String.format("Reservierungen : %d", 0));
		log.debug(String.format("Versicherungen : %d", 0));
		log.debug(String.format("Unfallmeldungen: %d", 0));
	}
}
