import java.util.ArrayList;

public class Jugador {
    // clase de jugador
    private int numFichas;
    public ArrayList<Carta> mano;
    private int nJugador;
    Jugador(int nJugador) {
        this.nJugador = nJugador;
        numFichas = 0;
    }
    public void agregarFichas(int numFichas)
    {
        this.numFichas += numFichas;
    }
    public int getFichas()
    {
        return numFichas;
    }
    public ArrayList<Carta> getMano(){
        return mano;
    }
    public String toString()
    {
        return  "Jugador No."+nJugador +" - "+ numFichas;
    }

}
