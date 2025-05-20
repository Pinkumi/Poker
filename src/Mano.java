import java.util.*;
import java.util.stream.Collectors;

public class Mano {
    // Atributos
    private ArrayList<Carta> cartas;

    // Constructor, recibe un arreglo de cartas
    public Mano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    //region Getters

    /*
        Devuelve el arreglo de cartas (Mano) del jugador
    */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    //endregion
    
    //region Operaciones para manipular la mano del jugador

    /*
        Recibe un entero como la posición de la carta
        que se quiere remover de la mano del jugador
    */
    public Carta removerCartaPos(int pos){
        return cartas.remove(pos);
    }
    public void removerCarta(Carta cartaARemover){
        cartas.remove(cartaARemover);
    }

    /*
        Recibe una carta y la agrega la carta a la mano
        del jugador
    */
    public void agregarCarta( Carta carta){
        cartas.add(carta);
    }

    public void ocultarCartas(){
        for (Carta carta : cartas) {
            carta.setVisible(false);
        }
    }
    public void mostrarCartas(){
        for (Carta carta : cartas) {
            if (!carta.isVisible()) {
                carta.setVisible(true);
            }
        }
    }

    /*
        Ordena la mano del jugador por el valor de la carta
    */
    public void ordenarMano(){
        cartas.sort(Comparator.comparing(v -> v.getValor()));
    }
    //endregion

    //region Métodos para consultar la mano del jugador

    /*
        Devuelve la cantidad de veces que se repite
        una carta
    */
    public int cantidadDeCartasConsecutivas(){
        int contador = 0;
        for(int i=0; i<cartas.size()-1; i++){
            if(cartas.get(i).getValor() + 1 == cartas.get(i+1).getValor()){
                contador++;
            }
        }
        return contador;
    }
    public void deshabilitarBotones(){
        for(Carta carta: cartas){
            carta.getCardBtn().setEnabled(false);
        }
    }
    public void habilitarBotones(){
        for(Carta carta: cartas){
            carta.getCardBtn().setEnabled(true);
        }
    }

    public boolean mismoPalo(){
        String paloCarta = cartas.get(0).getFigura();

        for(int i = 0; i < cartas.size(); i++){
            if(!cartas.get(i).getFigura().equals(paloCarta)){
                return false;
            }
        }
        return true;
    }

    public boolean hayCartasRepetidas(){
        HashMap<Integer, Integer> mano = new HashMap<>();

        for(Carta carta: cartas){
            int valor = carta.getValor();
            mano.put(valor, mano.getOrDefault(valor, 0) + 1);
        }

        for(int cantidad: mano.values()){
            if(cantidad > 1){
                return true;
            }
        }

        return false;
    }

    public HashMap<Integer, Integer> cantidadDeCartasDeIgualValor(){
        HashMap<Integer, Integer> mano = new HashMap<>();

        for(Carta carta: cartas){
            int valor = carta.getValor();
            mano.put(valor, mano.getOrDefault(valor, 0) + 1);
        }

        return mano;
    }
    //endregion

    //region Métodos del ranking de manos

    public Boolean RoyalFlush(){
        if(!mismoPalo()){
            return false;
        }
        Set<Integer> valoresRoyalFlush = new HashSet<>(Arrays.asList(10, 11, 12, 13, 1));
        Set<Integer> valoresMano = cartas.stream()
                .map(Carta::getValor)
                .collect(Collectors.toSet());
        return valoresMano.containsAll(valoresRoyalFlush);
    }

    public boolean StraightColor(){
        return mismoPalo() && cantidadDeCartasConsecutivas() == cartas.size()-1;
    }

    public boolean FourOfAKind(){
        return cantidadDeCartasDeIgualValor().containsValue(4);
    }

    public boolean FullHouse(){
        return cantidadDeCartasDeIgualValor().containsValue(3) && cantidadDeCartasDeIgualValor().containsValue(2);
    }

    public boolean Flush(){
        return mismoPalo();
    }

    public boolean Straight(){
        return cantidadDeCartasConsecutivas() == cartas.size()-1;
    }

    public boolean ThreeOfAKind(){
        return cantidadDeCartasDeIgualValor().containsValue(3);
    }

    public boolean TwoPairs(){
        int cantidadPares = (int) cantidadDeCartasDeIgualValor()
                .values()
                .stream()
                .filter(v -> v == 2)
                .count();

        return cantidadPares == 2;
    }

    public boolean OnePair(){
        return cantidadDeCartasDeIgualValor().containsValue(2);
    }

    public int HighCard(){
        int valorMasAlto = cartas.get(cartas.size()-1).getValor();

        for(Carta carta: cartas){
            if(carta.getValor() == 1){
                return 14;
            }
        }

        return valorMasAlto;
    }
    //endregion

    public int obtenerRanking() {
        ordenarMano();

        if (RoyalFlush()) return 10;
        if (StraightColor()) return 9;
        if (FourOfAKind()) return 8;
        if (FullHouse()) return 7;
        if (Flush()) return 6;
        if (Straight()) return 5;
        if (ThreeOfAKind()) return 4;
        if (TwoPairs()) return 3;
        if (OnePair()) return 2;

        return 1;
    }

    // Convierte la mano a texto para mostrarla
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : cartas) {
            sb.append(carta.toString()).append("\n");
        }
        return sb.toString();
    }


}
