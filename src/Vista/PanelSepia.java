
package Vista;

import Controlador.ATTListener;
import Controlador.SepiaListener;
import ManipulacionImagenes.Filtros;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author rae
 */
public class PanelSepia extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private SpinnerNumberModel sepia_spinner_model;
    private String mensaje_label;
    private SepiaListener listener;
    private PanelAutoaceptar panel_autoaceptar;
    
    private final int VALOR_INICIAL = 1;
    
    public PanelSepia(Interfaz ventana_principal){
   
        this.setLayout(new GridLayout(1,1,0,0));
        
        this.ventana_principal = ventana_principal;
        
        this.ventana_principal = ventana_principal;
        this.sepia_spinner_model = new SpinnerNumberModel(VALOR_INICIAL,1,255,1);
        this.mensaje_label = "Peso : ";
        this.listener = new SepiaListener(this,ventana_principal,VALOR_INICIAL);
        
        this.panel_autoaceptar = new PanelAutoaceptar(ventana_principal, sepia_spinner_model, mensaje_label, listener);
        this.add(panel_autoaceptar);
        
    }
    
   @Override
    public void poner_imagen_der(BufferedImage imagen) {  
       
        panel_autoaceptar.poner_imagen_der(imagen);
        
    }
    
    @Override
    public void poner_imagen_izq(BufferedImage imagen) {

        panel_autoaceptar.poner_imagen_izq(imagen);
        
    }
    
    public void boton_disponible(boolean disponible){
        
        panel_autoaceptar.boton_disponible(disponible);
     
     }
   
    
}
