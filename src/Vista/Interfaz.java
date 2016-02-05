
package Vista;

import Controlador.ArchivoListener;
import Controlador.BlendingMouseListener;
import Controlador.ConvolucionListener;
import Controlador.FiltrosListener;
import Controlador.GrisesListener;
import Controlador.MaximoListener;
import Controlador.MinimoListener;
import Controlador.RotacionListener;
import Controlador.WarholMouseListener;
import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author rae
 */
public class Interfaz extends JFrame {
    
    File file_imagen = null;
    BufferedImage imagen = null;
    BufferedImage imagen_guardar = null;
    
    BibliotecaGrafica bg = new BibliotecaGrafica();
    
    // Formato Interfaz Normal
    
    JLabel img_izq = new JLabel();
    JLabel img_der = new JLabel();
    JScrollPane scroll_img_izq = new JScrollPane(img_izq);
    JScrollPane scroll_img_der = new JScrollPane(img_der);
    
    JPanel panel_principal;
    
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
    
    // Formato Interfaz blending

    boolean blending_bool = false;
    
    JPanel blending_panel_izq;
    JPanel blending_panel_der;
   
    JLabel blending_label_sup = new JLabel();
    JLabel blending_label_inf = new JLabel();
    
    JScrollPane blending_scroll_label_sup = new JScrollPane(blending_label_sup);
    JScrollPane blending_scroll_label_inf = new JScrollPane(blending_label_inf);
            
    JSlider blending_slider = new JSlider(SwingConstants.HORIZONTAL,0,100,1);
    
    // Imagenes Blending
    BufferedImage blending_img_sup, blending_img_inf;
    
    // Formato Interfaz Favicom 
    
    boolean favicom_bool = false;
    
    JPanel favicom_panel_izq_inf_radio = new JPanel();
    JPanel favicom_panel_izq_inf = new JPanel();
    JPanel favicom_panel_der = new JPanel();
    JPanel favicom_panel_izq = new JPanel(); 
    
    JLabel favicom_label_sup = new JLabel();
    JLabel favicom_label_inf = new JLabel();
    
    JScrollPane favicom_scroll_label_sup = new JScrollPane(favicom_label_sup);
    JScrollPane favicom_scroll_label_inf = new JScrollPane(favicom_label_inf);
    
    JRadioButton izq_sup = new JRadioButton("Esquina superior izquierda",true);
    JRadioButton der_sup = new JRadioButton("Esquina superior derecha",false);
    JRadioButton izq_inf = new JRadioButton("Esquina inferior izquierda",false);
    JRadioButton der_inf = new JRadioButton("Esquina inferior derecha",false);
    ButtonGroup opciones = new ButtonGroup();
    
    JButton favicom_boton = new JButton("Aceptar");
    
    JLabel favicom_spinner_label = new JLabel("Nivel de transparencia (%)");
    SpinnerModel favicom_spinner_model = new SpinnerNumberModel(0,0,100,1);
    JSpinner favicom_spinner = new JSpinner(favicom_spinner_model);
    
    //Imagenes Favicom
    BufferedImage favicom_img_sup, favicom_img_inf;
    
    // Formato Interfaz Sepia 
    
    boolean sepia_bool = false;
    
    JPanel sepia_panel_izq = new JPanel();
    JPanel sepia_panel_der = new JPanel();
    
    JLabel sepia_label = new JLabel();
    
    JScrollPane sepia_scroll_label = new JScrollPane(sepia_label);
    
    JSlider sepia_slider = new JSlider(SwingConstants.HORIZONTAL,0,255,1);
        
    public Interfaz(){
    
        super("Editor gráfico");
        
        // Metodo que se encarga de cargar el menu
        crear_menu();
        //this.setLayout(new GridLayout(1,2,5,10));

        img_izq.setHorizontalAlignment(JLabel.CENTER);
        img_der.setHorizontalAlignment(JLabel.CENTER);
        
        warhol_label_1.setName("0");
        warhol_label_2.setName("1");
        warhol_label_3.setName("2");
        warhol_label_4.setName("3");
        
        //this.add(scroll_img_izq);
        //this.add(scroll_img_der);
        
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
        JMenuItem itemBlending = new JMenuItem("Blending");
        JMenuItem itemFavicom = new JMenuItem("Favicom");
        JMenuItem itemSepia = new JMenuItem("Sepia");
        JMenuItem itemAltoContraste = new JMenuItem("Alto Contraste");
                
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
        menuFiltros.add(itemBlending);
        menuFiltros.add(itemFavicom);
        menuFiltros.add(itemSepia);
        menuFiltros.add(itemAltoContraste);
        menuFiltros.add(menuMaximo);
        menuFiltros.add(menuMinimo);
        
        // Crea el listener para los filtros
        FiltrosListener filtros_listener = new FiltrosListener(this); 
       
        // Agrega un listener a los elementos de menu Filtros
        itemAzar.addActionListener(filtros_listener);
        itemRGB.addActionListener(filtros_listener);
        itemBrillo.addActionListener(filtros_listener);
        itemMosaico.addActionListener(filtros_listener);
        itemReduccion.addActionListener(filtros_listener);
        itemWarhol.addActionListener(filtros_listener);
        itemBlending.addActionListener(filtros_listener);
        itemFavicom.addActionListener(filtros_listener);
        itemSepia.addActionListener(filtros_listener);
        itemAltoContraste.addActionListener(filtros_listener);
             
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
            this.imagen = img;
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
    
    public void crear_submenu_maximo(JMenu menuMaximo){
        
        MaximoListener maximo_listener = new MaximoListener(this);
        
        JMenuItem itemMaximo3x3 = new JMenuItem("3 x 3");
        JMenuItem itemMaximo5x5 = new JMenuItem("5 x 5");
        
        menuMaximo.add(itemMaximo3x3);
        menuMaximo.add(itemMaximo5x5);
        
        itemMaximo3x3.addActionListener(maximo_listener);
        itemMaximo5x5.addActionListener(maximo_listener);
        
    }
    
    public void crear_submenu_minimo(JMenu menuMinimo){
        
        MinimoListener minimo_listener = new MinimoListener(this);
        
        JMenuItem itemMinimo3x3 = new JMenuItem("3 x 3");
        JMenuItem itemMinimo5x5 = new JMenuItem("5 x 5");
        
        menuMinimo.add(itemMinimo3x3);
        menuMinimo.add(itemMinimo5x5);
        
        itemMinimo3x3.addActionListener(minimo_listener);
        itemMinimo5x5.addActionListener(minimo_listener);
        
    }
    
    public void poner_imagen_izq(File file_imagen){
        try {
            this.imagen = ImageIO.read(file_imagen);
            img_izq.setIcon(new ImageIcon(imagen));
            img_der.setIcon(null);
            this.file_imagen = file_imagen;  
        } catch (IOException ex) {
            System.out.println("Problema al cargar la imagen");
        }
    }
    
    public void poner_imagen_der(BufferedImage imagen){
        this.imagen_guardar = imagen;  // Revisar ---------------------------------------------------------///////
        img_der.setIcon(new ImageIcon(imagen));
    }
    
    public void eliminar_imagen_der(){
        
       this.imagen_guardar = null; // Revisar ---------------------------------------------------------///////
        img_der.setIcon(null);
        
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
    
    public File getFile(){
        return file_imagen;
    }
    
    public BufferedImage getImage(){
        return imagen;
    }
    
    public BufferedImage getImageGuardar(){
        
        return this.imagen_guardar;
    }
    
    public void setImageGuardar(BufferedImage imagen_guardar){
        this.imagen_guardar = imagen_guardar;
    }
    
    public void setPanelPrincipal(JPanel panel_principal){
        
        this.panel_principal = panel_principal;
        
    }
    
    public JPanel getPanelPrincipal(){
        
        return this.panel_principal;
        
    }
    

    
    // Warhol
    
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
                
            });
            
            this.add(warhol_panel_izq);
            this.add(warhol_panel_der);
            this.revalidate();

        }
    }

    public void undo_warhol(){
         
        if(warhol_bool == true){
            
         this.remove(warhol_panel_izq);
         this.remove(warhol_panel_der);
            
         this.setLayout(new GridLayout(1,2,5,10));

         this.add(scroll_img_izq);
         this.add(scroll_img_der);   
         this.revalidate();
         
         warhol_bool = false;
        
        }
        
    }
    
    public void poner_imagen_warhol(int i, BufferedImage imagen){
        
        JLabel[] imgs = {warhol_label_1,warhol_label_2,warhol_label_3,warhol_label_4};
        imgs[i].setIcon(new ImageIcon(imagen));
        
    }
    
    // Blending
    
    public void do_blending(){
        
        if(blending_bool != true){
         
            blending_bool = true;
            
            this.remove(scroll_img_izq);
            this.remove(scroll_img_der);
            
            this.setLayout(new GridLayout(1,2,5,10));

            blending_panel_izq = new JPanel(new GridBagLayout()); 
            blending_panel_der = new JPanel(new BorderLayout());
            
            blending_panel_der.add(scroll_img_der,BorderLayout.CENTER);
            
            blending_label_sup.setHorizontalAlignment(JLabel.CENTER);
            blending_label_inf.setHorizontalAlignment(JLabel.CENTER);
            
//            blending_label_inf.addMouseListener(new BlendingMouseListener(this));
            
            blending_label_inf.setIcon(null);
            blending_label_inf.setText("Click para agregar imagen");
            
            blending_img_inf = null; // Eliminar cualquier imagen anterior que halla qeudado 
            blending_slider.setEnabled(false); // Desahbilitar el slider hasta que se carge una imagen
            
            try{
             
                blending_img_sup = ImageIO.read(file_imagen);
                //blending_img_inf = ImageIO.read(file_imagen);
                
                blending_label_sup.setIcon(new ImageIcon(blending_img_sup));
                //blending_label_inf.setIcon(new ImageIcon(blending_img_inf));
             
                blending_slider.addChangeListener((ChangeEvent e) -> {
                this.poner_imagen_der(bg.filtro_blending(blending_img_sup, blending_img_inf, blending_slider.getValue()));  
                });
                
            }catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }
        
            GridBagConstraints especif = new GridBagConstraints(); 

            especif.gridx = 1;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
        
            blending_panel_izq.add(blending_scroll_label_sup,especif);
           
            especif.gridx = 1;
            especif.gridy = 2;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
        
            
            blending_panel_izq.add(blending_scroll_label_inf,especif);
        
            especif.gridx = 0;
            especif.gridy = 3;
            especif.gridwidth = 3;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.1;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.HORIZONTAL;
            especif.anchor = GridBagConstraints.NORTH;
            
            blending_panel_izq.add(blending_slider,especif);
        
            blending_slider.setPaintLabels(true);
            blending_slider.setPaintTicks(true);
            blending_slider.setMajorTickSpacing(10);
            
            this.add(blending_panel_izq);
            this.add(blending_panel_der);
            this.revalidate();
            
        }
        
    }
    
    public void actualizar_interfaz(){
        revalidate();
        repaint();
    }
    
    public void undo_blending(){
        
        if(blending_bool == true){
            
            this.remove(blending_panel_izq);
            this.remove(blending_panel_der);
 
            this.setLayout(new GridLayout(1,2,5,10));

            this.add(scroll_img_izq);
            this.add(scroll_img_der);   
            this.revalidate();
            
            blending_bool = false;
            
        }
        
    }
    
    public void enable_blending_slider(){
        blending_slider.setEnabled(true);
    }
    
    public void poner_imagen_blending(File file_imagen){
        
        try{
          blending_img_inf = ImageIO.read(file_imagen);
          blending_label_inf.setIcon(new ImageIcon(blending_img_inf));
          blending_label_inf.setText("");
        }catch(IOException ex) {
            System.out.println("Problema al cargar la imagen");
        }
        
        revalidate();
        repaint();
        
    }
    
    // Favicom
    
    public void do_favicom(){
        
        if(favicom_bool != true){
            
            this.remove(scroll_img_izq);
            this.remove(scroll_img_der);
            
            this.setLayout(new GridLayout(1,2,5,10));

            favicom_bool = true;
            
            favicom_spinner_label.setHorizontalAlignment(JLabel.CENTER);
            
            favicom_panel_izq = new JPanel(new GridBagLayout()); 
            favicom_panel_der = new JPanel(new BorderLayout());
            
            favicom_panel_der.add(scroll_img_der,BorderLayout.CENTER);
            
            opciones.add(izq_sup);
            opciones.add(der_sup);
            opciones.add(izq_inf);
            opciones.add(der_inf);
            
            favicom_label_sup.setHorizontalAlignment(JLabel.CENTER);
            favicom_label_inf.setHorizontalAlignment(JLabel.CENTER);
            
            try{
                
                favicom_img_sup = ImageIO.read(file_imagen);
                favicom_img_inf = ImageIO.read(file_imagen);
                        
                favicom_label_sup.setIcon(new ImageIcon(favicom_img_sup));
                favicom_label_inf.setIcon(new ImageIcon(favicom_img_inf));
                
            }catch(IOException ex) {
                System.out.println("Error al cargar imagen"); 
            }
            
            GridBagConstraints especif = new GridBagConstraints();
            
            especif.gridx = 0;
            especif.gridy = 0;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
            
            favicom_panel_izq.add(favicom_scroll_label_sup,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.5;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            especif.ipadx = 0;
            
            favicom_panel_izq.add(favicom_panel_izq_inf,especif);
        
            especif.gridx = 0;
            especif.gridy = 2;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.10;
            especif.insets = new Insets(20,10,20,0);
            especif.fill = GridBagConstraints.NONE;
            especif.anchor = GridBagConstraints.CENTER;
            
            
            favicom_panel_izq.add(favicom_boton,especif);
            
            // Aqui empieza la comfiguracion del subpanel inferior del lado izquierdo
            
            favicom_panel_izq_inf.setLayout(new GridBagLayout());
            
            
            favicom_panel_izq_inf_radio.setLayout(new GridLayout(6,1));
            favicom_panel_izq_inf_radio.add(favicom_spinner_label);
            favicom_panel_izq_inf_radio.add(favicom_spinner);
            favicom_panel_izq_inf_radio.add(izq_sup);
            favicom_panel_izq_inf_radio.add(der_sup);
            favicom_panel_izq_inf_radio.add(izq_inf);
            favicom_panel_izq_inf_radio.add(der_inf);
            
            
           
            especif.gridx = 0;
            especif.gridy = 0;
            especif.gridwidth = 2;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            //especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
             
            favicom_panel_izq_inf.add(favicom_scroll_label_inf,especif);
            
            especif.gridx = 2;
            especif.gridy = 0;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 0.1;  
            especif.weighty = 1.0;
            //especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.VERTICAL;
            especif.anchor = GridBagConstraints.CENTER;
            
            
            favicom_panel_izq_inf.add(favicom_panel_izq_inf_radio,especif);
            
            
            this.add(favicom_panel_izq);
            this.add(favicom_panel_der);
            this.revalidate();
        
        }
        
    }
    
    public void undo_favicom(){
        
        
        if(favicom_bool == true){
        
            this.remove(favicom_panel_izq);
            this.remove(favicom_panel_der);
            
            this.setLayout(new GridLayout(1,2,5,10));

            this.add(scroll_img_izq);
            this.add(scroll_img_der);   
            this.revalidate();
            
            favicom_bool = false;
            
        }
        
    }
        
    // Sepia
    
    public void do_sepia(){
        
        if(sepia_bool != true){
         
            this.remove(scroll_img_izq);
            this.remove(scroll_img_der);
            
            this.setLayout(new GridLayout(1,2,5,10));
            
            sepia_bool = true;
            
            sepia_panel_izq = new JPanel(new GridBagLayout()); 
            sepia_panel_der = new JPanel(new BorderLayout());
            
            sepia_panel_der.add(scroll_img_der,BorderLayout.CENTER);
            
            
            
             sepia_slider.addChangeListener((ChangeEvent e) -> {
                    
                    Interfaz.this.poner_imagen_der(bg.filtro_sepia(this.imagen,sepia_slider.getValue()));
                  
             });
            
           
             
            GridBagConstraints especif = new GridBagConstraints();
         
            especif.gridx = 0;
            especif.gridy = 0;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
        
            sepia_panel_izq.add(scroll_img_izq,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.2;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
            sepia_panel_izq.add(sepia_slider,especif);
        
            sepia_slider.setPaintLabels(true);
            sepia_slider.setPaintTicks(true);
            sepia_slider.setMajorTickSpacing(15);
            
            this.add(sepia_panel_izq);
            this.add(sepia_panel_der);
            this.revalidate();
        
        }
        
    }
    
    public void undo_sepia(){
    
             if(sepia_bool == true){
        
            this.remove(sepia_panel_izq);
            this.remove(sepia_panel_der);
            
            this.setLayout(new GridLayout(1,2,5,10));

            this.add(scroll_img_izq);
            this.add(scroll_img_der);   
            this.revalidate();
            
            sepia_bool = false;
            
        }
        
    }
    
    public void undo_all(){
        undo_warhol();
        undo_blending();
        undo_favicom();
        undo_sepia();
        
    }
    
   
}

