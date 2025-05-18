import javax.swing.*;
import java.awt.*;

public abstract class Poker extends JFrame{
    // clase padre de juego poker
    protected final int WIDTH_SIZE = 1366;
    protected final int HEIGHT_SIZE = 768;

    protected int nJugadores;
    protected JButton foldButton;
    protected JButton callButton;
    protected JButton raiseButton;
    protected JLabel labelBotones;
    protected JLabel labelInfo;

    public Poker(int nJugadores){

        this.nJugadores = nJugadores;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(WIDTH_SIZE,HEIGHT_SIZE);
        setLocationRelativeTo(null);

        labelBotones = new JLabel();
        labelBotones.setLayout(null);
        labelBotones.setSize(WIDTH_SIZE,HEIGHT_SIZE/5);
        labelBotones.setLocation(0,(HEIGHT_SIZE/5)*4);
        labelBotones.setVisible(true);
        labelBotones.setIcon(new ImageIcon(new ImageIcon("images/tableroBotones.png").getImage().getScaledInstance(WIDTH_SIZE, HEIGHT_SIZE/5, Image.SCALE_SMOOTH)));

        labelInfo = new JLabel();
        labelInfo.setLayout(null);
        labelInfo.setSize(WIDTH_SIZE,HEIGHT_SIZE/9);
        labelInfo.setLocation(0,0);
        labelInfo.setVisible(true);
        labelInfo.setIcon(new ImageIcon(new ImageIcon("images/tableroInfo.png").getImage().getScaledInstance(WIDTH_SIZE, HEIGHT_SIZE/9, Image.SCALE_SMOOTH)));



        ImageIcon logo = new ImageIcon("images/logo.png");
        setIconImage(logo.getImage());
        getContentPane().setBackground( new Color(6, 77, 6) );
        setVisible(true);
        add(labelBotones);
        add(labelInfo);

    }
    protected abstract void repartirCartas();
}