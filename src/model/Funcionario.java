package model;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class Funcionario {

    private int id;
    private String nome;
    private String cargo;
    private Date jornadaDeTrabalho;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getJornadaDeTrabalho() {
        return jornadaDeTrabalho;
    }
    
    public void setJornadaDeTrabalho(Date jornadaDeTrabalho) {
        this.jornadaDeTrabalho = jornadaDeTrabalho;
    }

//    public Date setJornadaDeTrabalho(Ponto ponto) throws ParseException {
//        ControlePonto controlePonto = new ControlePonto();
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//        ponto.getDia();
//        String hora = "08:48";
//        Date data = (Date) format.parse(hora);
//        Time time = new Time(data.getTime());
//        data = controlePonto.formatDataHora(ponto.getDia(), time);
//
//        return data;
//    }
}
