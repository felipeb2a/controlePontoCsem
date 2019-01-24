/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 *
 * @author felipe.ferreira
 */
public class ImportExcel {

    public List<Ponto> carregarPontoExcel(int mes, int ano, String funcionario) throws FileNotFoundException, IOException, ParseException {
        //list
        List<Ponto> pontoList = new ArrayList();
        //object
        Ponto ponto;

        //hora format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        // local do arquivo
        int mesPadrao = mes + 1;
        String path = FilesPath.getPastaPontoCsem() + mesPadrao + ".xls";
        String filename = path;
        //carregar arquivo
        FileInputStream fileInputStream = new FileInputStream(filename);

        //ler workbook
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet worksheet = workbook.getSheet(funcionario);

        //linha 1
        HSSFRow row1 = worksheet.getRow(0);

        //percorrer linhas
        int linha = worksheet.getPhysicalNumberOfRows() - 1; //-1 ñ ler o total do mês
        //percorrer colunas
        int coluna = 5;
        //array multidimencional
        String[][] array = new String[linha][coluna];
        //linha
        HSSFRow row;
        //cell
        HSSFCell cell;

//      laço
        for (int i = 3; i < linha; i++) {
            for (int x = 0; x < coluna; x++) {
                try {
                    if (x == 0) {
                        row = worksheet.getRow(i);
                        //cell = row.getCell((short) x);                        
                        String dia = row.getCell(x).toString();
                        //remover ponto                        
                        String verificador = ".";
                        int result = dia.indexOf(verificador);
                        if (result == 1) {
                            String subStr = dia.substring(0, 1);
                            array[i][x] = subStr;
                        }
                        if (result == 2) {
                            String subStr = dia.substring(0, 2);
                            array[i][x] = subStr;
                        }
                    }else if (x > 0 && x < 7) {
                        row = worksheet.getRow(i);
                        Date hora = row.getCell(x).getDateCellValue();
                        String horaJ = sdf.format(hora);
                        array[i][x] = horaJ;

                    } else {
                        System.out.println(i);
                    }
                } catch (IllegalStateException ex) {
                    row = worksheet.getRow(i);
                    String str = row.getCell(x).toString();
                    array[i][x] = str;

                } catch (NullPointerException ex) {
                    String str = "00:00";
                    array[i][x] = str;
//                    System.out.println(i);
                }
            }
        }

        String somaHoraExtraFinal = "00:00";
        String somaHoraTrabalhada = "00:00";
        ControlePonto controlePonto = new ControlePonto();

        for (int i = 3; i < linha; i++) {
            ponto = new Ponto();
            for (int x = 0; x < coluna; x++) {
                if (x == 0) {
                    int dia = Integer.parseInt(array[i][x]);
                    //Date data = null;
                    try {
                        ponto = controlePonto.formatDataInt(dia, mes, ano);
                    } catch (ParseException ex) {
                        Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (x == 1) {
                    ponto.setEntrada(controlePonto.convertStringTime(array[i][x]));
                }
                if (x == 2) {
                    ponto.setSaidaIntervalo(controlePonto.convertStringTime(array[i][x]));
                }
                if (x == 3) {
                    ponto.setEntradaIntervalo(controlePonto.convertStringTime(array[i][x]));
                }
                if (x == 4) {
                    ponto.setSaida(controlePonto.convertStringTime(array[i][x]));
                }
            }

            ponto = controlePonto.calculoHoraTrabalhada(ponto);
            ponto = controlePonto.calculoHoraExtra(ponto);

            //soma hora trabalhada
            String str = controlePonto.convertStringTime(ponto.getHorasTrabalhadas());
            String[] parts = str.split(":");
            long horaTrabalhada = Long.valueOf(parts[0]);
            long minutoTrabalhado = Long.valueOf(parts[1]);
            somaHoraTrabalhada = controlePonto.somaHoraLong(horaTrabalhada, minutoTrabalhado, somaHoraTrabalhada);
            ponto.setSomaHoraTrabalhada(somaHoraTrabalhada);
//            System.out.println("Hora: " + horaTrabalhada + " | Minuto: " + minutoTrabalhado + " | HoraTrabalhada: " + somaHoraTrabalhada);

            //soma hora extra
            long horaExtra = ponto.getHoraE();
            long minutoExtra = ponto.getMinutoE();
            somaHoraExtraFinal = controlePonto.somaHoraLong(horaExtra, minutoExtra, somaHoraExtraFinal);
            ponto.setSomaHoraExtra(somaHoraExtraFinal);
//            System.out.println("Hora: " + horaExtra + " | Minuto: " + minutoExtra + " | HoraExtraFinal: " + somaHoraExtraFinal);

            pontoList.add(ponto);
        }
//        for (ControlePonto c : controleList) {
//            System.out.println(c.getDia());
//        }
        return pontoList;
    }
}
