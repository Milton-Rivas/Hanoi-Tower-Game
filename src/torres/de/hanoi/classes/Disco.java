package torres.de.hanoi.classes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Disco extends JLabel {
    protected int sizeDisco;
    public Disco(String img, int size, int poy) {
        ImageIcon image = new ImageIcon(img);
        this.setIcon(image);
        image = null;
        this.setOpaque(false);
        this.setBounds(0, poy, 350, 21);
        this.setHorizontalAlignment(CENTER);
        this.setVisible(true);
        this.sizeDisco = size;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Frame main = ((Frame) e.getComponent().getParent().getParent().getParent().getParent().getParent().getParent());
                if (main.reset){
                    main.resetJuego();
                    return;
                }
                Torre torre = ((Torre) e.getComponent().getParent());
                if (((Disco) e.getComponent()).getSizeDisco() == torre.getDiscos().get(torre.getDiscos().size() - 1)) {
                    if (main.getDiscoActivo() != null) {
                       return;
                    }
                    main.setPosDisco((int) e.getComponent().getY());
                    e.getComponent().setLocation(0, 20);
                    main.setDiscoActivo(e.getComponent());
                }
            }
        });
    }
    public int getSizeDisco() {
        return sizeDisco;
    }
}