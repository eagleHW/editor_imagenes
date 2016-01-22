/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.Interfaz;
import Vista.VentanaRGB;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author rae
 */
public class WarholMouseListener implements MouseListener {

    Interfaz ventana_principal;
    
    public WarholMouseListener(Interfaz ventana_principal){
        this.ventana_principal = ventana_principal;
    }
    
    // Hacer un listener para cada uno
    @Override
    public void mouseClicked(MouseEvent e) {
        
        int num_ventana = Integer.parseInt(((JLabel)e.getSource()).getName());
        
        new VentanaRGB(ventana_principal,num_ventana);
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
  
    }

    @Override
    public void mouseReleased(MouseEvent e) { 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
