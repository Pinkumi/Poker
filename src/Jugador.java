import java.util.ArrayList;

public class Jugador {
    // clase de jugador
    private int numFichas;
    public Mano mano;
    private int nJugador;

    Jugador(int nJugador, Mano mano) {
        this.nJugador = nJugador;
        this.mano = mano;
        numFichas = 0;

    }
    public void agregarFichas(int numFichas)
    {
        this.numFichas += numFichas;
    }
    public void colocarFichas(int numFichas) {this.numFichas -= numFichas;}
    public int getFichas()
    {
        return numFichas;
    }
    public void tomarCarta(Carta carta){
        mano.agregarCarta(carta);
    }
    public Mano getMano(){
        return mano;
    }
    public String toString()
    {
        return  "Jugador No."+nJugador +" - "+ numFichas;
    }

}