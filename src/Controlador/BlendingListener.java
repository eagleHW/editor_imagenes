/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelBlending;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rae
 */
public class BlendingListener extends AutoaceptarListener implements MouseListener{

    private Filtros filter = new Filtros();
    private PanelBlending panel_blending;
    private int user_selection; // Para verificar si el usuario escogio un archivo. 
    
    public BlendingListener(PanelBlending panel_blending, Interfaz ventana_principal, int valor_inicial){
        
        this.panel_blending = panel_blending;
        super.panel_editor = panel_blending;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
        
    }
    
    @Override
    public void aplicar_filtro() {
    
        panel_editor.poner_imagen_der(filter.filtro_blending(
                  ventana_principal.getImage(), panel_blending.getBlending_img_izq_inf(), valor));
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
        JFileChooser open_file = new JFileChooser();   
        FileFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());
        open_file.removeChoosableFileFilter(open_file.getFileFilter());
        open_file.addChoosableFileFilter(imageFilter);
            
        user_selection = open_file.showOpenDialog(null);
            
         if(user_selection == JFileChooser.APPROVE_OPTION) {
            
             panel_blending.poner_imagen_izq_inf_blending(open_file.getSelectedFile());
             panel_blending.enable_aceptar();
             System.out.println(open_file.getSelectedFile());
            
         }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
