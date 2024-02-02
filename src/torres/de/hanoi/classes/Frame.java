package torres.de.hanoi.classes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
/** @author Grupo 1 Cuarto año **/
public class Frame extends JFrame {
    protected Torre TorreA;
    protected Torre TorreB;
    protected Torre TorreC;
    protected Fondo contenedorDeTorres;
    protected Component discoActivo =null;
    protected int posDisco;
    protected int movimientos;
    protected int discosTolates;
    protected JLabel labelMovimientos = new JLabel("Movimientos: 0");
    protected JLabel labelMovimientosMin = new JLabel("Movimientos Minimos:" + movimientosMinimos(3));
    protected JLabel juegoCompleto = new JLabel();
    public boolean reset = false;
    public Frame(String titulo) {
        this.TorreA = new Torre("src/imgs/torre.png");
        this.TorreA.setName("TorreA");
        this.TorreB = new Torre("src/imgs/torre.png");
        this.TorreB.setName("TorreB");
        this.TorreC = new Torre("src/imgs/torre.png");
        this.TorreC.setName("TorreC");
        this.discosTolates = 3;
        generarDiscos(3);
        this.contenedorDeTorres = new Fondo("src/imgs/torre.jpg");
        Fondo contenedorControls = new Fondo("src/imgs/fondo.jpg");
        contenedorControls.setLayout(null);
        contenedorDeTorres.setBounds(0, 0, 1050, 400);
        contenedorControls.setBounds(0, 400, 1050, 300);
        
        JLabel logo = new JLabel();
        logo.setSize(269, 100);
        logo.setIcon(new ImageIcon("src/imgs/logo.png"));
        logo.setLocation(15, 150);
        this.juegoCompleto.setLocation(250, 100);
        this.juegoCompleto.setSize(400, 100);
        this.juegoCompleto.setForeground(Color.yellow);
        this.juegoCompleto.setFont(new Font("Comic Sans", Font.PLAIN, 40));
        this.juegoCompleto.setName("juegoCompleto");
        this.labelMovimientosMin.setLocation(150, 20);
        this.labelMovimientosMin.setSize(300, 25);
        this.labelMovimientosMin.setForeground(Color.white);
        this.labelMovimientosMin.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        this.labelMovimientosMin.setName("labelMovimientosMin");
        this.labelMovimientos.setLocation(150, 50);
        this.labelMovimientos.setSize(300, 25);
        this.labelMovimientos.setForeground(Color.white);
        this.labelMovimientos.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        this.labelMovimientos.setName("labelMovimientos");
        JButton instruccion = new JButton();
        instruccion.setText("Instrucciones");
        instruccion.setBackground(Color.white);
        instruccion.setBounds(920, 225, 125, 25);
        instruccion.addActionListener(event -> {
           JFrame info = new JFrame();
           JLabel text = new JLabel();
           text.setText("<html><span style='font-size:18px;'>Reglas para el juego Torres de Hanoi<br><br>Regla 1 - Solo se puede mover el disco de encima<br><br>Regla 2 - No se puede mover un disco sobre un disco menor<br><br>Regla 3 - El juego finaliza solo cuando la torre de la derecha tenga los todos los discos</span></html>");
           info.add(text);
           info.setTitle("Reglas del juego");
           info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           info.setSize(500, 500);
           info.setLocationRelativeTo(null);
           info.setVisible(true);
        });
        JLabel labelAros = new JLabel("Aros:");
        labelAros.setLocation(10, 20);
        labelAros.setSize(50, 25);
        labelAros.setForeground(Color.white);
        labelAros.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        JSpinner arosCuantos = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
        arosCuantos.setSize(50, 25);
        arosCuantos.setLocation(10, 50);
        JButton reiniciarButton = new JButton();
        reiniciarButton.setText("Reiniciar");
        reiniciarButton.setBounds(10, 100, 100, 25);
        reiniciarButton.setBackground(Color.white);
        reiniciarButton.addActionListener(event -> {
            this.discosTolates = (int) arosCuantos.getValue();
            resetJuego();
        });
        contenedorControls.add(reiniciarButton);
        contenedorControls.add(arosCuantos);
        contenedorControls.add(labelAros);
        contenedorControls.add(this.labelMovimientosMin);
        contenedorControls.add(this.labelMovimientos);
        contenedorControls.add(this.juegoCompleto);
        contenedorControls.add(instruccion);
        contenedorControls.add(logo);
        contenedorDeTorres.setMaximumSize(new Dimension(1050, 400));
        contenedorDeTorres.setLayout(new GridLayout(1, 3));
        contenedorDeTorres.setName("contenedorDeTorres");
        contenedorControls.setName("contenedorControls");
        contenedorDeTorres.add(this.TorreA);
        contenedorDeTorres.add(this.TorreB);
        contenedorDeTorres.add(this.TorreC);
        this.setName("Main");  
        this.setLayout(null);
        this.add(contenedorDeTorres);
        this.add(contenedorControls);
        this.setTitle(titulo);
        this.setMinimumSize(new Dimension(1067, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.repaint();
        this.setResizable(false);
        ArrayList<Integer> discos = this.TorreA.getDiscos();
        Collections.reverse(discos);
        this.TorreA.setDiscos(discos);
        discos = null;
    }
    public int getDiscosTolates() {
        return discosTolates;
    }
    private int movimientosMinimos(int numDisco) {
        return (int) Math.pow(2, (numDisco)) - 1;
    }
    public int getPosDisco() {
        return posDisco;
    }
    public void setPosDisco(int posDisco) {
        this.posDisco = posDisco;
    }
    public void setDiscoActivo(Component discoActivo) {
        this.discoActivo = discoActivo;
    }
    public Component getDiscoActivo() {
        return discoActivo;
    }
    public void printTorres() {
        System.out.println(this.TorreA.getDiscos());
        System.out.println(this.TorreB.getDiscos());
        System.out.println(this.TorreC.getDiscos());
    }
    public void moverDisco(Disco disco, Torre torre) {
        if (torre.getComponentCount() == 0) {
            disco.setLocation(0, calcularY(0));
            this.movimientos++;
            labelMovimientos.setText("Movimientos: " + this.movimientos);
            torre.add(disco);
            this.TorreA.updateUI();
            this.TorreB.updateUI();
            this.TorreC.updateUI();
            agregarDisco(torre, disco.getSizeDisco());
        } else {
            if (torre.getDiscos().get(torre.getDiscos().size() - 1) > disco.getSizeDisco()) {
                disco.setLocation(0, calcularY(torre.getComponentCount()));
                this.movimientos++;
                labelMovimientos.setText("Movimientos: " + this.movimientos);
                torre.add(disco);
                this.TorreA.updateUI();
                this.TorreB.updateUI();
                this.TorreC.updateUI();
                agregarDisco(torre, disco.getSizeDisco());
            } else {
                JOptionPane.showMessageDialog(null, "Movimiento invalido");
                disco.setLocation(0, this.posDisco);
                this.movimientos++;
                labelMovimientos.setText("Movimientos: " + this.movimientos);
            }
        }
            printTorres();
        this.discoActivo = null;
    }
    private void agregarDisco(Torre torre, int num) {
        ArrayList<Integer> discos = torre.getDiscos();
        discos.add(num);
        torre.setDiscos(discos);
    }
    private void generarDiscos(int cuantos) {
        for (int i = 0; i < cuantos; i++) {
            insertarDiscos(new Disco("src/imgs/d" + (i + 1) + ".png", i, (((i + 1) * 21) + (350 - ((21) * cuantos)))), (i + 1));
        }
    }
    
    
    private int calcularY(int cuantos) {
        return 350 - (cuantos * (21));
    }
    
   
    private void insertarDiscos(Disco disco, int index) {
        disco.setName("disco" + index);
        this.TorreA.add(disco);
        ArrayList<Integer> discos = this.TorreA.getDiscos();
        discos.add(disco.getSizeDisco());
        this.TorreA.setDiscos(discos);
    }
    public void resetJuego() {
         contenedorDeTorres.setImage("src/imgs/torre.jpg");
        this.TorreA.setDiscos(new ArrayList<>());
        this.TorreB.setDiscos(new ArrayList<>());
        this.TorreC.setDiscos(new ArrayList<>());
        this.TorreA.removeAll();
        this.TorreB.removeAll();
        this.TorreC.removeAll();
        generarDiscos(this.discosTolates);
        ArrayList<Integer> discos = this.TorreA.getDiscos();
        Collections.reverse(discos);
        this.TorreA.setDiscos(discos);
        discos = null;
        this.labelMovimientosMin.setText("Movimientos Minimos:" + movimientosMinimos(this.discosTolates));
        this.labelMovimientos.setText("Movimientos: 0");
        this.juegoCompleto.setText("");
        this.movimientos = 0;
        this.reset = false;
        this.discoActivo =null;
    }
    public void juegoCompletado() {
        if (movimientosMinimos(this.discosTolates) == this.movimientos) {
            this.juegoCompleto.setText("Excelente!!!");
            this.reset = true;
            this.contenedorDeTorres.setImage("src/imgs/torre-1.jpg");
        } else {
            this.juegoCompleto.setText("Muy bien");
            this.reset = true;
        }
    }
}
