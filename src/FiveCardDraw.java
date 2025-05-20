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
    private int jugadoresRestantes;
    private JLabel jugadorEnTurnoLabel;
    private JLabel fichasJugadorTurnoLabel;
    private JLabel boteLabel;


    public FiveCardDraw(int nJugadores){
        super(nJugadores);
        this.nJugadores = nJugadores;

        jugadoresRestantes = nJugadores;
        apuestaActual = 0;
        nRonda = 0;
        bote =0;
        cartaSize = 8*HEIGHT_SIZE/50;
        baraja.changeSizeCards(cartaSize);
        setTitle("Five Card Draw");

        jugadorEnTurnoLabel = new JLabel();
        fichasJugadorTurnoLabel = new JLabel();
        boteLabel = new JLabel();

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
        agregarEfectoBotones();
        repartirCartas();
        dibujarTablero(nJugadores);

    }
    @Override
    protected void repartirCartas(){
        baraja.mezclarCartas();
        for(int i = 0; i < nJugadores; i++){
            jugadores.add(new Jugador(i,new Mano(baraja.extraerCartas(5))));
            jugadores.get(i).agregarFichas(cantFichasIniciales);
        }
    }
    public void agregarEfectoBotones(){
        foldButton.addActionListener(e->{
            //Abandonar apuesta y mano.
            System.out.println("Se abandonara la mano.");

        });
        callButton.addActionListener( e->{
            //Igualar la apuesta actual.
            System.out.println("Se Igualara la apuesta.");
            jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            bote += apuestaActual;
            dibujarTablero(jugadoresRestantes);
        });
        raiseButton.addActionListener( e->{
            //Aumenta la apuesta actual.
            System.out.println("Se aumentar la apuesta.");
            String fichasRaise = JOptionPane.showInputDialog(null,"Ingrese la cantidad de fichas a aumentar:", "Aumentar", JOptionPane.QUESTION_MESSAGE);
            if(fichasRaise == null || fichasRaise.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ingreso nada, la apuesta se incrementara en 100$.");
                apuestaActual += 100;
                jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            }else if(Integer.parseInt(fichasRaise) <= apuestaActual){
                JOptionPane.showMessageDialog(null, "Tienes que aumentar mas a la apuesta actual, se incrementara en 100$.");
                apuestaActual += 100;
                jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            }else{
                apuestaActual = Integer.parseInt(fichasRaise);
                jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            }
            bote += apuestaActual;
            dibujarTablero(jugadoresRestantes);

        });
        betButton.addActionListener( e->{
            //Apostar
            System.out.println("Se Apostara.");
            String fichasApuesta = JOptionPane.showInputDialog(null,"Ingrese la cantidad de fichas a apostar:", "Apuesta", JOptionPane.QUESTION_MESSAGE);
            if(fichasApuesta == null || fichasApuesta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ingreso nada, la apuesta sera de 100$.");
                bote = 100;
            }else{
                bote = Integer.parseInt(fichasApuesta);
            }
            apuestaActual = bote;
            jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            dibujarTablero(jugadoresRestantes);


        });
        checkButton.addActionListener( e->{
            //Pasar (solo si no hay apuesta actual)

        });
    }
    public void actualizarInfo(){
        jugadorEnTurnoLabel.revalidate();
        jugadorEnTurnoLabel.repaint();
        fichasJugadorTurnoLabel.revalidate();
        fichasJugadorTurnoLabel.repaint();
        boteLabel.revalidate();
        boteLabel.repaint();
        labelInfo.revalidate();
        labelInfo.repaint();
    }
    protected void agregarFichasAlBote(int numFichas){
        bote += numFichas;
    }
    @Override
    protected void dibujarTablero(int jugadoresEnJuego){
        jugadorEnTurnoLabel.setText(String.valueOf(turnoJugador+1));
        jugadorEnTurnoLabel.setFont(new Font("Arial",Font.PLAIN,(HEIGHT_SIZE/22)+15));
        jugadorEnTurnoLabel.setLayout(null);
        jugadorEnTurnoLabel.setForeground(Color.orange);
        jugadorEnTurnoLabel.setBounds(WIDTH_SIZE - (15*WIDTH_SIZE/16) + WIDTH_SIZE/7,(HEIGHT_SIZE/18)- (HEIGHT_SIZE/20)/2,WIDTH_SIZE/7,HEIGHT_SIZE/20);
        labelInfo.add(jugadorEnTurnoLabel);

        fichasJugadorTurnoLabel.setText(String.valueOf(jugadores.get(turnoJugador).getFichas()) + " $");
        fichasJugadorTurnoLabel.setFont(new Font("Arial",Font.PLAIN,(HEIGHT_SIZE/22)+15));
        fichasJugadorTurnoLabel.setLayout(null);
        fichasJugadorTurnoLabel.setForeground(Color.orange);
        fichasJugadorTurnoLabel.setBounds(WIDTH_SIZE - (5*WIDTH_SIZE/16) + WIDTH_SIZE/9,(HEIGHT_SIZE/18)- (HEIGHT_SIZE/20)/2,WIDTH_SIZE/5,HEIGHT_SIZE/20);
        labelInfo.add(fichasJugadorTurnoLabel);

        boteLabel.setText(String.valueOf(bote) + " $");
        boteLabel.setFont(new Font("Arial",Font.PLAIN,(HEIGHT_SIZE/22)+15));
        boteLabel.setLayout(null);
        boteLabel.setForeground(Color.orange);
        boteLabel.setBounds(WIDTH_SIZE - (10*WIDTH_SIZE/16) + WIDTH_SIZE/12,(HEIGHT_SIZE/18)- (HEIGHT_SIZE/20)/2,WIDTH_SIZE/5,HEIGHT_SIZE/20);
        labelInfo.add(boteLabel);

        switch(jugadoresEnJuego){
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
        if(jugadoresEnJuego > 3 && jugadoresEnJuego < 8){
            //Dibujar 1 en cada lado
            JLabel cartas11 = new JLabel(image5CardsHorizontal);
            JLabel cartas12 = new JLabel(image5CardsHorizontal);
            cartas11.setBounds((WIDTH_SIZE/6)-(image5CardsHorizontal.getIconWidth()/2),  (HEIGHT_SIZE/2) - (image5CardsHorizontal.getIconHeight()/2),image5CardsHorizontal.getIconWidth(),image5CardsHorizontal.getIconHeight());
            cartas12.setBounds((5*WIDTH_SIZE/6)-(image5CardsHorizontal.getIconWidth()/2),  (HEIGHT_SIZE/2) - (image5CardsHorizontal.getIconHeight()/2),image5CardsHorizontal.getIconWidth(),image5CardsHorizontal.getIconHeight());
            add(cartas11);
            add(cartas12);
        }
        dibujarManoEnTurno();
        actualizarInfo();
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
        }
    }



}