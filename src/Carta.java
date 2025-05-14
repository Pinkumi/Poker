import javax.swing.*;
import java.awt.*;

public class Carta  extends JLabel {
    //clase carta
    private int valor;
    private String figura;

    public Carta(int valor, String figura ) {
        this.valor = valor;
        this.figura = figura;
        setOpaque(true);
        setBackground(Color.WHITE);
        setBounds(0,0,100,300);


    }



    public int getValor(){
        return valor;
    }

    public String getpalo(){
        return figura;
    }

    public boolean esIgualA(Carta otraCarta){
        if(valor == otraCarta.valor){
            if(figura.equals(otraCarta.figura)){
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
        if(figura.equals(otraCarta.figura)){
            return true;
        }
        return false;
    }


}
