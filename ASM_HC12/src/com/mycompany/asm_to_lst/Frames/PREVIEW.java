
package com.mycompany.asm_to_lst.Frames;

import com.mycompany.asm_to_lst.fileControl.CrearArchivo;
import com.mycompany.asm_to_lst.fileControl.GenerarRuta;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author AnthonySandoval
 */
public class PREVIEW extends javax.swing.JFrame {

     //"CrearArchivo" es la clase que crea o sobrescribe un archivo
    CrearArchivo controlArchivo;
    //Objeto para el funcionameito de guardar como
    GenerarRuta rutaGuardarComo;
    String rutaSeleccionada = "";
    
    public PREVIEW() {
        initComponents();
        controlArchivo = new CrearArchivo();
        rutaGuardarComo = new GenerarRuta();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane_LST = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        menuItem_save = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(textPane_LST);

        menu.setText("File");

        menuItem_save.setText("Guardar");
        menuItem_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_saveActionPerformed(evt);
            }
        });
        menu.add(menuItem_save);

        jMenuBar1.add(menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItem_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_saveActionPerformed

        guardarComo(textPane_LST.getText());
        
    }//GEN-LAST:event_menuItem_saveActionPerformed

     public void guardarComo(String texto)
    {
        //Objeto para generar la ruta
        rutaGuardarComo.crearRuta(JFileChooser.FILES_ONLY, false);
        
        if(!rutaGuardarComo.getRutaExt().equals("")){
            controlArchivo = new CrearArchivo();
            controlArchivo.crear(texto, rutaGuardarComo.getRutaExt());
            
            rutaGuardarComo.separarNomRuta();
            
            rutaGuardarComo.resetRutas();
            rutaSeleccionada = rutaGuardarComo.getRutaArchivo();
        }
    }
    
    public void showLST(String lst){
        textPane_LST.setText(lst);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menuItem_save;
    private javax.swing.JTextPane textPane_LST;
    // End of variables declaration//GEN-END:variables
}
