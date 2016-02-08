/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author rae
 */
public abstract class PanelEditorImagen extends JPanel{
    
    public abstract void poner_imagen_izq(BufferedImage imagen);
    public abstract void poner_imagen_der(BufferedImage imagen);
    
    
}
