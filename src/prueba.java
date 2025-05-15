import javax.swing.*;
import java.util.ArrayList;

public class prueba {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1500, 1500);
        Baraja baraja = new Baraja();
        int x = 0;
        int y = 0;
        baraja.changeSizeCards(300);
        baraja.mezclarCartas();
        for(int i = 0; i<5; i++){
            Carta carta = baraja.getCartas().get(i);
            carta.changePosition(x, y);
            frame.add(carta);
            x += carta.getWidth();
        }
        frame.setVisible(true);


    }
}
