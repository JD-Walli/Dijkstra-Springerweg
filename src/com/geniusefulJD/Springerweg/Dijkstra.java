package com.geniusefulJD.Springerweg;

import java.util.ArrayList;
import java.util.Collections;

//Allgemein: Punkt beschreibt immer den index (=nummer) eines Knotens, während der Knoten selber aus gewicht und Vorgänger besteht
// mit "Gewicht" meine ich immer die Strecke

public class Dijkstra {

    //Hauptprogramm
    public static void main(String[] args) {
        int feldGroesse = 3;

        //erstelle und befülle Adiazenzmatrix
        Adjazenzmatrix aMatrix = new Adjazenzmatrix(feldGroesse);
        aMatrix.befuellen0();
        aMatrix.befuellenSpringermuster();
        aMatrix.show();

        // VARIANTE 1: (automatische Lösung des Problems, die auf die Entsprechenden Ecken draufzukommen)
        //Array mit zu erreichenden Punkten (Punkten, die auf dem Weg nacheinander berührt werden sollen): links unten, rechts unten, rechts oben
        int[] punkteFolge = {parse2dTo1d(0,feldGroesse-1,feldGroesse), parse2dTo1d(feldGroesse-1,feldGroesse-1,feldGroesse),parse2dTo1d(feldGroesse-1,0,feldGroesse)};

        ArrayList <Integer> abschnitt1 = dijkstra(aMatrix, punkteFolge[0], punkteFolge[1]); //abschnitt von links unten nach rechts unten
        ArrayList <Integer> abschnitt2 = dijkstra(aMatrix, punkteFolge[1],punkteFolge[2]); //abschnitt von rechts unten nach rechts oben
        abschnitt1.addAll(abschnitt2); //an abschnitt1 wird abschnitt2 hintendran gehängt

        /* gibt die vorher errechneten abschnitte1 (mit 2 drangehängt) im Ausgabefenster aus.
         Beachte: das Mittelstück kommt zweimal vor (lässt sich easy lösen: in einer Zeile vor dem adAll schreiben abschnitt2.remove(0); ) */
        System.out.println("\n");
        for (int i:abschnitt1) {
            System.out.print(i+" ");
        }
        //um Variante 1 abzuschalten, bis hierher ausklammern

        /*
        // VARIANTE 2: (manuelle Eingabe von Start- und Endfeld. das Schachfeld ist von oben links nach unten rechts numeriert.)
        ArrayList <Integer> manuelleReihe = dijkstra(aMatrix, 1, 3); //abschnitt von links unten nach rechts unten
        System.out.println("\n");
        for (int i:manuelleReihe) {
            System.out.print(i+" ");
        }
         */
    }


    public static ArrayList <Integer> dijkstra(Adjazenzmatrix aMatrix, int startpunkt, int endpunkt){

        ArrayList <Integer> ausgangspunkte=new ArrayList<>();  /* Liste der Indizes der Abzuarbeitenden Punkte */
        ausgangspunkte.add(startpunkt);  //erster Abzuarbeitender Punkt: der Startpunkt
        Knoten[] knoten = new Knoten[aMatrix.getMatrixGroesse()]; /* Array aller Knoten*/

        // die Knoten aus dem Array werden initialisiert. Vorgänger erstmal auf -1 gesetzt (ändert sich auf jeden Fall im Prg)
        for (int i = 0; i < knoten.length; i++) {
            knoten[i]=new Knoten(-1); //minus eins wichtig für Bedingung am Ende von dijkstra()
        }
        knoten[startpunkt].setGewicht(0);  //gewicht des Startspunkt auf 0 setzen, da man ja schon "auf ihm steht"


        /*eigentlicher Dijkstra: wir suchen uns den nächsten Punkt und betrachten dessen Nachfolger.
        Wenn das Gewicht des aktuellen Punkts+Gewicht des Nachfolgers kleiner ist, als das aktuelle
        Gewicht des Nachfolgeknotens, übernimmt der Nachfolgeknoten die Eigenschaften von gewicht und vorgänger*/
        while(ausgangspunkte.size()!=0){
            int aktuellerPunkt=-1; /* Index des Knotens, der grade betrachtet wird*/

            //nächsten zu Verarbeitenden Punkt suchen: immer der mit dem kleinsten gewicht
            int klGewicht = Integer.MAX_VALUE;
            for (int i:ausgangspunkte) {
                if(knoten[i].getGewicht()<klGewicht){
                    aktuellerPunkt=i;
                    klGewicht=knoten[i].getGewicht();
                }
            }

            for (int folgePunkt = 0; folgePunkt < aMatrix.getMatrixGroesse(); folgePunkt++) { //durchläuft die "y Richtung" der Matrix an der x-Position des "aktuellen Punktes"
                if(aMatrix.matrix[aktuellerPunkt][folgePunkt]>0){ //prüfe ob schnittposition auch eine Verbindung beinhaltet
                    if(aMatrix.matrix[aktuellerPunkt][folgePunkt] + knoten[aktuellerPunkt].getGewicht()  <  knoten[folgePunkt].getGewicht()) { //Prüfe ob das neue Gewicht besser als das bisherige ist
                        knoten[folgePunkt].setGewicht(aMatrix.matrix[aktuellerPunkt][folgePunkt] + knoten[aktuellerPunkt].getGewicht()); //wenn bedingung erfüllt, setze gewicht des Knotens auf neuen Wert
                        knoten[folgePunkt].setVorgaenger(aktuellerPunkt); //setze vorgänger des Knotens auf neuen Wert
                        ausgangspunkte.add(folgePunkt); //setzt den nachfolgepunkt auf die Abzuarbeiten-Liste
                    }
                }
            }
            //sucht und entfernt den aktuell betrachteten Punkt aus der Abzuarbeiten-Liste
            for (int i = 0; i < ausgangspunkte.size(); i++) {
                if(ausgangspunkte.get(i)==aktuellerPunkt) {
                    ausgangspunkte.remove(i);
                    break;
                }
            }
        }


        /*geht vom Endpunkt aus Rückwärts den Pfad entlang (immer zum nächsten Vorgänger), bis der knoten keinen mehr hat (-> Startknoten)
        Weil die Abfolge dann Rückwärts ist, wird die Liste invertiert und das Ergebnis danach erst zurückgegeben*/
        ArrayList <Integer> ergebnis =new ArrayList<>();
        int i=endpunkt;
        do{
            ergebnis.add(i);
            i=knoten[i].getVorgaenger();
        } while (i!=-1);
        Collections.reverse(ergebnis);
        return ergebnis;
    }

    /** aus der Positionierung im Schachfeld wird die Nummer in der Matrix errechnet. Das Schachfeld ist dann von
     oben links nach unten rechts durchgehend numerriert (beginnend bei 0) */
    public static int parse2dTo1d (int x, int y, int feldGroesse){
        return x>=0 && x<feldGroesse && y>=0 && y<feldGroesse ? (x+y*feldGroesse) : -1; //es muss überprüft werden, ob die Koordinaten überhaubt innerhalb des Schachfelds liegen, da sonst Fehler in der Matrix auftreten (Rechenbeispiel mit x=0-2 und y=0+1 oder x=2+1 y=1-1
    }
}
