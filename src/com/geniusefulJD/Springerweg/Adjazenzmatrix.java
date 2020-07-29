package com.geniusefulJD.Springerweg;

public class Adjazenzmatrix {
    int[][]matrix;
    /** Größe des Schachfeldes */
    private final int feldGroesse;
    /**Größe der Matrix*/
    private final int matrixGroesse;


    //Konstruktor
    public Adjazenzmatrix(int feldGroesse) {
        this.feldGroesse = feldGroesse;
        matrixGroesse = feldGroesse*feldGroesse;
        matrix = new int[matrixGroesse][matrixGroesse];
    }


    /**befüllt die ganze Matrix mit 0*/
    public void befuellen0 (){
        for (int i=0;i<matrixGroesse;i++){
            for (int j=0;j<matrixGroesse;j++){
                matrix[i][j]=0;
            }
        }
    }


    /** iteriert über das imaginäre "Schachfeld" (arbeitet alle Felder ab) und trägt dann immer an der Stelle
    [bin ich grade] [einen Springerzug entfernt von bin ich grade] eine 1 ein
    (weil alle Kästechen im Springerabstand miteinander verbunden sind)*/

    /*das try und catch sorgt dafür, dass die Fehlermeldungen ignoriert werden, die entstehen, wenn versucht wird
     ei einer Position außerhalb des Feldes eine 1 einzutragen (kommt vor, weil am linken Rand des Feldes trotzdem
     versucht wird, einen Springerzug nach links zu machen) */
    public void befuellenSpringermuster(){
        for(int y=0;y<feldGroesse;y++){
            for (int x=0;x<feldGroesse;x++){
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x + 1, y + 2)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x + 1, y - 2)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x - 1, y + 2)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x - 1, y - 2)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x + 2, y + 1)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x + 2, y - 1)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x - 2, y + 1)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    matrix[parse2dTo1d(x, y)][parse2dTo1d(x - 2, y - 1)] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
    }


    /** zeigt die Matrix im Ausgabefenster an */
    public void show(){
        for (int i=0;i<matrixGroesse;i++){
            System.out.println();
            for (int j=0;j<matrixGroesse;j++){
                System.out.print(matrix[j][i]+" ");
            }
        }
    }


    /** aus der Positionierung im Schachfeld wird die Nummer in der Matrix errechnet. Das Schachfeld ist dann von
     oben links nach unten rechts durchgehend numerriert (beginnend bei 0) */
    private int parse2dTo1d (int x, int y){
        return x>=0 && x<feldGroesse && y>=0 && y<feldGroesse ? (x+y*feldGroesse) : -1; //es muss überprüft werden, ob die Koordinaten überhaubt innerhalb des Schachfelds liegen, da sonst Fehler in der Matrix auftreten (Rechenbeispiel mit x=0-2 und y=0+1 oder x=2+1 y=1-1
    }


    public int getFeldGroesse() {
        return feldGroesse;
    }

    public int getMatrixGroesse() {
        return matrixGroesse;
    }
}
