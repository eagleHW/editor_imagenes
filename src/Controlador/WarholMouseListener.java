/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.Interfaz;
import Vista.PanelWarhol;
import Vista.VentanaRGB;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author rae
 */
public class WarholMouseListener implements MouseListener {

    PanelWarhol panel_warhol;
    Interfaz ventana_principal;
    
    public WarholMouseListener(PanelWarhol panel_warhol,Interfaz ventana_principal){
        this.panel_warhol = panel_warhol;
        this.ventana_principal = ventana_principal;
    }
    
    // Hacer un listener para cada uno
    @Override
    public void mouseClicked(MouseEvent e) {
        
        int num_ventana = Integer.parseInt(((JLabel)e.getSource()).getName()); 
        BufferedImage imagen = (BufferedImage)((ImageIcon)(((JLabel)e.getSource()).getIcon())).getImage(); 
 
        new VentanaRGB(panel_warhol,imagen,num_ventana);
       
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
