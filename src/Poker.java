import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Poker extends JFrame{
    // clase padre de juego poker
    protected final int WIDTH_SIZE = 1900;
    protected final int HEIGHT_SIZE = 1060;

    protected int nJugadores;
    protected JButton foldButton;
    protected JButton callButton;
    protected JButton raiseButton;
    protected JLabel labelBotones;
    protected JLabel labelInfo;
    protected ArrayList<Jugador> jugadores;
    protected int cantFichasIniciales;
    protected Baraja baraja;

    public Poker(int nJugadores){

        this.nJugadores = nJugadores;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(WIDTH_SIZE,HEIGHT_SIZE);
        setLocationRelativeTo(null);

        jugadores = new ArrayList<>();
        baraja = new Baraja();

        Image fondoImage = new ImageIcon("images/fondoPoker.jpg").getImage().getScaledInstance(WIDTH_SIZE,HEIGHT_SIZE,Image.SCALE_SMOOTH);
        JLabel fondoPokerLabel = new JLabel(new ImageIcon(fondoImage));
        fondoPokerLabel.setLayout(null);
        cantFichasIniciales = 500;
        labelBotones = new JLabel();
        labelBotones.setLayout(null);
        labelBotones.setSize(WIDTH_SIZE,HEIGHT_SIZE/5);
        labelBotones.setLocation(0,(HEIGHT_SIZE/5)*4);
        labelBotones.setIcon(new ImageIcon(new ImageIcon("images/tableroBotones.png").getImage().getScaledInstance(WIDTH_SIZE, HEIGHT_SIZE/5, Image.SCALE_SMOOTH)));

        raiseButton = new JButton();
        raiseButton.setIcon(new ImageIcon(new ImageIcon("images/botones/raise.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        raiseButton.setContentAreaFilled(false);
        raiseButton.setBorderPainted(false);
        raiseButton.setBounds((WIDTH_SIZE/6)*5-WIDTH_SIZE/24,((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/20),WIDTH_SIZE/12, HEIGHT_SIZE/15);
        raiseButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/raiseDisable.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

        callButton = new JButton();
        callButton.setIcon(new ImageIcon(new ImageIcon("images/botones/call.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        callButton.setContentAreaFilled(false);
        callButton.setBorderPainted(false);
        callButton.setBounds((WIDTH_SIZE/6)*4-WIDTH_SIZE/24,((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/23),WIDTH_SIZE/12, HEIGHT_SIZE/15);
        callButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/callDisable.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

        foldButton = new JButton();
        foldButton.setIcon(new ImageIcon(new ImageIcon("images/botones/fold.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        foldButton.setContentAreaFilled(false);
        foldButton.setBorderPainted(false);
        foldButton.setBounds((WIDTH_SIZE/6)*2-WIDTH_SIZE/24, ((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/23) ,WIDTH_SIZE/12, HEIGHT_SIZE/15);
        foldButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/foldDisable.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

        labelBotones.add(raiseButton);
        labelBotones.add(foldButton);
        labelBotones.add(callButton);

        labelInfo = new JLabel();
        labelInfo.setLayout(null);
        labelInfo.setSize(WIDTH_SIZE,HEIGHT_SIZE/9);
        labelInfo.setLocation(0,0);
        labelInfo.setVisible(true);
        labelInfo.setIcon(new ImageIcon(new ImageIcon("images/tableroInfo.png").getImage().getScaledInstance(WIDTH_SIZE, HEIGHT_SIZE/9, Image.SCALE_SMOOTH)));


        ImageIcon logo = new ImageIcon("images/logo.png");
        setIconImage(logo.getImage());
        setContentPane(fondoPokerLabel);
        setVisible(true);
        fondoPokerLabel.add(labelBotones);
        fondoPokerLabel.add(labelInfo);



    }
    protected abstract void repartirCartas();

}

