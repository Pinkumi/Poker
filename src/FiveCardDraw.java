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
    private ArrayList<Jugador> jugadoresRetirados;



    public FiveCardDraw(int nJugadores){
        super(nJugadores);
        this.nJugadores = nJugadores;
        jugadoresRestantes = nJugadores;
        apuestaActual = 0;
        nRonda = 1;
        bote =0;
        cartaSize = 8*HEIGHT_SIZE/50;
        baraja.changeSizeCards(cartaSize);
        setTitle("Five Card Draw");
        jugadoresRetirados = new ArrayList<>();

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
        System.out.println(jugadores.get(turnoJugador).getMano());
        dibujarTablero(nJugadores);
        System.out.println(jugadores.get(turnoJugador).getMano());


        pasarDeRonda();

    }
    public void activarBotonesRondaApuestas(){
        if(apuestaActual == 0){
            betButton.setEnabled(true);
            checkButton.setEnabled(true);
            foldButton.setEnabled(true);
            callButton.setEnabled(false);
            raiseButton.setEnabled(false);
        }else{
            betButton.setEnabled(false);
            foldButton.setEnabled(true);
            callButton.setEnabled(true);
            raiseButton.setEnabled(true);
            checkButton.setEnabled(false);
        }
    }
    @Override
    protected void repartirCartas(){
        baraja.mezclarCartas();
        for(int i = 0; i < nJugadores; i++){
            jugadores.add(new Jugador(i,new Mano(baraja.extraerCartas(5))));
            jugadores.get(i).getMano().deshabilitarBotones();
            jugadores.get(i).agregarFichas(cantFichasIniciales);
        }
    }
    public void agregarEfectoBotones(){
        foldButton.addActionListener(e->{
            //Abandonar apuesta y mano.
            System.out.println("Se abandonara la mano.");
            jugadoresRestantes--;
            jugadoresRetirados.add(jugadores.remove(turnoJugador));

            if (turnoJugador == jugadores.size() - 1) {
                turnoJugador = 0;
                System.out.println("SE ACABO LA BETTING ROUND");
                //Activar botones dependiendo de la siguiente ronda
                nRonda++;
                if(nRonda == 4){
                    showDown();
                }else{
                    pasarDeRonda();
                }
            } else {
                turnoJugador++;
                activarBotonesRondaApuestas();
            }

        });
        callButton.addActionListener( e->{
            //Igualar la apuesta actual.

            System.out.println("Se Igualara la apuesta.");
            jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            bote += apuestaActual;
            dibujarTablero(jugadoresRestantes);

            Timer sleepTurno = new Timer(2000, a -> {
                if (turnoJugador == jugadores.size() - 1) {
                    turnoJugador = 0;
                    System.out.println("SE ACABO LA BETTING ROUND");
                    //Activar botones dependiendo de la siguiente ronda
                    nRonda++;
                    if(nRonda == 4){
                        showDown();
                    }else{
                        pasarDeRonda();
                    }
                } else {
                    turnoJugador++;
                    activarBotonesRondaApuestas();
                }
                dibujarTablero(jugadoresRestantes);
            });
            sleepTurno.setRepeats(false);
            sleepTurno.start();

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


            Timer sleepTurno = new Timer(2000, a -> {
                if (turnoJugador == jugadores.size() - 1) {
                    turnoJugador = 0;
                    System.out.println("SE ACABO LA BETTING ROUND");
                    //Activar botones dependiendo de la siguiente ronda
                    nRonda++;
                    if(nRonda == 4){
                        showDown();
                    }else{
                        pasarDeRonda();
                    }
                } else {
                    turnoJugador++;
                    activarBotonesRondaApuestas();
                }
                dibujarTablero(jugadoresRestantes);
            });
            sleepTurno.setRepeats(false);
            sleepTurno.start();

        });
        betButton.addActionListener( e->{
            //Apostar

            System.out.println("Se Apostara.");
            String fichasApuesta = JOptionPane.showInputDialog(null,"Ingrese la cantidad de fichas a apostar:", "Apuesta", JOptionPane.QUESTION_MESSAGE);
            if(fichasApuesta == null || fichasApuesta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ingreso nada, la apuesta sera de 100$.");
                bote += 100;
                apuestaActual = 100;
            }else{
                bote += Integer.parseInt(fichasApuesta);
                apuestaActual = Integer.parseInt(fichasApuesta);
            }

            jugadores.get(turnoJugador).colocarFichas(apuestaActual);
            dibujarTablero(jugadoresRestantes);


            Timer sleepTurno = new Timer(2000, a -> {
                if (turnoJugador == jugadores.size() - 1) {
                    turnoJugador = 0;
                    System.out.println("SE ACABO LA BETTING ROUND");
                    //Activar botones dependiendo de la siguiente ronda
                    nRonda++;
                    if(nRonda == 4){
                        showDown();
                    }else{
                        pasarDeRonda();
                    }

                } else {
                    turnoJugador++;
                    activarBotonesRondaApuestas();
                }
                dibujarTablero(jugadoresRestantes);
            });
            sleepTurno.setRepeats(false);
            sleepTurno.start();

        });
        checkButton.addActionListener( e->{
            //Pasar (solo si no hay apuesta actual)

            Timer sleepTurno = new Timer(2000, a -> {
                if (turnoJugador == jugadores.size() - 1) {
                    turnoJugador = 0;
                    System.out.println("SE ACABO LA BETTING ROUND");
                    //Activar botones dependiendo de la siguiente ronda
                    nRonda++;
                    if(nRonda == 4){
                        showDown();
                    }else{
                        pasarDeRonda();
                    }

                } else {
                    turnoJugador++;
                    activarBotonesRondaApuestas();
                }
                dibujarTablero(jugadoresRestantes);
            });
            sleepTurno.setRepeats(false);
            sleepTurno.start();



        });
    }
    public void pasarDeRonda(){
        if(nRonda == 1){

            activarBotonesRondaApuestas();
            mostrarApuestasIcon();
        }else if(nRonda == 2){
            rondaDraw(turnoJugador);
        }else if(nRonda == 3){
            apuestaActual=0;

            activarBotonesRondaApuestas();
            mostrarApuestasIcon();
        }
    }
    public void mostrarApuestasIcon(){
        JPanel panelApuestasRonda = new JPanel();
        panelApuestasRonda.setLayout(null);
        panelApuestasRonda.setOpaque(true);
        panelApuestasRonda.setBackground(new Color(255,215,0,50));
        panelApuestasRonda.setBounds(WIDTH_SIZE/2 - (WIDTH_SIZE/6), HEIGHT_SIZE/5, WIDTH_SIZE/3, HEIGHT_SIZE/4);

        Image imgApuestas = new ImageIcon("images/texto/apuestas.png")
                .getImage().getScaledInstance(WIDTH_SIZE/3, HEIGHT_SIZE/7, Image.SCALE_SMOOTH);
        JLabel labelApuestas = new JLabel(new ImageIcon(imgApuestas));
        labelApuestas.setOpaque(false);
        labelApuestas.setBounds(WIDTH_SIZE/2 - (WIDTH_SIZE/6),HEIGHT_SIZE/5, WIDTH_SIZE/3, HEIGHT_SIZE/7);
        panelApuestasRonda.add(labelApuestas);
        JRootPane root = getRootPane();
        root.setGlassPane(panelApuestasRonda);
        panelApuestasRonda.setVisible(true);
        Timer sleepApostarPanel = new Timer(2000, a -> {
            panelApuestasRonda.setVisible(false);
        });
        sleepApostarPanel.setRepeats(false);
        sleepApostarPanel.start();

    }
    public void showDown(){
        System.out.println("SE ACABOOO, EL GANADOR ES: .");
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
        jugadorEnTurnoLabel.setText(String.valueOf(jugadores.get(turnoJugador).getNJugador()+1));
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
        dibujarManos();
        MostrarManoEnTurno();
        actualizarInfo();
        revalidate();
        repaint();


    }
    public void MostrarManoEnTurno(){
        for(Jugador jugador : jugadores){
            jugador.getMano().ocultarCartas();
        }
        jugadores.get(turnoJugador).getMano().mostrarCartas();
        revalidate();
        repaint();
    }

    private void dibujarManos(){
        for(Jugador jugador : jugadores){
           // Mano manoEnTurno = jugadores.get(turnoJugador).getMano();
            Mano manoEnTurno = jugador.getMano();
            ArrayList<Carta> cartasJugador = manoEnTurno.getMano();
            int y = (4*HEIGHT_SIZE/5) - cartasJugador.getFirst().getHeight() - HEIGHT_SIZE/18;
            for(int i = 3; i <= 7; i++){
                Carta carta = cartasJugador.get(i-3);
                carta.setLocation(i*WIDTH_SIZE/10 - carta.getWidth()/2, y);
                add(carta);
            }
        }


    }
    public void rondaDraw(int turno){
        betButton.setEnabled(false);
        raiseButton.setEnabled(false);
        foldButton.setEnabled(false);
        checkButton.setEnabled(false);
        callButton.setEnabled(false);

        ArrayList<Carta> cartasDescartadas = new ArrayList<>();
        jugadores.get(turno).getMano().habilitarBotones();
        JPanel panelDrawCard = new JPanel();
        panelDrawCard.setLayout(null);
        panelDrawCard.setOpaque(false);
        panelDrawCard.setBounds(WIDTH_SIZE/2 - (WIDTH_SIZE/6), HEIGHT_SIZE/5, WIDTH_SIZE/3, HEIGHT_SIZE/4);

        Image imgDrawCard = new ImageIcon("images/texto/descarte.png")
                .getImage().getScaledInstance(WIDTH_SIZE/3, HEIGHT_SIZE/7, Image.SCALE_SMOOTH);

        JLabel labelDrawCard = new JLabel(new ImageIcon(imgDrawCard));
        labelDrawCard.setOpaque(false);
        labelDrawCard.setBounds(WIDTH_SIZE/2 - (WIDTH_SIZE/6),HEIGHT_SIZE/5, WIDTH_SIZE/3, HEIGHT_SIZE/7);

        JButton okButton = new JButton(new ImageIcon(new ImageIcon("images/botones/ok.png")
                .getImage().getScaledInstance(labelDrawCard.getWidth()/5, 2*labelDrawCard.getHeight()/3, Image.SCALE_SMOOTH)));
        okButton.setSize(labelDrawCard.getWidth()/5, 2*labelDrawCard.getHeight()/3);
        okButton.setLocation(labelDrawCard.getX() + (labelDrawCard.getWidth()/2 - okButton.getWidth()/2), labelDrawCard.getY()+(labelDrawCard.getHeight() + 30));
        okButton.setContentAreaFilled(false);

        okButton.setBorderPainted(false);
        panelDrawCard.add(labelDrawCard);
        panelDrawCard.add(okButton);

        JRootPane root = getRootPane();
        root.setGlassPane(panelDrawCard);
        panelDrawCard.setVisible(true);

        okButton.addActionListener(b-> {
            panelDrawCard.setVisible(false);
            okButton.setEnabled(false);

            for(Carta carta : cartasDescartadas){
                carta.setVisible(false);
                remove(carta);
                Carta nuevaCarta = baraja.extraerCarta(true);
                jugadores.get(turno).getMano().getCartas().set(jugadores.get(turno).getMano().getCartas().indexOf(carta), nuevaCarta);
                add(nuevaCarta);
                dibujarManos();
                MostrarManoEnTurno();
            }
            for(Carta carta : jugadores.get(turno).getMano().getCartas()) {
                carta.getCardBtn().setEnabled(false);
                //carta.setVisible(true);
            }
            Timer sleepTurno = new Timer(2000, e -> {
                if (turnoJugador == jugadores.size() - 1) {
                    turnoJugador = 0;
                    System.out.println("SE ACABO LA DRAW ROUND");
                    //Activar botones dependiendo de la siguiente ronda
                    nRonda++;
                    pasarDeRonda();


                } else {
                    turnoJugador++;
                    rondaDraw(turnoJugador);
                }

                dibujarTablero(jugadoresRestantes);
            });
            sleepTurno.setRepeats(false);
            sleepTurno.start();
            dibujarTablero(jugadoresRestantes);
            //System.out.println(jugadores.get(turno).getMano());

        });
        revalidate();
        repaint();
        for(int i = 0; i <5; i++){
            Carta cartaJugador = jugadores.get(turno).getMano().getCartas().get(i);
            cartaJugador.getCardBtn().setEnabled(true);
            cartaJugador.getCardBtn().addActionListener(e -> {
                cartaJugador.getCardBtn().setEnabled(false);
                cartasDescartadas.add(cartaJugador);
                cartaJugador.setLocation(cartaJugador.getX(), cartaJugador.getY() - 50);
                revalidate();
                repaint();
            });
        }

    }



}