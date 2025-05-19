import javax.swing.*;
import java.awt.*;

public class FiveCardDraw extends Poker{
    //primera modalidad de juego (subclase de Poker)
    private int nJugadores;
    private JButton checkButton;
    private JButton betButton;
    public FiveCardDraw(int nJugadores){
        this.nJugadores = nJugadores;
        super(nJugadores);
        setTitle("Five Card Draw");

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


        repartirCartas();

    }
    @Override
    protected void repartirCartas(){
        baraja.mezclarCartas();
        for(int i = 0; i < nJugadores; i++){
            jugadores.add(new Jugador(i,new Mano(baraja.extraerCartas(5))));
            jugadores.get(i).agregarFichas(cantFichasIniciales);
        }

    }

}