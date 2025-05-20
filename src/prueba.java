import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class prueba {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1366, 1700);
        Baraja baraja = new Baraja();
        baraja.changeSizeCards(170);
        //baraja.mezclarCartas();
        int y = 10;
        int x = 15;
        int cartaPos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Carta carta = baraja.getCartas().get(cartaPos);
                carta.setLocation(x, y);
                frame.add(carta);
                carta.getCardBtn().addActionListener(e -> System.out.println("Se presiono esta carta"));

                if(carta.getValor()%2==1){
                    carta.voltear();
                    //carta.getCardBtn().setEnabled(false);
                }
                x += carta.getWidth()+5;
                cartaPos++;
            }
            y += baraja.getCartas().get(0).getHeight()+5;
            x = 15;
        }
        ImageIcon logo = new ImageIcon("images/logo.png");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground( new Color(4,117,0) );
        frame.setVisible(true);
    }
}
