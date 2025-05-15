import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    private ArrayList<Carta> cartas;

    public Baraja(){
        cartas = new ArrayList<>();
        llenarCartas();

    }
    private void llenarCartas(){
        String [] figuras = { "corazon", "trebol", "diamante", "pica"};
        for (String figura : figuras) {
            for (int j = 1; j < 14; j++) {
                Carta carta = new Carta(j, figura);
                cartas.add(carta);
            }
        }
    }
    public void mezclarCartas(){
        Collections.shuffle(cartas);
    }
    public ArrayList<Carta> extraerCartas(int numCartas){
        ArrayList<Carta> cartasExtraidas = new ArrayList<>();
        for (int i=0; i<5; i++) {
            Carta carta = cartas.removeFirst();
            cartasExtraidas.add(carta);
        }
        return cartasExtraidas;
    }
}
