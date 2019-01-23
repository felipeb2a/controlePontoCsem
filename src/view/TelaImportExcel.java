/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.FuncionarioDAO;
import model.ImportExcel;
import model.Ponto;
import model.ControlePonto;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import model.Icone;

/**
 *
 * @author felipe.ferreira
 */
public class TelaImportExcel extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private String nameDb;

    public TelaImportExcel() throws SQLException, ClassNotFoundException {
        initComponents();
        MaximizeTela();
        ListarUsuario();
    }

    //MOSTRAR TELA    
    public void mostrarTela(ArrayList Tela) {
        this.setVisible(true);
        this.mostrarTela = Tela;
    }

    //ALTERAR ICONE JAVA
    Icone icon = new Icone();

    public void icone() {
        URL url = icon.getIcone(nameDb);
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(iconeTitulo);
    }

    //NOME DB    
    public void nameDb(String nameDb) throws SQLException, ClassNotFoundException {
        this.nameDb = nameDb;
//        ListarUsuario(nameDb);
        icone();
    }

    public void MaximizeTela() {
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    //LISTAR USUARIO COMBOBOX
    public void ListarUsuario() throws SQLException, ClassNotFoundException {
        try {
            nameDb = "csem_controle_de_ponto";
            FuncionarioDAO dao = new FuncionarioDAO();
            Funcionario funcionario;
            List funcionarioList = dao.ObterNomeFuncionario(nameDb);
            Iterator it = funcionarioList.iterator();

            while (it.hasNext()) {
                funcionario = (Funcionario) it.next();
                String nome = funcionario.getNome();

                //INSERIR NOME
                cbFuncionario.addItem(nome);
            }
        } catch (SQLException ex) {

            if (ex.getMessage().contains(new String("The Network Adapter could not establish the connection"))) {

                JOptionPane.showMessageDialog(this, "Não foi possivel Conectar Com o Banco de Dados!");

            }

            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(this, "Erro de Desconhecido!");

            //ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btImport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPonto = new javax.swing.JTable();
        cbAno = new com.toedter.calendar.JYearChooser();
        cbMes = new com.toedter.calendar.JMonthChooser();
        jLabel1 = new javax.swing.JLabel();
        lbHorasTrabalhadas = new javax.swing.JLabel();
        txtHorasExtras = new javax.swing.JLabel();
        txtHoraTrabalhada = new javax.swing.JLabel();
        lbHorasExtras = new javax.swing.JLabel();
        cbFuncionario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btImport.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btImport.setText("IMPORTAR");
        btImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportActionPerformed(evt);
            }
        });

        tbPonto.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbPonto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia da Semana", "Data", "Entrada", "Saída Intervalo", "Entrada Intervalo", "Saída", "Horas Trabalhadas", "Hora Extra", "Compensação", "Motivo", "Título 11", "Título 12"
            }
        ));
        jScrollPane1.setViewportView(tbPonto);

        cbAno.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        cbMes.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SELECIONE O MÊS E O ANO");

        lbHorasTrabalhadas.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbHorasTrabalhadas.setText("Horas trabalhadas:");

        txtHorasExtras.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        txtHoraTrabalhada.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lbHorasExtras.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbHorasExtras.setText("Hora Extra:");

        cbFuncionario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cbFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("SELECIONE O FUNCIONARIO");

        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btSalvar.setText("SALVAR");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22)
                        .addComponent(lbHorasTrabalhadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoraTrabalhada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbHorasExtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHorasExtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btImport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbHorasTrabalhadas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtHorasExtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHorasExtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraTrabalhada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbAno, cbFuncionario, cbMes, jLabel1, jLabel2});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportActionPerformed
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (cbFuncionario.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar o funcionário\n";
            valida = false;
        }

        //CAMPOS VALIDADOS
        if (valida) {
            //PEGAR VALORES MES E ANO
            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.MONTH, cbMes.getMonth());
            ControlePonto controlePonto = new ControlePonto();
            String mes = controlePonto.month(c1);
            
            //CONFIRMAR DADOS
            Object[] options = {"Confirmar", "Cancelar"};
            int opcao = JOptionPane.showOptionDialog(null, "Clique Confirmar para continuar! \n" + "\n MÊS: " + mes
                    + "\n ANO: " + cbAno.getYear(), "CONFIRMAÇÃO OS DADOS SELECIONADOS", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (opcao == 0) {

                //DEFINIR TAMANHO DA ARRAY
                String[][] array = new String[33][5];
                
                //OBJECT
                ImportExcel excel = new ImportExcel();
                Ponto ponto = new Ponto();
                try {
                    List listaControlePonto = excel.carregarPontoExcel(cbMes.getMonth(), cbAno.getYear(), 
                            ((String) cbFuncionario.getSelectedItem()));
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
                    txtHoraTrabalhada.setText(ponto.getSomaHoraTrabalhada());

                } catch (IOException ex) {
                    Logger.getLogger(TelaImportExcel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaImportExcel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione os campos para continuar!");
            }
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btImportActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSalvarActionPerformed

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
                try {
                    new TelaImportExcel().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaImportExcel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaImportExcel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btImport;
    private javax.swing.JButton btSalvar;
    private com.toedter.calendar.JYearChooser cbAno;
    private javax.swing.JComboBox<String> cbFuncionario;
    private com.toedter.calendar.JMonthChooser cbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbHorasExtras;
    private javax.swing.JLabel lbHorasTrabalhadas;
    private javax.swing.JTable tbPonto;
    private javax.swing.JLabel txtHoraTrabalhada;
    private javax.swing.JLabel txtHorasExtras;
    // End of variables declaration//GEN-END:variables
}
