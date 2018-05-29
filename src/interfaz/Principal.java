/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import static java.awt.Color.*;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logica.*;


/**
 *
 * @author luis
 */
public class Principal extends javax.swing.JFrame {
    
    Control controlador;
    Min_Max minimax;
    String auxCaballoBlanco;
    URL urlCaballoBlanco;
    ImageIcon iconAux;
    JButton blanco;
    String auxCaballoNegro;
    URL urlCaballoNegro;
    ImageIcon iconNegro;
    JButton negro;
    
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setResizable(false);
        this.setTitle("ALPHAZERO");
        this.setLocationRelativeTo(null);
        
        //agrega el banner
        String path = "/img/AI.png";
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(url);
        banner.setIcon(icon);
        
      
        
        controlador = new Control();
        minimax = new Min_Max();
        minimax.calcularTotalPosibilidades();
        cargarTablero();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablero = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        banner = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        items = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jugar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablero.setLayout(new java.awt.GridLayout(6, 6));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(banner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jLabel1.setText("# de Items");

        jugar.setText("JUGAR!");
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(items))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(0, 16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jugar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jugar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        // TODO add your handling code here:       
        try {
            int numeroItems = Integer.parseInt(items.getText());
            
            if (numeroItems > 33) {
                JOptionPane.showMessageDialog(null,"No puede ingresar tantos items","Error", JOptionPane.ERROR_MESSAGE);
                items.setText(null);
            }
           else if ((numeroItems % 2) == 0) {
                JOptionPane.showMessageDialog(null,"Ingrese un número impar Ej: "+ (numeroItems-1),"Error", JOptionPane.WARNING_MESSAGE);
                items.setText(null);
           }
           else{
               int[] juego = controlador.generarJuego(numeroItems);
               controlador.setCaballoMaquina(0);
               controlador.setCaballoOponente(juego[1]);
               ArrayList <Integer> manzanas = new ArrayList<>();
                for (int i = 2; i < juego.length; i++) {
                   manzanas.add(juego[i]);
                }
                ArrayList <Integer> x = new ArrayList<>();
                x.add(8);
                controlador.setManzanas(x);
                int[] z = {0,juego[1],8};
                cargarJuego(z); 
                int movimientoMaquina = controlador.moverMaquina();
                System.out.println("La maquina se debe mover a: "+movimientoMaquina);
                //habilitarBotones(30);
                jugar.setEnabled(false);
           }
        } catch (HeadlessException | NumberFormatException e) {
            //System.out.println(e);
            JOptionPane.showMessageDialog(null,"Ingrese un número","Error", JOptionPane.ERROR_MESSAGE);
            items.setText(null);
        }
    }//GEN-LAST:event_jugarActionPerformed

    public void juegoMaquina(int pos, int posActual){
        
        
        switch (posActual-pos) {
            //dos arriba uno derecha
            case -11:
                tablero.remove(posActual);
                this.repaint();
                tablero.add(blanco, posActual-6);
                tablero.remove(posActual-6);
                                try{
                    Thread.sleep(500);
                } catch (Exception e) {}      
                this.paintAll(this.getGraphics());
                tablero.add(blanco,posActual-6);
                
                break;
                
            //uno arriba - dos a la derecha
            case -4:
                break; 
                
            //uno abajo - dos a la derecha
            case 8:
                break;
                
            // 2 abajo - 1 derecha   
            case 13:
                break;
                
            //2 abajo - 1 izquierda    
            case 11:
                break;
            
            //1 abajo - 2 izquierda
            case 4:
                break;
                
            //1 arriba - 2 izquierda
            case -8:
                break;
                
            //2 arriba - 1 izquierda
            case -13:
                break;
            default:
                break;
        }
        
    }
    
    public void initJuego(){
        
    }
    
    
    public void cargarJuego(int[] juego){
        
          //definicion de variables para las imagenes de los caballos
        String auxCaballoBlanco = "/img/0.png";
        URL urlCaballoBlanco = this.getClass().getResource(auxCaballoBlanco);
        ImageIcon iconAux = new ImageIcon(urlCaballoBlanco);
        JButton blanco = new JButton();
        blanco.setPreferredSize(new Dimension(50, 50));
        blanco.setIcon(iconAux);
        
        String auxCaballoNegro = "/img/1.png";
        URL urlCaballoNegro = this.getClass().getResource(auxCaballoNegro);
        ImageIcon iconNegro = new ImageIcon(urlCaballoNegro);
        JButton negro = new JButton();
        negro.setPreferredSize(new Dimension(50, 50));
        negro.setIcon(iconNegro);
        
        tablero.remove(juego[0]);
        tablero.add(blanco, juego[0]);
        tablero.remove(juego[1]);
        tablero.add(negro,juego[1]);
        
        
        String manzana = "/img/manzana.png";
        URL urlManzana = this.getClass().getResource(manzana);
        ImageIcon iconManzana = new ImageIcon(urlManzana);
        
        
        this.paintAll(this.getGraphics());
        
        for (int i = 2; i < juego.length; i++) {
            JButton botonManzana = new JButton();
            botonManzana.setPreferredSize(new Dimension(50, 50));
            botonManzana.setIcon(iconManzana);
            
            tablero.remove(juego[i]);
            tablero.add(botonManzana, juego[i]);
        }
        
        
        this.paintAll(this.getGraphics());
        
    }
    
//public void habilitarBotones(int pos){
//    //ArrayList<Integer> posibles = minimax.getMovimientos(juego[1]);
//    for (int i = 0; i < 36; i++) {
//        if (posibles.contains(i)) {
//            tablero.getComponent(i).setBackground(LIGHT_GRAY);
//            tablero.getComponent(i).setEnabled(true);
//        }
//    }
//}
    
public void cargarTablero(){
    
    for (int i = 0; i < 6; i++) {
        
        for (int j = 0; j < 6; j++) {
            
            JButton auxBoton = new JButton();
            auxBoton.setPreferredSize( new Dimension (50,50));
            auxBoton.setEnabled(false);

            if ((((i+j) % 2) == 0))
                auxBoton.setBackground(WHITE);
            else
                auxBoton.setBackground(BLACK);
            tablero.add(auxBoton);
            auxBoton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    
                }
            });
        }
    }   
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JTextField items;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jugar;
    private javax.swing.JPanel tablero;
    // End of variables declaration//GEN-END:variables
}
