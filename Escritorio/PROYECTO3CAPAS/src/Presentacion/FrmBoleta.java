/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;



import Entidad.*;
import Negocios.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;


/**********GENERAR PDF**********/
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.File;
import java.io.IOException;

import java.util.Date;

/********************************/


import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;




import javax.mail.BodyPart;





import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sound.midi.ShortMessage;
import javax.swing.JOptionPane;


import javax.swing.table.DefaultTableModel;





/**
 *
 * @author ASUS
 */
public class FrmBoleta extends javax.swing.JFrame {  
    
    private static String emailFrom = "josuechambilla565@gmail.com";
    private static String passwordFrom = "xmqzugugjfoppife";
    private String emailTo;
    private String subject;
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    
    DefaultTableModel modelo=new DefaultTableModel();
    public FrmBoleta() {
        initComponents();
        mProperties = new Properties();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRE_UNITARIO");
        modelo.addColumn("SUBTOTAL");
        this.tblProductos.setModel(modelo);
        
         
    }
    
   private void createEmail() throws MessagingException {
        emailTo = txtcorreo.getText().trim();
        subject = "Boleta electronica";
        
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        
                BodyPart texto = new MimeBodyPart();    
                
                texto.setText("Gracias por su compra");     
                MimeMultipart partes = new MimeMultipart(); 
                partes.addBodyPart(texto);
   
                texto = new MimeBodyPart();      
                DataSource source = new FileDataSource("D:/"+txtnombrecliente.getText()+".pdf");
                texto.setDataHandler(new DataHandler(source));
                texto.setFileName("Boleta Electronica.pdf");
                partes.addBodyPart(texto);
                
                 
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(partes);
                     
            
        } catch (AddressException ex) {
            Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void pdf() {
        try{          
            FileOutputStream archivo;
            //File file = new File("src/pdf/"+txtnombrecliente.getText()+".pdf");
            File file = new File("D:/"+txtnombrecliente.getText()+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
                
            doc.open();
            
            Image img = Image.getInstance("src/Logo/Logo.png");
            
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            Date date= new Date();
            fecha.add("Factura: "+txtserie.getText()+" - "+txtnumero.getText()+"\n"+ "Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date)+"\n\n");
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f,40f,70f,60f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            Encabezado.addCell(img);
            
            String ruc = "222222";
            String nom = "Kalpent";
            String tel = "982531293";
            String dir = "Calle Agustin N. 329";
            String ubi = "Tacna-Peru";
            
            Encabezado.addCell("");
            Encabezado.addCell("RUC:" +ruc+ " \nNombre: "+nom+"\nTelefono: "+tel+ "\nDireccion: "+dir+"\nUbicacion: "+ubi);    
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
                                 
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos de   Cliente"+ "\n");
            doc.add(cli);
            
            PdfPTable tablacli = new PdfPTable(5);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{30f,70f,40f,90f,40f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);       
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni",negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre",negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Direccion",negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Email",negrita));
            PdfPCell cl5 = new PdfPCell(new Phrase("Movil",negrita));
            cl1.setBorder(2);
            cl2.setBorder(2);
            cl3.setBorder(2);
            cl4.setBorder(2);
            cl5.setBorder(2);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);
            tablacli.addCell(cl5);
            tablacli.addCell(txtdnicli.getText());
            tablacli.addCell(txtnombrecliente.getText());
            tablacli.addCell(txtdireccion.getText());
            tablacli.addCell(txtcorreo.getText());
            tablacli.addCell(txtmovil.getText());
            
            doc.add(tablacli);
            
            //separador
            Paragraph separador = new Paragraph();
            separador.add(Chunk.NEWLINE);
            separador.add("\n");
            doc.add(separador);
            
            //producto
            PdfPTable tablapro = new PdfPTable(5);
            tablapro.setWidthPercentage(100);
            tablapro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{20f,50f,50f,50f,40f};
            tablapro.setWidths(Columnapro);
            tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);       
            PdfPCell pro1 = new PdfPCell(new Phrase("Codigo",negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Nombre",negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Cantidad",negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio Unitario",negrita));
            PdfPCell pro5 = new PdfPCell(new Phrase("Subtotal",negrita));
            pro1.setBorder(2);
            pro2.setBorder(2);
            pro3.setBorder(2);
            pro4.setBorder(2);
            pro5.setBorder(2);
            pro1.setBackgroundColor(BaseColor.BLUE);
            pro2.setBackgroundColor(BaseColor.BLUE);
            pro3.setBackgroundColor(BaseColor.BLUE);
            pro4.setBackgroundColor(BaseColor.BLUE);   
            pro5.setBackgroundColor(BaseColor.BLUE); 
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            tablapro.addCell(pro4);
            tablapro.addCell(pro5);
            for(int i = 0; i<tblProductos.getRowCount();i++){
                String Codigo = tblProductos.getValueAt(i, 0).toString();
                String Nombre = tblProductos.getValueAt(i, 1).toString();
                String Cantidad = tblProductos.getValueAt(i, 2).toString();
                String Precio_Unitario = tblProductos.getValueAt(i, 3).toString();
                String Subtotal = tblProductos.getValueAt(i, 4).toString();
                tablapro.addCell(Codigo);
                tablapro.addCell(Nombre);
                tablapro.addCell(Cantidad);
                tablapro.addCell(Precio_Unitario);
                tablapro.addCell(Subtotal);
                
            }
                                
            doc.add(tablapro);
            
            //total
            Paragraph total = new Paragraph();
            total.add(Chunk.NEWLINE);
            total.add("\n Total a Pagar -> S/."+txttotal.getText());
            total.setAlignment(Element.ALIGN_RIGHT);
            doc.add(total);
            
            //Gracias
            Paragraph Gracias = new Paragraph();
            Gracias.add(Chunk.NEWLINE);
            Gracias.add("\n Gracias por su compra ");
            Gracias.setAlignment(Element.ALIGN_CENTER);
            doc.add(Gracias);
                      
            
            doc.close();
            archivo.close();
            
            System.out.println("Creado el pdf");            
                     
            }catch(Exception e){
                System.out.println("Erro al crear el pdf"+ e); 
            }
    }
    

    /**
     * Creates new form FrmBoleta
     */


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbltitulo = new javax.swing.JLabel();
        txtserie = new javax.swing.JTextField();
        txtnumero = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        txtenviar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdnicli = new javax.swing.JTextField();
        txtdniemp = new javax.swing.JTextField();
        btndni = new javax.swing.JButton();
        txtnombrecliente = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        txtbuscar = new javax.swing.JButton();
        txtnombreempleado = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtmovil = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtnombreproducto = new javax.swing.JTextField();
        txtcodproducto = new javax.swing.JTextField();
        txtpreciounitario = new javax.swing.JTextField();
        btnagregar = new javax.swing.JButton();
        btnquitar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtigv = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtcancompra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btndni1 = new javax.swing.JButton();
        txtenviar1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        lbltitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbltitulo.setText("BOLETA DE VENTA");
        lbltitulo.setToolTipText("");

        txtfecha.setBackground(new java.awt.Color(51, 255, 51));
        txtfecha.setEnabled(false);
        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        txtenviar.setText("ENVIAR");
        txtenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtenviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtenviar)
                .addGap(84, 84, 84))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(lbltitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtenviar))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setText("DNI CLIENTE:");

        jLabel3.setText("DNI EMPLEADO:");

        btndni.setText("Buscar DNI");
        btndni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndniActionPerformed(evt);
            }
        });

        txtnombrecliente.setBackground(new java.awt.Color(102, 255, 51));
        txtnombrecliente.setEnabled(false);

        jButton8.setText("Buscar DNI");

        txtbuscar.setText("Buscar DNI");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });

        txtnombreempleado.setBackground(new java.awt.Color(102, 255, 51));
        txtnombreempleado.setEnabled(false);

        txtcorreo.setBackground(new java.awt.Color(102, 255, 51));
        txtcorreo.setEnabled(false);

        txtdireccion.setBackground(new java.awt.Color(102, 255, 51));
        txtdireccion.setEnabled(false);

        txtmovil.setBackground(new java.awt.Color(102, 255, 51));
        txtmovil.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdniemp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnombreempleado))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdnicli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(21, 21, 21))
                            .addComponent(txtbuscar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmovil, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtdnicli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnombrecliente)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtcorreo)
                        .addComponent(txtdireccion)
                        .addComponent(txtmovil))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdniemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndni, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtnombreempleado))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel4.setText("COD PRODUCTO:");

        txtcantidad.setBackground(new java.awt.Color(102, 255, 102));
        txtcantidad.setEnabled(false);

        jButton3.setText("Buscar DNI");

        txtnombreproducto.setBackground(new java.awt.Color(102, 255, 102));
        txtnombreproducto.setEnabled(false);
        txtnombreproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreproductoActionPerformed(evt);
            }
        });

        txtpreciounitario.setBackground(new java.awt.Color(102, 255, 102));
        txtpreciounitario.setEnabled(false);

        btnagregar.setText("AGREGAR");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        btnquitar.setText("QUITAR");
        btnquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarActionPerformed(evt);
            }
        });

        jLabel5.setText("IGV:");

        txtigv.setBackground(new java.awt.Color(102, 255, 102));
        txtigv.setText("0");
        txtigv.setEnabled(false);

        txttotal.setBackground(new java.awt.Color(102, 255, 102));
        txttotal.setText("0");
        txttotal.setEnabled(false);

        jLabel6.setText("TOTAL:");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Precio Unitario", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(tblProductos);

        txtcancompra.setText("0");

        jLabel7.setText("Cantidad a comprar:");

        btndni1.setText("Buscar PRODUCTO");
        btndni1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndni1ActionPerformed(evt);
            }
        });

        txtenviar1.setText("IMPRIMIR");
        txtenviar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtenviar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcancompra, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnquitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcodproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btndni1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombreproducto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtenviar1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpreciounitario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtnombreproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcodproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btndni1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtpreciounitario)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtenviar1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcancompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(btnagregar)
                        .addComponent(btnquitar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaActionPerformed

    private void txtnombreproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreproductoActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        LocalDate local=LocalDate.now();
        txtfecha.setText(local+"");
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        EntCliente Ecli = new EntCliente();
        NegCliente Ncli = new NegCliente();
        Ecli.setDni(txtdnicli.getText());
        ResultSet rs =Ncli.MtdBuscarDni(Ecli);
        if(rs!=null)
        {         
            try {
                while(rs.next())
                {
                    txtnombrecliente.setText(rs.getString(2)+" "+rs.getString(3));
                    txtdireccion.setText(rs.getString(4));
                    txtcorreo.setText(rs.getString(5));
                    txtmovil.setText(rs.getString(7));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void btndniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndniActionPerformed
        // TODO add your handling code here:
        EntEmpleado Eemp = new EntEmpleado();
        NegEmpleado Nemp = new NegEmpleado();
        Eemp.setDni(txtdniemp.getText());
        ResultSet rs =Nemp.MtdBuscarDni(Eemp);
        if(rs!=null)
        {         
            try {
                while(rs.next())
                {
                    txtnombreempleado.setText(rs.getString(2)+" "+rs.getString(3));

                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btndniActionPerformed

    private void btndni1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndni1ActionPerformed
        // TODO add your handling code here:
        EntProducto Epro = new EntProducto();
        NegProducto Npro = new NegProducto();
        Epro.setId(Integer.parseInt(txtcodproducto.getText()));
        ResultSet rs =Npro.MtdBuscarProducto(Epro);
        if(rs!=null)
        {         
            try {
                while(rs.next())
                {
                    txtnombreproducto.setText(rs.getString(2));
                    txtcantidad.setText(rs.getString(6));
                    txtpreciounitario.setText(rs.getString(4));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btndni1ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        //GUARDAR DATOS BOLETA 
        EntBoleta Ebol = new EntBoleta();
        NegBoleta Nbol = new NegBoleta();
        Ebol.setSerie(txtserie.getText());
        Ebol.setNumero(txtnumero.getText());
        Ebol.setFecha(txtfecha.getText());
        Ebol.setDnicli(txtdnicli.getText());
        Ebol.setDniemp(txtdniemp.getText());
        Ebol.setEstado("ACTIVO");
        Ebol.setIgv(Double.parseDouble(txtigv.getText()));
        Ebol.setTOTAL(Double.parseDouble(txttotal.getText()));
        if(Nbol.MtdAgregarBoleta(Ebol)==true)
        {
            System.out.println("Datos Guardados Satisfactoriamente");
        }
        else
        {
            System.out.println("Error Guardar datos");
        }
        // GUARDAR EL DETALLE BOLETA
        int cantb=tblProductos.getRowCount();
        EntDetalle Edet = new EntDetalle();
        NegDetalle Ndet = new NegDetalle();
        for (int i = 0; i < cantb; i++) 
        {
            Edet.setSerie(txtserie.getText());
            Edet.setNumero(txtnumero.getText());
            String v1=(String) modelo.getValueAt(i, 0);
            int Vcod =Integer.valueOf(v1);
            Edet.setCodproducto(Vcod);
            String v2= (String) modelo.getValueAt(i, 1);
            int Vcan = Integer.parseInt(v2);
            Edet.setUniprocompra(Vcan);
            String v3= (String) modelo.getValueAt(i, 2);
            double Vpru = Double.parseDouble(v3);
            Edet.setPreunitario(Vpru);
            String v4= (String) modelo.getValueAt(i, 3);
            double Vst = Double.parseDouble(v4);
            Edet.setSubtotal(Vst);
            
            if(Ndet.MtdAgregarDetalle(Edet)==true)
            {
                JOptionPane.showMessageDialog(null,"Producto Guardado");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error de dato");
            }                       
        }       
        
        
        //ACTUALIZAR STOCK
        EntProducto Epro=new EntProducto();
        NegProducto Npro=new NegProducto();
        
        
    }//GEN-LAST:event_btnguardarActionPerformed

    
    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        int canactual=Integer.parseInt(txtcantidad.getText());
        int cancompra=Integer.parseInt(txtcancompra.getText());
      
        if(cancompra>canactual)
        {
            JOptionPane.showMessageDialog(null, "Error de ingresar la cantidad de compra");
            txtcancompra.setText("0");
        }
        else
        {
            String[] deta=new String[5];
            deta[0]=txtcodproducto.getText();
            deta[1]=txtnombreproducto.getText();
            deta[2]=txtcancompra.getText();
            deta[3]=txtpreciounitario.getText();
            int Vcan=Integer.parseInt(deta[2]);
            Double Vpru=Double.parseDouble(deta[3]);
            double Vst=Vcan*Vpru;         
            deta[4]=String.valueOf(Vst);
            modelo.addRow(deta);            
            
            //calculando total igv,total
            if("0".equals(txtigv.getText())){
            double aux,auxt;
            aux=Vst*0.18;
            auxt=Vst*1.18;
            txttotal.setText(String.valueOf(auxt));
            txtigv.setText(String.valueOf(aux));
            }else{
                double aux,sub;
                
                aux=Double.parseDouble(txtigv.getText());
                sub=Vst*0.18;
                aux=aux+sub;
                
                double auxt,subt;
                auxt=Double.parseDouble(txttotal.getText());
                subt=(Vst*0.18)+Vst;
                auxt=auxt+subt;
                
                txttotal.setText(String.valueOf(auxt));
                
                txtigv.setText(String.valueOf(aux));
            }
            
            //stock
            int stock = Vcan;
            int cant = Integer.parseInt(txtcantidad.getText());
            int cantTotal = cant-stock;
            txtcantidad.setText(String.valueOf(cantTotal)); 
                
            System.out.println(cant-stock);
        }
        
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarActionPerformed
        // TODO add your handling code here:
        int fila=tblProductos.getSelectedRow();
        if(fila>=0)
        {
            modelo.removeRow(fila);
            
            double aux=0;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccionar fila");
        }
    }//GEN-LAST:event_btnquitarActionPerformed

    private void txtenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtenviarActionPerformed
        try {
            createEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(FrmBoleta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
        sendEmail();
               
    }//GEN-LAST:event_txtenviarActionPerformed

    private void txtenviar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtenviar1ActionPerformed
        // TODO add your handling code here:
        pdf();
    }//GEN-LAST:event_txtenviar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBoleta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btndni;
    private javax.swing.JButton btndni1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnquitar;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTable tblProductos;
    private javax.swing.JButton txtbuscar;
    private javax.swing.JTextField txtcancompra;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcodproducto;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdnicli;
    private javax.swing.JTextField txtdniemp;
    private javax.swing.JButton txtenviar;
    private javax.swing.JButton txtenviar1;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtigv;
    private javax.swing.JTextField txtmovil;
    private javax.swing.JTextField txtnombrecliente;
    private javax.swing.JTextField txtnombreempleado;
    private javax.swing.JTextField txtnombreproducto;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtpreciounitario;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
