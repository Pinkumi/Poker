import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FiveCardDraw extends Poker{
    //primera modalidad de juego (subclase de Poker)
    private int nJugadores;
    private JButton checkButton;
    private JButton betButton;
    private ImageIcon image5CardsVertical;
    private ImageIcon image5CardsHorizontal;
    private int cartaSize;


    public FiveCardDraw(int nJugadores){
        this.nJugadores = nJugadores;
        super(nJugadores);

        cartaSize = 170;
        baraja.changeSizeCards(cartaSize);
        setTitle("Five Card Draw");

        image5CardsHorizontal = new ImageIcon(new ImageIcon("images/mano5cardsH.png").getImage().getScaledInstance(161*((cartaSize*2)/3)/100, cartaSize, Image.SCALE_SMOOTH));
        image5CardsVertical = new ImageIcon(new ImageIcon("images/mano5cardsV.png").getImage().getScaledInstance(cartaSize, 161*((cartaSize*2)/3)/100, Image.SCALE_SMOOTH));

        checkButton = new JButton();
        checkButton.setIcon(new ImageIcon(new ImageIcon("images/botones/check.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        checkButton.setContentAreaFilled(false);
        checkButton.setBorderPainted(false);
        checkButton.setBounds((WIDTH_SIZE/6)*3-WIDTH_SIZE/24,((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/45),WIDTH_SIZE/12, HEIGHT_SIZE/15);
        checkButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/checkDisable.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

        betButton = new JButton();
        betButton.setIcon(new ImageIcon(new ImageIcon("images/botones/bet.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        betButton.setContentAreaFilled(false);
        betButton.setBorderPainted(false);
        betButton.setBounds((WIDTH_SIZE/6)-WIDTH_SIZE/24, ((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/20) ,WIDTH_SIZE/12, HEIGHT_SIZE/15);
        betButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/betDisable.png").getImage().getScaledInstance(WIDTH_SIZE/12, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

        labelBotones.add(betButton);
        labelBotones.add(checkButton);



        String entrada = JOptionPane.showInputDialog(null,"Ingrese la cantidad de fichas iniciales:", "Cantidad de Fichas", JOptionPane.QUESTION_MESSAGE);
        if(entrada == null || entrada.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ingreso nada, la cantidad de fichas iniciales es de 500.");
        }else{
            cantFichasIniciales = Integer.parseInt(entrada);
        }

        baraja.changeSizeCards(cartaSize);
        repartirCartas();
        dibujarTablero();

    }
    @Override
    protected void repartirCartas(){
        baraja.mezclarCartas();
        for(int i = 0; i < nJugadores; i++){
            jugadores.add(new Jugador(i,new Mano(baraja.extraerCartas(5))));
            jugadores.get(i).agregarFichas(cantFichasIniciales);
        }
    }
    @Override
    protected void dibujarTablero(){

        JLabel jugadorEnTurnoLabel = new JLabel(String.valueOf(turnoJugador+1));
        jugadorEnTurnoLabel.setFont(new Font("Arial",Font.PLAIN,60));
        jugadorEnTurnoLabel.setLayout(null);
        jugadorEnTurnoLabel.setForeground(Color.orange);
        jugadorEnTurnoLabel.setBounds(WIDTH_SIZE - (15*WIDTH_SIZE/16) + WIDTH_SIZE/7,(HEIGHT_SIZE/18)- (HEIGHT_SIZE/20)/2,WIDTH_SIZE/7,HEIGHT_SIZE/20);
        labelInfo.add(jugadorEnTurnoLabel);

        JLabel fichasJugadorTurnoLabel = new JLabel(String.valueOf(jugadores.get(turnoJugador).getFichas()) + " $");
        fichasJugadorTurnoLabel.setFont(new Font("Arial",Font.PLAIN,60));
        fichasJugadorTurnoLabel.setLayout(null);
        fichasJugadorTurnoLabel.setForeground(Color.orange);
        fichasJugadorTurnoLabel.setBounds(WIDTH_SIZE - (5*WIDTH_SIZE/16) + WIDTH_SIZE/9,(HEIGHT_SIZE/18)- (HEIGHT_SIZE/20)/2,WIDTH_SIZE/5,HEIGHT_SIZE/20);
        labelInfo.add(fichasJugadorTurnoLabel);

        switch(nJugadores){
            case 2,4:
                //Dibujar 1 arriba
                JLabel cartas1 = new JLabel(image5CardsVertical);
                cartas1.setBounds((WIDTH_SIZE/2)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                add(cartas1);

                break;
            case 3,5:
                //Dibujar 2 arriba
                JLabel cartas2 = new JLabel(image5CardsVertical);
                JLabel cartas3 = new JLabel(image5CardsVertical);
                cartas2.setBounds((WIDTH_SIZE/3)-(image5CardsVertical.getIconWidth()/2) ,(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                cartas3.setBounds((2*WIDTH_SIZE/3)-(image5CardsVertical.getIconWidth()/2) ,(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                add(cartas2);
                add(cartas3);
                break;
            case 6:
                //Dibujar 3 arriba
                JLabel cartas4 = new JLabel(image5CardsVertical);
                JLabel cartas5 = new JLabel(image5CardsVertical);
                JLabel cartas6 = new JLabel(image5CardsVertical);
                cartas4.setBounds((WIDTH_SIZE/4)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                cartas5.setBounds((2*WIDTH_SIZE/4)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                cartas6.setBounds((3*WIDTH_SIZE/4)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                add(cartas4);
                add(cartas5);
                add(cartas6);
                break;
            case 7:
                //Dibujar 4 arriba
                JLabel cartas7 = new JLabel(image5CardsVertical);
                JLabel cartas8 = new JLabel(image5CardsVertical);
                JLabel cartas9 = new JLabel(image5CardsVertical);
                JLabel cartas10 = new JLabel(image5CardsVertical);
                cartas7.setBounds((WIDTH_SIZE/5)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                cartas8.setBounds((2*WIDTH_SIZE/5)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                cartas9.setBounds((3*WIDTH_SIZE/5)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                cartas10.setBounds((4*WIDTH_SIZE/5)-(image5CardsVertical.getIconWidth()/2),(HEIGHT_SIZE/9) + (HEIGHT_SIZE/20),image5CardsVertical.getIconWidth(),image5CardsVertical.getIconHeight());
                add(cartas7);
                add(cartas8);
                add(cartas9);
                add(cartas10);
                break;

        }
        if(nJugadores > 3 && nJugadores < 8){
            //Dibujar 1 en cada lado
            JLabel cartas11 = new JLabel(image5CardsHorizontal);
            JLabel cartas12 = new JLabel(image5CardsHorizontal);
            cartas11.setBounds((WIDTH_SIZE/6)-(image5CardsHorizontal.getIconWidth()/2),  (HEIGHT_SIZE/2) - (image5CardsHorizontal.getIconHeight()/2),image5CardsHorizontal.getIconWidth(),image5CardsHorizontal.getIconHeight());
            cartas12.setBounds((5*WIDTH_SIZE/6)-(image5CardsHorizontal.getIconWidth()/2),  (HEIGHT_SIZE/2) - (image5CardsHorizontal.getIconHeight()/2),image5CardsHorizontal.getIconWidth(),image5CardsHorizontal.getIconHeight());
            add(cartas11);
            add(cartas12);
        }
        dibujarManoEnTurno();
        revalidate();
        repaint();

    }
    private void dibujarManoEnTurno(){
        Mano manoEnTurno = jugadores.get(turnoJugador).getMano();
        ArrayList<Carta> cartasJugador = manoEnTurno.getMano();
        int y = (4*HEIGHT_SIZE/5) - cartasJugador.getFirst().getHeight() - HEIGHT_SIZE/18;
        for(int i = 3; i <= 7; i++){
            Carta carta = cartasJugador.get(i-3);
            carta.setLocation(i*WIDTH_SIZE/10 - carta.getWidth()/2, y);
            add(carta);
//            System.out.println(cartasJugador.get(i-2).toString());
        }
    }



}