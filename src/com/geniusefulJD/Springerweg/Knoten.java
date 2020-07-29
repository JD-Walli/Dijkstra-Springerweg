package com.geniusefulJD.Springerweg;

public class Knoten {
    private int gewicht, vorgaenger; //gewicht entspricht Strecke

    public Knoten(int vorgaenger){
        gewicht=Integer.MAX_VALUE;
        this.vorgaenger=vorgaenger;
    }


    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getVorgaenger() {
        return vorgaenger;
    }

    public void setVorgaenger(int vorgaenger) {
        this.vorgaenger = vorgaenger;
    }
}
