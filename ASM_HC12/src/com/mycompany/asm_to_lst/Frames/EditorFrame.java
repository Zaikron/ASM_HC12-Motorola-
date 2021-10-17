
package com.mycompany.asm_to_lst.Frames;

import com.mycompany.asm_to_lst.TabsFormat.ButtonTabComponent;
import com.mycompany.asm_to_lst.CODE_TABLE;
import com.mycompany.asm_to_lst.TabsFormat.NumeroLinea;
import com.mycompany.asm_to_lst.fileControl.GenerarRuta;
import com.mycompany.asm_to_lst.fileControl.CrearArchivo;
import com.mycompany.asm_to_lst.fileControl.AbrirArchivo;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

/**
 *
 * @author AnthonySandoval
 */
public class EditorFrame extends javax.swing.JFrame {
    
    //"GenerarRuta" es la clase que ayuda a crear la ruta en la que se guardara el archivo
    GenerarRuta gr;
    //"AbrirArchivo" es las clase que permite abrir un archivo y leerlo para cargarlo en el editor
    AbrirArchivo leer;
    //"CrearArchivo" es la clase que crea o sobrescribe un archivo
    CrearArchivo controlArchivo;
    //Objeto para el funcionameito de guardar como
    GenerarRuta rutaGuardarComo;
    //Nombre del archivo que se abre para colocarlo en la pestaña
    String nomArchivo = "";
    //La cadena "rutaSeleccionada" contendra la rura seleccionada del archivo guardado o abierto
    String rutaSeleccionada = "";
    //Componente para crear pestañas, (Obtenido de la pagina de Oracle)
    ButtonTabComponent buttonTabComponent;
    //Objetos auxiliares que sirven para añiadir pestañas de forma dinamica
    JTextPane areaAux;
    JScrollPane scrollAux;
    JViewport viewAux;
    
    CODE_TABLE ct;
    
    public EditorFrame() {
        initComponents();
        //generarPestana("Nuevo");
        controlArchivo = new CrearArchivo();
        rutaGuardarComo = new GenerarRuta();
        
        //abrirArchivo("C:\\Users\\AnthonySandoval\\Desktop\\pruebasASM.txt");
        //abrirArchivo("C:\\Users\\AnthonySandoval\\Desktop\\P9.asm");
        //areaAux.setText("ORG $4000\nABA\nADCA #30\nADCA $30\nADCA $300\nADCA %1111");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelText = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItem_new = new javax.swing.JMenuItem();
        menuItem_abrir = new javax.swing.JMenuItem();
        menuItem_save = new javax.swing.JMenuItem();
        menuItem_ldt = new javax.swing.JMenuItem();
        menuItem_view = new javax.swing.JMenuItem();
        menuItem_CONT = new javax.swing.JMenuItem();
        menuItem_tab = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Texto");

        panelText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTextMouseClicked(evt);
            }
        });

        jMenu1.setText("File");

        menuItem_new.setText("Nuevo");
        menuItem_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_newActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_new);

        menuItem_abrir.setText("Abrir");
        menuItem_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_abrirActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_abrir);

        menuItem_save.setText("Guardar");
        menuItem_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_saveActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_save);

        menuItem_ldt.setText("Guardar como ...");
        menuItem_ldt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_ldtActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_ldt);

        menuItem_view.setText("Vista Previa LST");
        menuItem_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_viewActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_view);

        menuItem_CONT.setText("Vista Previa CONTLOC");
        menuItem_CONT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_CONTActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_CONT);

        menuItem_tab.setText("Vista Previa TABSIM");
        menuItem_tab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_tabActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem_tab);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(panelText, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelText.getAccessibleContext().setAccessibleName("text");
        panelText.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItem_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_newActionPerformed
        
        generarPestana("Nuevo");
        setSelectedTab();
    }//GEN-LAST:event_menuItem_newActionPerformed

    private void menuItem_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_viewActionPerformed
        ct = new CODE_TABLE();
        ct.initTable();
        ct.initLabels();
        
        PREVIEW pre = new PREVIEW();
        pre.setVisible(true);
        pre.setLocationRelativeTo(null);
        pre.showLST(ct.transformToLST(areaAux.getText(), "LST", true));
    }//GEN-LAST:event_menuItem_viewActionPerformed

    private void menuItem_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_abrirActionPerformed
        //Se utiliza la clase para crear la ruta en donde se abrira el archivo
        gr = new GenerarRuta();
        gr.crearRuta(JFileChooser.FILES_ONLY, true);
        gr.separarNomRuta();
        
        nomArchivo = gr.getNomSinRuta();
        abrirArchivo(gr.getRutaArchivo());
        setSelectedTab();
    }//GEN-LAST:event_menuItem_abrirActionPerformed

    private void panelTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTextMouseClicked
        setSelectedTab();
    }//GEN-LAST:event_panelTextMouseClicked

    private void menuItem_ldtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_ldtActionPerformed
        
        //Si no hay pestañas no se ejecuta
        if(panelText.getTabCount()!= 0){
            //Accedere a travez de los componentes hasta llegar al JTextPane y obtener el texto
            scrollAux = (JScrollPane)panelText.getSelectedComponent();
            //El viewport es lo que esta dentro del scroll, tambien lo guardo en un objeto
            viewAux = scrollAux.getViewport();

           //Se establece el editor con los datos obtenidos de la lectura
            //Dentro del viewport esta el textpane, lo guardo en un objeto
            areaAux = (JTextPane)viewAux.getView();

            guardarComo(areaAux.getText());
        }
    }//GEN-LAST:event_menuItem_ldtActionPerformed

    private void menuItem_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_saveActionPerformed
        //Se le pasa por parametros los datos normales y la ruta para guardarlos en el mismo archivo
        
        if(panelText.getTabCount() != 0){
            controlArchivo.crear(areaAux.getText(), rutaSeleccionada);
        }
    }//GEN-LAST:event_menuItem_saveActionPerformed

    private void menuItem_CONTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_CONTActionPerformed
        ct = new CODE_TABLE();
        ct.initTable();
        ct.initLabels();
        
        PREVIEW pre = new PREVIEW();
        pre.setVisible(true);
        pre.setLocationRelativeTo(null);
        pre.showLST(ct.transformToLST(areaAux.getText(), "CONTLOC", true));
    }//GEN-LAST:event_menuItem_CONTActionPerformed

    private void menuItem_tabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_tabActionPerformed

        ct = new CODE_TABLE();
        ct.initTable();
        ct.initLabels();
        ct.transformToLST(areaAux.getText(), "CONTLOC", true);
        
        PREVIEW pre = new PREVIEW();
        pre.setVisible(true);
        pre.setLocationRelativeTo(null);
        pre.showLST(ct.getTABSIM());
    }//GEN-LAST:event_menuItem_tabActionPerformed

    public void guardarComo(String texto)
    {
        //Objeto para generar la ruta
        rutaGuardarComo.crearRuta(JFileChooser.FILES_ONLY, false);
        
        if(!rutaGuardarComo.getRutaExt().equals("")){
            controlArchivo = new CrearArchivo();
            controlArchivo.crear(texto, rutaGuardarComo.getRutaExt());
            
            rutaGuardarComo.separarNomRuta();
            
            panelText.setTitleAt(panelText.getSelectedIndex(), rutaGuardarComo.getNomSinRuta());
            rutaGuardarComo.resetRutas();
            rutaSeleccionada = rutaGuardarComo.getRutaArchivo();
        }
    }
    
    
    private void abrirArchivo(String ruta){
        
        leer = new AbrirArchivo();
        //Comprobar si la ruta no esta vacia
        if(!ruta.equals("")){
            
            //Se utiliza el objeto de la clase "AbrirArchivo", se utiliza su metodo "leer" y se le manda la ruta del archivo
            leer.leer(ruta);
            
            //Pestaña
            generarPestana(nomArchivo);
            
            //establezco el texto en el objeto obtenido que pertenece al TextPane
            areaAux.setText(leer.getData());
            
            //Se guarda la ruta en la cadena, (Era para evitar un error que no recuerdo exactamente cual era :b)
            rutaSeleccionada = ruta;
            leer.datos = "";
        }
        
    }
    
    
    private void generarPestana(String nom){
            //Pestañas
            buttonTabComponent = new ButtonTabComponent(panelText);
            //Objetos para crear las nuevas pestañas con los numeros de lineas
            JTextPane editorNuevo = new JTextPane();
            JScrollPane scrollNuevo = new JScrollPane();
            NumeroLinea numLinea = new NumeroLinea(editorNuevo);
            scrollNuevo.setViewportView(editorNuevo);
            scrollNuevo.setRowHeaderView(numLinea);
            
            //Añado la pestaña con su nombre y panel de texto ya modificado
            panelText.add(nom, scrollNuevo);
            //Le paso el objeto que permite cerrar las pestañas
            panelText.setTabComponentAt(panelText.getTabCount()-1, buttonTabComponent);
            //Selecciono la ultima pestaña seleccionada
            panelText.setSelectedIndex(panelText.getTabCount()-1);
     
            //Obtengo el scroll de la pestaña seleccionada y lo guardo en un objeto
            scrollAux = (JScrollPane)panelText.getSelectedComponent();
            //El viewport es lo que esta dentro del scroll, tambien lo guardo en un objeto
            viewAux = scrollAux.getViewport();
            
            //Se establece el editor con los datos obtenidos de la lectura
            //Dentro del viewport esta el textpane, lo guardo en un objeto
            areaAux = (JTextPane)viewAux.getView();
    }
    
    
    private void setSelectedTab(){
        String rutaSeleccionada = "";
        if(panelText.getTabCount() != 0){
            
            //Obtengo el scroll de la pestaña seleccionada y lo guardo en un objeto
            scrollAux = (JScrollPane)panelText.getSelectedComponent();

            //El viewport es lo que esta dentro del scroll, tambien lo guardo en un objeto
            viewAux = scrollAux.getViewport();
            //Se establece el editor con los datos obtenidos de la lectura
            //Dentro del viewport esta el textpane, lo guardo en un objeto
            areaAux = (JTextPane)viewAux.getView();

        }  
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItem_CONT;
    private javax.swing.JMenuItem menuItem_abrir;
    private javax.swing.JMenuItem menuItem_ldt;
    private javax.swing.JMenuItem menuItem_new;
    private javax.swing.JMenuItem menuItem_save;
    private javax.swing.JMenuItem menuItem_tab;
    private javax.swing.JMenuItem menuItem_view;
    private javax.swing.JTabbedPane panelText;
    // End of variables declaration//GEN-END:variables
}
