import java.util.ArrayList;

public class Jugador {

    // Atributos
    private int numFichas;
    public Mano mano;
    private int nJugador;

    // Constructor
    Jugador(int nJugador, Mano mano) {
        this.nJugador = nJugador;
        this.mano = mano;
        numFichas = 0;

    }

    //region Getters
    /*
        Devuelve la cantidad de fichas actuales
     */
    public int getFichas() {
        return numFichas;
    }

    /*
        Devuelve la mano actual del jugador
     */
    public Mano getMano(){
        return mano;
    }

    /*
        Devuelve el número identificador del jugador
     */
    public int getNJugador(){
        return nJugador;
    }
    //endregion

    /*
        Recibe una cantidad entera de fichas y
        las suma al número de fichas actuales
     */
    public void agregarFichas(int numFichas) {
        this.numFichas += numFichas;
    }

    /*
        Recibe una cantidad entera de fichas y
        las resta al número de fichas actuales
     */
    public void colocarFichas(int numFichas) {this.numFichas -= numFichas;}

    /*
        Recibe una carta y la agrega a la mano
        del jugador
     */
    public void tomarCarta(Carta carta){
        mano.agregarCarta(carta);
    }

    /*
        representación del jugador en texto
     */
    public String toString()
    {
        return  "Jugador No."+nJugador +" - "+ numFichas;
    }

}