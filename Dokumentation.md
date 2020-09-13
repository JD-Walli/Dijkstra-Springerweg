# Dijkstra-Springerweg-Michael
Das Programm findet den kürzesten Weg zwischen zwei Punkten auf einem nxn Schachfeld, den ein Springer gehen kann, mit Hilfe des Dijkstra Algorithmus. Eingabewerte sind Schachfeldgröße, Start-, und Endpunkt.


<h3><b>Übersicht Programmablauf:</b></h3>

1. Adjazenzmatrix erstellen
    - über alle Felder iterieren (in 2d matrix)
    - jedes Feld mit dem erreichbaren verknüpfen (an entsprechender Stelle auf der Matrix 1 eintragen)
      Wichtig: von 2d auf 1d indexierung rechnen
2. startpunkt zu ausgangspunkte hinzufügen
    - Aus ausgangspunkte den Punkt mit kleinstem Wert suchen -> "aktueller Punkt"
    - Punkte durcharbeiten, die in Adjazenzmatrix mit "1" mit dem aktuellen Punkt verbunden sind
    - Vergleiche jeweils, ob bisheriger Knoten.wert im Knotenarray größer als der aktuelle+dem des aktuellen Nachfolgerknotens aus Matrix ist. wenn größer, setze Knotenwert auf    aktuellerKnoten.wert+Matrixwert und setze den Vorgänger neu. wenn kleiner, lass den alten wert.
    - NachfolgerPunkt in ausgangspunkte hinzufügen. aktuellen Punkt aus ausgangspunkte entfernen. Schritte wiederholen
    - Abbruch wenn ausgangspunkte leer
3. Endpunkt betrachten und jeweils den vorgänger merken -> daraus ergibt sich kürzester Weg


<h3><b>Weiterführende Erklärungen:</h3></b>

 - Ausgangspunkte ist eine arraylist, in der die Indizes der noch zu behandelnden Punkte gespeichert sind
 - Knoten hat die Eigenschaften: Vorgängerindex; wert (=Gewicht/Strecke)
 - Nummerierung immer von 0 beginnend
 - 2 Nummerierungssysteme: Koordinatensystem beim Schachfeld; Durchnumeriertes Schachfeld (Achsen bei Adjazenzmatrix)
 - "Punkt" = Knotenname = Nummer auf Schachbrett = index in Adjazenzmatrix


<h1>Docs</h1>

<h3><b>Code zum Berechnen einer Lösung:</b></h3>

1. adjazenzmatrix erstellen:  
<code>Adjazenzmatrix<Adjazenzmatrix> aMatrix = new Adjazenzmatrix(feldGroesse);</code>
<code>aMatrix.befuellen0();</code>
<code>aMatrix.befuellenSpringermuster();</code>
2. Dijkstra berechnen (in der Liste stehen dann in der Reihenfolge die besuchten Felder drinnen):
<code>ArrayList <Integer> Ergebnis = dijkstra(aMatrix, Anfangsfeld, Endfeld);</code>
