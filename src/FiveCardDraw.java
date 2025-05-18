import javax.swing.*;

public class FiveCardDraw extends Poker{
    //primera modalidad de juego (subclase de Poker)
    private int nJugadores;
    private JButton checkButton;
    private JButton betButton;
    public FiveCardDraw(int nJugadores){
        this.nJugadores = nJugadores;
        super(nJugadores);
        setTitle("Five Card Draw");
    }
    @Override
    protected void repartirCartas(){
        System.out.println("Repartiendo cartas");
    }

}