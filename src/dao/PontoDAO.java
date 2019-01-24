package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Format;
import model.Funcionario;
import model.Ponto;
import model.PontoMes;

/**
 *
 * @author felipe.ferreira
 */
public class PontoDAO extends AcessDB {

    public List<Ponto> ObterListPontoMes(Ponto ponto, String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List pontoList = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select * from ponto p inner join pontomes pm on pm.idPontoMes = p.fkPontoMes inner join funcionario f on f.idFuncionario = p.fkFuncionario  where pm.mes = ?  and f.idFuncionario = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //passando os parametros para a consulta
        stm.setInt(1,  ponto.getPontoMes().getId());
        stm.setInt(2,  ponto.getFuncionario().getId());
        
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        
        //chamar metodo lista funcionario
        pontoList = consultaListPonto(resultado);
        
        // Encerrando a conexão.
        conexao.close();
        return pontoList;
    }

    public Ponto obterPonto(Ponto ponto, String dbName) throws SQLException, ClassNotFoundException, ParseException {
        Format format = new Format();
        // lista de retorno
        Ponto pontoRetorno = new Ponto();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select * from funcionario f where f.nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //passando os parametros para a consulta
        stm.setTimestamp(1,  format.convertDataTimeSql(ponto.getDia()));

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //chamar metodo lista funcionario
        pontoRetorno = consultaPonto(resultado);

        // Encerrando a conexão.
        conexao.close();
        return pontoRetorno;
    }

    public List<Ponto> consultaListPonto(ResultSet resultado) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List pontoList = new ArrayList();

        // criando objeto de retorno
        while (resultado.next()) {
            Ponto pontoRetorno;
            pontoRetorno = new Ponto();
            pontoRetorno.setId(resultado.getInt("idPonto"));
            pontoRetorno.setDia(resultado.getDate("dia"));
            pontoRetorno.setEntrada(resultado.getTime("entrada"));
            pontoRetorno.setSaidaIntervalo(resultado.getTime("saidaIntervalo"));
            pontoRetorno.setEntradaIntervalo(resultado.getTime("entradaIntervalo"));
            pontoRetorno.setSaida(resultado.getTime("saida"));
            pontoRetorno.setHorasTrabalhadas(resultado.getTime("horasTrabalhadasDia"));
            pontoRetorno.setHoraExtraFomatada(resultado.getString("horasExtrasTotalDia"));
            pontoRetorno.setHoraE(resultado.getLong("horaExtraDia"));
            pontoRetorno.setMinutoE(resultado.getLong("minutoExtraDia"));
            pontoRetorno.setMotivo(resultado.getString("motivo"));
            
            //ponto mes
            PontoMes pontoMes = new PontoMes();
            pontoMes.setId(resultado.getInt("idPontoMes"));
            pontoMes.setSomaHoraTrabalhada(resultado.getString("somaHoraTrabalhada"));
            pontoMes.setSomaHoraExtra(resultado.getString("somaHoraExtra"));
            pontoMes.setSaldo(resultado.getString("saldo"));            
            pontoMes.setMes(resultado.getInt("mes"));
            pontoMes.setAno(resultado.getInt("ano"));
            
            //funcionario
            Funcionario funcionario = new Funcionario();
            funcionario.setId(resultado.getInt("idFuncionario"));
            funcionario.setNome(resultado.getString("nome"));
            funcionario.setCargo(resultado.getString("cargo"));
            funcionario.setJornadaDeTrabalho(resultado.getTime("jornadaDeTrabalho"));

            pontoList.add(pontoRetorno);
        }

        return pontoList;
    }
    
    public Ponto consultaPonto(ResultSet resultado) throws SQLException, ClassNotFoundException {

        Ponto pontoRetorno = new Ponto();
        // criando objeto de retorno
        while (resultado.next()) {
            pontoRetorno = new Ponto();
            pontoRetorno.setId(resultado.getInt("idPonto"));
            pontoRetorno.setDia(resultado.getDate("dia"));
            pontoRetorno.setEntrada(resultado.getTime("entrada"));
            pontoRetorno.setSaidaIntervalo(resultado.getTime("saidaIntervalo"));
            pontoRetorno.setEntradaIntervalo(resultado.getTime("entradaIntervalo"));
            pontoRetorno.setSaida(resultado.getTime("saida"));
            pontoRetorno.setHorasTrabalhadas(resultado.getTime("horasTrabalhadasDia"));
            pontoRetorno.setHoraExtraFomatada(resultado.getString("horasExtrasTotalDia"));
            pontoRetorno.setHoraE(resultado.getLong("horaExtraDia"));
            pontoRetorno.setMinutoE(resultado.getLong("minutoExtraDia"));
            pontoRetorno.setMotivo(resultado.getString("motivo"));
            
            //ponto mes
            PontoMes pontoMes = new PontoMes();
            pontoMes.setId(resultado.getInt("idPontoMes"));
            pontoMes.setSomaHoraTrabalhada(resultado.getString("somaHoraTrabalhada"));
            pontoMes.setSomaHoraExtra(resultado.getString("somaHoraExtra"));
            pontoMes.setSaldo(resultado.getString("saldo"));            
            pontoMes.setMes(resultado.getInt("mes"));
            pontoMes.setAno(resultado.getInt("ano"));
            
            //funcionario
            Funcionario funcionario = new Funcionario();
            funcionario.setId(resultado.getInt("idFuncionario"));
            funcionario.setNome(resultado.getString("nome"));
            funcionario.setCargo(resultado.getString("cargo"));
            funcionario.setJornadaDeTrabalho(resultado.getTime("jornadaDeTrabalho"));
            
        }

        return pontoRetorno;
    }
    
    public void salvaPonto(List<Ponto> pontoList, Ponto ponto, String nameDb) throws ClassNotFoundException, SQLException, ParseException {
        Connection conexao = conectar(nameDb);
        Format format = new Format();
        
        for (Iterator it = pontoList.iterator(); it.hasNext();) {

            Ponto pontoLista = (Ponto) it.next();

            String sql = "insert into ponto values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            //SEQUENCIA
            //<-----------------------------------
            int seq = obterProximoValorSequence(nameDb);

            //alterando objeto
            pontoLista.setId(seq);

            stmt.setInt(1, pontoLista.getId());
            //----------------------------------->

            stmt.setTimestamp(2, format.convertDataTimeSql(pontoLista.getDia()));
            stmt.setTimestamp(3, format.convertDataTimeSql(pontoLista.getEntrada()));
            stmt.setTimestamp(4, format.convertDataTimeSql(pontoLista.getSaidaIntervalo()));
            stmt.setTimestamp(5, format.convertDataTimeSql(pontoLista.getEntradaIntervalo()));
            stmt.setTimestamp(6, format.convertDataTimeSql(pontoLista.getSaida()));
            stmt.setTimestamp(7, format.convertDataTimeSql(pontoLista.getHorasTrabalhadas()));
            stmt.setString(8, pontoLista.getHoraExtraFomatada());
            stmt.setLong(9, pontoLista.getHoraE());
            stmt.setLong(10, pontoLista.getMinutoE());
            stmt.setString(11, ponto.getMotivo());
            stmt.setInt(12, ponto.getPontoMes().getId());
            stmt.setInt(13, ponto.getFuncionario().getId());

            stmt.execute();
            stmt.close();
        }
    }

    //OBTER SEQUENCIA MYSQL
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from ponto order by idPonto";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("idPonto");
            retorno = id + 1;
        }
        // Encerrando a conexão.
        conexao.close();
        return retorno;
    }
}
