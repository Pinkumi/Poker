import javax.swing.*;

public class Carta  extends JLabel {
    //clase carta
    private int valor;
    private String color;
    private String palo;

    public Carta(int valor, String palo, String color) {
        this.valor = valor;
        this.palo = palo;
        this.color = color;
    }

    public String toString() {
        String valorCarta;
        switch(valor){
            case 1: valorCarta = "A"; break;
            case 11: valorCarta = "J"; break;
            case 12: valorCarta = "Q"; break;
            case 13: valorCarta = "K"; break;
            default: valorCarta = String.valueOf(valor); break;
        }
        return palo + valorCarta;
    }

    public int getValor(){
        return valor;
    }

    public String getpalo(){
        return palo;
    }

    public boolean esIgualA(Carta otraCarta){
        if(valor == otraCarta.valor){
            if(palo.equals(otraCarta.palo)){
                return true;
            }
        }

        return false;
    }

    public boolean tieneElMismoValor(Carta otraCarta){
        if(valor == otraCarta.valor){
            return true;
        }

        return false;
    }

    public boolean tieneLaMismapalo(Carta otraCarta){
        if(palo.equals(otraCarta.palo)){
            return true;
        }
        return false;
    }


}
