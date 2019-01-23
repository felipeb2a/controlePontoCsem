package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import model.Format;
import model.Ponto;

/**
 *
 * @author felipe.ferreira
 */
public class PontoDAO extends AcessDB {

    public void salvaPonto(List<Ponto> pontoList, String nameDb) throws ClassNotFoundException, SQLException, ParseException {
        Connection conexao = conectar(nameDb);
        Format format = new Format();
        for (Iterator it = pontoList.iterator(); it.hasNext();) {

            Ponto ponto = new Ponto();

            String sql = "insert into ponto values(?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            //SEQUENCIA
            //<-----------------------------------
            int seq = obterProximoValorSequence(nameDb);

            //alterando objeto
            ponto.setId(seq);

            stmt.setInt(1, ponto.getId());
            //----------------------------------->

            stmt.setDate(2, format.convertDataSql(ponto.getDia()));
            stmt.setDate(3, format.convertDataSql(ponto.getEntrada()));
            stmt.setDate(4, format.convertDataSql(ponto.getSaidaIntervalo()));
            stmt.setDate(5, format.convertDataSql(ponto.getEntradaIntervalo()));
            stmt.setDate(6, format.convertDataSql(ponto.getSaida()));
            stmt.setDate(7, format.convertDataSql(ponto.getHorasTrabalhadas()));
            stmt.setString(8, ponto.getHoraExtraFomatada());
            stmt.setLong(9, ponto.getHoraE());
            stmt.setLong(10, ponto.getMinutoE());
            stmt.setInt(11, ponto.getPontoMes().getId());
            stmt.setInt(12, ponto.getFuncionario().getId());

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
