package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import model.Ponto;

/**
 *
 * @author felipe.ferreira
 */
public class PontoDAO extends AcessDB {

    public void salvaPonto(List<Ponto> pontoList, String nameDb) throws ClassNotFoundException, SQLException {
        Connection conexao = conectar(nameDb);

        for (Iterator it = pontoList.iterator(); it.hasNext();) {
            String sql = "insert into ponto values(?,?,?,?,?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            //SEQUENCIA
            //<-----------------------------------
            int seq = obterProximoValorSequence(nameDb);

            //alterando objeto
            usuario.setId(seq);

            stmt.setInt(1, usuario.getId());
            //----------------------------------->

            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.setInt(5, usuario.getNivel().getId());

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
