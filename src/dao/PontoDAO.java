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
import model.PontoMes;

/**
 *
 * @author felipe.ferreira
 */
public class PontoDAO extends AcessDB {

    public void salvaPonto(List<Ponto> pontoList, Ponto ponto, String nameDb) throws ClassNotFoundException, SQLException, ParseException {
        Connection conexao = conectar(nameDb);
        Format format = new Format();
        
        for (Iterator it = pontoList.iterator(); it.hasNext();) {

            Ponto pontoLista = (Ponto) it.next();

            String sql = "insert into ponto values(?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            //SEQUENCIA
            //<-----------------------------------
            int seq = obterProximoValorSequence(nameDb);

            //alterando objeto
            pontoLista.setId(seq);

            stmt.setInt(1, pontoLista.getId());
            //----------------------------------->

            stmt.setDate(2, format.convertDataSql(pontoLista.getDia()));
            stmt.setDate(3, format.convertDataSql(pontoLista.getEntrada()));
            stmt.setDate(4, format.convertDataSql(pontoLista.getSaidaIntervalo()));
            stmt.setDate(5, format.convertDataSql(pontoLista.getEntradaIntervalo()));
            stmt.setDate(6, format.convertDataSql(pontoLista.getSaida()));
            stmt.setDate(7, format.convertDataSql(pontoLista.getHorasTrabalhadas()));
            stmt.setString(8, pontoLista.getHoraExtraFomatada());
            stmt.setLong(9, pontoLista.getHoraE());
            stmt.setLong(10, pontoLista.getMinutoE());
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
