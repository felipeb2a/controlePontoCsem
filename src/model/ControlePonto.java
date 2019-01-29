package model;

import dao.PontoDAO;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe.ferreira
 */
public class ControlePonto {

    //SOMA HORAS MINUTOS
    public static String somaHora(String horaInicial, String horaFinal) {
        double sumResult = getHours(horaInicial) + getHours(horaFinal);
        int hours = (int) sumResult;
        int minutes = (int) ((sumResult - hours) * 60);
        String hoursStr = String.valueOf(hours);
        String minutesStr = (minutes < 10 ? "0" : "")
                + String.valueOf(minutes);
        return hoursStr + ":" + minutesStr;
    }

    private static double getHours(String hourMinuteStr) {
        String[] parts = hourMinuteStr.split(":");
        return Integer.valueOf(parts[0]) + ((double) Integer.valueOf(parts[1]) / 60);
    }

    public static String somaHoraLong(long hora, long minute, String horaFinal) {
        String[] parts = horaFinal.split(":");
//        System.out.println(horaFinal);

        long horaFinalSplit = Integer.valueOf(parts[0]);
        long minuteFinalSplit = Integer.valueOf(parts[1]);

        double sumResult = getHoursLong(hora, minute) + getHoursLong(horaFinalSplit, minuteFinalSplit);

        int hours = (int) sumResult;
        double minutesDouble = ((sumResult - hours));

        minutesDouble = getMinute(minutesDouble);
        int minutes = (int) minutesDouble;

        String hoursStr = String.valueOf(hours);
        String minutesStr = "";

        //verificar minutos
        if (minutes > -10 && minutes < 0) {
            String m = String.valueOf(minutes);
            String[] part = m.split("-");
            minutesStr = "-0" + part[1];
        } else if (minutes > 0 && minutes < 10) {
            minutesStr = (minutes > 0 && minutes < 10 ? "0" : "") + String.valueOf(minutes);
        } else {
            minutesStr = String.valueOf(minutes);
        }

        //retorno
        return hoursStr + ":" + minutesStr;

    }

    private static double getMinute(double minutesDouble) {
        minutesDouble = Double.valueOf(String.format(Locale.US, "%.2f", minutesDouble));
        minutesDouble = minutesDouble * 60;
        minutesDouble = Double.valueOf(String.format(Locale.US, "%.1f", minutesDouble));
        String[] parts = String.valueOf(minutesDouble).split("\\.");
        double partsD1 = Double.valueOf(parts[0]);
        double partsD2 = Double.valueOf(parts[1]);
        if (partsD1 > 0 && partsD2 > 5) {
            minutesDouble = Math.ceil(minutesDouble);
        }
        if (partsD1 < 0 && partsD2 > 5) {
            minutesDouble = Math.floor(minutesDouble);
        }
        if (partsD1 > 0 && partsD2 < 5) {
            minutesDouble = Math.floor(minutesDouble);
        }
        if (partsD1 < 0 && partsD2 < 5) {
            minutesDouble = Math.ceil(minutesDouble);
        }

        return minutesDouble;
    }

    private static double getHoursLong(long hour, long minute) {
        return hour + ((double) minute / 60);
    }

    //DIA DA SEMANA
    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    //MES INT
    public String month(Calendar cal) {
        return new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
    }

    //FORMAT DATAS
    public Date formatDataString(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateFormat = sdf.parse(data);

        return dateFormat;
    }

    public String formatDataReturnString(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = sdf.format(data);

        return dateFormat;
    }

    public Ponto formatDataInt(int dia, int mes, int ano) throws ParseException {
        Ponto controlePonto = new Ponto();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar c = Calendar.getInstance();;
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date data;
        String a = (new SimpleDateFormat(" dd/MM/yyyy HH:mm").format(c.getTime()));
        data = sdf.parse(a);

        controlePonto.setDia(data);
//        System.out.println(c.getTime());
        //definir dia da semana
        controlePonto.setDiaSemana(weekDay(c));
        return controlePonto;
    }

    public void formatDataTimeInt(int dia, int mes, int ano) throws ParseException {
        mes = mes + 1;
        Ponto ponto = new Ponto();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.of(ano, mes, dia, 0, 0);

        String str = ldt.format(dtf);
    }

    public Date formatDataHora(Date data, Time time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar c = Calendar.getInstance();
        //pegar data
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        c.setTime(time);
        //definir data
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);

        String a = (new SimpleDateFormat(" dd/MM/yyyy HH:mm").format(c.getTime()));
        data = sdf.parse(a);

        return data;
    }

    public Time convertStringTime(String timeStr) {
        Time time = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date data = (Date) format.parse(timeStr);
            time = new Time(data.getTime());
        } catch (ParseException | NullPointerException ex) {
            System.out.println(getClass().getName() + "\n" + ex);
        }
        return time;
    }

    public String convertStringTime(Time timeStr) {
        String dateFormat = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            dateFormat = format.format(timeStr);
        } catch (NullPointerException ex) {
            System.out.println(getClass().getName() + "\n" + ex);
        }
        return dateFormat;
    }

    public Time convertDateTime(Date timeDate) {
        Time time = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String dateFormat = format.format(timeDate);
            Date data = format.parse(dateFormat);
            time = new Time(data.getTime());
        } catch (ParseException | NullPointerException ex) {
            System.out.println(getClass().getName() + "\n" + ex);
        }
        return time;
    }

    //CALCULAR HORA TRABALHADA
    public Ponto calculoHoraTrabalhada(Ponto ponto) {
        try {

            Date entrada = ponto.getEntrada();
            Date saidaIntervalo = ponto.getSaidaIntervalo();
            Date entradaIntervalo = ponto.getEntradaIntervalo();
            Date saida = ponto.getSaida();

            // horas trabalhadas
            long horas = (saida.getTime() - (entradaIntervalo.getTime() - saidaIntervalo.getTime()) - entrada.getTime()) / 3600000;

            // minutos trabalhados
            long minutos = (((saida.getTime() - (entradaIntervalo.getTime() - saidaIntervalo.getTime()) - entrada.getTime()) - horas * 3600000)
                    / 60000);

            // convert horas trabalhadas;
            ponto.setHorasTrabalhadas(convertStringTime(horas + ":" + minutos));

        } catch (NullPointerException ex) {
            System.out.println(getClass().getName() + "\n" + ex);
        }
        return ponto;
    }

    //CALCULAR HORA EXTRA
    public Ponto calculoHoraExtra(Ponto ponto) {
        try {
            //verificador de dias nao uteis
            String entrada = convertStringTime(ponto.getEntrada());
            String saidaIntervalo = convertStringTime(ponto.getSaidaIntervalo());
            String entradaIntervalo = convertStringTime(ponto.getEntradaIntervalo());
            String saida = convertStringTime(ponto.getSaida());

            long horaExtra = 0;
            long minutoExtra = 0;

            if (entrada.equals("00:00") && saidaIntervalo.equals("00:00") && entradaIntervalo.equals("00:00") && saida.equals("00:00")) {
                ponto.setHoraExtraFomatada("00:00");
            } else {
                // hora extra
                horaExtra = (ponto.getHorasTrabalhadas().getTime() - ponto.jornadaDeTrabalho().getTime()) / 3600000;
                // minutos
                minutoExtra = (((ponto.getHorasTrabalhadas().getTime() - ponto.jornadaDeTrabalho().getTime())
                        - horaExtra * 3600000)
                        / 60000);

                //salvar hora e minuto tipo long
                ponto.setHoraE(horaExtra);
                ponto.setMinutoE(minutoExtra);

                ponto.setHoraExtraFomatada(formatarHoraNegativa(horaExtra, minutoExtra));
            }
        } catch (NullPointerException ex) {
            System.out.println(getClass().getName() + "\n" + ex);
        } catch (ParseException ex) {
            Logger.getLogger(Ponto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ponto;
    }

    //HORA NEGATIVA
    public String formatarHoraNegativa(long hora, long minuto) {
        //String retorno
        String strRetorno = "";
        // convert string;
        String horaStr = Long.toString(hora);
        String minutoStr = Long.toString(minuto);
        // verificador negativo
        int verificadorHora = horaStr.indexOf("-", 0);
        int verificadorMinuto = minutoStr.indexOf("-", 0);

        if (verificadorHora == 0 && verificadorMinuto == 0) {
            String[] str = minutoStr.split("-");
            str[1] = verificaMinutoMenorDez(str[1]);
            strRetorno = horaStr + ":" + str[1];
        }
        if (verificadorHora == 0 && verificadorMinuto == -1) {
            minutoStr = verificaMinutoMenorDez(minutoStr);
            strRetorno = horaStr + ":" + minutoStr;
        }
        if (verificadorHora == -1 && verificadorMinuto == 0) {
            String[] str = minutoStr.split("-");
            str[1] = verificaMinutoMenorDez(str[1]);
            strRetorno = "-" + horaStr + ":" + str[1];
        }
        if (verificadorHora == -1 && verificadorMinuto == -1) {
            minutoStr = verificaMinutoMenorDez(minutoStr);
            strRetorno = horaStr + ":" + minutoStr;
        }
        return strRetorno;
    }

    //VERIFICA MINUTO MENOR Q 10
    public String verificaMinutoMenorDez(String minuto) {
//        String retorno; 
        //verifica minuto < 10
        int min = Integer.valueOf(minuto);
        if (min > 0 && min < 10) {
            minuto = "0" + minuto;
        }
        return minuto;
    }

    //VERIFICA ANO BISSEXTO
    public boolean verificaAnoBissexto(int ano) {
        boolean bissexto = false;
        if ((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0))) {
            bissexto = true;
        } else {
            bissexto = false;
        }
        return bissexto;
    }
    
    //COMPENSAR HORA MES ANTERIOR
    public Ponto compensarHoraExtraMesAnterior(Ponto ponto, Ponto pontoMesAnterior, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
        //retorno
        Ponto pontoRetorno = new Ponto();
        //buscar ponto mes anterior
        PontoDAO pontoDao = new PontoDAO();
        List<Ponto> pontoMesAnteriorList = pontoDao.ObterListPontoMes(pontoMesAnterior, nameDb);

        //percorrer lista mes anterior
        for (Iterator it = pontoMesAnteriorList.iterator(); it.hasNext();) {
            pontoMesAnterior = (Ponto) it.next();

            //converter data
            String d1 = formatDataReturnString(pontoMesAnterior.getDia());
            String d2 = formatDataReturnString(ponto.getDia());
            
            //converter para data devido erro ao converter para LocalDateTime
            Date diaPontoMesAnterior1  = formatDataString(d1);
            Date diaPonto1  = formatDataString(d2);
            
            //converter Date para  LocalDateTime
            LocalDateTime diaPonto = LocalDateTime.ofInstant(diaPonto1.toInstant(), ZoneId.systemDefault());
            LocalDateTime diaPontoMesAnterior = LocalDateTime.ofInstant(diaPontoMesAnterior1.toInstant(), ZoneId.systemDefault());
            
            //dias entre as datas do mes atual e mes anterior
            long dias = ChronoUnit.DAYS.between(diaPontoMesAnterior, diaPonto);
            
            //VERIFICA SE O PERIODO ESTA DENTRO DOS 30 DIAS
            if (dias > 0 && dias < 30) {

                //VERIFICA HORA POSITIVA MES ANTERIOR
                if (ponto.getHoraE() == 0 && ponto.getMinutoE() == 0) {
                    //para o loop
                    break;
                } else if ((pontoMesAnterior.getHoraE() > 0 && pontoMesAnterior.getMinutoE() > 0)
                        || (pontoMesAnterior.getHoraE() >= 0 && pontoMesAnterior.getMinutoE() > 0)) {
                    System.out.println("hora positiva:  " + pontoMesAnterior.getHoraE() + " | minuto positivo: " + pontoMesAnterior.getMinutoE());

                    //soma hora e minuto
                    String horaSoma = somaHoraLong(pontoMesAnterior.getHoraE(), pontoMesAnterior.getMinutoE(),
                            ponto.getHoraE() + ":" + ponto.getMinutoE());

                    //split hora somada
                    String[] parts = horaSoma.split(":");
                    long horaSplit = Integer.valueOf(parts[0]);
                    long minuteSplit = Integer.valueOf(parts[1]);

                    if (horaSplit < 0) {
                        ponto.setHoraE(horaSplit);
                        pontoMesAnterior.setHoraE(0);
                    }
                    if (minuteSplit < 0) {
                        ponto.setMinutoE(minuteSplit);
                        pontoMesAnterior.setMinutoE(0);
                    }
                    if (horaSplit > 0) {
                        ponto.setHoraE(0);
                        pontoMesAnterior.setHoraE(horaSplit);
                    }
                    if (minuteSplit > 0) {
                        ponto.setMinutoE(0);
                        pontoMesAnterior.setMinutoE(minuteSplit);
                    }
                    if (horaSplit == 0) {
                        ponto.setHoraE(0);
                    }
                    if (minuteSplit == 0) {
                        ponto.setMinutoE(0);
                    }

                    System.out.println("hora ponto:  " + ponto.getHoraE() + " | minuto ponto: " + ponto.getMinutoE());
                    System.out.println("hora ponto anterior:  " + pontoMesAnterior.getHoraE() + " | minuto ponto anterior: " + pontoMesAnterior.getMinutoE());

                    //alterar ponto mes anterior
//                pontoDao = new PontoDAO();
//                pontoDao.atualizarHoraMinutoExtraMesAnterior(pontoMesAnterior, nameDb);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        pontoRetorno = ponto;

        return pontoRetorno;
    }
    //COMPENSAR HORA MES ATUAL
    public List<Ponto> compensarHoraExtraMesAtual(Ponto ponto, List<Ponto> listaControlePonto) throws SQLException, ClassNotFoundException, ParseException {
        //retorno
        List<Ponto> listaControlePontoRetorno = null;
        
        //objeto ponto local
        Ponto pontoLocal = new Ponto();
        
        //percorrer lista mes anterior
        for (Iterator it = listaControlePonto.iterator(); it.hasNext();) {
            pontoLocal = (Ponto) it.next();

            //converter data
            String d1 = formatDataReturnString(pontoLocal.getDia());
            String d2 = formatDataReturnString(ponto.getDia());
            
            //converter para data devido erro ao converter para LocalDateTime
            Date diaPontoLocal1  = formatDataString(d1);
            Date diaPonto1  = formatDataString(d2);
            
            //converter Date para  LocalDateTime
            LocalDateTime diaPontoLocal = LocalDateTime.ofInstant(diaPontoLocal1.toInstant(), ZoneId.systemDefault());
            LocalDateTime diaPonto = LocalDateTime.ofInstant(diaPonto1.toInstant(), ZoneId.systemDefault());
            
            //dias entre as datas do mes atual e mes anterior
            long dias = ChronoUnit.DAYS.between(diaPontoLocal, diaPonto);
            
            //verificar se a diferença entre os dias é negativa
            if(dias < 0){
                dias = ChronoUnit.DAYS.between(diaPonto, diaPontoLocal);
            }
            
            //VERIFICA SE O PERIODO ESTA DENTRO DOS 30 DIAS
            if (dias > 0 && dias < 30) {

                //VERIFICA HORA POSITIVA MES ANTERIOR
                if (ponto.getHoraE() == 0 && ponto.getMinutoE() == 0) {
                    //apenas adiciona o valor ao objeto de retorno
                    pontoLocal = ponto;
                    
                } else if ((pontoLocal.getHoraE() > 0 && pontoLocal.getMinutoE() > 0)
                        || (pontoLocal.getHoraE() >= 0 && pontoLocal.getMinutoE() > 0)) {
                    System.out.println("hora positiva:  " + pontoLocal.getHoraE() + " | minuto positivo: " + pontoLocal.getMinutoE());

                    //soma hora e minuto
                    String horaSoma = somaHoraLong(pontoLocal.getHoraE(), pontoLocal.getMinutoE(),
                            ponto.getHoraE() + ":" + ponto.getMinutoE());

                    //split hora somada
                    String[] parts = horaSoma.split(":");
                    long horaSplit = Integer.valueOf(parts[0]);
                    long minuteSplit = Integer.valueOf(parts[1]);

                    if (horaSplit < 0) {
                        ponto.setHoraE(horaSplit);
                        pontoLocal.setHoraE(0);
                    }
                    if (minuteSplit < 0) {
                        ponto.setMinutoE(minuteSplit);
                        pontoLocal.setMinutoE(0);
                    }
                    if (horaSplit > 0) {
                        ponto.setHoraE(0);
                        pontoLocal.setHoraE(horaSplit);
                    }
                    if (minuteSplit > 0) {
                        ponto.setMinutoE(0);
                        pontoLocal.setMinutoE(minuteSplit);
                    }
                    if (horaSplit == 0) {
                        ponto.setHoraE(0);
                    }
                    if (minuteSplit == 0) {
                        ponto.setMinutoE(0);
                    }

                    System.out.println("hora ponto:  " + ponto.getHoraE() + " | minuto ponto: " + ponto.getMinutoE());
                    System.out.println("hora ponto anterior:  " + pontoLocal.getHoraE() + " | minuto ponto anterior: " + pontoLocal.getMinutoE());

                    //alterar ponto mes anterior
//                pontoDao = new PontoDAO();
//                pontoDao.atualizarHoraMinutoExtraMesAnterior(pontoMesAnterior, nameDb);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        listaControlePontoRetorno.add(pontoLocal);

        return listaControlePontoRetorno;
    }
}
