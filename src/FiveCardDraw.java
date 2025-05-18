public class FiveCardDraw extends Poker{
    //primera modalidad de juego (subclase de Poker)
    private int nJugadores;
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