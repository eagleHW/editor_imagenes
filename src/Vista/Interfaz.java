
package Vista;

import Controlador.ArchivoListener;
import Controlador.ConvolucionListener;
import Controlador.FiltrosListener;
import Controlador.FotomosaicoMenuListener;
import Controlador.GrisesListener;
import Controlador.MaximoListener;
import Controlador.MinimoListener;
import Controlador.OleoListener;
import Controlador.ReduccionListener;
import Controlador.RotacionListener;
import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author rae
 */
public class Interfaz extends JFrame {
    
    private File file_imagen = null;
    private BufferedImage imagen = null;
    private BufferedImage imagen_guardar = null;
    
    private BibliotecaGrafica bg = new BibliotecaGrafica();
        
    private JMenuItem itemGuardar;
    private JMenu menuFiltros;
    
    private PanelEditorImagen panel_principal;
            
    public Interfaz(){
    
        super("Editor gráfico");

        // Metodo que se encarga de cargar el menu
        crear_menu();
        
        // Panel al cargar la imagen
        panel_principal = new PanelBasico(this);
        //panel_principal.poner_imagen_izq(imagen);
        this.add(panel_principal);
        
        // Aributos de la ventana principal
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
        
    private void crear_menu(){
        
        // Crea barra de menu
        JMenuBar barra = new JMenuBar();
        this.setJMenuBar(barra);
       
        // Crea el menu Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');
        
        // Crea los elementos del menu Archivo
        JMenuItem itemNuevaImagen = new JMenuItem("Nueva imagen");
        this.itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemGuardar.setEnabled(false);
        
        // Agrega los elementos del menu Archivo
        menuArchivo.add(itemNuevaImagen);  
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemSalir);
     
        // Agrega los nemonicos
        itemNuevaImagen.setAccelerator(KeyStroke.getKeyStroke
                            (KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke
                            (KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        
        itemSalir.setAccelerator(KeyStroke.getKeyStroke
                            (KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        
        
        // Crea el listener para los elementos de menu Archivo
        ArchivoListener archivo_listener = new ArchivoListener(this);
        
        // Agrega un listener a los elementos de menu Archivo
        itemNuevaImagen.addActionListener(archivo_listener);
        itemGuardar.addActionListener(archivo_listener);
        itemSalir.addActionListener(archivo_listener);
        
        // Crea el menu Filtro
        this.menuFiltros = new JMenu("Filtros");
        menuFiltros.setMnemonic('F');
        menuFiltros.setEnabled(false);
        
        // Crea los elementos del menu Filtros 
        JMenuItem itemAzar = new JMenuItem("Azar");
        JMenuItem itemRGB = new JMenuItem("RGB");
        JMenuItem itemBrillo = new JMenuItem("Brillo");
        JMenuItem itemMosaico = new JMenuItem("Mosaico");
        JMenuItem itemWarhol = new JMenuItem("Warhol");
        JMenuItem itemBlending = new JMenuItem("Blending");
        JMenuItem itemFavicom = new JMenuItem("Favicom");
        JMenuItem itemSepia = new JMenuItem("Sepia");
        JMenuItem itemAltoContraste = new JMenuItem("Alto Contraste");
        JMenuItem itemBlackLight = new JMenuItem("BlackLight");
        JMenuItem itemAtt = new JMenuItem("AT&T");
        JMenuItem itemSemitono = new JMenuItem("Semitono");
        JMenuItem itemLetra = new JMenuItem("Letra");
        JMenuItem itemDithering = new JMenuItem("Dithering"); 
        JMenuItem itemResampling = new JMenuItem("Resampling");
        
        // Creamos el submenu Grises
    
        JMenu menuGrises = new JMenu("Grises");
        crear_submenu_grises(menuGrises);     
        
        // Creamos el submenu Convolucion
        
        JMenu menuConvolucion = new JMenu("Convolucion");
        crear_submenu_convolucion(menuConvolucion);
        
        // Creamos el submenu Rotacion
        
        JMenu menuRotacion = new JMenu("Rotacion");
        crear_submenu_rotacion(menuRotacion);
        
        // Creamos el submenu Maximo
        
        JMenu menuMaximo = new JMenu("Maximo");
        crear_submenu_maximo(menuMaximo);
        
        // Creamos el submenu Minimo
        
        JMenu menuMinimo = new JMenu("Minimo");
        crear_submenu_minimo(menuMinimo);
        
        // Creamos el submenu Oleo
        
        JMenu menuOleo = new JMenu("Oleo");
        crear_submenu_oleo(menuOleo);
        
        // Creamos el submenu Reduccion
        
        JMenu menuReduccion = new JMenu("Reduccion");
        crear_submenu_reduccion(menuReduccion);
        
        // Cremos el submenu Fotomosaico
        
        JMenu menuFotomosaico = new JMenu("Fotomosaico");
        crear_submenu_fotomosaico(menuFotomosaico);
        
        // Agrega los elementos del menu filtro
        // incluyendo los submenus
        
        menuFiltros.add(itemAltoContraste);
        menuFiltros.add(itemAtt);
        menuFiltros.add(itemAzar);
        menuFiltros.add(itemBlackLight);
        menuFiltros.add(itemBlending);
        menuFiltros.add(itemBrillo);
        menuFiltros.add(menuConvolucion);
        menuFiltros.add(itemDithering);
        menuFiltros.add(itemFavicom);
        menuFiltros.add(menuFotomosaico);
        menuFiltros.add(menuGrises);
        menuFiltros.add(itemLetra);
        menuFiltros.add(menuMaximo);
        menuFiltros.add(menuMinimo);
        
        menuFiltros.add(itemMosaico);
        menuFiltros.add(menuOleo);
        menuFiltros.add(itemRGB);
        menuFiltros.add(menuReduccion);
        menuFiltros.add(itemResampling);
        menuFiltros.add(menuRotacion);
        menuFiltros.add(itemSemitono);
        menuFiltros.add(itemSepia);
        menuFiltros.add(itemWarhol);
        
        // Crea el listener para los filtros
        FiltrosListener filtros_listener = new FiltrosListener(this); 
       
        // Agrega un listener a los elementos de menu Filtros
        itemAzar.addActionListener(filtros_listener);
        itemRGB.addActionListener(filtros_listener);
        itemBrillo.addActionListener(filtros_listener);
        itemMosaico.addActionListener(filtros_listener);
        itemWarhol.addActionListener(filtros_listener);
        itemBlending.addActionListener(filtros_listener);
        itemFavicom.addActionListener(filtros_listener);
        itemSepia.addActionListener(filtros_listener);
        itemAltoContraste.addActionListener(filtros_listener);
        itemBlackLight.addActionListener(filtros_listener);
        itemAtt.addActionListener(filtros_listener);
        itemSemitono.addActionListener(filtros_listener);
        itemLetra.addActionListener(filtros_listener);
        itemDithering.addActionListener(filtros_listener);
        itemResampling.addActionListener(filtros_listener);
        
        // Añade los menus a la barra de menu
        barra.add(menuArchivo);
        barra.add(menuFiltros);
        
        /*
        // Para evitar estar cargando imagen cada vez que pruebo
        try {
            BufferedImage img;
            File file = new File("/home/rae/little_face_puppy.jpg");
            img = ImageIO.read(file);
            this.file_imagen = file;
            this.imagen = img;
        } catch (IOException ex) {
            System.out.println("Error al cargar imagen de prueba");
        }*/
       
    }
    
    private void crear_submenu_grises(JMenu menuGrises){
       
        GrisesListener grises_listener = new GrisesListener(this);
        
        //Crea los elementos del submenu Grises        
        JMenuItem itemGrisPromedio = new JMenuItem("Promedio");
        JMenuItem itemGrisLuminicensia = new JMenuItem("Luminicensia");
        JMenuItem itemGrisDesaturacion = new JMenuItem("Desaturación");
        JMenuItem itemGrisMaximo = new JMenuItem("Máximo");
        JMenuItem itemGrisMinimo = new JMenuItem("Mínimo");
        JMenuItem itemGrisVerde = new JMenuItem("Verde");
        
        // Añade los elementos del submenu grises
        menuGrises.add(itemGrisPromedio);
        menuGrises.add(itemGrisLuminicensia);   
        menuGrises.add(itemGrisDesaturacion);
        menuGrises.add(itemGrisMaximo);
        menuGrises.add(itemGrisMinimo);
        menuGrises.add(itemGrisVerde);
        
        // Agrega un listener a los elementos del submenu Grises
        itemGrisPromedio.addActionListener(grises_listener);
        itemGrisLuminicensia.addActionListener(grises_listener);
        itemGrisDesaturacion.addActionListener(grises_listener);
        itemGrisMaximo.addActionListener(grises_listener);
        itemGrisMinimo.addActionListener(grises_listener);
        itemGrisVerde.addActionListener(grises_listener);
        
    }
    
    private void crear_submenu_convolucion(JMenu menuConvolucion){
        
        ConvolucionListener convolucion_listener = new ConvolucionListener(this);
        
        // Crea los elementos del submenu convolucion
        JMenuItem itemConvolucion3x3 = new JMenuItem("3 x 3");
        JMenuItem itemConvolucion5x5 = new JMenuItem("5 x 5");
        JMenuItem itemConvolucion7x7 = new JMenuItem("7 x 7");
        JMenuItem itemConvolucion9x9 = new JMenuItem("9 x 9");
        
        // Añade los elementos del submenu convolucion
        menuConvolucion.add(itemConvolucion3x3);
        menuConvolucion.add(itemConvolucion5x5);
        menuConvolucion.add(itemConvolucion7x7);
        menuConvolucion.add(itemConvolucion9x9);
        
        // Agrega un listener a los elementos del submenu convolucion
        
        itemConvolucion3x3.addActionListener(convolucion_listener);
        itemConvolucion5x5.addActionListener(convolucion_listener);
        itemConvolucion7x7.addActionListener(convolucion_listener);
        itemConvolucion9x9.addActionListener(convolucion_listener);
        
    }
    
    private void crear_submenu_rotacion(JMenu menuRotacion){
        
       RotacionListener rotacion_listener = new RotacionListener(this); 
        
       JMenuItem itemRotacion90 = new JMenuItem("90"); 
       JMenuItem itemRotacion180 = new JMenuItem("180");
       JMenuItem itemRotacion270 = new JMenuItem("270");
       JMenuItem itemRotacionMatriz = new JMenuItem("Matriz");
        
       menuRotacion.add(itemRotacion90);
       menuRotacion.add(itemRotacion180);
       menuRotacion.add(itemRotacion270);
       menuRotacion.add(itemRotacionMatriz);
       
       itemRotacion90.addActionListener(rotacion_listener);
       itemRotacion180.addActionListener(rotacion_listener);
       itemRotacion270.addActionListener(rotacion_listener);
       itemRotacionMatriz.addActionListener(rotacion_listener);
       
    }
    
    private void crear_submenu_maximo(JMenu menuMaximo){
        
        MaximoListener maximo_listener = new MaximoListener(this);
        
        JMenuItem itemMaximo3x3 = new JMenuItem("3 x 3");
        JMenuItem itemMaximo5x5 = new JMenuItem("5 x 5");
        
        menuMaximo.add(itemMaximo3x3);
        menuMaximo.add(itemMaximo5x5);
        
        itemMaximo3x3.addActionListener(maximo_listener);
        itemMaximo5x5.addActionListener(maximo_listener);
        
    }
    
    private void crear_submenu_minimo(JMenu menuMinimo){
        
        MinimoListener minimo_listener = new MinimoListener(this);
        
        JMenuItem itemMinimo3x3 = new JMenuItem("3 x 3");
        JMenuItem itemMinimo5x5 = new JMenuItem("5 x 5");
        
        menuMinimo.add(itemMinimo3x3);
        menuMinimo.add(itemMinimo5x5);
        
        itemMinimo3x3.addActionListener(minimo_listener);
        itemMinimo5x5.addActionListener(minimo_listener);
        
    }
    
    private void crear_submenu_oleo(JMenu menuOleo){
        
        OleoListener oleo_listener = new OleoListener(this);
        
        JMenuItem itemOleoGris = new JMenuItem("Gris");
        JMenuItem itemOleoColor = new JMenuItem("Color");
        
        menuOleo.add(itemOleoGris);
        menuOleo.add(itemOleoColor);
        
        itemOleoGris.addActionListener(oleo_listener);
        itemOleoColor.addActionListener(oleo_listener);
        
    }
   
    private void crear_submenu_reduccion(JMenu menuReduccion){
        
       ReduccionListener reduccion_listener = new ReduccionListener(this);
       
       JMenuItem itemReduccionProporcion = new JMenuItem("Proporcion");
       JMenuItem itemReduccionPorcentaje = new JMenuItem("Porcentaje"); 
        
       menuReduccion.add(itemReduccionProporcion);
       menuReduccion.add(itemReduccionPorcentaje);
        
       itemReduccionProporcion.addActionListener(reduccion_listener);
       itemReduccionPorcentaje.addActionListener(reduccion_listener);
       
    }
    
    private void crear_submenu_fotomosaico(JMenu menuFotomosaico){
        
        FotomosaicoMenuListener fotomosaico_menu_listener = new FotomosaicoMenuListener(this);
        
        JMenuItem itemFotomosaico = new JMenuItem("Fotomosaico");
        JMenuItem itemFotomosaicoActualizar = new JMenuItem("Cargar Imágenes");
        
        menuFotomosaico.add(itemFotomosaico);
        menuFotomosaico.add(itemFotomosaicoActualizar);
        
        itemFotomosaico.addActionListener(fotomosaico_menu_listener);
        itemFotomosaicoActualizar.addActionListener(fotomosaico_menu_listener);
        
    }
    
    
    public File getFile(){
        return file_imagen;
    }
    
    public void setFile(File file_imagen){
        
          try {
            this.file_imagen = file_imagen;   
            this.imagen = ImageIO.read(file_imagen);    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
            
            System.out.println("Problema al cargar la imagen - Interfaz.java");
        }
        
    } 
    
    public BufferedImage getImage(){
        return imagen;
    }
    
    public BufferedImage getImageGuardar(){
        
        return this.imagen_guardar;
    }
    
    public void setImageGuardar(BufferedImage imagen_guardar){
        this.imagen_guardar = imagen_guardar;
        enable_item_guardar(true);
    }
    
     public PanelEditorImagen getPanelPrincipal(){
        
        return this.panel_principal;
        
    }
    
    public void setPanelPrincipal(PanelEditorImagen panel_principal){
        
        this.panel_principal = panel_principal;
        
    }
         
    public void actualizar_interfaz(){
        revalidate();
        repaint();
    }
           
    public void enable_item_guardar(boolean enabled){
       
        itemGuardar.setEnabled(enabled);
        
    }
    
    public void enable_menu_filtros(boolean enabled){
        
        menuFiltros.setEnabled(enabled);
        
    }
    
    
}

