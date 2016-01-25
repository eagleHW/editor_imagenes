
package Vista;

import Controlador.ArchivoListener;
import Controlador.ConvolucionListener;
import Controlador.FiltrosListener;
import Controlador.GrisesListener;
import Controlador.RotacionListener;
import Controlador.WarholMouseListener;
import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

/**
 *
 * @author rae
 */
public class Interfaz extends JFrame {
    
    // Formato Interfaz Normal
    
    JLabel img_izq = new JLabel();
    JLabel img_der = new JLabel();
    JScrollPane scroll_img_izq = new JScrollPane(img_izq);
    JScrollPane scroll_img_der = new JScrollPane(img_der);
    
    // Formato Interfaz Warhol
    
    boolean warhol_bool = false;
   
    JPanel warhol_panel_izq; 
    JPanel warhol_panel_der; 
    
    JLabel warhol_label_1 = new JLabel();
    JLabel warhol_label_2 = new JLabel();
    JLabel warhol_label_3 = new JLabel();
    JLabel warhol_label_4 = new JLabel();
    
    JScrollPane scroll_label_1 = new JScrollPane(warhol_label_1);
    JScrollPane scroll_label_2 = new JScrollPane(warhol_label_2);
    JScrollPane scroll_label_3 = new JScrollPane(warhol_label_3);
    JScrollPane scroll_label_4 = new JScrollPane(warhol_label_4);

    JButton button_generar = new JButton("Generar");
    
    // Imagenes Warhol
    BufferedImage img0,img1,img2,img3;
    
    File file_imagen = null;
    BufferedImage imagen = null;

       
    
    public Interfaz(){
    
        super("Editor gráfico");
        
        // Metodo que se encarga de cargar el menu
        crear_menu();
        this.setLayout(new GridLayout(1,2,5,10));

        img_izq.setHorizontalAlignment(JLabel.CENTER);
        img_der.setHorizontalAlignment(JLabel.CENTER);
        
        warhol_label_1.setName("0");
        warhol_label_2.setName("1");
        warhol_label_3.setName("2");
        warhol_label_4.setName("3");
        
        this.add(scroll_img_izq);
        this.add(scroll_img_der);
        
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
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemSalir = new JMenuItem("Salir");
        
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
        JMenu menuFiltros = new JMenu("Filtros");
        menuFiltros.setMnemonic('F');
        
        // Crea los elementos del menu Filtros 
        JMenuItem itemAzar = new JMenuItem("Azar");
        JMenuItem itemRGB = new JMenuItem("RGB");
        JMenuItem itemBrillo = new JMenuItem("Brillo");
        JMenuItem itemMosaico = new JMenuItem("Mosaico");
        JMenuItem itemReduccion = new JMenuItem("Reduccion");
        JMenuItem itemWarhol = new JMenuItem("Warhol");
        
        // Creamos el submenu Grises
    
        JMenu menuGrises = new JMenu("Grises");
        crear_submenu_grises(menuGrises);     
        
        // Creamos el submenu Convolucion
        
        JMenu menuConvolucion = new JMenu("Convolucion");
        crear_submenu_convolucion(menuConvolucion);
        
        // Creamos el submenu Rotacion
        
        JMenu menuRotacion = new JMenu("Rotacion");
        crear_submenu_rotacion(menuRotacion);
        
        
        // Agrega los elementos del menu filtro
        // incluyendo los submenus
        
        menuFiltros.add(itemAzar);
        menuFiltros.add(itemRGB);
        menuFiltros.add(itemBrillo);
        menuFiltros.add(menuGrises);
        menuFiltros.add(itemMosaico);
        menuFiltros.add(itemReduccion);
        menuFiltros.add(itemWarhol);
        menuFiltros.add(menuConvolucion);
        menuFiltros.add(menuRotacion);
        
        // Crea el listener para los filtros
        FiltrosListener filtros_listener = new FiltrosListener(this); 
       
        // Agrega un listener a los elementos de menu Filtros
        itemAzar.addActionListener(filtros_listener);
        itemRGB.addActionListener(filtros_listener);
        itemBrillo.addActionListener(filtros_listener);
        itemMosaico.addActionListener(filtros_listener);
        itemReduccion.addActionListener(filtros_listener);
        itemWarhol.addActionListener(filtros_listener);
            
        // Añade los menus a la barra de menu
        barra.add(menuArchivo);
        barra.add(menuFiltros);
        
        
        
        // Para evitar estar cargando imagen cada vez que pruebo
        try {
            BufferedImage img;
            File file = new File("/home/rae/prueba.jpg");
            img = ImageIO.read(file);
            img_izq.setIcon(new ImageIcon(img));
            this.file_imagen = file;
        } catch (IOException ex) {
            System.out.println("Error al cargar imagen de prueba");
        }
       
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
    
    public void crear_submenu_convolucion(JMenu menuConvolucion){
        
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
    
    
    public void crear_submenu_rotacion(JMenu menuRotacion){
        
       RotacionListener rotacion_listener = new RotacionListener(this); 
        
       JMenuItem itemRotacion90 = new JMenuItem("90"); 
       JMenuItem itemRotacion180 = new JMenuItem("180");
       JMenuItem itemRotacion270 = new JMenuItem("270");
        
       menuRotacion.add(itemRotacion90);
       menuRotacion.add(itemRotacion180);
       menuRotacion.add(itemRotacion270);
       
       itemRotacion90.addActionListener(rotacion_listener);
       itemRotacion180.addActionListener(rotacion_listener);
       itemRotacion270.addActionListener(rotacion_listener);
       
    }
    
    public void poner_imagen_izq(File file_imagen){
        try {
            BufferedImage imagen = ImageIO.read(file_imagen);
            img_izq.setIcon(new ImageIcon(imagen));
            img_der.setIcon(null);
            this.file_imagen = file_imagen;
        } catch (IOException ex) {
            System.out.println("Problema al cargar la imagen");
        }
    }
    
    public void poner_imagen_der(BufferedImage imagen){
        this.imagen = imagen;
        img_der.setIcon(new ImageIcon(imagen));
    }
    
    public void poner_imagen_espera(){
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(new File("wait_whale.jpg"));
            img_der.setIcon(new ImageIcon(imagen));
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    
   
    }
    
    public BufferedImage getImage(){
        return imagen;
    }
    
    public File getFile(){
        return file_imagen;
    }
    
    public void poner_imagen_warhol(int i, BufferedImage imagen){
        
        JLabel[] imgs = {warhol_label_1,warhol_label_2,warhol_label_3,warhol_label_4};
        imgs[i].setIcon(new ImageIcon(imagen));
        
    }
    
    public void warholizate(){
          
        if(warhol_bool != true){

            WarholMouseListener warhol_listener = new WarholMouseListener(this);
            BibliotecaGrafica bg = new BibliotecaGrafica();
            
            warhol_bool = true;

            this.remove(scroll_img_izq);
            this.remove(scroll_img_der);

            this.setLayout(new GridLayout(1,2,5,10));

            warhol_panel_izq = new JPanel(new GridLayout(2,2,5,10));
            warhol_panel_der = new JPanel(new BorderLayout());

            warhol_panel_der.add(scroll_img_der, BorderLayout.CENTER);
            warhol_panel_der.add(button_generar, BorderLayout.PAGE_START);
            
            warhol_label_1.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_2.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_3.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_4.setHorizontalAlignment(JLabel.CENTER);

            try {

              img0 = ImageIO.read(file_imagen);
              img1 = ImageIO.read(file_imagen);
              img2 = ImageIO.read(file_imagen);
              img3 = ImageIO.read(file_imagen);

              warhol_label_1.setIcon(new ImageIcon(img0));
              warhol_label_2.setIcon(new ImageIcon(img1));
              warhol_label_3.setIcon(new ImageIcon(img2));
              warhol_label_4.setIcon(new ImageIcon(img3));

              warhol_label_1.addMouseListener(warhol_listener);
              warhol_label_2.addMouseListener(warhol_listener);
              warhol_label_3.addMouseListener(warhol_listener);
              warhol_label_4.addMouseListener(warhol_listener);
              
              
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }

            warhol_panel_izq.add(scroll_label_1);
            warhol_panel_izq.add(scroll_label_2);
            warhol_panel_izq.add(scroll_label_3);
            warhol_panel_izq.add(scroll_label_4);
            
            button_generar.addActionListener((ActionEvent e) -> {
                
                poner_imagen_der(bg.pegar_imagenes(img0, img1, img2, img3));
                prueba();
                
            });
            
            this.add(warhol_panel_izq);
            this.add(warhol_panel_der);
            this.revalidate();

        }
    }
    
    public void prueba(){
        this.repaint();
    }
    
}
