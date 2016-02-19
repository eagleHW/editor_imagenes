
package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelSemitono;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author rae
 */
public class SemitonoListener implements ChangeListener, ActionListener {
 
    private Interfaz ventana_principal;
    private PanelSemitono panel_semitono;
    private Filtros filter = new Filtros();
            
    private int reduccion = 100;
    private int ventana = 3;
    
    public SemitonoListener(PanelSemitono panel_semitono, Interfaz ventana_principal){
        
        this.panel_semitono = panel_semitono;
        this.ventana_principal = ventana_principal;        
    }
    
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
        
        switch (spinner.getName()) {
            case "Reduccion":
                reduccion =  spinner_number_model.getNumber().intValue();
                panel_semitono.cambiar_imagen_izq(filter.filtro_reduccion_porcentaje(panel_semitono.get_image_der_inf(), reduccion));
                break; 
            case "Ventana" :
                ventana  = spinner_number_model.getNumber().intValue();
                break;
            default:
                System.out.println("No cayo en ninguna opcion");
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        panel_semitono.poner_imagen_der(filter.filtro_semitono(ventana_principal.getImage(), reduccion, ventana,
                        panel_semitono.get_coleccion_imagen()));
        
    
    }
    
}
