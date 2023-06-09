/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Entidad.EntProducto;
import Negocios.NegProducto;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ASUS
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtboleta = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnStock1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtboleta.setText("Boleta");
        txtboleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtboletaActionPerformed(evt);
            }
        });

        btnStock.setText("Stock");
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Id Categoria\n1 = ZAPATILLAS HOMBRE\n2 = ZAPATILLAS MUJER\n3 = ZAPATILLAS NIÑOS\n4 = ZAPATILLAS NIÑA");
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setText("ESTADISTICA");

        btnStock1.setText("Empleados");
        btnStock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStock1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStock)
                            .addComponent(txtboleta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStock1)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtboleta)
                        .addGap(18, 18, 18)
                        .addComponent(btnStock)
                        .addGap(18, 18, 18)
                        .addComponent(btnStock1)))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtboletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtboletaActionPerformed
        // TODO add your handling code here:
        EntProducto Ejug = new EntProducto(); 
        NegProducto Njug = new NegProducto();
        int cant = 0;
        ResultSet rs = Njug.MtdListarBoletas();

        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()){                    
                 dataset.setValue(rs.getInt("total"),rs.getString("fecha"), rs.getString("boleta"));                
            }          
                JPanel graficon = new JPanel();
                getContentPane().add(graficon);

                // Creando el Grafico
                JFreeChart chart = ChartFactory.createBarChart(
                        "Total de Boletas(Monto)", // El titulo de la gráfica
                        "Boleta", // Etiqueta de categoria
                        "Ganancias", // Etiqueta de valores
                        dataset, // Datos
                        PlotOrientation.VERTICAL, // orientacion
                        true, // Incluye Leyenda
                        true, // Incluye tooltips
                        false // URLs?
                );
                // Mostrar Grafico
                ChartFrame frame = new ChartFrame("Graficador", chart);
                frame.pack();
                frame.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtboletaActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        EntProducto Ejug = new EntProducto(); 
        NegProducto Njug = new NegProducto();
        int cant = 0;
        ResultSet rs = Njug.MtdListar();

        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()){                    
                 dataset.setValue(rs.getInt("cantidad"),rs.getString("id_categoria"), rs.getString("id"));                
            }          
                JPanel graficon = new JPanel();
                getContentPane().add(graficon);

                // Creando el Grafico
                JFreeChart chart = ChartFactory.createBarChart(
                        "Total de Productos", // El titulo de la gráfica
                        "Id_categoria", // Etiqueta de categoria
                        "Cantidad", // Etiqueta de valores
                        dataset, // Datos
                        PlotOrientation.VERTICAL, // orientacion
                        true, // Incluye Leyenda
                        true, // Incluye tooltips
                        false // URLs?
                );
                // Mostrar Grafico
                ChartFrame frame = new ChartFrame("Graficador", chart);
                frame.pack();
                frame.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStockActionPerformed
    DefaultCategoryDataset datos =new DefaultCategoryDataset();
    JFreeChart grafico;
    JPanel panel;
    private void btnStock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStock1ActionPerformed
        int c=0,a=0,v=0;
        NegProducto Njug = new NegProducto();
        
        ResultSet rs=Njug.MtdListarEmpleados();
        if (rs!=null){
            try{
                while(rs.next()){
                    //txtNombreE.setText(rs.getString(2)+" "+rs.getString(3));
                    System.out.println(rs.getString("nombre"));
                    String op = rs.getString("nombre");
                    switch(op){
                        case "CAJERO":{                           
                            c++;
                            break;
                        }
                        case "ADMINISTRADOR":{
                            a++;
                            break;
                        }
                        case "VENDEDOR":{
                            v++;
                            break;
                        }
                        
                    }
                }
                //GENERANDO GRAFICO
                // TODO add your handling code here:
                panel = new JPanel();
                getContentPane().add(panel);
                // Fuente de Datos
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.setValue(c, "CAJERO", "CAJERO");
                dataset.setValue(a, "ADMINISTRADOR", "ADMINISTRADOR");
                dataset.setValue(v, "VENDEDOR", "VENDEDOR");
                // Creando el Grafico
                JFreeChart chart = ChartFactory.createBarChart3D
                ("Administracion","Tipo", "creados", 
                dataset, PlotOrientation.VERTICAL, true,true, false);
                chart.setBackgroundPaint(Color.cyan);
                chart.getTitle().setPaint(Color.black); 
                CategoryPlot p = chart.getCategoryPlot(); 
                p.setRangeGridlinePaint(Color.red); 
                // Mostrar Grafico

                ChartFrame frame = new ChartFrame("JFreeChart", chart);
                frame.pack();
                frame.setVisible(true);
            }catch(Exception e){
               Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE,null,e);
            }
            
        }
    }//GEN-LAST:event_btnStock1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnStock1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton txtboleta;
    // End of variables declaration//GEN-END:variables
}
