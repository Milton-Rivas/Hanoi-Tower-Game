package torres.de.hanoi.classes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Torre extends JPanel {

    private Image bg;
    private String imagen;
    protected ArrayList<Integer> discos;
    public Torre(String imagen) {
        this.imagen = imagen;
        this.discos = new ArrayList<>();
        this.setLayout(null);
        this.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                if (e.getComponent().getName().equals("TorreC")) {
                    Frame main = ((Frame) e.getComponent().getParent().getParent().getParent().getParent().getParent());
                    if (main.getDiscosTolates() == ((Torre) e.getComponent()).getComponentCount()) {
                        main.juegoCompletado();
                    }
                }
            }
            @Override
            public void componentRemoved(ContainerEvent e) {

                if (((Torre) e.getComponent()).getDiscos().isEmpty()) {
                    return;
                }
                ArrayList discos = ((Torre) e.getComponent()).getDiscos();
                discos.remove(discos.size() - 1);
                ((Torre) e.getComponent()).setDiscos(discos);
                discos = null;

            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Frame main = ((Frame) e.getComponent().getParent().getParent().getParent().getParent().getParent());
                if (main.getDiscoActivo() == null) {
                    return;
                }
                if (e.getComponent().getName().equals(main.getDiscoActivo().getParent().getName())) {
                    main.getDiscoActivo().setLocation(0, (int) main.getPosDisco());
                    main.setDiscoActivo(null);
                } else {
                    main.moverDisco((Disco) main.getDiscoActivo(), (Torre) e.getComponent());
                }
            }
        });
    }
    public void setDiscos(ArrayList<Integer> discos) {
        this.discos = discos;
    }
    public ArrayList<Integer> getDiscos() {
        return discos;
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
