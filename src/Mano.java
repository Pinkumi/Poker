import java.util.*;
import java.util.stream.Collectors;

public class Mano {

    private ArrayList<Carta> cartas;

    public Mano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        ordenarMano();
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

    public void ordenarMano(){
        cartas.sort(Comparator.comparing(v -> v.getValor()));
    }

    //region Metodos basicos de comparacion entre cartas

    public int cantidadDeCartasConsecutivas(){
        int contador = 0;
        for(int i=0; i<cartas.size()-1; i++){
            if(cartas.get(i).getValor() + 1 == cartas.get(i+1).getValor()){
                contador++;
            }
        }
        return contador;
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

    //region Metodos del ranking de manos

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


}
