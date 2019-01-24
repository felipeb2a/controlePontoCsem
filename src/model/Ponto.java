/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author felipe.ferreira
 */
public class Ponto {

    private int id;
    private Date dia;
    private String diaSemana;
    private Time entrada;
    private Time saidaIntervalo;
    private Time entradaIntervalo;
    private Time saida;
    private long horaE;
    private long minutoE;
    private Time horasTrabalhadas;
    private String somaHoraTrabalhada;
    private String horaExtraFomatada;
    private String somaHoraExtra;
    private String compensaHora;
    private String saldo;
    private String motivo;
    private Time jornadaDeTrabalho;
    private Funcionario funcionario;
    private PontoMes pontoMes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Time getEntrada() {
        return entrada;
    }

    public void setEntrada(Time entrada) {
        this.entrada = entrada;
    }

    public Time getSaidaIntervalo() {
        return saidaIntervalo;
    }

    public void setSaidaIntervalo(Time saidaIntervalo) {
        this.saidaIntervalo = saidaIntervalo;
    }

    public Time getEntradaIntervalo() {
        return entradaIntervalo;
    }

    public void setEntradaIntervalo(Time entradaIntervalo) {
        this.entradaIntervalo = entradaIntervalo;
    }

    public Time getSaida() {
        return saida;
    }

    public void setSaida(Time saida) {
        this.saida = saida;
    }

    public long getHoraE() {
        return horaE;
    }

    public void setHoraE(long horaE) {
        this.horaE = horaE;
    }

    public long getMinutoE() {
        return minutoE;
    }

    public void setMinutoE(long minutoE) {
        this.minutoE = minutoE;
    }

    public Time getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Time horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getSomaHoraTrabalhada() {
        return somaHoraTrabalhada;
    }

    public void setSomaHoraTrabalhada(String somaHoraTrabalhada) {
        this.somaHoraTrabalhada = somaHoraTrabalhada;
    }

    public String getHoraExtraFomatada() {
        return horaExtraFomatada;
    }

    public void setHoraExtraFomatada(String horaExtraFomatada) {
        this.horaExtraFomatada = horaExtraFomatada;
    }

    public String getSomaHoraExtra() {
        return somaHoraExtra;
    }

    public void setSomaHoraExtra(String somaHoraExtra) {
        this.somaHoraExtra = somaHoraExtra;
    }

    public String getCompensaHora() {
        return compensaHora;
    }

    public void setCompensaHora(String compensaHora) {
        this.compensaHora = compensaHora;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    

    public Time jornadaDeTrabalho() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String hora = "08:48";
        Date data = (Date) format.parse(hora);
        Time time = new Time(data.getTime());
        return time;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public PontoMes getPontoMes() {
        return pontoMes;
    }

    public void setPontoMes(PontoMes pontoMes) {
        this.pontoMes = pontoMes;
    }

}
