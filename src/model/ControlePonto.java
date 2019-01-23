package model;

import java.sql.Time;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe.ferreira
 */
public class ControlePonto {

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

    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    public String month(Calendar cal) {
        return new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
    }

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();;
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);

        Date data;
        String a = (new SimpleDateFormat(" dd/MM/yyyy").format(c.getTime()));
        data = sdf.parse(a);

        controlePonto.setDia(data);

        //definir dia da semana
        controlePonto.setDiaSemana(weekDay(c));
        return controlePonto;
    }

    public Date formatDataHora(Date data, Time time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar c = Calendar.getInstance();
        //pegar data
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        //pegar hora
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

    public String convertStringTime(Date timeStr) {
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
            ponto.setHorasTrabalhadas(formatDataHora(ponto.getDia(),
                    convertStringTime(horas + ":" + minutos)));

            //somar horas trabalhadas
            String somaHorasTrabalhadas ="00:00";
            somaHorasTrabalhadas = somaHoraLong(horas, minutos, somaHorasTrabalhadas);
            
        } catch (ParseException | NullPointerException ex) {
            System.out.println(getClass().getName() + "\n" + ex);
        }
        return ponto;
    }

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
                horaExtra = (ponto.getHorasTrabalhadas().getTime() - ponto.jornadaDeTrabalho(ponto).getTime()) / 3600000;
                // minutos
                minutoExtra = (((ponto.getHorasTrabalhadas().getTime() - ponto.jornadaDeTrabalho(ponto).getTime())
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
           strRetorno = horaStr + ":" + str[1];
        }
        if (verificadorHora == 0 && verificadorMinuto == -1) {
            strRetorno = horaStr + ":" + minutoStr;
        }
        if (verificadorHora == -1 && verificadorMinuto == 0) {
            String[] str = minutoStr.split("-");
            strRetorno = "-" + horaStr + ":" + str[1];
        }
        if (verificadorHora == -1 && verificadorMinuto == -1) {
            strRetorno = horaStr + ":" + minutoStr;
        }
        return strRetorno;
    }

}
