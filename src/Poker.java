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
    protected JPanel panelInfo;

    public Poker(int nJugadores){
        this.nJugadores = nJugadores;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1366,768);

        labelBotones = new JLabel();
        labelBotones.setLayout(null);
        labelBotones.setSize(WIDTH_SIZE,HEIGHT_SIZE/5);
        labelBotones.setLocation(0,(HEIGHT_SIZE/5)*4);
        labelBotones.setVisible(true);
       // labelBotones.setBackground(new Color(74,54,33));
        labelBotones.setIcon(new ImageIcon(new ImageIcon("images/tableroBotones.png").getImage().getScaledInstance(WIDTH_SIZE, HEIGHT_SIZE/5, Image.SCALE_SMOOTH)));
        panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setSize(WIDTH_SIZE,HEIGHT_SIZE/8);
        panelInfo.setLocation(0,0);
        panelInfo.setVisible(true);
        panelInfo.setBackground(new Color(74,54,33));


        ImageIcon logo = new ImageIcon("images/logo.png");
        setIconImage(logo.getImage());
        getContentPane().setBackground( new Color(6, 77, 6) );
        setVisible(true);
        add(labelBotones);
        add(panelInfo);

    }
    protected abstract void repartirCartas();
}