import java.util.ArrayList;

public class Mano {

    private ArrayList<Carta> cartas;

    public Mano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    public ArrayList<Carta> getMano() {
        return cartas;
    }
    public Carta removerCartaPos(int pos){
        return cartas.remove(pos);
    }
    public void agregarCarta( Carta carta){
        cartas.add(carta);
    }

    //Aqui van las funciones para detectar las jugadas por mano, ej; hayPar(), hayEscalera(), hayEscaleraReal()





}
