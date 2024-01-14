package de.woock.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.woock.entity.Beleg;
import de.woock.entity.Fahrbericht;
import de.woock.entity.Geldbetrag;
import de.woock.entity.KfzKlasse;
import de.woock.entity.Reservierung;
import de.woock.entity.Unfallmeldung;
import de.woock.service.communication.ReservierungAccess;
import de.woock.service.communication.UnfallmeldungAccess;
import de.woock.ui.model.AbrechnungUI;
import de.woock.util.Zeitpauschale;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AbrechnungsService {
	
	private final ReservierungAccess reservierungsService; 
	private final UnfallmeldungAccess unfallmeldungService;
	private final TarifeService        tarifeService;
	private final PDFService           pdfService;
	
	public void monatsabrechnung(int monat) {
//		List<Mitglied> mitglieder = mitgliederRepository.findAll();
//
//		mitglieder.forEach(mitglied -> {
//			Rechnung rechnung = new Rechnung(mitglied);
//		
//	        rechnung.add(new Buchung(1, new Geldbetrag(10), "Monatsbeitrag", 0L));
//	        
//	        List<Reservierung> reservierungen = reservierungsService.reservierungenVonMitglied(mitglied.getMitgliedID());
//	        
//	        reservierungen.forEach(reservierung -> {
//	        	Fahrbericht fahrbericht = reservierung.getFahrbericht();
//	        	Integer       km          = fahrbericht.getKm().getStrecke();
//	        	KfzKlasse     kfzklasse   = reservierung.getKfz().getKlasse();
//	        	List<Beleg>   belege      = reservierung.getBelege();
//	        	Zeitpauschale zeitpausch  = bestpreis(fahrbericht, kfzklasse);
//	        	int           zeitraum    = (int) zeitpausch.zeit;
//	        	Geldbetrag    pauschale   = zeitpausch.pauschale;
//	        	String        tarif       = zeitpausch.tarif;
//	        	
//	        	rechnung.add(new Buchung(km       ,tarifeService.getKilometerTarif(kfzklasse), "km Tarif"  , reservierung.getId())); 
//	        	rechnung.add(new Buchung(zeitraum, pauschale, tarif, reservierung.getId()));
//	        	belege.forEach(beleg -> rechnung.add(new Buchung(1, new Geldbetrag(-beleg.getBetrag().getBetrag().intValue()), 
//	        			                                                            beleg.getBeschreibung(), 
//	        			                                                            reservierung.getId())));
//	        	try {pdfService.erzeugeRechnung(rechnung, reservierung);} 
//	        	catch (IOException e) {e.printStackTrace();} 
//	        });
//	        rechnungRepository.save(rechnung);
//		});
	}

	public List<Beleg> buchen(AbrechnungUI tempAbrechnungUI, String auftragsId) {
		Unfallmeldung unfallmeldung = unfallmeldungService.unfallmeldungMitId(auftragsId);
		Reservierung  reservierung  = unfallmeldung.getReservierung();
		List<Beleg>   belege        = reservierung.getBelege();
		belege.add(new Beleg(UUID.randomUUID().toString().substring(0, 7), 
				            new Geldbetrag(tempAbrechnungUI.getBetrag()), 
				            tempAbrechnungUI.getBeschreibung()));
		reservierungsService.update(reservierung);
		return belege;
		
	}

	private Zeitpauschale bestpreis(Fahrbericht fahrbericht, KfzKlasse k) {
		
		long wochen  = (long)fahrbericht.getZeitraum().getWochen();
		long tage    = (long)fahrbericht.getZeitraum().getTage();
		long stunden = (long)fahrbericht.getZeitraum().getStunden();
		
		double stundenPreis = tarifeService.getStundenTarif(k).multiply(stunden).getBetrag().doubleValue();
		double tagesPreis   = tarifeService.getTagesTarif(k)  .multiply(tage)   .getBetrag().doubleValue();
		
		double tagestarif   = tarifeService.getTagesTarif(k)  .multiply(1)      .getBetrag().doubleValue();
		double wochentarif  = tarifeService.getWochenTarif(k) .multiply(1)      .getBetrag().doubleValue();
		
		if(wochen == 0 && tage == 0)
			return stundenPreis <  tagestarif ? new Zeitpauschale(stunden, tarifeService.getStundenTarif(k), "Stundentarif")
					                          : new Zeitpauschale(1      , tarifeService.getTagesTarif(k)  , "Tagestarif");
		else if (wochen == 0) 
			return tagesPreis  < wochentarif ? new Zeitpauschale(tage    , tarifeService.getTagesTarif(k)  , "Tagestarif") 
					                         : new Zeitpauschale(1       , tarifeService.getWochenTarif(k) , "Wochentarif");
		else	
			return new Zeitpauschale(tage    , tarifeService.getTagesTarif(k), "Tagestarif");
	}

}
