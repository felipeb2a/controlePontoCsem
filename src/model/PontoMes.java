package model;

/**
 *
 * @author felipe.ferreira
 */
public class PontoMes {

    private int id;
    private String somaHoraTrabalhada;
    private String somaHoraExtra;
    private String saldo;
    private int mes;
    private int ano;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSomaHoraTrabalhada() {
        return somaHoraTrabalhada;
    }

    public void setSomaHoraTrabalhada(String somaHoraTrabalhada) {
        this.somaHoraTrabalhada = somaHoraTrabalhada;
    }

    public String getSomaHoraExtra() {
        return somaHoraExtra;
    }

    public void setSomaHoraExtra(String somaHoraExtra) {
        this.somaHoraExtra = somaHoraExtra;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
