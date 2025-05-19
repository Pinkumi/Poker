import javax.swing.*;
import java.awt.*;

public class SevenCardStud extends Poker{
    //segunda modalidad de juego (subclase de Poker)
    private int nJugadores;
    private JButton completeButton;
    public SevenCardStud(int nJugadores){
        super(nJugadores);
        this.nJugadores = nJugadores;
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
    }

    @Override
    protected void repartirCartas() {

    }
    @Override
    protected void dibujarTablero() {

    }

}