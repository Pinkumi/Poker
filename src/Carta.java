import javax.swing.*;
import java.awt.*;

public class Carta extends JLabel {
    //clase carta
    private int valor;
    private String figura;
    private int width;
    private int height;
    private int xPos;
    private int yPos;
    private JLabel image;

    public Carta(int valor, String figura) {
        this.valor = valor;
        this.figura = figura;
        this.width = 200;
        this.height = 300;
        this.xPos = 0;
        this.yPos = 0;
        image = new JLabel();

        setOpaque(true);
        setBackground(Color.WHITE);
        setBounds(xPos, yPos, width, height);
        definirIcon();
        add(image);
    }
    private void definirIcon() {
        image.setIcon(encontrarIcon());
        if (valor >= 11 && valor <= 13) {
            image.setBounds((width - (width-(width/2)))/2, (height - (height-(height/3)))/2, width-(width/2), height-(height/3));
        }else{
            image.setBounds((width - (width-(width/2)))/2, (height - (height-(height/2)))/2, width-(width/2), height-(height/2));
        }


    }
    private ImageIcon encontrarIcon() {
        String directorio = "";
        String figuraReal = switch (valor) {
            case 11 -> "jack";
            case 12 -> "reina";
            case 13 -> "rey";
            default -> "";
        };
        Image img;
        if (valor >= 11 && valor <= 13) {
            directorio = "images/figuras/" + figuraReal + "_" + figura + ".png";
            img = new ImageIcon(directorio).getImage().getScaledInstance(width-(width/2),height-(height/3), Image.SCALE_SMOOTH);
        }else{
            directorio = "images/palos/" + figura + ".png";
            img = new ImageIcon(directorio).getImage().getScaledInstance(width-(width/2),height-(height/2), Image.SCALE_SMOOTH);
        }
        return new ImageIcon(img);
    }

    public void changeSize(int width, int height) {
        this.width = width;
        this.height = height;
        setBounds(xPos, yPos, width, height);
        definirIcon();
        repaint();
        revalidate();

    }

    public void changePosition(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        setBounds(xPos, yPos, width, height);
    }

    public int getValor() {
        return valor;
    }

    public String getFigura() {
        return figura;
    }

    public boolean esIgualA(Carta otraCarta) {
        if (valor == otraCarta.valor) {
            return figura.equals(otraCarta.figura);
        }
        return false;
    }

    public boolean tieneElMismoValor(Carta otraCarta) {
        return valor == otraCarta.valor;
    }

    public boolean tieneMismaFigura(Carta otraCarta) {
        return figura.equals(otraCarta.figura);
    }


}
