package torres.de.hanoi.classes;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Fondo extends JPanel {
    private Image bg;
    private String imagen;
    public Fondo(String imagen) {
        this.imagen = imagen; 
    }
    public void setImage(String  imagen)
        {
          this.imagen = imagen; 
          repaint();
        }
    @Override
    public void paintComponent(Graphics g) {
        int width = this.getSize().width;
        int height = this.getSize().height;
        if (this.bg != null) {
            g.drawImage(this.bg, 0, 0, width, height, null);
        }
        super.paintComponent(g);
        this.setOpaque(false);
        this.bg = new ImageIcon(this.imagen).getImage();
        repaint();
    }
}
