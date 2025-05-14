import javax.swing.*;

public class prueba {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1500, 1500);

        Carta cartaPrueba1 = new Carta(10, "trebol");
        Carta cartaPrueba8 = new Carta(10, "pica");
        Carta cartaPrueba2 = new Carta(11, "corazon");
        Carta cartaPrueba3 = new Carta(11, "diamante");
        Carta cartaPrueba4 = new Carta(12, "corazon");
        Carta cartaPrueba5 = new Carta(12, "pica");
        Carta cartaPrueba6 = new Carta(13, "trebol");
        Carta cartaPrueba7 = new Carta(13, "corazon");

        Carta cartaPrueba9 = new Carta(10, "corazon");
        Carta cartaPrueba10 = new Carta(10, "diamante");

        cartaPrueba1.changePosition(0, 50);
        cartaPrueba8.changePosition(300, 50);
        cartaPrueba2.changePosition(600, 50);
        cartaPrueba3.changePosition(900, 50);
        cartaPrueba4.changePosition(1200, 50);

        cartaPrueba5.changePosition(0, 450);
        cartaPrueba6.changePosition(300, 450);
        cartaPrueba7.changePosition(600, 450);
        cartaPrueba9.changePosition(900, 450);
        cartaPrueba10.changePosition(1200, 450);

        cartaPrueba1.changeSize(100, 150);
        cartaPrueba4.changeSize(300, 450);

        frame.add(cartaPrueba1);
        frame.add(cartaPrueba2);
        frame.add(cartaPrueba3);
        frame.add(cartaPrueba4);
        frame.add(cartaPrueba5);
        frame.add(cartaPrueba6);
        frame.add(cartaPrueba7);
        frame.add(cartaPrueba8);
        frame.add(cartaPrueba9);
        frame.add(cartaPrueba10);
        frame.setVisible(true);

    }
}
