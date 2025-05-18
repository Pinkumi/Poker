import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JComboBox<String> modoJuego;
    private JSpinner cantJugadores;
    private JButton iniciarBtn;
    private Poker poker;
    public Menu() {
        setTitle("Poker");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon("images/logo.png");
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);

        Image fondoImage = new ImageIcon("images/pokerMenu.png").getImage().getScaledInstance(800,500,Image.SCALE_SMOOTH);
        JLabel fondoLabel = new JLabel(new ImageIcon(fondoImage));
        fondoLabel.setLayout(null);

        modoJuego = new JComboBox<>();
        modoJuego.addItem("Five Card Draw");
        modoJuego.addItem("Seven Card Stud");
        modoJuego.setBounds(340,200,120,30);

        cantJugadores = new JSpinner();
        cantJugadores.setModel(new SpinnerNumberModel(2,2,8,1));
        cantJugadores.setBounds(380,250,40,30);

        Image botonStartImage = new ImageIcon("images/botones/start.png").getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        iniciarBtn = new JButton(new ImageIcon(botonStartImage));
        iniciarBtn.setBounds(350,300,100,100);
        iniciarBtn.setContentAreaFilled(false);
        iniciarBtn.setBorderPainted(false);

        fondoLabel.add(iniciarBtn);
        fondoLabel.add(cantJugadores);
        fondoLabel.add(modoJuego);

        setContentPane(fondoLabel);
        iniciarBtn.addActionListener(e -> {
            String modo = (String) modoJuego.getSelectedItem();
            int jugadores = (int) cantJugadores.getValue();
            JOptionPane.showMessageDialog(this,
                    "Modo: " + modo + "\nJugadores: " + jugadores);
            if(modo.equals("Five Card Draw")) {
                poker = new FiveCardDraw(jugadores);
            }else{
                poker = new SevenCardStud(jugadores);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
    }
}
