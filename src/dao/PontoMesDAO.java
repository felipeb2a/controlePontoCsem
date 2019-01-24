package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import model.PontoMes;

/**
 *
 * @author felipe.ferreira
 */
public class PontoMesDAO extends AcessDB{
    public void salvaPontoMes(List<PontoMes> pontoList, String nameDb) throws ClassNotFoundException, SQLException, ParseException {
        Connection conexao = conectar(nameDb);
        for (Iterator it = pontoList.iterator(); it.hasNext();) {

            PontoMes pontoMes = new PontoMes();

            String sql = "insert into pontoMes values(?,?,?,?,?,?)";

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
    }

    //OBTER SEQUENCIA MYSQL
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from pontoMes order by idPonto";

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
