public class SevenCardStud extends Poker{
    //segunda modalidad de juego (subclase de Poker)
    private int nJugadores;
    public SevenCardStud(int nJugadores){
        this.nJugadores = nJugadores;
        super(nJugadores);
        setTitle("Seven Card Stud");
    }

    @Override
    protected void repartirCartas() {

    }

}