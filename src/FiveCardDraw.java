public class FiveCardDraw extends Poker{
//primera modalidad de juego (subclase de Poker)
    public FiveCardDraw(){
        super();
        setTitle("Five Card Draw");
    }
    @Override
    protected void repartirCartas(){
        System.out.println("Repartiendo cartas en Poker Texas...");
    }

}
