package de.woock.service;

import static com.lowagie.text.Font.BOLD;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.FontFactory.HELVETICA;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.alignment.HorizontalAlignment.RIGHT;
import static com.lowagie.text.alignment.VerticalAlignment.CENTER;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import de.woock.entity.Buchung;
import de.woock.entity.Rechnung;
import de.woock.entity.Reservierung;

@Service
public class PDFService {
	
	@Value("${stattauto.pdf}")  private String DEST;
	@Value("${stattauto.logo}") private String fileLocation;
	
    Font ftTitle          = getFont(HELVETICA, 24, BOLD  , new Color(80, 157, 56));
    Font ftAnschriftTitel = getFont(HELVETICA, 18, NORMAL, new Color(47,  89, 32));
    Font ftAnschrift      = getFont(HELVETICA, 12, NORMAL, new Color(47,  89, 32));
    Font ftTableHeader    = getFont(HELVETICA, 12, BOLD  , new Color(47,  89, 32));
	
	public void erzeugeRechnung(Rechnung rechnung, Reservierung reservierung) throws IOException {
	
		try {
			Document doc = new Document();
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(DEST + reservierung.getMitglied().getName() + "_" + reservierung.getMitglied().getVorname() + ".pdf"));
			doc.open();
			
			doc.add(createTitle(doc, ftTitle));
			createLogo(doc, fileLocation);
//			createAnschrift(doc, reservierung.getMitglied(), ftAnschriftTitel, ftAnschrift);
//			createRechnungsnummer(doc, rechnung, reservierung, writer);
	        
			createTableBuchungen(doc, rechnung.getBuchungen(), ftTableHeader);
	        doc.add(new LineSeparator());
	        createTableSumme(doc, rechnung.getBuchungen(), ftTableHeader);
	        doc.add(Chunk.NEWLINE);
	        doc.add(new LineSeparator());
	        createBank(doc, writer);
	        createLine(writer);
			
			doc.close();
			writer.close();
		
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Notifications.create()
                     .title("Stammdaten")
                     .text("Rechnungen generiert!")
                     .showInformation();
	}

	private Element createTitle(Document document, Font ftTitle) {
		return new Paragraph(new Chunk("Rechnung", ftTitle));
	}

	private void createLogo(Document document, String fileLocation) throws IOException {
		Image image = Image.getInstance(fileLocation);
        image.scaleAbsolute(150.0f, 150.0f);
        image.setAbsolutePosition(430.0f, 670.0f);
		document.add(image);
	}
	
//	private void createAnschrift(Document document, Mitglied mitglied, Font ftAnschriftTitel, Font ftAnschrift) {
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//		document.add(new Paragraph(new Chunk("Rechnung an:"                                  , ftAnschriftTitel)));
//		document.add(new Paragraph(new Chunk(mitglied.getVorname() + " " + mitglied.getName(), ftAnschrift)));
//		document.add(new Paragraph(new Chunk(mitglied.getAdresse().getStrasse()              , ftAnschrift)));
//		document.add(new Paragraph(new Chunk(mitglied.getAdresse().getOrt()                  , ftAnschrift)));
//		document.add(new Paragraph(new Phrase(mitglied.getEmail()                            , ftAnschrift)));
//	}
//
//	private void createRechnungsnummer(Document document, Rechnung rechnung, Reservierung reservierung, PdfWriter writer) {
//		writeText(document, writer, 390f, 600f, "Kundennummer           : " + rechnung.getMitglied().getMitgliedID().substring(0, 7), 12f);
//		writeText(document, writer, 390f, 580f, "Rechnungsnummer     : " + rechnung.getId(), 12f);
//		writeText(document, writer, 390f, 560f, "Abrechnungszeitraum: " + Month.of(reservierung.getZeitraum().getStartZeit().getMonth()), 12f);
//	}
	
	private void createLine(PdfWriter writer) {
        PdfContentByte canvas = writer.getDirectContent();
        canvas.setColorStroke(Color.BLACK);
        canvas.moveTo(340, 315); canvas.lineTo(470, 315);
        canvas.moveTo(340, 319); canvas.lineTo(470, 319);
        canvas.moveTo(35,  515); canvas.lineTo(560, 515);
        canvas.closePathStroke();
		
	}
	
	private void createTableBuchungen(Document document, List<Buchung> buchungen, Font ftTableHeader) {
		Table table = new Table(5);
        
        table.setPadding(2);
        table.setSpacing(0);
        table.setWidth(100);
        table.setBorder(0);
        
        Cell cellTitleReservierung = new Cell(new Chunk("Reservierung", ftTableHeader) );
        cellTitleReservierung.setBorder(0);
        cellTitleReservierung.setWidth(10f);
        table.addCell(cellTitleReservierung);
        Cell cellTitleAnzahl = new Cell(new Chunk("Anzahl", ftTableHeader) );
        cellTitleAnzahl.setBorder(0);
        cellTitleAnzahl.setWidth(10f);
        table.addCell(cellTitleAnzahl);
        Cell cellTitleBeschreibung = new Cell(new Chunk("Beschreibung", ftTableHeader));
        cellTitleBeschreibung.setBorder(0);
        table.addCell(cellTitleBeschreibung);
        Cell cellTitlePreis = new Cell(new Chunk("Preis", ftTableHeader) );
        cellTitlePreis.setBorder(0);
        table.addCell(cellTitlePreis);
        Cell cellTitleGesamtbetrag = new Cell(new Chunk("Gesamtbetrag", ftTableHeader));
        cellTitleGesamtbetrag.setBorder(0);
        table.addCell(cellTitleGesamtbetrag);
        
        for (Buchung buchung : buchungen) {
//        	if (buchung.getReservierungsId() != 0) {
        		Cell cellreservierung = new Cell(new Paragraph(buchung.getReservierungsId().toString()));
        		cellreservierung.setWidth(10f);
        		cellreservierung.setBorder(0);
        		table.addCell(cellreservierung);
//        	}

        	Cell cellAnzahl = new Cell(new Paragraph(buchung.getAnzahl().toString()));
            cellAnzahl.setWidth(10f);
            cellAnzahl.setBorder(0);
			table.addCell(cellAnzahl);


			Cell cellBeschreibung = new Cell(new Paragraph(buchung.getText()));
			cellBeschreibung.setBorder(0);
			table.addCell(cellBeschreibung);
            
			Cell cellPreis = new Cell(new Paragraph(buchung.getBetrag().toString() + "")); //�
            cellPreis.setBorder(0);
			table.addCell(cellPreis);

			Cell cellGesamtbetrag = new Cell(new Paragraph(buchung.getGesamtbetrag().toString() + "")); //�
			cellGesamtbetrag.setBorder(0);
			table.addCell(cellGesamtbetrag);
        }
		document.add(table);
	}
	
	private void createTableSumme(Document document, List<Buchung> buchungen, Font ftTableHeader) {
		Table table = new Table(4);
	    
	    table.setPadding(2);
	    table.setSpacing(0);
	    table.setWidth(100);
	    table.setBorder(0);
	    // Setting table headers

	    
	    Cell cellZwischensumme = new Cell(new Chunk("Zwischensumme", ftTableHeader) );
	    cellZwischensumme.setVerticalAlignment(CENTER);
	    cellZwischensumme.setHorizontalAlignment(RIGHT);
	    cellZwischensumme.setBorder(0);
	    cellZwischensumme.setWidth(10f);
	    cellZwischensumme.setColspan(3);
	    table.addCell(cellZwischensumme);
	    Cell cellZwischensummeBetrag = new Cell(new Chunk("64.90�", ftTableHeader));
	    cellZwischensummeBetrag.setVerticalAlignment(CENTER);
	    cellZwischensummeBetrag.setBorder(0);
	    table.addCell(cellZwischensummeBetrag);
	    
	    Cell cellMehrwertsteuer = new Cell(new Chunk(" 7% Mehrwertsteuer", ftTableHeader) );
	    cellMehrwertsteuer.setVerticalAlignment(CENTER);
	    cellMehrwertsteuer.setHorizontalAlignment(RIGHT);
	    cellMehrwertsteuer.setBorder(0);
	    cellMehrwertsteuer.setWidth(10f);
	    cellMehrwertsteuer.setColspan(3);
	    table.addCell(cellMehrwertsteuer);
	    Cell cellMehrwertsteuerBetrag = new Cell(new Chunk("  4.54�", ftTableHeader));
	    cellMehrwertsteuerBetrag.setVerticalAlignment(CENTER);
	    cellMehrwertsteuerBetrag.setBorder(0);
	    table.addCell(cellMehrwertsteuerBetrag);
	    
	    Cell cellGesamtsumme = new Cell(new Chunk("Gesamtsumme", ftTableHeader) );
	    cellGesamtsumme.setVerticalAlignment(CENTER);
	    cellGesamtsumme.setHorizontalAlignment(RIGHT);
	    cellGesamtsumme.setBorder(0);
	    cellGesamtsumme.setWidth(10f);
	    cellGesamtsumme.setColspan(3);
	    table.addCell(cellGesamtsumme);
	    Cell cellGesamtsummeBetrag = new Cell(new Chunk("69.44�", ftTableHeader));
	    cellGesamtsummeBetrag.setVerticalAlignment(CENTER);
	    cellGesamtsummeBetrag.setBorder(0);
	    table.addCell(cellGesamtsummeBetrag);
	    
		document.add(table);
	}

	private void createBank(Document document, PdfWriter writer) {
		writeText(document, writer, 20f, 35f, "StattAuto", 8f);
		writeText(document, writer, 20f, 25f, "Am Stadtteich 14", 8f);
		writeText(document, writer, 20f, 15f, "23440 Hamburg", 8f);

		writeText(document, writer, 260f, 35f, "www.StattAuto.de", 8f);
		writeText(document, writer, 260f, 25f, "info@StattAuto.de", 8f);
		writeText(document, writer, 260f, 15f, "0183 77483210", 8f);
		
		writeText(document, writer, 450f, 35f, "IBAN: DE87206905000017748330", 8f);
		writeText(document, writer, 450f, 25f, "BIC: GENODEF1S11", 8f);
		writeText(document, writer, 450f, 15f, "USt-ID: DE283778", 8f);
		
	}
	
	private void writeText(Document document, PdfWriter writer, float x, float y, String text, float size) {
		
		PdfContentByte cb = writer.getDirectContent();
		BaseFont bf;
		try {
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			cb.saveState();
			cb.beginText();
			cb.moveText(x, y);
			cb.setFontAndSize(bf, size);
			cb.showText(text);
			cb.endText();
			cb.restoreState();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
}
