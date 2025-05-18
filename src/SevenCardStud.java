import javax.swing.*;
import java.awt.*;

public class SevenCardStud extends Poker{
    //segunda modalidad de juego (subclase de Poker)
    private int nJugadores;
    private JButton completeButton;
    public SevenCardStud(int nJugadores){
        this.nJugadores = nJugadores;
        super(nJugadores);
        setTitle("Seven Card Stud");
        completeButton = new JButton();
        completeButton.setIcon(new ImageIcon(new ImageIcon("images/botones/complete.png").getImage().getScaledInstance(WIDTH_SIZE/10, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));
        completeButton.setContentAreaFilled(false);
        completeButton.setBorderPainted(false);
        completeButton.setBounds((WIDTH_SIZE/6)-WIDTH_SIZE/20,((HEIGHT_SIZE/5)/2) - (HEIGHT_SIZE/20),WIDTH_SIZE/10, HEIGHT_SIZE/15);

        labelBotones.add(completeButton);
        completeButton.setDisabledIcon(new ImageIcon(new ImageIcon("images/botones/completeDisable.png").getImage().getScaledInstance(WIDTH_SIZE/10, HEIGHT_SIZE/15, Image.SCALE_SMOOTH)));

//        completeButton.setEnabled(false);
//        raiseButton.setEnabled(false);
//        callButton.setEnabled(false);
    }

    @Override
    protected void repartirCartas() {

    }

}