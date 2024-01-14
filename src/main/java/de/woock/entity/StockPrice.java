package de.woock.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StockPrice {

	private String        symbol;
	private Double        price;
	private LocalDateTime time;
}
