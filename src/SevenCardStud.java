import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SevenCardStud extends Poker{
    //segunda modalidad de juego (subclase de Poker)
    private int nJugadores;
    private int bote;
    private JButton completeButton;
    private JButton callButton;
    private ArrayList<Jugador> jugadoresRetirados = new ArrayList<>();
    public SevenCardStud(int nJugadores){

        super(nJugadores);
        this.nJugadores = nJugadores;
        bote = 10;

        setTitle("Seven Card Stud");

        completeButton = new JButton();
        completeButton.setIcon(new ImageIcon(new ImageIcon("images/botones/complete.png").getImage().getScaledInstance(WIDTH_SIZE/10, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        completeButton.setContentAreaFilled(false);
        completeButton.setBorderPainted(false);
        completeButton.setBounds((WIDTH_SIZE/6)-WIDTH_SIZE/20,((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/20),WIDTH_SIZE/10, HEIGHT_SIZE/15);
        labelBotones.add(completeButton);
        completeButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/completeDisable.png").getImage().getScaledInstance(WIDTH_SIZE/10, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

        String entrada = JOptionPane.showInputDialog(null,"Ingrese la cantidad de fichas iniciales:", "Cantidad de Fichas", JOptionPane.QUESTION_MESSAGE);
        if(entrada == null || entrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingreso nada, la cantidad de fichas iniciales es de 500.");
        }else{
            cantFichasIniciales = Integer.parseInt(entrada);
        }

//        completeButton.setEnabled(false);
//        raiseButton.setEnabled(false);
//        callButton.setEnabled(false);

        anteMano(10);
        repartirCartas();
        cuartaStreet();
        quintaStreet();
        sextaStreet();
        septimaStreet();


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
        rondaApuestas();
    }

    public void quintaStreet() {
        System.out.println("------ Quinta Street ------");
        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(true));
            }
        }
        rondaApuestas();
    }

    public void sextaStreet() {
        System.out.println("------ Sexta Street ------");
        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(true));
            }
        }
        rondaApuestas();
    }

    public void septimaStreet() {
        System.out.println("------ Séptima Street ------ ");

        for (Jugador jugador : jugadores) {
            if (!jugadoresRetirados.contains(jugador)) {
                jugador.tomarCarta(baraja.extraerCarta(false));
            }
        }
        Jugador ganador = null;
        int mejorPuntaje = -1;

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
    }


    public void rondaApuestas() {

        for (Jugador jugador : jugadores) {
            if (jugadoresRetirados.contains(jugador) || jugador.getFichas() <= 0){
                continue;
            };

            String mensaje = jugador + "\n"
                    + "Fichas disponibles: " + jugador.getFichas() + "\n"
                    + "Apuesta actual: " + apuestaActual + "\nBote: " + bote;

            System.out.println("Cartas en mano:");
            System.out.println(jugador.getMano().toString());

            System.out.println(mensaje);
            String opciones = " 1. Igualar \n 2. Subir \n 3. Retirase";
            System.out.println(opciones);
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt()-1;

            switch (opcion) {
                case 0: // Igualar

                    if (jugador.getFichas() >= apuestaActual) {
                        bote += apuestaActual;

                    } else {
                        System.out.println("No puedes igualar");
                        jugadoresRetirados.add(jugador);

                    }
                    break;

                case 1: // Subir
                    Scanner scn2 = new Scanner(System.in);
                    System.out.println("Cuánto deseas subir: ");
                    String entrada = scn2.nextLine();

                    if (entrada != null && !entrada.isEmpty()) {

                        int subida = Integer.parseInt(entrada);
                        int total = apuestaActual + subida;

                        if (jugador.getFichas() >= total) {
                            apuestaActual = total;
                            jugador.colocarFichas(total);
                            bote += total;

                        } else {
                            System.out.println("No tienes suficientes fichas");
                            jugadoresRetirados.add(jugador);
                        }
                    }
                    break;

                case 2: // Retirarse
                default:
                    jugadoresRetirados.add(jugador);
                    System.out.println(jugador + " retirado");
                    break;
            }
        }

        System.out.println("Pozo acumulado: " + bote + " fichas");
    }

    public void anteMano(int ante){
        System.out.println("Ante Mano de apuestas: ");
        bote = ante;

        for(Jugador jugador : jugadores){
            if(jugador.getFichas() >= ante){
                jugador.colocarFichas(ante);
                bote += ante;
                System.out.println(jugador + " coloca " + ante);

            }else{
                System.out.println(jugador + "sin fichas necesarias");
            }
        }
        System.out.println("Pozo inicial: " + bote + " fichas");

    }


    @Override
    protected void dibujarTablero() {

    }

}