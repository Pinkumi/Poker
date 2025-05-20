import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SevenCardStud extends Poker{
    //segunda modalidad de juego (subclase de Poker)
    private int nJugadores;
    private JButton checkButton;
    private JButton betButton;
    private JButton completeButton;
    private ImageIcon image5CardsVertical;
    private ImageIcon image5CardsHorizontal;
    private int cartaSize;
    private JButton callButton;
    private ArrayList<Jugador> jugadoresRetirados = new ArrayList<>();
    private JLabel jugadorEnTurnoLabel;
    private JLabel fichasJugadorTurnoLabel;
    private JLabel boteLabel;
    private int jugadoresRestantes;

    public SevenCardStud(int nJugadores){
        super(nJugadores);
        this.nJugadores = nJugadores;
        cartaSize = 8 * HEIGHT_SIZE / 50;
        bote = 10;

        setTitle("Seven Card Stud");
        setLayout(null);

        jugadorEnTurnoLabel = new JLabel();
        fichasJugadorTurnoLabel = new JLabel();
        boteLabel = new JLabel();

        image5CardsHorizontal = new ImageIcon(new ImageIcon("images/mano5cardsH.png")
                .getImage().getScaledInstance(161 * ((cartaSize * 2) / 3) / 100, cartaSize, Image.SCALE_SMOOTH));
        image5CardsVertical = new ImageIcon(new ImageIcon("images/mano5cardsV.png")
                .getImage().getScaledInstance(cartaSize, 161 * ((cartaSize * 2) / 3) / 100, Image.SCALE_SMOOTH));

        checkButton = new JButton();
        checkButton.setIcon(new ImageIcon(new ImageIcon("images/botones/check.png")
                .getImage().getScaledInstance(WIDTH_SIZE / 12, HEIGHT_SIZE / 15, Image.SCALE_SMOOTH)));
        checkButton.setContentAreaFilled(false);
        checkButton.setBorderPainted(false);
        checkButton.setBounds((WIDTH_SIZE / 6) * 3 - WIDTH_SIZE / 24, ((HEIGHT_SIZE / 5) / 2) - (HEIGHT_SIZE / 45),
                WIDTH_SIZE / 12, HEIGHT_SIZE / 15);
        checkButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/checkDisable.png")
                .getImage().getScaledInstance(WIDTH_SIZE / 12, HEIGHT_SIZE / 15, Image.SCALE_SMOOTH)));

        betButton = new JButton();
        betButton.setIcon(new ImageIcon(new ImageIcon("images/botones/bet.png")
                .getImage().getScaledInstance(WIDTH_SIZE / 12, HEIGHT_SIZE / 15, Image.SCALE_SMOOTH)));
        betButton.setContentAreaFilled(false);
        betButton.setBorderPainted(false);
        betButton.setBounds((WIDTH_SIZE / 6) - WIDTH_SIZE / 24, ((HEIGHT_SIZE / 5) / 2) - (HEIGHT_SIZE / 20),
                WIDTH_SIZE / 12, HEIGHT_SIZE / 15);
        betButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/betDisable.png")
                .getImage().getScaledInstance(WIDTH_SIZE / 12, HEIGHT_SIZE / 15, Image.SCALE_SMOOTH)));

        completeButton = new JButton();
        completeButton.setIcon(new ImageIcon(new ImageIcon("images/botones/complete.png")
                .getImage().getScaledInstance(WIDTH_SIZE / 10, HEIGHT_SIZE / 15, Image.SCALE_SMOOTH)));
        completeButton.setContentAreaFilled(false);
        completeButton.setBorderPainted(false);
        completeButton.setBounds((WIDTH_SIZE / 6) - WIDTH_SIZE / 20, ((HEIGHT_SIZE / 5) / 2) - (HEIGHT_SIZE / 20),
                WIDTH_SIZE / 10, HEIGHT_SIZE / 15);
        completeButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/completeDisable.png")
                .getImage().getScaledInstance(WIDTH_SIZE / 10, HEIGHT_SIZE / 15, Image.SCALE_SMOOTH)));

        labelBotones.add(checkButton);
        labelBotones.add(betButton);
        labelBotones.add(completeButton);

        String entrada = JOptionPane.showInputDialog(null, "Ingrese la cantidad de fichas iniciales:",
                "Cantidad de Fichas", JOptionPane.QUESTION_MESSAGE);
        if (entrada == null || entrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingreso nada, la cantidad de fichas iniciales es de 500.");
        } else {
            cantFichasIniciales = Integer.parseInt(entrada);
        }

        baraja.changeSizeCards(cartaSize);

        anteMano(10);
        repartirCartas();
        cuartaStreet();
        quintaStreet();
        sextaStreet();
        septimaStreet();

        setVisible(true);

    }

    @Override
    protected void repartirCartas() {
        baraja.mezclarCartas();

        for(int i = 0; i < nJugadores; i++){
            ArrayList<Carta> cartasJugador = new ArrayList<>();
            cartasJugador.add(baraja.extraerCarta(false));
            cartasJugador.add(baraja.extraerCarta(false));
            cartasJugador.add(baraja.extraerCarta(true));

            Mano manoJugador = new Mano(cartasJugador);
            Jugador jugador = new Jugador(i, manoJugador);
            jugador.agregarFichas(cantFichasIniciales);
            jugadores.add(jugador);
        }

    }

    public void cuartaStreet() {
        System.out.println("------ Cuarta Street ------");
        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(true));
            }
        }

        dibujarTablero(nJugadores - jugadoresRetirados.size());
        rondaApuestas(10);
    }

    public void quintaStreet() {
        System.out.println("------ Quinta Street ------");
        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(true));
            }
        }

        dibujarTablero(nJugadores - jugadoresRetirados.size());
        rondaApuestas(10);
    }

    public void sextaStreet() {
        System.out.println("------ Sexta Street ------");
        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(true));
            }
        }

        dibujarTablero(nJugadores - jugadoresRetirados.size());
        rondaApuestas(10);
    }

    public void septimaStreet() {
        System.out.println("------ Séptima Street ------ ");

        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(false));
            }
        }
        Jugador ganador = null;
        int mejorPuntaje = - 1;

        for (Jugador jugador : jugadores) {
            if (jugadoresRetirados.contains(jugador)) continue;

            int puntos = jugador.getMano().obtenerRanking();
            System.out.println(jugador + ": " + jugador.getMano().obtenerRanking());

            if (puntos > mejorPuntaje) {
                mejorPuntaje = puntos;
                ganador = jugador;
            }

            System.out.println("Cartas en mano:");
            System.out.println(jugador.getMano().toString());
        }

        if (ganador != null) {
            System.out.println("\nGanador:" + ganador + " con " + ganador.getMano().obtenerRanking());
            ganador.agregarFichas(bote);
            System.out.println("Fichas ganadas: " + bote);
            bote = 0;
        } else {
            System.out.println("No hubo ganador.");
        }

        for (Jugador jugador : jugadores) {
            jugador.getMano().mostrarCartas();
        }

        dibujarTablero(nJugadores - jugadoresRetirados.size());
    }


    public void rondaApuestas(int ante) {
        int apuestaActual = ante;
        HashMap<Jugador, Integer> apuestas = new HashMap<>();


        for (Jugador jugador : jugadores) {
            apuestas.put(jugador, ante);
        }

        for (Jugador jugador : jugadores) {
            if (jugadoresRetirados.contains(jugador) || jugador.getFichas() <= 0) {
                continue;
            }

            ArrayList<Integer> cartasVisiblesTemporal = new ArrayList<>();
            ArrayList<Carta> cartasJugador = jugador.getMano().getCartas();

            for (int i = 0; i < cartasJugador.size(); i++) {
                Carta cartasOcultas = cartasJugador.get(i);
                if (!cartasOcultas.esVisible()) {
                    cartasOcultas.voltear7();
                    cartasVisiblesTemporal.add(i);
                }
            }

            int yaApostado = apuestas.get(jugador);
            int diferencia = apuestaActual - yaApostado;

            System.out.println("Cartas en mano:");
            System.out.println(jugador.getMano());

            System.out.println("\n" + jugador);
            System.out.println("Fichas disponibles: " + jugador.getFichas());
            System.out.println("Apuesta actual: " + apuestaActual);
            System.out.println("Tú has apostado: " + yaApostado);
            System.out.println("1. Igualar (" + diferencia + ")");
            System.out.println("2. Subir");
            System.out.println("3. Retirarse");

            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1: // Igualar
                    if (jugador.getFichas() >= diferencia) {
                        jugador.colocarFichas(diferencia);
                        apuestas.put(jugador, yaApostado + diferencia);
                        bote += diferencia;
                    } else {
                        System.out.println("No puedes igualar. Te retiras");
                        jugadoresRetirados.add(jugador);
                    }
                    break;

                case 2: // Subir
                    System.out.print("Cuánto deseas subir: ");
                    int subida = sc.nextInt();
                    int nuevaApuesta = apuestaActual + subida;
                    int total = nuevaApuesta - yaApostado;

                    if (jugador.getFichas() >= total) {
                        jugador.colocarFichas(total);
                        apuestas.put(jugador, nuevaApuesta);
                        apuestaActual = nuevaApuesta;
                        bote += total;
                    } else {
                        System.out.println("No tienes suficientes fichas. Te retiras");
                        jugadoresRetirados.add(jugador);
                    }
                    break;

                case 3: // Retirarse
                default:
                    jugadoresRetirados.add(jugador);
                    System.out.println(jugador + " se ha retirado.");
                    break;
            }
            for (int j : cartasVisiblesTemporal) {
                cartasJugador.get(j).voltear7();
            }

        }

        System.out.println("\nBote total: " + bote + " fichas.");
    }


    public void anteMano(int ante){
        System.out.println("Ante Mano de apuestas: ");
        bote = 0;

        for (Jugador jugador : jugadores) {
            if (jugador.getFichas() >= ante) {
                jugador.colocarFichas(ante);
                bote += ante;
                System.out.println(jugador + " coloca " + ante);
            } else {
                System.out.println(jugador + " sin fichas suficientes. Se retira.");
                jugadoresRetirados.add(jugador);
            }
        }

        System.out.println("Bote inicial: " + bote + " fichas");
    }


    @Override
    protected void dibujarTablero(int jugadoresEnJuego) {
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

        dibujarManoEnTurno();
        actualizarInfo();
        revalidate();
        repaint();
    }

    private void dibujarManoEnTurno(){
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof Carta) {
                remove(comp);
            }
        }

        for (Jugador jugador : jugadores) {
            Mano mano = jugador.getMano();
            ArrayList<Carta> cartasJugador = mano.getCartas();
            int y = (4 * HEIGHT_SIZE / 5) - cartasJugador.getFirst().getHeight() - HEIGHT_SIZE / 18;

            for (int i = 0, pos = 0; i < cartasJugador.size(); i++) {
                Carta carta = cartasJugador.get(i);
                if (carta.esVisible()) {
                    carta.setLocation((pos + 3) * WIDTH_SIZE / 10 - carta.getWidth() / 2, y);
                    add(carta);
                    carta.setVisible(true);
                    pos++;
                }
            }
        }

        revalidate();
        repaint();
    }

    public void MostrarManoEnTurno(){
        for (Jugador jugador : jugadores) {
            jugador.getMano().mostrarCartas();
        }
        jugadores.get(turnoJugador).getMano().mostrarCartas();
        dibujarTablero(nJugadores - jugadoresRetirados.size());
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


}