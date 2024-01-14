package de.woock.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.woock.entity.Geldbetrag;
import de.woock.entity.KfzKlasse;
import lombok.Data;

@ConfigurationProperties(prefix = "stattauto")
@Component
@Data
public class TarifeConfig {
	
	private Map<KfzKlasse, Map<String, Geldbetrag>> tarife = new HashMap<>();
}