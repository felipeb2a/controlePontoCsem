package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.PontoMes;

/**
 *
 * @author felipe.ferreira
 */
public class PontoMesDAO extends AcessDB {

    public List<PontoMes> ObterListPontoMes(String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List pontoMesList = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select * from pontomes pm order by pm.idPontoMes;";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        
        //chamar metodo lista funcionario
        pontoMesList = consultaListPontoMes(resultado);
        
        // Encerrando a conexão.
        conexao.close();
        return pontoMesList;
    }

    public PontoMes obterPontoMes(PontoMes pontoMes, String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        PontoMes pontoMesRetorno = new PontoMes();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select * from pontomes pm where pm.mes = ? and pm.ano = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //passando os parametros para a consulta
        stm.setInt(1, pontoMes.getMes());
        stm.setInt(2, pontoMes.getAno());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //chamar metodo lista funcionario
        pontoMesRetorno = consultaPontoMes(resultado);

        // Encerrando a conexão.
        conexao.close();
        return pontoMesRetorno;
    }

    public List<PontoMes> consultaListPontoMes(ResultSet resultado) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List pontoMesList = new ArrayList();

        // criando objeto de retorno
        while (resultado.next()) {
            PontoMes pontoMesRetorno = new PontoMes();
            pontoMesRetorno.setId(resultado.getInt("idPontoMes"));
            pontoMesRetorno.setSomaHoraTrabalhada(resultado.getString("somaHoraTrabalhada"));
            pontoMesRetorno.setSomaHoraExtra(resultado.getString("somaHoraExtra"));
            pontoMesRetorno.setSaldo(resultado.getString("saldo"));            
            pontoMesRetorno.setMes(resultado.getInt("mes"));
            pontoMesRetorno.setAno(resultado.getInt("ano"));

            pontoMesList.add(pontoMesRetorno);
        }

        return pontoMesList;
    }
    
    public PontoMes consultaPontoMes(ResultSet resultado) throws SQLException, ClassNotFoundException {

        PontoMes pontoMesRetorno = new PontoMes();
        // criando objeto de retorno
        while (resultado.next()) {
            pontoMesRetorno = new PontoMes();
            pontoMesRetorno.setId(resultado.getInt("idPontoMes"));
            pontoMesRetorno.setSomaHoraTrabalhada(resultado.getString("somaHoraTrabalhada"));
            pontoMesRetorno.setSomaHoraExtra(resultado.getString("somaHoraExtra"));
            pontoMesRetorno.setSaldo(resultado.getString("saldo"));            
            pontoMesRetorno.setMes(resultado.getInt("mes"));
            pontoMesRetorno.setAno(resultado.getInt("ano"));
        }

        return pontoMesRetorno;
    }
    
    public void salvaPontoMes(PontoMes pontoMes, String nameDb) throws ClassNotFoundException, SQLException, ParseException {
        Connection conexao = conectar(nameDb);

        String sql = "insert into pontomes values(?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        //SEQUENCIA
        //<-----------------------------------
        int seq = obterProximoValorSequence(nameDb);

        //alterando objeto
        pontoMes.setId(seq);

        stmt.setInt(1, pontoMes.getId());
        //----------------------------------->

        stmt.setString(2, pontoMes.getSomaHoraTrabalhada());
        stmt.setString(3, pontoMes.getSomaHoraExtra());
        stmt.setString(4, pontoMes.getSaldo());
        stmt.setInt(5, pontoMes.getMes());
        stmt.setInt(6, pontoMes.getAno());

        stmt.execute();
        stmt.close();

    }

    //OBTER SEQUENCIA MYSQL
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from pontomes order by idPontoMes";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("idPontoMes");
            retorno = id + 1;
        }
        // Encerrando a conexão.
        conexao.close();
        return retorno;
    }
}
