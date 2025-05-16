import javax.swing.*;
import java.awt.*;

public abstract class Poker extends JFrame{
    // clase padre de juego poker
    public Poker(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1366,768);
        ImageIcon logo = new ImageIcon("images/logo.png");
        setIconImage(logo.getImage());
        getContentPane().setBackground( new Color(4,117,0) );
        setVisible(true);
    }
    protected abstract void repartirCartas();
}
