package de.woock.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Component;

import de.woock.entity.StockPrice;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Component
public class DataCtrl {

	public Flux<Typnutzung> Typnutzung() {
		// hole alle Reservierungen
		// 1. welche KfzKlasse wurde wie oft gebucht?
		// 2. welche KfzKlasse wurde wie lang gebucht?
		return Flux.just(new Typnutzung("VW Golf"       , 50),
				         new Typnutzung("Volvo 80"      , 10),
				         new Typnutzung("Fiat 500"      , 8),
				         new Typnutzung("Opel Astra"    , 20),
				         new Typnutzung("Volvo 80"      , 10),
				         new Typnutzung("Mercedes 200 C", 7));
	}
	
	public Flux<StockPrice> getPricesFor(String symbol) {
		return Flux.interval(Duration.ofSeconds(1))//.take(50).onBackpressureBuffer(50)
	               .map(s -> new StockPrice(symbol, generatePrice(), LocalDateTime.now()))
	               .share();
	}
	

	private Double generatePrice() {
		Random rand = new Random();
		return rand.nextDouble() * 100.0;
	}
	
	@AllArgsConstructor
	public class Typnutzung {
		public String  KfzTyp;
		public Integer anzahlNutzungen;
	}
}
