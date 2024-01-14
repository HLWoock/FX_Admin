package de.woock.stammdaten.mitglieder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StreetReader {
	
	public Map<String, Map<String, String>> streets = new HashMap<>();
	
	public String       directory = "C:\\Users\\Administrator\\Documents\\workspaces\\workspace-spring-tool-suite-4-4.19.0.RELEASE\\FX_Admin\\src\\main\\resources\\static\\streetnames";
	public List<String> list      = Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")); 
	public String       url       = "http://onlinestreet.de/strassen/in-%s/%s.html";
	
	
	public StreetReader() {
		List<String> list = Arrays.asList("Hamburg Reinbek Lüneburg Pinneberg Buchholz Lübeck Kiel Stade Bremen".split(" ")); 
		list.forEach(stadt -> parseStrassenverzeichnisFuer(stadt));
	}

	public void schreibeStrassenverzeichnisFuer(final String stadt) {
		
		list.forEach(l -> {
			try {
				String fileName = String.format(directory +"/%s_%s.txt", stadt, l);
				File file = new File(fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				String path = String.format(url, stadt, l);
				URL url = new URL(path);
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null && inputLine.indexOf("<table class=\"strassen\">") == -1);
				inputLine = in.readLine();
				
				bw.write("<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>");
				bw.write("<streets>");
				bw.write("<table>");
				bw.write(inputLine);
				bw.write("</streets>");
				bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}});		
	}
	
	public void parseStrassenverzeichnisFuer(String stadt) {
		Map<String, String> localStreets = new HashMap<>();
		list.forEach(l -> {
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				String fileName = String.format(directory + "/%s_%s.txt", stadt, l);
				
				File file = new File(fileName);
				Document doc = dBuilder.parse(file);
				NodeList preList = doc.getElementsByTagName("tr");
				for (int i = 1; i < preList.getLength(); i++) {
					String street = preList.item(i).getFirstChild().getFirstChild().getFirstChild().getTextContent();
					String city   = preList.item(i).getFirstChild().getFirstChild().getFirstChild().getNextSibling().getTextContent();
					localStreets.put(street, city);
				}
			} catch (ParserConfigurationException | SAXException | IOException e) {
			}
			streets.put(stadt, localStreets);
		});
	}
}
