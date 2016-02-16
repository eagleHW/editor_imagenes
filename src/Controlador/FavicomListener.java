/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.BibliotecaGrafica;
import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelFavicom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rae
 */
public class FavicomListener implements ChangeListener, ActionListener , MouseListener {

    private PanelFavicom panel_favicom;
    private Filtros filter = new Filtros();
    private int user_selection; // Para verificar si el usuario escogio un archivo. 
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
            
    private int reduccion = 100;
    private int transparencia = 0; // 100 por si primero reduce por lo que el valor de transparencia todavia no se actualiza
    
    private int posc_x = 0;
    private int posc_y = 0;
    
    public FavicomListener(PanelFavicom panel_favicom, Interfaz ventana_principal){
    
        this.panel_favicom = panel_favicom;
        this.ventana_principal = ventana_principal;
        
    }
   
    @Override
    public void stateChanged(ChangeEvent e) {
   
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
        BufferedImage fondo;
        BufferedImage temporal;
        
        switch (spinner.getName()) {
            case "Reduccion":
    
                    reduccion =  spinner_number_model.getNumber().intValue();
                    temporal = filter.filtro_reduccion_porcentaje(panel_favicom.get_inf_image(),reduccion);
                    fondo = new BufferedImage(temporal.getWidth(),temporal.getHeight(), BufferedImage.TYPE_INT_RGB);
                    bg.pasar_blanco(fondo);
                    temporal = filter.filtro_blending(fondo, temporal , transparencia);
                    panel_favicom.cambiar_imagen_izq(temporal);
           
                    break;
            case "Transparencia":
                
                    transparencia = spinner_number_model.getNumber().intValue();
                    temporal = filter.filtro_reduccion_porcentaje(panel_favicom.get_inf_image(), reduccion);
                    fondo = new BufferedImage(temporal.getWidth(),temporal.getHeight(), BufferedImage.TYPE_INT_RGB);
                    bg.pasar_blanco(fondo);
                    temporal = filter.filtro_blending(fondo, temporal, transparencia);
                    panel_favicom.cambiar_imagen_izq(temporal);
             
                break;
                
            case "X":
                
                    posc_x = spinner_number_model.getNumber().intValue();
                
                break;
            
            case "Y":
                
                    posc_y = spinner_number_model.getNumber().intValue();
                
                break;
            default:
                System.out.println("No cayo en ningun caso");
        }
            
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        panel_favicom.poner_imagen_der( filter.filtro_blending_favicom(ventana_principal.getImage(), 
                                    panel_favicom.get_inf_image(), posc_x , posc_y , transparencia, reduccion));
               
                
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
            
             panel_favicom.poner_imagen_izq_inf(open_file.getSelectedFile());
             panel_favicom.enable_options();
            
         }
        
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
