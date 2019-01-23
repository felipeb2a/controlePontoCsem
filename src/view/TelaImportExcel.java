/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.ImportExcel;
import model.Ponto;
import model.ControlePonto;
import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felipe.ferreira
 */
public class TelaImportExcel extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private int armazenaAno;
    
    public TelaImportExcel() {
        initComponents();
    }
    
    //MOSTRAR TELA    
    public void mostrarTela(ArrayList Tela) {
        this.setVisible(true);
        this.mostrarTela = Tela;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPonto = new javax.swing.JTable();
        cbAno = new com.toedter.calendar.JYearChooser();
        cbMes = new com.toedter.calendar.JMonthChooser();
        jLabel1 = new javax.swing.JLabel();
        lbHorasTrabalhadas = new javax.swing.JLabel();
        txtHorasExtras = new javax.swing.JLabel();
        txtHoraTrabalhada = new javax.swing.JLabel();
        lbHorasExtras = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Carregar Arquivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbPonto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbPonto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia da Semana", "Data", "Entrada", "Saída Intervalo", "Entrada Intervalo", "Saída", "Horas Trabalhadas", "Hora Extra", "Compensação", "Motivo", "Título 11", "Título 12"
            }
        ));
        jScrollPane1.setViewportView(tbPonto);

        jLabel1.setText("SELECIONE O MÊS E O ANO");

        lbHorasTrabalhadas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbHorasTrabalhadas.setText("Horas trabalhadas:");

        txtHorasExtras.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtHoraTrabalhada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lbHorasExtras.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbHorasExtras.setText("Hora Extra:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(22, 22, 22)
                                .addComponent(lbHorasTrabalhadas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoraTrabalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(lbHorasExtras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHorasExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbAno, cbMes, jLabel1});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, txtHoraTrabalhada, txtHorasExtras});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(lbHorasTrabalhadas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtHorasExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraTrabalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHorasExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbAno, cbMes, jLabel1});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, lbHorasExtras, lbHorasTrabalhadas, txtHoraTrabalhada, txtHorasExtras});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //string mes
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, cbMes.getMonth());
//        Ponto ponto = new Ponto();
        ControlePonto controlePonto = new ControlePonto();
        String mes = controlePonto.month(c1);

        //CONFIRMAR DADOS
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "Clique Confirmar para continuar! \n" + "\n MÊS: " + mes
                + "\n ANO: " + cbAno.getYear(), "CONFIRMAÇÃO OS DADOS SELECIONADOS", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {

            String[][] array = new String[33][5];
            ImportExcel excel = new ImportExcel();
            Ponto ponto = new Ponto();
            try {
                List listaControlePonto = excel.carregarPontoExcel(cbMes.getMonth(), cbAno.getYear());
                DefaultTableModel model = (DefaultTableModel) tbPonto.getModel();
                model.setNumRows(0);

                for (Iterator it = listaControlePonto.iterator(); it.hasNext();) {

                    ponto = (Ponto) it.next();
                    Object linha[]
                            = {ponto.getDiaSemana(),
                                controlePonto.formatDataReturnString(ponto.getDia()),
                                controlePonto.convertDateTime(ponto.getEntrada()),
                                controlePonto.convertDateTime(ponto.getSaidaIntervalo()),
                                controlePonto.convertDateTime(ponto.getEntradaIntervalo()),
                                controlePonto.convertDateTime(ponto.getSaida()),
                                controlePonto.convertDateTime(ponto.getHorasTrabalhadas()),
                                ponto.getHoraExtraFomatada()};
                    model.addRow(linha);
                }
                
                String[] str = ponto.getSomaHoraExtra().split(":");
                String horaFormatada = controlePonto.formatarHoraNegativa(Long.valueOf(str[0]), Long.valueOf(str[1]));
                int verificador = ponto.getSomaHoraExtra().indexOf("-", 0);
                if (verificador == 0) {
                    txtHorasExtras.setForeground(Color.red);
                }
                if (verificador == -1) {
                    txtHorasExtras.setForeground(Color.green);
                }
                txtHorasExtras.setText(horaFormatada);
                txtHoraTrabalhada.setText( ponto.getSomaHoraTrabalhada());
                
            } catch (IOException ex) {
                Logger.getLogger(TelaImportExcel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaImportExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione os campos para continuar!");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaImportExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImportExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImportExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImportExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaImportExcel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser cbAno;
    private com.toedter.calendar.JMonthChooser cbMes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbHorasExtras;
    private javax.swing.JLabel lbHorasTrabalhadas;
    private javax.swing.JTable tbPonto;
    private javax.swing.JLabel txtHoraTrabalhada;
    private javax.swing.JLabel txtHorasExtras;
    // End of variables declaration//GEN-END:variables
}
