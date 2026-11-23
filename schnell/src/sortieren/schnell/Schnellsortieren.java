package sortieren.schnell;

import java.util.Random;
import liste.Zweiliste;

public class Schnellsortieren {

    // Hat die Liste nur einen Wert?
    public static boolean nureinwert(int[] liste) {
	int anfang = liste[0];

	for (int zahl : liste) {
	    if (zahl != anfang) {
		return false;
	    }
	}

	return true;
    }

    // Nehme, wie viele Zahlen sind kleiner.
    public static int kleiner(int[] liste, int zahl) {
	int k = 0;

	for (int i = 0; i < liste.length; i++) {
	    if (liste[i] < zahl) {
		k += 1;
	    }
	}

	return k;
    }

    // Gieße die unsortierte Liste in einer linken Seite und in einer
    // rechten Seite abhängig vom Drehpunkt.
    public static Zweiliste giesse(int[] liste, int drehpunkt) {
	int links = Schnellsortieren.kleiner(liste, drehpunkt);
	int rechts = liste.length - links;

	int[] linksliste = new int[links];
	int[] rechtsliste = new int[rechts];

	int linksindex = 0;
	int rechtsindex = 0;

	for (int i = 0; i < liste.length; i++) {

	    if (liste[i] < drehpunkt) {
		// Ist die Zahl kleiner als Drehpunkt, gieße links.
		linksliste[linksindex] = liste[i];
		linksindex += 1;
		
	    } else {
		// Gieße rechts.
		rechtsliste[rechtsindex] = liste[i];
		rechtsindex += 1;
		
	    }
	}

	return new Zweiliste(linksliste, rechtsliste);
    }

    public static int[] sortiere(int[] unsortiert) {

	if (unsortiert.length <= 1) {
	    // Schon sortiert.
	    
	    return unsortiert;
	}

	if (unsortiert.length == 2) {
	    // Sortiere per Hand.

	    if (unsortiert[0] > unsortiert[1]) {
		// Tausche.
		return new int[] {unsortiert[1], unsortiert[0]};
	    }

	    // Tausche nicht.
	    return unsortiert;
	}

	// Hat die Liste nur einen Wert?
	if (Schnellsortieren.nureinwert(unsortiert)) {

	    // Nichts zu sortieren.
	    return unsortiert;
	}

	// Dann die Liste hat Größe mindestens 3 und mindestens zwei
	// verschiedene Zahlen. Sortiere sie.

	Random r = new Random();
	// nextInt: [0,n)
	int drehpunktindex = r.nextInt(unsortiert.length);
	int drehpunkt = unsortiert[drehpunktindex];

	Zweiliste linksrechts = giesse(unsortiert, drehpunkt);

	// Sortiere die zwei gegossene Liste.

	int[] linkssortiert = Schnellsortieren.sortiere(linksrechts.eins);
	int[] rechtssortiert = Schnellsortieren.sortiere(linksrechts.zwei);

	// Füge die Listen.

	int[] sortiert = new int[unsortiert.length];

	for (int i = 0; i < linkssortiert.length; i++) {
	    sortiert[i] = linkssortiert[i];
	}

	for (int i = 0; i < rechtssortiert.length; i++) {
	    sortiert[i + linkssortiert.length] = rechtssortiert[i];
	}

	return sortiert;
    }
}
