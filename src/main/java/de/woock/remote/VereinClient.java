package de.woock.remote;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import de.woock.entity.Mitglied;

public interface VereinClient {
	
	@PostExchange("/mitglied")
	Mitglied mitgliedErstellen(@RequestBody Mitglied mitglied);
	
	@GetExchange("/mitglieder") 
	Collection<Mitglied> mitglieder();
}
