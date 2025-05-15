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
    private JLabel valorSupLabel;
    private JLabel valorInfLabel;
    private boolean esVisible;

    public Carta(int valor, String figura) {
        this.valor = valor;
        this.figura = figura;
        this.width = 200;
        this.height = 300;
        this.xPos = 0;
        this.yPos = 0;
        this.esVisible=true;
        image = new JLabel();
        valorSupLabel = new JLabel(); valorInfLabel = new JLabel();


        setOpaque(true);
        setBackground(Color.WHITE);
        setBounds(xPos, yPos, width, height);
        definirIcon();
        add(image);
        add(valorSupLabel);
        add(valorInfLabel);
        setBorder(BorderFactory.createEtchedBorder());
    }
    private void definirIcon() {
        image.setIcon(encontrarIcon());
        if (valor >= 11 && valor <= 13) {
            image.setBounds((width - (width-(width/2)))/2, (height - (height-(height/3)))/2, width-(width/2), height-(height/3));
        }else{
            image.setBounds((width - (width-(width/2)))/2, (height - (height-(height/2)))/2, width-(width/2), height-(height/2));
        }
        Image img = new ImageIcon("images/carta_trasera.png").getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        setDisabledIcon(new ImageIcon(img));
        String figuraEmoji = switch (figura) {
            case "corazon" -> "🖤";
            case "trebol" -> "🍀";
            case "pica" -> "♠";
            case "diamante" -> "♦";
            default -> "";
        };
        valorSupLabel.setText(String.valueOf(valor)+figuraEmoji); valorInfLabel.setText(String.valueOf(valor)+figuraEmoji);
        valorSupLabel.setFont(new Font("SansSerif", Font.PLAIN, width/9));
        valorInfLabel.setFont(new Font("SansSerif", Font.PLAIN, width/9));
        valorSupLabel.setBounds(0,0, (width - (width-(width/2)))/2, (height - (height-(height/3)))/2);
        valorInfLabel.setBounds(width - ((width - (width-(width/2)))/2), height -((height - (height-(height/3)))/2) , (width - (width-(width/2)))/2, (height - (height-(height/3)))/2);
    }
    public void voltear() {
        if(esVisible){
            setEnabled(false);
            image.setVisible(false);
            valorSupLabel.setVisible(false);
            valorInfLabel.setVisible(false);
        }else{
            setEnabled(true);
            image.setVisible(true);
            valorSupLabel.setVisible(true);
            valorInfLabel.setVisible(true);
        }
        esVisible = !esVisible;
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